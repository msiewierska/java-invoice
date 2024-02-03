package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) throws IllegalArgumentException {
        if (name == null || name == "") {
            throw new IllegalArgumentException("Please add name");
        }

        if (price == null) {
            throw new IllegalArgumentException("Please add price");
        }

        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Please add positive price");
        }

        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal tax = price.multiply(taxPercent);
        return price.add(tax);
    }
}
