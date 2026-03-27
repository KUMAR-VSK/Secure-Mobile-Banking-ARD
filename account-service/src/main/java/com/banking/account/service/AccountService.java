package com.banking.account.service;

import com.banking.account.entity.Account;
import com.banking.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Long userId) {
        String accountNumber = generateAccountNumber();
        Account account = new Account(userId, accountNumber);
        return accountRepository.save(account);
    }

    public Account getBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

    public Account getAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId).orElse(null);
    }

    public void updateBalance(String accountNumber, BigDecimal newBalance) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElse(null);
        if (account != null) {
            account.setBalance(newBalance);
            accountRepository.save(account);
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder("ACC");
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
