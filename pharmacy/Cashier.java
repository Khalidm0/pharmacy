package pharamacy;

import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author kan
 */
public class Cashier extends User implements Serializable {
    private double revenue;
    public Order o;          // Keeps track of a current order
    public Product p;        // Keeps track of a product (though not used in current logic)
    public int counter;      // Keeps track of number of orders processed by this cashier
    protected static ArrayList<Cashier> ca = new ArrayList<>();
    
    // Constructor with parameters for username, password, and id
    public Cashier(String username, String password, int id) {
        super(username, password, id);
    }

    // Default constructor
    public Cashier() {
    }

    public double getRevenue() {
        return revenue;
    }

    @Override
    public boolean logIn(String username, String password) {
        // Checks if the cashier's username and password match the input
        for (Cashier cashier : ca) {
            if ((username.equals(cashier.username) && password.equals(cashier.password))) {
                return true;
            }
        }
        return false;  
    }

    @Override
    public String toString() {
        return "Cashier(username=" + username + ", password=" + password + ")";
    }

    public void addOrder(Order or) {
        Order.allOrders.add(or); // Add order to the global order list
    }

    public void cancel(Order orderToRemove) {
        if(Order.allOrders.remove(orderToRemove)) {
            System.out.println("Order is cleared");
        } else {
            System.out.println("Order not found"); // Inform if the order couldn't be removed
        }
    }

    public void createOrder() {
        // Create and add a new Order instance to the order list
        Order o = new Order();
        addOrder(o);
    }

    public void cashierOrders() {
        // Reset counter
        this.counter = 0;

        // Count orders handled by this cashier
        for (Order order : Order.allOrders) {
            if (order.getCashierId() == this.id) {
                this.counter++;
            }
        }
    }

    public Cashier cashierMax() {
        // Return cashier with the maximum order count
        Cashier max = ca.get(0);
        for (Cashier c : ca) {
            if (max.counter < c.counter) {
                max = c; // Update to the cashier with greater order count
            }
        }
        return max;
    }

    public double payment(Order or) {
        double pay = 0;
        for (Order o : Order.allOrders) {
            if (or.getId() == o.getId()) {
                for (Product c : o.getOrderList()) {
                    pay += c.getQuantity() * c.getPrice(); // Calculate payment for the order
                }
            }
        }
        return pay;
    }

    public void calculateRevenue() {
        revenue = 0; // Reset revenue before calculation
        for (Order order : Order.allOrders) {
            if (order.getCashierId() == this.id) { // Calculate only for this cashier's orders
                for (Product product : order.getOrderList()) {
                    revenue += product.getPrice() * product.getQuantity(); // Calculate revenue
                }
            }
        }
    }

    public static Cashier getCashierWithMaxRevenue() {
        Cashier maxRevenueCashier = null;
        double maxRevenue = 0;

        for (Cashier cashier : ca) {
            cashier.calculateRevenue(); // Calculate revenue for each cashier
            if (cashier.getRevenue() > maxRevenue) {
                maxRevenue = cashier.getRevenue();
                maxRevenueCashier = cashier; // Update max revenue cashier
            }
        }
        return maxRevenueCashier; // Return the cashier with maximum revenue
    }
}