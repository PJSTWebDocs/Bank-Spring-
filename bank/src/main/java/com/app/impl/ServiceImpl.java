package com.app.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.dto.AccountDto;
import com.app.entity.Account;
import com.app.mapper.AccountMapper;
import com.app.repository.AccountRepository;
import com.app.service.AccountService;

@Service
public class ServiceImpl implements AccountService {

	public ServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	private AccountRepository accountRepository;
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return  AccountMapper.mapToAccountDto(savedAccount);
		
	}
	@Override
	public AccountDto getAccountBYId(Long id) {
		// TODO Auto-generated method stub
		
		Account a = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(a);
	}
	@Override
	public AccountDto AccountDeposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account a = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
		double l =a.getBalance()+ amount;
		a.setBalance(l);
		Account savedAccount =accountRepository.save(a);
		return AccountMapper.mapToAccountDto(savedAccount) ;
	}
	@Override
	public AccountDto AccountWithDraw(Long id, double amount) {
		Account a = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
		if(a.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		double l =a.getBalance()- amount;
		a.setBalance(l);
		Account savedAccount =accountRepository.save(a);
		return AccountMapper.mapToAccountDto(savedAccount) ;
	}
	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		
		List<Account> accounts= accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}
	@Override
	public AccountDto deleteAccountBYId(Long id) {
		// TODO Auto-generated method stub
		
		Account a = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
		accountRepository.deleteById(id);
		
		return AccountMapper.mapToAccountDto(a);
	}

}
