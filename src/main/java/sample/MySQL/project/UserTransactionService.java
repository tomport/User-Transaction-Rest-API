package sample.MySQL.project;

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
    private UserRepository userRepository;

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public String removeUser(Long userId) {
        userRepository.deleteById(userId);
        return "Deleted";
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public User updateUserById(User user, Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.setUserInfoById(user.getFirstName(),
                    user.getLastName(), userId);
            return getUserById(userId);
        } else {
            return userRepository.save(user);
        }
    }

    public Transaction addNewTransaction(Transaction transaction) {
        if (userRepository.existsById(transaction.getUserId())) {
            transaction.setTimeCreated();
            transaction.setTimeUpdated();
            return transactionRepository.save(transaction);
        } else {
            return null;
        }
    }

    public String removeTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
        return "Deleted";
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactionRepository.findAll().forEach(transactions::add);
        return transactions;
    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).get();
    }

    public List<Transaction> getTransactionByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction updateTransactionById(Transaction transaction, Long transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.setTransactionInfoById(transaction.getUserId(), transaction.getAmount(),
                    new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()), transactionId);
            return getTransactionById(transactionId);
        } else {
            transaction.setTimeCreated();
            transaction.setTimeUpdated();
            return transactionRepository.save(transaction);
        }
    }
}
