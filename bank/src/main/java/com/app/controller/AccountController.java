package com.app.controller;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AccountDto;
import com.app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount( @RequestBody AccountDto accountDto ) {
		AccountDto a= accountService.createAccount(accountDto);
		System.out.println(a.getId());
		System.out.println(a.getAccountHolderName());
		System.out.println(a.getBalance());
		return new ResponseEntity<>( a, HttpStatus.CREATED);
	}
	
	
	//GetAccount
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById( @PathVariable("id") Long id ) {
		AccountDto accountDto = accountService.getAccountBYId(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id, @RequestBody Map<String,Double> request ) {
		
		Double amount= request.get("amount");
		AccountDto accountDto = accountService.AccountDeposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id, @RequestBody Map<String,Double> request ) {
		
		Double amount= request.get("amount");
		AccountDto accountDto = accountService.AccountWithDraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accountDto = accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<AccountDto> deleteAccount(@PathVariable("id") Long id ) {
		AccountDto a=accountService.deleteAccountBYId(id);
		
		
		return ResponseEntity.ok(a);
	}
	
}
