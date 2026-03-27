package com.banking.account.controller;

import com.banking.account.entity.Account;
import com.banking.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        Account account = accountService.createAccount(userId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable String accountNumber) {
        Account account = accountService.getBalance(accountNumber);
        if (account == null) {
            return ResponseEntity.status(404).body("Account not found");
        }
        return ResponseEntity.ok(Map.of(
                "accountNumber", account.getAccountNumber(),
                "balance", account.getBalance()
        ));
    }

    @PutMapping("/update-balance")
    public ResponseEntity<String> updateBalance(@RequestBody Map<String, Object> request) {
        String accountNumber = (String) request.get("accountNumber");
        BigDecimal newBalance = new BigDecimal(request.get("balance").toString());
        accountService.updateBalance(accountNumber, newBalance);
        return ResponseEntity.ok("Balance updated");
    }
}
