package com.airtnt.backend.currency;

import com.airtnt.common.entity.Currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void testAddCurrency() {
        Currency usDollar = Currency.builder().unit("USD").symbol("$").build();

        currencyRepository.save(usDollar);
    }
}
