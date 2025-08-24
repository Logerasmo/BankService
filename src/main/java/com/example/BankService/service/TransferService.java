package com.example.BankService.service;

import com.example.BankService.logger.Logger;
import com.example.BankService.other.BankCard;
import com.example.BankService.repository.Repository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class TransferService {
    Repository repository;

    public TransferService(Repository repository){
        this.repository = repository;
    }
    private Integer operationsCount = 0;
    public ResponseEntity<String> transfer(String numFrom, String dateFrom, String cvcFrom, String numTo, Integer sum){
        String logs = "from: " + numFrom + " to: " + numTo + " sum: " + sum.floatValue() * 0.01F + " commission: " + sum.floatValue() * 0.0001F;
        Optional<BankCard> from = repository.getCardByNumber(numFrom);
        Optional<BankCard> to = repository.getCardByNumber(numTo);
        if (from.isEmpty() || to.isEmpty()){
            logs += " Card not found 400";
            Logger.log(logs);
            return new ResponseEntity<>("Card not found", HttpStatusCode.valueOf(400));
        }
        if (!from.get().checkInfo(cvcFrom, dateFrom)){
            logs += " CVC/date wrong 400";
            Logger.log(logs);
            return new ResponseEntity<>("CVC/date wrong", HttpStatusCode.valueOf(400));
        }
        if (!from.get().spendMoney(sum)){
            logs += " Not enough money 500";
            Logger.log(logs);
            return new ResponseEntity<>("Not enough money", HttpStatusCode.valueOf(500));
        }
        to.get().addMoney(sum);
        operationsCount++;
        logs += " OK 200";
        Logger.log(logs);
        return new ResponseEntity<>(operationsCount.toString(), HttpStatusCode.valueOf(200));
    }

    public Integer getLastOperationID() {
        return operationsCount;
    }

    public Repository getRepository() {
        return repository;
    }
}
