package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends TaxFreeProduct {

    private final BigDecimal akcyza;

    public FuelCanister(String name, BigDecimal price) {

        super(name, price);

        this.akcyza = new BigDecimal("5.56");
    }

    public BigDecimal getAkcyza() {
        return akcyza;
    }

    public BigDecimal getPriceWithTax() {
        return getPrice().multiply(getTaxPercent()).add(getPrice()).add(this.akcyza);
    }
}
