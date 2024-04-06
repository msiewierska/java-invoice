package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Objects;

public class BottleOfWine extends Product {

    private final BigDecimal akcyza;

    public BottleOfWine(String name, BigDecimal price,BigDecimal tax) {

        super(name, price, tax);

        this.akcyza = new BigDecimal("5.56");
    }

    public BigDecimal getAkcyza() {
        return akcyza;
    }

    public BigDecimal getPriceWithTax() {
        return getPrice().multiply(getTaxPercent()).add(getPrice()).add(this.akcyza);
    }
}
