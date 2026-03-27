package com.banking.transaction.controller;

import com.banking.transaction.entity.Transaction;
import com.banking.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody Map<String, Object> request) {
        String fromAccount = (String) request.get("fromAccount");
        String toAccount = (String) request.get("toAccount");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        Transaction transaction = transactionService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionHistory(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/suspicious")
    public ResponseEntity<List<Transaction>> getSuspicious() {
        List<Transaction> transactions = transactionService.getSuspiciousTransactions();
        return ResponseEntity.ok(transactions);
    }
}
