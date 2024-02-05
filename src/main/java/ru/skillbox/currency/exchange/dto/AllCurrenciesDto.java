package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ValCurs")
public class AllCurrenciesDto {
    private List<CurrencyDto> currencies;

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    @XmlElement(name = "Valute")
    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }
}
