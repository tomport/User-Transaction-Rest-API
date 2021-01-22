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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;

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
            UserTransaction userTransaction = new UserTransaction(transaction.getUserId());
            userTransactionRepository.save(userTransaction);
            return transactionRepository.save(transaction);
        }
        else {
            return null;
        }
    }

    public String removeTransaction(Long transactionId) {
        userTransactionRepository.deleteById(transactionId);
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

    public List<Transaction> getTransactionsByUser(Long userId) {
        List<UserTransaction> userTransactions = userTransactionRepository.findByUserId(userId);
        List<Transaction> transactions = new ArrayList<Transaction>();

        while(!userTransactions.isEmpty()){
            transactions.add(transactionRepository.findById(userTransactions.get(0).getTransactionId()).get());
            userTransactions.remove(0);
        }
        return transactions;
    }
    public Long getTransactionSumByUser(Long userId) {
        List<UserTransaction> userTransactions = userTransactionRepository.findByUserId(userId);
        Long sum = new Long(0);
        while(!userTransactions.isEmpty()){
            sum += transactionRepository.findById(userTransactions.get(0).getTransactionId()).get().getAmount();
            userTransactions.remove(0);
        }
        return sum;
    }

    public Transaction updateTransactionById(Transaction transaction, Long transactionId) {
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
