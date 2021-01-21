package sample.MySQL.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/transactions")
    private Transaction addNewTransaction(@RequestBody Transaction transaction) {
        transaction.setTimeCreated();
        transaction.setTimeUpdated();
        return transactionRepository.save(transaction);
    }
    @DeleteMapping("/transactions/{transactionId}")
    private String deleteTransaction(@PathVariable("transactionId") Long transactionId)
    {
        transactionRepository.deleteById(transactionId);
        return "Deleted";
    }
    @GetMapping("/transactions")
    private List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }
    @GetMapping("/transactions/{transactionId}")
    private Transaction getTransactions(@PathVariable("transactionId") Long transactionId)
    {
        return transactionRepository.findById(transactionId).get();
    }
    @PutMapping("/transactions/{transactionId}")
    private Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable Long transactionId)
    {
    return transactionRepository.findById(transactionId)
                .map(transaction1 -> {
                    transaction1.setUserId(transaction.getUserId());
                    transaction1.setTimeUpdated();
                    transaction1.setAmount(transaction.getAmount());
                    return transactionRepository.save(transaction1);
                })
                .orElseGet(() -> {
                    transaction.setTimeCreated();
                    transaction.setTimeUpdated();
                    transaction.setTransactionId(transactionId);
                    return transactionRepository.save(transaction);
                });
    }

}
