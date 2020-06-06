package com.example.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.example.utils.SecurityUtil;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		try {
			return Optional.of(SecurityUtil.getCurrentUser().getUsername());
		} catch (NullPointerException ex) {
			return null;
		}
	}

}
