/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharamacy;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author kan
 */
public class Product implements Serializable{
    protected static ArrayList<Product> product = new ArrayList<>();
    private int id;
    private String name;
    private double price;
    public int quantity;
    private String supplier;
    private int totalSold;
    private double revenue;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity,String supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public static void setProduct(ArrayList<Product> product) {
        Product.product = product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static ArrayList<Product> getProduct() {
        return product;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public double getRevenue() {
        return revenue;
    }
    
    public static Product findBestSeller() {
        if (product.isEmpty()) {
            System.out.println("No products available.");
            return null;
        }

        Product bestSeller = null;
        int maxSold = 0;

        for (Product p : product) {
            if (p.getTotalSold() > maxSold) {
                maxSold = p.getTotalSold();
                bestSeller = p;
            }
        }

        return bestSeller;
    }
    
    public double calculateRevenue() {
    return totalSold * price; 
}
    
}
