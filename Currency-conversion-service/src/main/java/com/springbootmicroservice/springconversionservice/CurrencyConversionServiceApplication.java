package com.springbootmicroservice.springconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
// http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/100
// http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/100	
}
