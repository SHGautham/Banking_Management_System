package com.example.BankingProject.Service;

import com.example.BankingProject.Model.AccountModel;
import com.example.BankingProject.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountModel createAccount(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    public List<AccountModel> getAllAccount() {
        return accountRepository.findAll();
    }

    public AccountModel getAccountByid(Long id){
        return accountRepository.findById(id).orElse(null);
    }

    public String deleteAccountByid(Long id){
        accountRepository.deleteById(id);
        return "Account has Deleted successfully";
    }

    public String getBalance(String accountNumber){
        AccountModel accountData = accountRepository.getAccountByAccountNumber(accountNumber);
        return accountData.getBalance()+"";
    }


    public AccountModel updateAccountById(Long id , AccountModel accountModel){

        AccountModel accountdata = accountRepository.findById(id).orElse(null);

        accountdata.setBalance(accountModel.getBalance());
        accountdata.setAccountNumber(accountModel.getAccountNumber());
        accountdata.setPin(accountModel.getPin());

        return accountRepository.save(accountdata);
    }

    public AccountModel getAccountByAccountNumber(String accountNum) {
        return accountRepository.getAccountByAccountNumber(accountNum);
    }

    public List<AccountModel> getAccountGreaterThan(BigDecimal amount) {
        return accountRepository.getAccountGreaterThan(amount);
    }

    public String ValidatePinNumber(Long id ,String pin) {
        AccountModel accountDetails = accountRepository.findById(id).orElse(null);
        if(accountDetails == null){
            return "Account not available";
        }
        if(accountDetails.getPin().compareTo(pin) == 0 ){
            return "Pin Number Matched";
        }
        return "Pin Number Not Matched";
    }

    public List<AccountModel> getAccountslessthanMinimumBalance(BigDecimal amount) {
        return accountRepository.getAccountslessthanMinimumBalance(amount);
    }

    public String getName(String accountNumber) {
        AccountModel accountModel = accountRepository.getAccountByAccountNumber(accountNumber);
        return accountModel.getName();
    }

    public boolean checkAccExist(String accountNumber) {
        AccountModel dummy = null ;
        dummy = accountRepository.getAccountByAccountNumber(accountNumber);
        if(dummy != null){
            return true;
        }
        return false ;
    }
}
