package com.revature.controller.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import com.revature.model.Message;

@FeignClient("expense")
public interface ExpenseClient {

	@GetMapping("/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Message getAllExpenses();
}
