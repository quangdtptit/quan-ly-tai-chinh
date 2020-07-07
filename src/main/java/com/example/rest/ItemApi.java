package com.example.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.converter.ItemConverter;
import com.example.model.BillItemEntity;
import com.example.model.CustomerEntity;
import com.example.model.ItemBoughtEntity;
import com.example.model.ItemOutputEntity;
import com.example.model.dto.ItemDTO;
import com.example.repository.BillItemRepository;
import com.example.repository.CustomerRepository;
import com.example.repository.ItemBoughtRepository;
import com.example.repository.ItemOuputRepository;
import com.example.service.ItemService;
import com.example.utils.SecurityUtil;

@RestController
@RequestMapping("/api/v1/item")
public class ItemApi {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemConverter itemConverter;

	@Autowired
	private ItemOuputRepository itemOutputRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BillItemRepository billItemRepository;

	@Autowired
	private ItemBoughtRepository itemBoughtRepository;

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Integer id) {
		return itemService.delete(id);
	}

	@PostMapping("/order")
	public List<ItemDTO> getOrder(@RequestBody Integer[] ids) {
		List<ItemDTO> result = new ArrayList<>();
		for (Integer id : ids) {
			ItemOutputEntity itemOutputEntity = itemOutputRepository.findById(id).get();
			result.add(itemConverter.toDTO(itemOutputEntity));
		}
		return result;
	}

	@Transactional
	@PostMapping("/payment")
	public Integer payment(@RequestBody List<ItemDTO> list, @RequestParam String name) {
		try {
			List<ItemBoughtEntity> listBought = new ArrayList<>();
			List<ItemOutputEntity> listOut = new ArrayList<>();
			int total = 0;
			long amount = 0;
			for (ItemDTO item : list) {
				ItemBoughtEntity itemBoughtEntity = new ItemBoughtEntity();
				ItemOutputEntity itemOutputEntity = itemOutputRepository.findById(item.getId()).get();
				if (item.getTotal() > itemOutputEntity.getTotal())
					return HttpStatus.INTERNAL_SERVER_ERROR.value();

				itemOutputEntity.setTotal(itemOutputEntity.getTotal() - item.getTotal());
				listOut.add(itemOutputEntity);
				total += item.getTotal();
				amount += item.getTotal() * itemOutputEntity.getPrice();

				itemBoughtEntity.setItemEntity(itemOutputEntity);
				itemBoughtEntity.setName(itemOutputEntity.getName());
				itemBoughtEntity.setPrice(itemOutputEntity.getPrice());
				itemBoughtEntity.setNumber(item.getTotal());
				listBought.add(itemBoughtEntity);
			}
			
			//update item
			itemOutputRepository.saveAll(listOut);
			
			// save KH
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setName(name);
			customerEntity = customerRepository.save(customerEntity);

			// save HD
			BillItemEntity billItemEntity = new BillItemEntity();
			billItemEntity.setTotal(total);
			billItemEntity.setAmount(amount);
			billItemEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			billItemEntity.setCreateBy(SecurityUtil.getCurrentUser().getUsername());
			billItemEntity.setCustomerEntity(customerEntity);
			billItemEntity = billItemRepository.save(billItemEntity);

			// save SP DA BAN
			for (ItemBoughtEntity e : listBought) {
				e.setBillItemEntity(billItemEntity);
			}
			itemBoughtRepository.saveAll(listBought);
			return HttpStatus.OK.value();
		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR.value();
		}
	}
}
