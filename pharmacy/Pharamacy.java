/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package pharamacy;

import java.io.*;
import java.util.*;
import java.util.InputMismatchException;

public class Pharamacy {
    
    private static Admin admin; 
    public static Cashier cashier;
    private static Scanner input = new Scanner(System.in);
    

    public static void main(String[] args) throws ClassNotFoundException {
        readAdminFromFile("Admins.dat");
        readCashierFromFile("Cashier.dat");
        readCustomerFromFile("Customer.dat");
        readProductFromFile("Product.dat");
        
        if (Admin.ad.isEmpty()) {
            System.out.println("No admin found, creating default admin...");
            Admin newadmin = new Admin("admin123","adminpass",1);
            Admin.ad.add(newadmin);
        }

        admin = Admin.ad.get(0);
        boolean isLoggedIn = false;

        do {
            System.out.print("Enter username: ");
            String user = input.nextLine();

            System.out.print("Enter password: ");
            String pass = input.nextLine();

            // Check login
            if (admin.logIn(user, pass)) {
                System.out.println("Admin logged in.");
                showMenu(); 
                isLoggedIn = true; 
            } 
            
            else {
                System.out.println("Invalid username or password.");
                System.out.print("Do you want to try again? (y/n): ");
                String choice = input.nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("Exiting login process.");
                    break; // Exit the loop if the user chooses not to retry
                }
            }
        } while (!isLoggedIn);  
        
    }

    // Show menu with options
    public static void showMenu() {
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Admin");
            System.out.println("2. Delete Admin");
            System.out.println("3. Edit Admin");
            System.out.println("4. List Admins");
            System.out.println("5. Add Cashier");
            System.out.println("6. Delete Cashier");
            System.out.println("7. Edit Cashier");
            System.out.println("8. List Cashiers");
            System.out.println("9. Add Customer");
            System.out.println("10. Delete Customer");
            System.out.println("11. Edit Customers");
            System.out.println("12. List Customers");
            System.out.println("13. Add product");
            System.out.println("14. Delete Product");
            System.out.println("15. Edit product");
            System.out.println("16. List Products");
            System.out.println("17. List supplier and pricing");
            System.out.println("18. supplier with max orders");
            System.out.println("19. customer with max revenue");
            System.out.println("20. supplier with max revenue");
            System.out.println("21. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();  

            switch (choice) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    deleteAdmin();
                    break;
                case 3:
                    editAdmin();
                    break;
                case 4:
                    listAdmins();
                    break;
                case 5:
                    addCashier();
                    break;
                case 6:
                    deleteCashier();
                    break;
                case 7:
                    editCashier();
                
                case 8:
                    listCashiers();
                    break;
                case 9:
                    addCustomers();
                    break;
                case 10:
                    deleteCustomers();
                    break;
                case 11:
                    editCustomers();
                case 12:
                    listCustomers();
                    break;
                case 13:
                    addProduct();
                    break;
                case 14:
                    deleteProducts();
                    break;
                case 15:
                    editProducts();
                    break;
                case 16:
                    listProducts();
                    break;
                case 17:
                    admin.viewSuppliersAndPricing();
                    break;
                    
                case 18:
                    String topSupplier = admin.getSupplierWithMaxProducts();
                    if (topSupplier != null) {
                    System.out.println("Supplier with the maximum number of products: " + topSupplier);
                } else {
                    System.out.println("No suppliers found.");
                }
                break;
                
                case 19:
                    int maxCustomer = admin.getCustomerIdWithMaxRevenue();
                    System.out.println("Customer with max revenue is: " + maxCustomer);
                    break;
                    
                case 20:
                String topRevenueSupplier = admin.getSupplierWithMaxRevenue();
                    if (topRevenueSupplier != null && !topRevenueSupplier.isEmpty()) {
                    System.out.println("Supplier with the maximum revenue: " + topRevenueSupplier);
                    } else {
                     System.out.println("No suppliers found or no products available.");
                      }
                   break;
                case 21:
                    saveAdminToFile("Admins.dat");
                    saveCashierToFile("Cashier.dat");
                    saveCustomerToFile("Customer.dat");
                    saveProductToFile("Product.dat");
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    
    public static void saveAdminToFile(String fileName) {
        try (ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream(fileName))) {
                fileobj.writeObject(Admin.ad);  // Assuming Admin has a meaningful toString() method   
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void readAdminFromFile(String fileName) throws ClassNotFoundException {
       
        try (ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(fileName))) {
               Admin.ad = (ArrayList<Admin>)fileobj.readObject();  // Assuming Admin has a meaningful toString() method
        System.out.print("size = " + Admin.ad.size());
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void saveCashierToFile(String fileName) {
        try (ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream(fileName))) {
                fileobj.writeObject(Cashier.ca);  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void readCashierFromFile(String fileName) throws ClassNotFoundException {
        try (ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(fileName))) {
               Cashier.ca = (ArrayList<Cashier>)fileobj.readObject();  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void saveCustomerToFile(String fileName) {
        try (ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream(fileName))) {
                fileobj.writeObject(Customer.customers);  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void readCustomerFromFile(String fileName) throws ClassNotFoundException {
        try (ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(fileName))) {
               Customer.customers = (ArrayList<Customer>)fileobj.readObject();  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    
    public static void saveProductToFile(String fileName) {
        try (ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream(fileName))) {
                fileobj.writeObject(Product.product);  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void readProductFromFile(String fileName) throws ClassNotFoundException {
        try (ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(fileName))) {
               Product.product = (ArrayList<Product>)fileobj.readObject();  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    public static void saveOrderToFile(String fileName) {
        try (ObjectOutputStream fileobj = new ObjectOutputStream(new FileOutputStream(fileName))) {
                fileobj.writeObject(Order.allOrders);  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void readOrderFromFile(String fileName) throws ClassNotFoundException {
        try (ObjectInputStream fileobj = new ObjectInputStream(new FileInputStream(fileName))) {
               Order.allOrders = (ArrayList<Order>)fileobj.readObject();  // Assuming Admin has a meaningful toString() method
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }
    
    public static void addAdmin() {
        try {
            System.out.print("Enter admin username: ");
            String username = input.nextLine();
            System.out.print("Enter admin password: ");
            String password = input.nextLine();
            System.out.print("Enter admin id: ");
            int id = input.nextInt();
            input.nextLine();  

            Admin newAdmin = new Admin(username, password, id);
            admin.addAdmin(newAdmin);
            System.out.println("Size = " + Admin.ad.size());
            

            System.out.println("Admin added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); // Clear the buffer
        }
    }

   
    public static void deleteAdmin() {
        try {
            System.out.print("Enter admin id to delete: ");
            int id = input.nextInt();
            input.nextLine();  // Consume the newline
            admin.deleteAdmin(id);
            System.out.println("Admin deleted successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); // Clear the buffer
        }
    }

   
    public static void editAdmin() {
        try {
            System.out.print("Enter admin id to edit: ");
            int id = input.nextInt();
            input.nextLine();  // Consume the newline
            System.out.print("Enter new username: ");
            String username = input.nextLine();
            System.out.print("Enter new password: ");
            String password = input.nextLine();

            admin.editadmin(id, username, password);
            System.out.println("Admin updated successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }

    
    public static void listAdmins() {
        admin.listadmin();
    }

    
    public static void addCashier() {
        try {
            System.out.print("Enter cashier username: ");
            String username = input.nextLine();
            System.out.print("Enter cashier password: ");
            String password = input.nextLine();
            System.out.print("Enter cashier id: ");
            int id = input.nextInt();
            input.nextLine();  

            Cashier newCashier = new Cashier(username, password, id);
            admin.addcashier(newCashier);

            System.out.println("Cashier added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }

    
    public static void deleteCashier() {
        try {
            System.out.print("Enter cashier id to delete: ");
            int id = input.nextInt();
            input.nextLine(); 
            admin.deletecashier(id);
            System.out.println("Cashier deleted successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }
    
     public static void editCashier() {
        try {
            System.out.print("Enter cashier id to edit: ");
            int id = input.nextInt();
            input.nextLine();  
            System.out.print("Enter new username: ");
            String username = input.nextLine();
            System.out.print("Enter new password: ");
            String password = input.nextLine();

            admin.editcashier(username, password, id);
             System.out.println("Cashier updated successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }

        public static void listCashiers() {
        admin.listcashier();
    }

    
    public static void addCustomers() {
        try {
            System.out.print("Enter customer username: ");
            String username = input.nextLine();
            System.out.print("Enter customer password: ");
            String password = input.nextLine();
            System.out.print("Enter customer id: ");
            int id = input.nextInt();
            input.nextLine();  

            Customer newCustomer = new Customer(username, password, id);
            admin.addCustomer(newCustomer);

            System.out.println("Customer added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }

    
    public static void deleteCustomers() {
        try {
            System.out.print("Enter customer id to delete: ");
            int id = input.nextInt();
            input.nextLine();  
            admin.deletecashier(id);
            System.out.println("Customer deleted successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }
    
    public static void editCustomers() {
        try{
        System.out.print("Enter customer id to edit: ");
        int id = input.nextInt();
        input.nextLine();  
        System.out.print("Enter new username: ");
        String username = input.nextLine();
        System.out.print("Enter new password: ");
        String password = input.nextLine();

        admin.editCustomer(id, username, password);
        System.out.println("Customer updated successfully");

        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
        
    }


    public static void listCustomers() {
        admin.listCustomer();
    }

    
    public static void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String pname = input.nextLine();
            System.out.print("Enter product supplier: ");
            String supplier = input.nextLine();
            System.out.print("Enter product id: ");
            int id = input.nextInt();
            System.out.print("Enter product quatity: ");
            int q = input.nextInt();
            System.out.print("Enter product price: ");
            double pprice= input.nextDouble();
            input.nextLine();  

            Product p = new Product(id,pname,pprice,q,supplier);
            admin.addProduct(p);

            System.out.println("Customer added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }

    
    public static void deleteProducts() {
        try {
            System.out.print("Enter product id to delete: ");
            int id = input.nextInt();
            input.nextLine();  
            admin.deleteProduct(id);
            System.out.println("product deleted successfully");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
    }
    
    public static void editProducts() {
        try{
        System.out.print("Enter product id to edit: ");
        int id = input.nextInt();
        input.nextLine();  
        System.out.print("Enter new price: ");
        double price = input.nextDouble();
        admin.editProduct(id, price);
        System.out.println("Cashier updated successfully");

        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for ID.");
            input.nextLine(); 
        }
        
    }

    public static void listProducts() {
        admin.listProduct();
    }
    
    
}
