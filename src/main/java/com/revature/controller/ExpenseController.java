package com.revature.controller;

import org.springframework.web.bind.annotation.RestController;

import com.revature.controller.client.ExpenseClient;
import com.revature.model.Message;

@RestController
public class ExpenseController implements ExpenseClient {

	@Override
	public Message getAllExpenses() {
		return new Message("All the expenses for an admin!");
	}
}
