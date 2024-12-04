package com.app.service;

import java.util.List;

import com.app.dto.AccountDto;


public interface AccountService {
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccountBYId(Long id);
	AccountDto AccountDeposit(Long id, double amount);
	AccountDto AccountWithDraw(Long id, double amount);
	List <AccountDto> getAllAccounts();
	AccountDto deleteAccountBYId(Long id);
}
