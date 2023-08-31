package com.springbootmicroservice.springexchangeservice.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmicroservice.springexchangeservice.Beans.CurrencyExchange;
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findByFromAndTo(String from, String to);
}
