package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Set<Product> products = new HashSet<>();

    private Map<String, Integer> qtys = new HashMap<String, Integer>();
    public void addProduct(Product product) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        products.add(product);
        qtys.put(product.getName(), 1);
    }

    public void addProduct(Product product, Integer quantity) throws IllegalArgumentException {
        if (product == null) {
            throw new IllegalArgumentException();
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Please add positive qty");
        }

        products.add(product);
        qtys.put(product.getName(), quantity);
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = new BigDecimal(0);

        for (Product product : products) {
            for (int i = 0; i < qtys.get(product.getName()); i++) {
                subtotal =  subtotal.add(product.getPrice());
            }
        }

        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = new BigDecimal(0);

        for (Product product : products) {
            for (int i = 0; i < qtys.get(product.getName()); i++) {
                tax =  tax.add(product.getPrice().multiply(product.getTaxPercent()));
            }
        }

        return tax;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
