package userTransactionRestAPI.userTransaction;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import userTransactionRestAPI.user.*;
import userTransactionRestAPI.transaction.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserTransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

    public User addNewUser(User user) {
        if(user.getFirstName() == null|| user.getLastName() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Name incomplete.");
        }
        return userRepository.save(user);
    }

    public String removeUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user does not exist.");
        }
        userRepository.deleteById(userId);
        return "User removed.";
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user does not exist.");
        }
        return userRepository.findById(userId).get();
    }

    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public User updateUserById(User user, Long userId) {
        if(user.getFirstName() == null|| user.getLastName() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Name incomplete.");
        }
        if (!userRepository.existsById(userId)) {
            return userRepository.save(user);
        }
        userRepository.setUserInfoById(user.getFirstName(),
                user.getLastName(), userId);
        return getUserById(userId);
    }

    public Transaction addNewTransaction(Transaction transaction) {
        if(!userRepository.existsById(transaction.getUserId())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user does not exist");
        }
        if(transaction.getAmount() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Amount is missing.");
        }
        transaction.setTimeCreated();
        transaction.setTimeUpdated();
        UserTransaction userTransaction = new UserTransaction(transaction.getUserId());
        userTransactionRepository.save(userTransaction);
        return transactionRepository.save(transaction);

    }

    public List<Transaction> addNewTransactions(List<Transaction> transactions) {
        if(transactions.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No transactions in input.");
        }
        List<Transaction> added = new ArrayList<Transaction>();
        for(Transaction t : transactions){
            added.add(addNewTransaction(t));
        }
        return added;
    }
    public String removeTransaction(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction does not exist.");
        }
        userTransactionRepository.deleteById(transactionId);
        transactionRepository.deleteById(transactionId);
        return "Transaction removed.";
    }

    public List<Transaction> getTransactions() {
        if(transactionRepository.count() == 0){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No transactions exist.");
        }
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public Transaction getTransactionById(Long transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Transaction does not exist.");
        }
        return transactionRepository.findById(transactionId).get();
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User does not exist");
        }
        List<UserTransaction> userTransactions = userTransactionRepository.findByUserId(userId);
        List<Transaction> transactions = new ArrayList<Transaction>();

        while(!userTransactions.isEmpty()){
            transactions.add(transactionRepository.findById(userTransactions.get(0).getTransactionId()).get());
            userTransactions.remove(0);
        }
        return transactions;
    }
    public Long getTransactionSumByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User does not exist");
        }
        List<UserTransaction> userTransactions = userTransactionRepository.findByUserId(userId);
        Long sum = 0L;
        while(!userTransactions.isEmpty()){
            sum += transactionRepository.findById(userTransactions.get(0).getTransactionId()).get().getAmount();
            userTransactions.remove(0);
        }
        return sum;
    }

    public Transaction updateTransactionById(Transaction transaction, Long transactionId) {
        if(!userRepository.existsById(transaction.getUserId())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user does not exist");
        }
        if(transaction.getAmount() == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Amount is missing.");
        }
        if (transactionRepository.existsById(transactionId)) {
            userTransactionRepository.setUserTransactionInfoById(transaction.getUserId(), transactionId);
            transactionRepository.setTransactionInfoById(transaction.getUserId(), transaction.getAmount(),
                    new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()), transactionId);

            return getTransactionById(transactionId);
        }
        else {
            transaction.setTimeCreated();
            transaction.setTimeUpdated();
            UserTransaction userTransaction = new UserTransaction(transaction.getUserId());
            userTransactionRepository.save(userTransaction);
            return transactionRepository.save(transaction);
        }
    }
}
