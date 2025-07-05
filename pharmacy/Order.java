package pharamacy;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author kan
 */
public class Order  implements Serializable{
    private int id;
    private double total;
    protected int cashierId;
    private int customerId;
    public static ArrayList<Product> orderList = new ArrayList<>(); 
    public static ArrayList<Order> allOrders = new ArrayList<>();

    public Order() {
        
    }

    public Order(int id, int cashierId, int customerId) {
        this.id = id;
        this.cashierId = cashierId;
        this.customerId = customerId;
    }
    
    public int getId() {
        return id;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Product> orderList) {
        this.orderList = orderList;
    }

    public static ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public static void setAllOrders(ArrayList<Order> allOrders) {
        Order.allOrders = allOrders;
    }
    
    public void addProduct(Product p) {
  
    boolean foundInStock = false;   // Check if the product is available in stock
    for (Product stockProduct : Product.product) {
        if (stockProduct.getId() == p.getId()) {
            foundInStock = true;
            
           
            if (stockProduct.quantity > 0) { 
                orderList.add(p);
                stockProduct.quantity--; 
                System.out.println("Product: " + p.getName() + " added to order. Remaining stock: " + stockProduct.quantity);
                
            }
             else
            {
                System.out.println("Product: " + p.getName() + " is out of stock.");
            }
            break;
        }
    }
    
    
    if (!foundInStock) {
        System.out.println("Product: " + p.getName() + " not found in stock.");
    }
}

   
    public void removeProduct(Product product) {
        if (orderList.remove(product)) {
            System.out.println("Product : " + product+" is removed from order ");
        } else {
            System.out.println("Product not found in order");
        }
    }

    
    public double calculateTotal() {
       total = 0;
        for (Product product : orderList) {
            total += product.getPrice();
        }
        return total;
    }

     public void displayOrder() {
        System.out.println("Order ID: " + id);
        for (Product product : orderList) {
            System.out.println("Product Name: " + product.getName() + ", Price: " + product.getPrice() + ", Quantity: " + 1); 
        }
        System.out.println("Total: " + calculateTotal());
    }
    
}