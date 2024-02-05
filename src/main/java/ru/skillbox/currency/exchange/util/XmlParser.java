package ru.skillbox.currency.exchange.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
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
            JAXBContext context = JAXBContext.newInstance(AllCurrenciesDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            dto = (AllCurrenciesDto) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    private String fetchXml() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String xml = response.getBody();
        if (xml == null) {
            throw new RuntimeException("Актуальные данные не доступны!");
        }
        return xml;
    }
}
