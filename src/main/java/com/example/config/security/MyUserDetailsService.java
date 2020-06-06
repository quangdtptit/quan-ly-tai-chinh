package com.example.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.StaffEntity;
import com.example.repository.StaffRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<StaffEntity> optStaff = staffRepository.findByUsername(username);
		if (!optStaff.isPresent()) {
			throw new UsernameNotFoundException(username + " NOT FOUND !");
		}
		StaffEntity staffEntity = optStaff.get();
		CustomUserDetails customUserDetails = new CustomUserDetails(staffEntity.getUsername(),
				staffEntity.getPassword(), true, true, true, true, this.getAuthorities(staffEntity));
		BeanUtils.copyProperties(staffEntity, customUserDetails);
		return customUserDetails;
	}

	private List<GrantedAuthority> getAuthorities(StaffEntity staffEntity) {
		String role = staffEntity.getRole();
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}

}
