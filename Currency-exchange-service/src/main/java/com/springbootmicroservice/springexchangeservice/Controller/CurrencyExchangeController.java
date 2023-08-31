package com.springbootmicroservice.springexchangeservice.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservice.springexchangeservice.Beans.CurrencyExchange;
import com.springbootmicroservice.springexchangeservice.Repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	// 	this logger statement is being printed by micrometer dependency
	//	INFO [currency-exchange,25a3102ae6bcfbed2b6497bf2a24fea4,
	//	 e13cb3cb204573aa]
	// these ids will be required for tracing the requests
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private CurrencyExchangeRepository repository;
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
			@PathVariable String to) {
		
		
		logger.info("retrieveExchangeValue called with {} to {}",from+" to "+to);
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if (currencyExchange==null) {
			throw new RuntimeException("Unable to find data for "+from+" to "+to);
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
