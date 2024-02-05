package ru.skillbox.currency.exchange.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.dto.AllCurrenciesDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RepositoryUpdater {
    private final CurrencyRepository repository;
    private final XmlParser xmlParser;
    private final CurrencyMapper mapper;

    public void update() {
        repository.deleteAll();
        AllCurrenciesDto dtoList = xmlParser.parseCurrencies();
        List<Currency> entities = dtoList
                .getCurrencies()
                .stream()
                .map(mapper::convertToEntity)
                .collect(Collectors.toList());
        repository.saveAllAndFlush(entities);
    }
}