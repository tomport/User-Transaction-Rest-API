package userTransactionRestAPI.transaction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userTransactionRestAPI.userTransaction.UserTransactionService;

import java.util.List;


@RestController
public class TransactionController {
    @Autowired
    UserTransactionService userTransactionService;

    @PostMapping("/transactions")
    private Transaction addTransaction(@RequestBody Transaction transaction) {
        return userTransactionService.addNewTransaction(transaction);
    }
    @PostMapping("/transactions/multiple")
    private List<Transaction> addTransactions(@RequestBody List<Transaction> transactions) {
        return userTransactionService.addNewTransactions(transactions);
    }
    @DeleteMapping("/transactions/{transactionId}")
    private String deleteTransaction(@PathVariable("transactionId") Long transactionId) {
        return userTransactionService.removeTransaction(transactionId);
    }
    @GetMapping("/transactions")
    private List<Transaction> getAllTransactions(){
        return userTransactionService.getTransactions();
    }
    @GetMapping("/transactions/{transactionId}")
    private Transaction getById(@PathVariable("transactionId") Long transactionId) {
        return userTransactionService.getTransactionById(transactionId);
    }
    @PutMapping("/transactions/{transactionId}")
    private Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable Long transactionId) {
        return userTransactionService.updateTransactionById(transaction, transactionId);
    }
}
