/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pharamacy;
import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author kan
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Customer extends User implements Serializable{
    private double revenue;
    public static ArrayList<Customer> customers = new ArrayList<>();
    

    public Customer(String username, String password,int id) {
        super(username,password,id);
        this.revenue = 0.0;
    }

    public void rateOrder(int rate) {
        switch (rate) {
            case 1:
                System.out.println("You rated the order 1/5 (Very bad)");
                break;
            case 2:
                System.out.println("You rated the order 2/5 (Bad)");
                break;
            case 3:
                System.out.println("You rated the order 3/5 (Average)");
                break;
            case 4:
                System.out.println("You rated the order 4/5 (Good)");
                break;
            case 5:
                System.out.println("You rated the order 5/5 (Excellent)");
                break;
            default:
                System.out.println("Invalid rating. Please rate between 1 and 5.");
        }
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void viewOrderHistory(int custid) {
        boolean hasOrders = false;

        for (Order order : Order.allOrders) { 
            if (order.getCustomerId() == custid) {
                hasOrders = true;
                System.out.println("Order ID: " + order.getId());
                for (Product product : order.getOrderList()) {
                    System.out.println("Product Name: " + product.getName() + ", Price: " + product.getPrice());
                }
                System.out.println("Total: " + order.calculateTotal());
                System.out.println("-------------------");
            }
        }

        if (!hasOrders) {
            System.out.println("No orders found for Customer ID: " + custid);
        }
    }
    
    
   
    
    @Override
    public boolean logIn(String username, String password) {
    for(Customer cust : customers){
        if((username.equals(cust.username) && password.equals(cust.password))){
        return true;
        }
        }
        return false;   
    }
    
   @Override
    public String toString() {
        return "Customer{id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + "}";
    }
    
    
    

}


