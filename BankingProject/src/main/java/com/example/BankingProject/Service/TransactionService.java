package com.example.BankingProject.Service;

import com.example.BankingProject.Model.AccountModel;
import com.example.BankingProject.Model.TransactionModel;
import com.example.BankingProject.Repository.AccountRepository;
import com.example.BankingProject.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository ;

    public String withdraw(String accountNumber, BigDecimal amount){
        AccountModel accountdata = null;
        accountdata = accountRepository.getAccountByAccountNumber(accountNumber);

        if(accountdata == null){
            return "Account not available";
        }
        if(accountdata.getBalance().compareTo(amount) < 0){
            return "Insufficient Balance";
        }
        accountdata.setBalance(accountdata.getBalance().subtract(amount));
        accountRepository.save(accountdata);
        TransactionModel transactionData = new TransactionModel();
        transactionData.setAccountNumber(accountNumber);
        transactionData.setAccountModel(accountdata);
        transactionData.setAmount(amount);
        transactionData.setDate(LocalDate.now());
        transactionData.setType("WithDraw");
        transactionRepository.save(transactionData);
        return "WithDraw Done Successfully , Your Balance Amount :  "+ accountdata.getBalance() ;
     }

    public String Deposit(String accountNumber, BigDecimal amount){
        AccountModel accountdata = null;

        accountdata = accountRepository.getAccountByAccountNumber(accountNumber);

        if(accountdata == null){
            return "Account not available";
        }

        accountdata.setBalance(accountdata.getBalance().add(amount));
        accountRepository.save(accountdata);

        TransactionModel transactionData = new TransactionModel();
        transactionData.setAccountNumber(accountNumber);
        transactionData.setAccountModel(accountdata);
        transactionData.setAmount(amount);
        transactionData.setDate(LocalDate.now());
        transactionData.setType("Deposit");
        transactionRepository.save(transactionData);
        return "Deposit Done Successfully , Your Balance Amount :  "+ accountdata.getBalance() ;
    }

    public List<TransactionModel> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public List<TransactionModel> getStatementByAccNum(String accountnumber) {
        return transactionRepository.getTransactionByAccNum(accountnumber);
    }

    public List<TransactionModel> getTransactionbyType(String type) {
        return transactionRepository.getTransactionbyType(type);
    }

    public List<TransactionModel> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
    return transactionRepository.findByDateBetween(startDate, endDate);
}

    public String transferAmountFromOneAccountToAnother(String fromAcc, String toAcc, BigDecimal amount , String pin) {
        AccountModel accountData1 = null;
        accountData1 = accountRepository.getAccountByAccountNumber(fromAcc);
        if(accountData1 == null){
            return "Account : "+fromAcc+" Not Available ";
        }

        if (accountData1.getPin() == null || !accountData1.getPin().equals(pin)) {
            return "Wrong Pin Number";
        }

        AccountModel accountData2 = null;
        accountData2 = accountRepository.getAccountByAccountNumber(toAcc);
        if(accountData2 == null){
            return "Account : "+toAcc+" Not Available ";
        }

        if(accountData1.getBalance().compareTo(amount) < 0){
            return "Insuffiient Balance";
        }

        accountData1.setBalance(accountData1.getBalance().subtract(amount));
        accountRepository.save(accountData1);

        accountData2.setBalance(accountData2.getBalance().add(amount));
        accountRepository.save(accountData2);

        TransactionModel transactionData1 = new TransactionModel();
        transactionData1.setAccountNumber(fromAcc);
        transactionData1.setAccountModel(accountData1);
        transactionData1.setAmount(amount);
        transactionData1.setDate(LocalDate.now());
        transactionData1.setType("CreditTransfer");
        transactionRepository.save(transactionData1);

        TransactionModel transactionData2 = new TransactionModel();
        transactionData2.setAccountNumber(toAcc);
        transactionData2.setAccountModel(accountData2);
        transactionData2.setAmount(amount);
        transactionData2.setDate(LocalDate.now());
        transactionData2.setType("DebitTransfer");
        transactionRepository.save(transactionData2);

        return "Amount :"+amount+" has been transfer from "+fromAcc+" to "+toAcc ;
    }

    public List<HashMap<String,String>> getStatsOfWithdraw(String accountNumber) {
        BigDecimal min = transactionRepository.findMinWithdrawAmount(accountNumber);
        BigDecimal max = transactionRepository.findMaxWithdrawAmount(accountNumber);
        BigDecimal avg = transactionRepository.findAverageWithdrawAmount(accountNumber);
        HashMap<String,String> statsofWithdraw = new HashMap<>();
        statsofWithdraw.put("Average", String.format("%.2f", avg));
        statsofWithdraw.put("Max", String.format("%.2f", max));
        statsofWithdraw.put("Min", String.format("%.2f", min));
        List<HashMap<String,String>> statsList = new ArrayList<>();
        statsList.add(statsofWithdraw);
        return statsList ;
    }

    public List<HashMap<String,String>> getStatsOfDeposit(String accountNumber) {
        BigDecimal min = transactionRepository.findMinDepositAmount(accountNumber);
        BigDecimal max = transactionRepository.findMaxDepositAmount(accountNumber);
        BigDecimal avg = transactionRepository.findAverageDepositAmount(accountNumber);
        HashMap<String,String> statsofDeposit = new HashMap<>();
        statsofDeposit.put("Average", String.format("%.2f", avg));
        statsofDeposit.put("Max", String.format("%.2f", max));
        statsofDeposit.put("Min", String.format("%.2f", min));
        List<HashMap<String,String>> statsList = new ArrayList<>();
        statsList.add(statsofDeposit);
        return statsList ;
    }

    public List<HashMap<String,String>> getStatsOfCredit(String accountNumber) {
        BigDecimal min = transactionRepository.findMinCreditAmount(accountNumber);
        BigDecimal max = transactionRepository.findMaxCreditAmount(accountNumber);
        BigDecimal avg = transactionRepository.findAverageCreditAmount(accountNumber);
        HashMap<String,String> statsofCredit = new HashMap<>();
        statsofCredit.put("Average", String.format("%.2f", avg));
        statsofCredit.put("Max", String.format("%.2f", max));
        statsofCredit.put("Min", String.format("%.2f", min));
        List<HashMap<String,String>> statsList = new ArrayList<>();
        statsList.add(statsofCredit);
        return statsList ;
    }

    public List<HashMap<String,String>> getStatsOfDebit(String accountNumber) {
        BigDecimal min = transactionRepository.findMinDebitAmount(accountNumber);
        BigDecimal max = transactionRepository.findMaxDebitAmount(accountNumber);
        BigDecimal avg = transactionRepository.findAverageDebitAmount(accountNumber);
        HashMap<String,String> statsofDebit = new HashMap<>();
        statsofDebit.put("Average", String.format("%.2f", avg));
        statsofDebit.put("Max", String.format("%.2f", max));
        statsofDebit.put("Min", String.format("%.2f", min));
        List<HashMap<String,String>> statsList = new ArrayList<>();
        statsList.add(statsofDebit);
        return statsList ;
    }

    public List<AccountModel> getAccountsWithZeroTransactions() {
        return accountRepository.findAccountsWithNoTransactions();
    }



}
