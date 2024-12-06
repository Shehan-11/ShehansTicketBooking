package com.example.ShehansTicketBooking.Backend.Repo;

import com.example.ShehansTicketBooking.Backend.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {

}
