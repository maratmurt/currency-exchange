package ru.skillbox.currency.exchange.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.dto.AllCurrenciesDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Slf4j
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "currency-data")
public class XmlParser {
    private String url;

    public AllCurrenciesDto parseCurrencies() {
        String xml = fetchXml();
        AllCurrenciesDto dto;
        try {
            Unmarshaller unmarshaller = JAXBContext
                    .newInstance(AllCurrenciesDto.class)
                    .createUnmarshaller();
            dto = (AllCurrenciesDto) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    private String fetchXml() {
        RestTemplate restTemplate = new RestTemplate();
        String xml = restTemplate.getForEntity(url, String.class).getBody();
        if (xml == null) {
            throw new RuntimeException("Актуальные данные не доступны!");
        }
        return xml;
    }
}
