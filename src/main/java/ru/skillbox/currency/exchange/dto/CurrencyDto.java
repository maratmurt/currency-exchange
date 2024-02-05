package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skillbox.currency.exchange.util.DoubleAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Valute")
public class CurrencyDto {
    private Long id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Nominal")
    private Long nominal;

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    private Double value;

    @XmlElement(name = "NumCode")
    private Long isoNumCode;

    @XmlElement(name = "CharCode")
    private String isoCharCode;
}