package userTransactionRestAPI.userTransaction;

import userTransactionRestAPI.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTransactionController {
    @Autowired
    UserTransactionService userTransactionService;

    @GetMapping("/users/{userId}/transactions")
    private List<Transaction> getTransactionsByUserId(@PathVariable("userId") Long userId) {
        return userTransactionService.getTransactionsByUser(userId);
    }
    @GetMapping("/users/{userId}/transactionSum")
    private Long getTransactionSumByUserId(@PathVariable("userId") Long userId) {
        return userTransactionService.getTransactionSumByUser(userId);
    }

}
