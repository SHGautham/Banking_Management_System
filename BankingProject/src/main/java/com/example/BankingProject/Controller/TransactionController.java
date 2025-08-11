package com.example.BankingProject.Controller;

import com.example.BankingProject.Model.AccountModel;
import com.example.BankingProject.Model.TransactionModel;
import com.example.BankingProject.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/withdraw/{accountNumber}")
    public String withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        return transactionService.withdraw(accountNumber, amount);
    }

    @PostMapping("/deposit/{accountNumber}")
    public String deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        return transactionService.Deposit(accountNumber, amount);
    }

    @GetMapping("/getAllTransaction")
    public ResponseEntity<List<TransactionModel>> getAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }

//    FE - client
    @GetMapping("/generateStatement")
    public ResponseEntity<List<TransactionModel>> getStatementByAccNum(@RequestParam String accountnumber) {
         return ResponseEntity.ok(transactionService.getStatementByAccNum(accountnumber));
    }

    @GetMapping("/getTransactionbyType")
    public List<TransactionModel> getTransactionbyType(@RequestParam String type) {
        return transactionService.getTransactionbyType(type);
    }

    @GetMapping("/filter")
    public List<TransactionModel> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return transactionService.getTransactionsByDateRange(startDate, endDate);
    }


    //    FE - client
    @PostMapping("/transfer")
    public ResponseEntity<String> transferAmountFromOneAccountToAnother(@RequestParam String fromAcc, @RequestParam String toAcc, @RequestParam BigDecimal amount, @RequestParam String pin) {
        return ResponseEntity.ok(transactionService.transferAmountFromOneAccountToAnother(fromAcc, toAcc, amount, pin));
    }

    //    FE - client
    @GetMapping("/stats/withdraw/{accountNumber}")
    public ResponseEntity<List<HashMap<String, String>>> getStatsOfWithdrawImproved(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getStatsOfWithdraw(accountNumber));
    }

    //    FE - client
    @GetMapping("/stats/deposit/{accountNumber}")
    public ResponseEntity<List<HashMap<String, String>>> getStatsOfdeposit(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getStatsOfDeposit(accountNumber));
    }

    //    FE - client
    @GetMapping("/stats/debit/{accountNumber}")
    public ResponseEntity<List<HashMap<String, String>>> getStatsOfdebit(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getStatsOfDebit(accountNumber));
    }

    //    FE - client
    @GetMapping("/stats/credit/{accountNumber}")
    public ResponseEntity<List<HashMap<String, String>>> getStatsOfcredit(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getStatsOfCredit(accountNumber));
    }

    @GetMapping("/getAccountsWithZeroTransactions")
    public List<AccountModel> getAccountsWithZeroTransactions() {
        return transactionService.getAccountsWithZeroTransactions();
    }

}
