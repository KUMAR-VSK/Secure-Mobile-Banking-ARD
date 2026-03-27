package com.banking.transaction.service;

import com.banking.transaction.entity.Transaction;
import com.banking.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction transfer(String fromAccount, String toAccount, BigDecimal amount) {
        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
    }

    public List<Transaction> getSuspiciousTransactions() {
        return transactionRepository.findBySuspiciousTrue();
    }
}
