package com.example.controller.stocker;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.BillWareHouseEntity;
import com.example.model.SupplierEntity;
import com.example.model.WareHouseEntity;
import com.example.model.dto.ItemDTO;
import com.example.model.dto.UploadExcelItemDTO;
import com.example.repository.BillWareHouseRepository;
import com.example.repository.SupplierRepository;
import com.example.repository.WareHouseRepository;
import com.example.service.ItemService;
import com.example.utils.CommonUtil;
import com.example.utils.ExcelUtil;
import com.example.utils.FileUtil;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private BillWareHouseRepository billWareHouseRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@RequestMapping(value = "/stocker/items", method = RequestMethod.GET)
	public ModelAndView enterInventoryPage() {
		ModelAndView mav = new ModelAndView("items/list-item");
		List<ItemDTO> itemDTOs = itemService.findAll();
		mav.addObject("items", itemDTOs);
		return mav;
	}

	@RequestMapping(value = { "/stocker/items/", "/stocker/items/{id}" }, method = RequestMethod.GET)
	public ModelAndView editsPage(@PathVariable(name = "id", required = false) Integer id) {
		ItemDTO item = new ItemDTO();
		if (id != null) {
			item = itemService.findById(id);
		}
		ModelAndView mav = new ModelAndView("items/edits-item");
		mav.addObject("item", item);
		return mav;
	}

	@RequestMapping(value = { "/stocker/items/" }, method = RequestMethod.POST)
	public String editsPage(@ModelAttribute ItemDTO itemDTO) {
		itemDTO = itemService.save(itemDTO);
		System.out.println("SAVE BY ID :" + itemDTO.getId());
		return "redirect:/stocker/items";
	}

	// DANG BO QUA BUOC CHECK VALIDATE UploadExcelItemDTO
	@RequestMapping(value = "/stocker/items/upload", method = RequestMethod.POST)
	public String uploadExcel(@ModelAttribute UploadExcelItemDTO upItemDTO) {

		System.out.println(upItemDTO);
		List<ItemDTO> itemDTOs = new ExcelUtil().importFileExcel(FileUtil.toFile(upItemDTO.getFile()), ItemDTO.class);

		SupplierEntity supplierEntity = null;
		Optional<SupplierEntity> optSupplier = supplierRepository.findByName(upItemDTO.getNameSupplier());
		if (!optSupplier.isPresent()) {
			supplierEntity = new SupplierEntity();
			supplierEntity.setName(upItemDTO.getNameSupplier());
			supplierEntity = supplierRepository.save(supplierEntity);
		} else {
			supplierEntity = optSupplier.get();
		}
		// ADD HOA DON NHAP KHO
		BillWareHouseEntity billWareHouseEntity = CommonUtil.caculation(itemDTOs);
		billWareHouseEntity.setSupplierEntity(supplierEntity);
		billWareHouseEntity = billWareHouseRepository.save(billWareHouseEntity);

		Optional<WareHouseEntity> optWareHouse = wareHouseRepository.findById(upItemDTO.getIdWareHouse());
		WareHouseEntity wareHouseEntity = optWareHouse.get();

		long record = itemService.saveList(itemDTOs, billWareHouseEntity, wareHouseEntity);

		System.out.println("RECORD SAVE :" + record);

		return "redirect:/stocker/items";
	}

	@RequestMapping(value = "/stocker/items/download", method = RequestMethod.GET)
	public void downloadExcel(HttpServletResponse response) {
		List<ItemDTO> list = itemService.findAll();
		boolean checked = new ExcelUtil().exportExcel(list, response, "item");
		System.out.println("DOWNLOAD CHECKED :" + checked);
	}

}