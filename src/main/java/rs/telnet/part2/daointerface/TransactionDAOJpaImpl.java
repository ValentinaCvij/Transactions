package rs.telnet.part2.daointerface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.telnet.part2.modelclass.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TransactionDAOJpaImpl implements TransactionDAO {

    private EntityManager entityManager;

    @Autowired
    public TransactionDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;

    }

    //get all the transactions from the database
    @Override
    public List<Transaction> getAllTransactions() {
        Query theQuery= (Query) entityManager.createQuery("from Transaction");
        List<Transaction> transactions = theQuery.getResultList();

        return transactions;
    }

    //return the transaction by giving id as input
    @Override
    public Transaction findTransactionById(int theId) {
        Transaction theTransaction = entityManager.find(Transaction.class,theId);
        return theTransaction;
    }

    //add the transaction to the database
    @Override
    public Transaction saveTransaction(Transaction theTransaction) {
        Transaction dbTransaction = entityManager.merge(theTransaction);
        theTransaction.setId(dbTransaction.getId());
        return theTransaction;
    }

    //delete the transaction from the database using transaction id
    @Override
    public void deleteTransactionById(int theId) {
        Query theQuery = (Query) entityManager.createQuery("delete from Transaction where id=:transactionId");
        theQuery.setParameter("transactionId", theId);
        theQuery.executeUpdate();
    }
}