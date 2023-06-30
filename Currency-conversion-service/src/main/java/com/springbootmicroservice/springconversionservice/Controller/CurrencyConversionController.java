package com.springbootmicroservice.springconversionservice.Controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springbootmicroservice.springconversionservice.Bean.CurrencyConversion;
import com.springbootmicroservice.springconversionservice.FeignProxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		// RestTemplate is used to call RestAPI
		// getForEntity("url",
		// response should be converted into which kind of class,
		// uriVariables(hashmap))
		// the structure of CurrencyConversion is same as the response it is getting from CurrencyExchange API
		// id,from,to,conversionMultiple,environment these values get mapped automatically
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, uriVariables );
		
		CurrencyConversion currencyConversionRes = responseEntity.getBody();
				
		return new CurrencyConversion(currencyConversionRes.getId(),
				from,to,quantity,
				currencyConversionRes.getConversionMultiple(),
				quantity.multiply(currencyConversionRes.getConversionMultiple())
				,currencyConversionRes.getEnvironment());
		
	}
	

	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		CurrencyConversion currencyConversionRes = 
				currencyExchangeProxy.retrieveExchangeValue(from, to);
			
		return new CurrencyConversion(currencyConversionRes.getId(),
				from,to,quantity,
				currencyConversionRes.getConversionMultiple(),
				quantity.multiply(currencyConversionRes.getConversionMultiple())
				,currencyConversionRes.getEnvironment());
		
}
}
