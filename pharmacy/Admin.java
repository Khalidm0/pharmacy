/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharamacy;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author kan
 */
public class Admin extends User implements Serializable{
    
    public static ArrayList<Admin> ad = new ArrayList<>();
    
    public Admin() {
    }

    public Admin(String username, String password, int id) {
        super(username, password, id);
    }
    
    
    
    public void addAdmin(Admin a){
        ad.add(a);
        System.out.println("Admin add successfully");
    }
    
    public void deleteAdmin(int id){
        for (Admin admin : ad) {
            if (admin.getId() == id) {
                ad.remove(admin);
                System.out.println("Admin with id: " + id + " deleted successfully");
                return;
            }
 
        }
        System.out.println("This admin doesn't exit");
    }
    
    public void editadmin(int id,String username,String password){
        
        for (Admin admin : ad) {
            if (admin.getId() == id) {
                admin.setUsername(username);
                admin.setPassword(password);
                System.out.println("admin details updated.");
                return;
            }
        }
        System.out.println("Admin not found.");
    }
    
    public void listadmin(){
        if (ad.isEmpty()) {
            System.out.println("No admins available.");
        } else {
            System.out.println("List of Admins:");
            for (Admin admins : ad) {
                System.out.println("ID: " + admins.getId() + ", Username: " + admins.getUsername());
            }
        }
    }
    
    public boolean adminExists(int id) {
    for (Admin admin : ad) { // Assuming adminsList is your list of Admin objects
        if (admin.getId() == id) {
            return true; // Admin with the given ID exists
        }
    }
    return false; // No admin found with the given ID
}
    
     public boolean cashierExists(int id) {
    for (Cashier cashier : Cashier.ca) { // Assuming adminsList is your list of Admin objects
        if (cashier.getId() == id) {
            return true; // Admin with the given ID exists
        }
    }
    return false; // No admin found with the given ID
}
    
     public void addcashier(Cashier a){
        Cashier.ca.add(a); 
    }
    public void deletecashier(int id){
        for (Cashier cashier : Cashier.ca) {
            if (cashier.getId() == id) {
                Cashier.ca.remove(cashier);
                System.out.println("Cashier with id: " + id + " deleted successfully");
                return;
            }
            
        }
         System.out.println("Cashier you want to delete doesn't exit");
    }
    
    public void editcashier(String username,String password,int id){
        
        for (Cashier cashier : Cashier.ca) {
            if (cashier.getId() == id) {
                cashier.setUsername(username);
                cashier.setPassword(password);
                System.out.println("Cashier details updated.");
                return;
            }
        }
        System.out.println("Cashier not found.");
    }
    public void listcashier(){
       if (Cashier.ca.isEmpty()) {
            System.out.println("No cashiers available.");
        } else {
            System.out.println("List of Cashiers:");
            for (Cashier cashier : Cashier.ca) {
                System.out.println("ID: " + cashier.getId() + ", Username: " + cashier.getUsername());
            }
        } 

    }
    
    public void addCustomer(Customer cu){
        Customer.customers.add(cu); 
        System.out.println("Customer added successfully.");
    }
    public void deleteCustomer(int id){
        for (Customer customer : Customer.customers) {
            if (customer.getId() == id) {
                Customer.customers.remove(customer);
                 System.out.println("Customer with id: " + id + " deleted successfully");
                return;
            }
      
        }
            System.out.println("Customer you want to delete doesn't exit");
    }
    
    public void editCustomer(int id,String username,String password){
        for (Customer customer : Customer.customers) {
            if (customer.getId() == id) {
                customer.setUsername(username);
                customer.setPassword(password);
                System.out.println("Customer details updated.");
                return;
            }
        }
        System.out.println("Customer not found.");
    }
    public void listCustomer(){
       if (Customer.customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("List of Customers:");
            for (Customer customer : Customer.customers) {
                System.out.println("ID: " + customer.getId() + ", Username: " + customer.getUsername());
            }
        } 

    }
    
    public void addProduct(Product p){
        Product.product.add(p);
        System.out.println("Product added successfully");
    }
    
    public void deleteProduct(int id){
    for(Product pro : Product.product){
        if(pro.getId() == id){
            Product.product.remove(pro);
            System.out.println("Product removed successfully");
         }
        
       }
            System.out.println("Product not found");
   }
 

    public void editProduct(int id, double price){
    for (Product pro : Product.product) {
            if (pro.getId() == id) {
                pro.setPrice(price);
                System.out.println("product details updated.");
                return;
            }
        }
        System.out.println("product not found.");
    
    }
    
    public void listProduct(){
       if (Product.product.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("List of products:");
            for (Product pro : Product.product) {
                System.out.println("ID: " + pro.getId() + ", name: " + pro.getName() + ", price: " + pro.getPrice());
            }
        } 

    }
    
    
    public void viewSuppliersAndPricing() {
        if (Product.getProduct().isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("List of Suppliers and Pricing:");
         System.out.println("Supplier       Product Name        Price    ");
        System.out.println("----------------------------------------------");

        for (Product p : Product.getProduct()) {
            System.out.println(
            p.getSupplier() + "          " +
            p.getName() + "              " +
            p.getPrice() + "        " );
        }
    }
    
    
    // Method to find the supplier with the maximum number of products
   public String getSupplierWithMaxProducts() {
    if (Product.getProduct().isEmpty()) {
        return "No products available.";
    }

    // Count the products for each supplier
    Map<String, Integer> supplierProductCount = new HashMap<>();
    for (Product p : Product.getProduct()) {
        supplierProductCount.put(p.getSupplier(), 
            supplierProductCount.getOrDefault(p.getSupplier(), 0) + 1);
    }

    // Find the supplier with the maximum products
    String maxSupplier = null;
    int maxCount = 0;

    for (Map.Entry<String, Integer> entry : supplierProductCount.entrySet()) {
        if (entry.getValue() > maxCount) {
            maxSupplier = entry.getKey();
            maxCount = entry.getValue();
        }
    }

    return maxSupplier;
}
   


public static int getCustomerIdWithMaxRevenue() {
    int maxRevenueCustomerId = -1; // Default value for no customer found
    double maxRevenue = 0.0;

    for (Customer customer : Customer.customers) {
        double customerRevenue = 0.0;
        for (Order order : Order.allOrders) {
            if (order.getCustomerId() == customer.getId()) {
                customerRevenue += order.calculateTotal();
            }
        }
        if (customerRevenue > maxRevenue) {
            maxRevenue = customerRevenue;
            maxRevenueCustomerId = customer.getId();
        }
    }

    return maxRevenueCustomerId;
}
    

public String getSupplierWithMaxRevenue() {
    // Check if there are any products available
    if (Product.getProduct().isEmpty()) {
        return "No products available.";
    }

    Map<String, Double> supplierRevenue = new HashMap<>();

    // Calculate revenue for each product and aggregate by supplier
    for (Product product : Product.getProduct()) {
        double revenue = product.calculateRevenue();
        supplierRevenue.put(product.getSupplier(), 
            supplierRevenue.getOrDefault(product.getSupplier(), 0.0) + revenue);
    }

    String maxSupplier = null;
    double maxRevenue = 0; // Initialize to 0 to find the max correctly

    // Find the supplier with the maximum revenue
    for (Map.Entry<String, Double> entry : supplierRevenue.entrySet()) {
        if (entry.getValue() > maxRevenue) {
            maxSupplier = entry.getKey();
            maxRevenue = entry.getValue();
        }
    }

    // Return the supplier with the maximum revenue or a message if none found
    if (maxSupplier == null) {
        return "No suppliers found.";
    } else {
        return maxSupplier + " with revenue: " + maxRevenue;
    }
}


    @Override
    public String toString() {
        return "Admin{id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + "}";
    }
    
    @Override
    public boolean logIn(String username, String password) {
        for(Admin add : ad){
        if((username.equals(add.username) && password.equals(add.password))){
        return true;
        }
        }
        return false;
    }

    
}
    
    
    
    



