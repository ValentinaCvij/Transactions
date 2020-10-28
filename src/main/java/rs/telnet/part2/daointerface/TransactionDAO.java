package rs.telnet.part2.daointerface;

import rs.telnet.part2.modelclass.Transaction;
import java.util.List;

public interface TransactionDAO {
    List<Transaction> getAllTransactions();

    Transaction findTransactionById(int theId);

    Transaction saveTransaction(Transaction theTransaction);

    void deleteTransactionById(int theId);
}
