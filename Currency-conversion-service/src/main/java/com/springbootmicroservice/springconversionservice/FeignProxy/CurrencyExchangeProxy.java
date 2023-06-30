package com.springbootmicroservice.springconversionservice.FeignProxy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springbootmicroservice.springconversionservice.Bean.CurrencyConversion;

// in currencyExchangeService's application.properties -> 
// spring.application.name = currency-exchange. That's why name="currency-exchange"
// localhost:8000 where Currency Exchange is running

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable("from") String from,
			@PathVariable("to") String to);
}
