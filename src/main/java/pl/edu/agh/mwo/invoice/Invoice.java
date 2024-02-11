package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    public void addProduct(Product product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException();
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Please add positive qty");
        }

        products.put(product, quantity);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal(0);

        for (Map.Entry<Product, Integer> set : products.entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                subtotal =  subtotal.add(set.getKey().getPrice());
            }
        }

        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = new BigDecimal(0);

        for (Map.Entry<Product, Integer> set : products.entrySet()) {
            for (int i = 0; i < set.getValue(); i++) {
                Product product = set.getKey();
                tax =  tax.add(product.getPrice().multiply(product.getTaxPercent()));
            }
        }

        return tax;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
