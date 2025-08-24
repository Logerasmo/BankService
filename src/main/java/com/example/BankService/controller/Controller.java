package com.example.BankService.controller;

import com.example.BankService.logger.Logger;
import com.example.BankService.service.RequestBodyGetterConfirm;
import com.example.BankService.service.RequestBodyGetterTransfer;
import com.example.BankService.service.TransferService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://serp-ya.github.io")
@RequestMapping("/")
@RestController
public class Controller {
    TransferService service;

    public Controller(TransferService service){
        this.service = service;
    }

    @GetMapping("get")
    public String justGet(){
        return "hello";
    }
    @PostMapping("transfer")
    public ResponseEntity<String> transfer(@RequestBody RequestBodyGetterTransfer req){
        System.out.println(service.getLastOperationID());
        return service.transfer(req.getCardFromNumber(), req.getCardFromValidTill(), req.getCardFromCVV(), req.getCardToNumber(), req.getAmount().getValue());
    }

    @PostMapping("confirmOperation")
    private ResponseEntity<String> confirmOperation(@RequestBody RequestBodyGetterConfirm req){
        if (Integer.valueOf(req.getOperationId()).equals(service.getLastOperationID())){
            Logger.log("code: " + req.getCode() + " operationID: " + req.getOperationId() + " confirmed");
            return new ResponseEntity<>(req.getOperationId(), HttpStatusCode.valueOf(200));
        }
        Logger.log("code: " + req.getCode() + " not confirmed");
        return new ResponseEntity<>("Operation ID wrong", HttpStatusCode.valueOf(500));

    }


}
