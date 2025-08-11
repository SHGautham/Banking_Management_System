package com.example.BankingProject.Repository;

import com.example.BankingProject.Model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository <AccountModel , Long>{

    @Query("SELECT a FROM AccountModel a WHERE a.accountNumber = :accountNum")
    AccountModel getAccountByAccountNumber(String accountNum);

    @Query("SELECT a FROM AccountModel a WHERE a.balance > :amount")
    List<AccountModel> getAccountGreaterThan(BigDecimal amount);

    @Query("SELECT a FROM AccountModel a WHERE a.balance < :amount")
    List<AccountModel> getAccountslessthanMinimumBalance(BigDecimal amount);

    @Query("SELECT a FROM AccountModel a LEFT JOIN a.transactions t WHERE t.id IS NULL")
    List<AccountModel> findAccountsWithNoTransactions();

}
