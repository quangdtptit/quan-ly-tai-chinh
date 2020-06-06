package com.example.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.config.security.CustomUserDetails;

public class SecurityUtil {

	public static CustomUserDetails getCurrentUser() {
		return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
