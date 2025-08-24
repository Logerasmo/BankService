package com.example.BankService.config;

import com.example.BankService.controller.Controller;
import com.example.BankService.repository.Repository;
import com.example.BankService.service.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public Repository repository(){
        return new Repository();
    }

    @Bean
    public TransferService transferService(){
        return new TransferService(repository());
    }

    @Bean
    public Controller controller(){
        return new Controller(transferService());
    }
}
