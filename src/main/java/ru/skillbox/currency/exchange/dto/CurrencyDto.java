package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.skillbox.currency.exchange.util.DoubleAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {
    private Long id;

    private String name;

    private Long nominal;

    private Double value;

    private Long isoNumCode;

    private String isoCharCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public Long getNominal() {
        return nominal;
    }

    @XmlElement(name = "Nominal")
    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    public Double getValue() {
        return value;
    }

    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public void setValue(Double value) {
        this.value = value;
    }

    public Long getIsoNumCode() {
        return isoNumCode;
    }

    @XmlElement(name = "NumCode")
    public void setIsoNumCode(Long isoNumCode) {
        this.isoNumCode = isoNumCode;
    }

    public String getIsoCharCode() {
        return isoCharCode;
    }

    @XmlElement(name = "CharCode")
    public void setIsoCharCode(String isoCharCode) {
        this.isoCharCode = isoCharCode;
    }
}