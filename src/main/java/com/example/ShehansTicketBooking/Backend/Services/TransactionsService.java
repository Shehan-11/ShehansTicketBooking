package com.example.ShehansTicketBooking.Backend.Services;

import com.example.ShehansTicketBooking.Backend.Models.Transactions;
import com.example.ShehansTicketBooking.Backend.Repo.TransactionsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionsService.class);

    @Autowired
    private TransactionsRepo transactionsRepo;

    public Transactions createTransaction(Transactions transactions) {
        // Ensure system time is set
        logger.info("Setting the Last Updated Time To System Date Time");
        if (transactions.getTransactionDate() == null) {
            transactions.setTransactionDate(LocalDateTime.now());
        }
        return transactionsRepo.save(transactions);
    }
}
