package com.spring.route;

import com.spring.CurrencyExchange;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public
class CurrencyExchangeTransformer {
    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {
        currencyExchange.setConversionMultiple(
                currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        return currencyExchange;
    }
}
