package com.bootcamp.banking.walletyanki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WalletYankiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletYankiApplication.class, args);
	}

}
