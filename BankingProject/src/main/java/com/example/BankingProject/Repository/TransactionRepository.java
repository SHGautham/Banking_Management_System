package com.example.BankingProject.Repository;


import com.example.BankingProject.Model.AccountModel;
import com.example.BankingProject.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository <TransactionModel , Long > {
    @Query("SELECT t FROM TransactionModel t WHERE t.accountNumber = :accountnumber")
    List<TransactionModel> getTransactionByAccNum(String accountnumber);

    @Query("SELECT t FROM TransactionModel t WHERE t.type = :type")
    List<TransactionModel> getTransactionbyType(String type);

//    WithDraw

    @Query("SELECT AVG(t.amount) FROM TransactionModel t WHERE t.type = 'WithDraw' AND t.accountNumber = :accountNumber")
    BigDecimal findAverageWithdrawAmount(String accountNumber);

    @Query("SELECT MIN(t.amount) FROM TransactionModel t WHERE t.type = 'WithDraw' AND t.accountNumber = :accountNumber")
    BigDecimal findMinWithdrawAmount(String accountNumber);

    @Query("SELECT MAX(t.amount) FROM TransactionModel t WHERE t.type = 'WithDraw' AND t.accountNumber = :accountNumber")
    BigDecimal findMaxWithdrawAmount(String accountNumber);

//    Deposit

    @Query("SELECT AVG(t.amount) FROM TransactionModel t WHERE t.type = 'Deposit' AND t.accountNumber = :accountNumber")
    BigDecimal findAverageDepositAmount(String accountNumber);

    @Query("SELECT MIN(t.amount) FROM TransactionModel t WHERE t.type = 'Deposit' AND t.accountNumber = :accountNumber")
    BigDecimal findMinDepositAmount(String accountNumber);

    @Query("SELECT MAX(t.amount) FROM TransactionModel t WHERE t.type = 'Deposit' AND t.accountNumber = :accountNumber")
    BigDecimal findMaxDepositAmount(String accountNumber);

//    Debited

    @Query("SELECT AVG(t.amount) FROM TransactionModel t WHERE t.type = 'DebitTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findAverageDebitAmount(String accountNumber);

    @Query("SELECT MIN(t.amount) FROM TransactionModel t WHERE t.type = 'DebitTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findMinDebitAmount(String accountNumber);

    @Query("SELECT MAX(t.amount) FROM TransactionModel t WHERE t.type = 'DebitTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findMaxDebitAmount(String accountNumber);

//    Credited

    @Query("SELECT AVG(t.amount) FROM TransactionModel t WHERE t.type = 'CreditTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findAverageCreditAmount(String accountNumber);

    @Query("SELECT MIN(t.amount) FROM TransactionModel t WHERE t.type = 'CreditTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findMinCreditAmount(String accountNumber);

    @Query("SELECT MAX(t.amount) FROM TransactionModel t WHERE t.type = 'CreditTransfer' AND t.accountNumber = :accountNumber")
    BigDecimal findMaxCreditAmount(String accountNumber);

    @Query("SELECT a FROM AccountModel a LEFT JOIN a.transactions t WHERE t.id IS NULL")
    List<AccountModel> findAccountsWithNoTransactions();


    List<TransactionModel> findByDateBetween(LocalDate startDate, LocalDate endDate);
}