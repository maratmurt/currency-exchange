package ru.skillbox.currency.exchange.util;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.skillbox.currency.exchange.dto.AllCurrenciesDto;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RepositoryUpdater {
    private final CurrencyRepository repository;
    private final XmlParser xmlParser;
    private final CurrencyMapper mapper;

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.HOURS)
    public void update() {
        AllCurrenciesDto newDtos = xmlParser.parseCurrencies();
        List<Currency> entities = new ArrayList<>();
        for (CurrencyDto newDto : newDtos.getCurrencies()) {
            Currency entity = repository.findByIsoCharCode(newDto.getIsoCharCode());
            if (entity != null) {
                entity.setName(newDto.getName());
                entity.setValue(newDto.getValue());
                entity.setNominal(newDto.getNominal());
                entity.setIsoNumCode(newDto.getIsoNumCode());
            } else {
                entity = mapper.convertToEntity(newDto);
            }
            entities.add(entity);
        }
        repository.saveAllAndFlush(entities);
    }
}
