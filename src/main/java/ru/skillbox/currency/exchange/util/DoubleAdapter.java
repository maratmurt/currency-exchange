package ru.skillbox.currency.exchange.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DoubleAdapter extends XmlAdapter<String, Double> {
    private final DecimalFormat format;

    public DoubleAdapter() {
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
        customSymbol.setDecimalSeparator(',');
        this.format = new DecimalFormat("0,0", customSymbol);
    }

    @Override
    public Double unmarshal(String value) throws Exception {
        return format.parse(value).doubleValue();
    }

    @Override
    public String marshal(Double value) {
        return format.format(value);
    }
}
