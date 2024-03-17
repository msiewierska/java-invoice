package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    private static Integer invoiceCount = 0;

    private int invoiceNumber;

    public Invoice() {
        this.invoiceNumber = ++invoiceCount;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getPrint() {
        String print = "";
        print = print + "Invoice number: " + getInvoiceNumber() + "\n";
        int i = 1;
        for (Product product : products.keySet()) {
            print = print + i + "." + product.getName() + "(qty:" + products.get(product) + ") \n";
            i++;
        }

        print = print + "Liczba pozycji: " + products.size();
        return print;
    }

    public Map<Product, Integer> getProducts
            () {
        return products;
    }
}
