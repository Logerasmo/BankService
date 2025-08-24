package com.example.BankService;

import com.example.BankService.other.BankCard;
import com.example.BankService.repository.Repository;
import com.example.BankService.service.TransferService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import java.util.NoSuchElementException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankServiceApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;

	private final GenericContainer<?> myApp = new GenericContainer<>("app:1.0").withExposedPorts(5500);

	TransferService transferService;
	Repository repository;
	BankCard bankCard1;
	BankCard bankCard2;
	BankCard bankCard3;
	@BeforeEach
	void beforeEach(){
		bankCard1 = new BankCard("1000000000000000", 1000, "123", "10/28");
		bankCard2 = new BankCard("2000000000000000", 2000, "456", "11/28");
		bankCard3 = new BankCard("3000000000000000", 3000, "789", "12/28");
		transferService = new TransferService(new Repository());
		repository = transferService.getRepository();
		repository.addCard(bankCard1);
		repository.addCard(bankCard2);
		repository.addCard(bankCard3);
	}
	@Test
	void bankCardTest() {
		Assertions.assertEquals(1000, bankCard1.getMoney());
		bankCard1.addMoney(1000);
		Assertions.assertEquals(2000, bankCard1.getMoney());
		Assertions.assertTrue(bankCard1.spendMoney(1500));
		Assertions.assertEquals(500, bankCard1.getMoney());
		Assertions.assertFalse(bankCard1.spendMoney(1000));
		Assertions.assertEquals(500, bankCard1.getMoney());
		Assertions.assertTrue(bankCard1.checkInfo("123", "10/28"));
		Assertions.assertFalse(bankCard1.checkInfo("124", "10/28"));
	}

	@Test
	void repositoryTest(){
		Assertions.assertFalse(repository.addCard(bankCard1));
		Assertions.assertEquals(bankCard1, repository.getCardByNumber("1000000000000000").get());
		Assertions.assertFalse(repository.getCardByNumber("1200000000000000").isPresent());
	}

	@Test
	void transferTest(){
		Assertions.assertEquals(200, transferService.transfer("1000000000000000", "10/28", "123", "2000000000000000", 500).getStatusCode().value());
		Assertions.assertEquals(500, bankCard1.getMoney());
		Assertions.assertEquals(2500, bankCard2.getMoney());
		Assertions.assertEquals(500, transferService.transfer("1000000000000000", "10/28", "123", "2000000000000000", 1000).getStatusCode().value());

	}
	@Test
	void containerTest(){
		myApp.start();
	}
}
