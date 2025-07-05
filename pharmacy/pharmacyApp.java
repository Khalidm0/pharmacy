/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package pharamacy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import static pharamacy.Admin.ad;

/**
 *
 * @author kan
 */
public class pharmacyApp extends Application {
    
    
    Admin newAdmin = new Admin("admin123", "adminpass", 1);
   Cashier newCashier = new Cashier("cashier123", "cashierpass", 2);
    Customer newCustomer = new Customer("customer123", "customerpass", 3);

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
       readAdminFromFile("Admins.dat");
       readCashierFromFile("Cashier.dat");
       readCustomerFromFile("Customer.dat");
       readProductFromFile("Product.dat");

       
       ///khalid start
        Label l1 = new Label("You logged in as a customer.");
        Label l2 = new Label("Click to view order history: ");
        Label l3 = new Label("Click to rate your order: ");

        Button view = new Button("View Order History");
        Button rate = new Button("Rate Us");

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(l1, l2, view, l3, rate);
        mainLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene mainScene = new Scene(mainLayout, 800, 300);

        // Ahmed
        Label cust = new Label("Enter your id");
        TextField custid = new TextField();
        Button custOk = new Button("Ok");
        VBox customer = new VBox(10);
        customer.getChildren().addAll(cust,custid,custOk);
        Scene viewCustomerScene = new Scene(customer,350,350);
        customer.setStyle("-fx-padding: 20; -fx-alignment: center;");

       
        Label HistoryLabel = new Label("Order History:");
        Button returnToMain1 = new Button("Return");
        VBox orderHistoryLayout = new VBox(15);
        orderHistoryLayout.getChildren().addAll(HistoryLabel, returnToMain1);
        orderHistoryLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene orderHistoryScene = new Scene(orderHistoryLayout, 400, 200);

       
        Label rateLabel = new Label("Rate Your Experience:");
        final ToggleGroup ratingGroup = new ToggleGroup();
        RadioButton rb1 = new RadioButton("1");
        rb1.setToggleGroup(ratingGroup);
        RadioButton rb2 = new RadioButton("2");
        rb2.setToggleGroup(ratingGroup);
        RadioButton rb3 = new RadioButton("3");
        rb3.setToggleGroup(ratingGroup);

        Button submitRating = new Button("Submit Rating");
        Button returnToMain2 = new Button("Return");

        VBox ratingLayout = new VBox(15);
        ratingLayout.getChildren().addAll(rateLabel, rb1, rb2, rb3, submitRating, returnToMain2);
        ratingLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        Scene ratingScene= new Scene(ratingLayout, 400, 300);

        

        view.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) { 
                 primaryStage.setScene(viewCustomerScene);
            } 
        });

        custOk.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) { 
                String c = custid.getText();
                int cid = Integer.parseInt(c);
                newCustomer.viewOrderHistory(cid);
                primaryStage.setScene(orderHistoryScene);
            } 
        });
        
        rate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                
            primaryStage.setScene(ratingScene);
     
            }
        });
        
        returnToMain1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                
            primaryStage.setScene(mainScene);
     
            }
        });

         returnToMain2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                
            primaryStage.setScene(mainScene);
     
            }
        });


       submitRating.setOnAction(new EventHandler<ActionEvent>() {
       @Override
         public void handle(ActionEvent e) {
        RadioButton selected = (RadioButton) ratingGroup.getSelectedToggle();
        if (selected != null) {
            System.out.println("You rated: " + selected.getText());
        } else {
            System.out.println("No rating selected.");
        }
       }
     });

      ///khalid end
      
        
       // mahmoud start

        // Cashier scene layout
        GridPane pane2 = new GridPane();
        pane2.setAlignment(Pos.CENTER);
        pane2.setPadding(new Insets(15));
        pane2.setHgap(10);
        pane2.setVgap(10);

        // Add components to the GridPane for the cashier scene
        Label lblProductName = new Label("Product Name:");
        TextField txtProductName = new TextField();
        Label lblCalculate = new Label("Calculate:");
        TextField txtCalculate = new TextField();
        Button btnCreateOrder = new Button("Create Order");
        Button btnCalculate = new Button("Calculate");
        Button btnRemove = new Button("Remove");
        VBox m=new VBox(20);
        m.getChildren().addAll(btnCreateOrder,btnCalculate,btnRemove,lblProductName,lblCalculate);
        Scene scene = new Scene(m,200,250);
        pane2.add(lblProductName, 0, 0);
        pane2.add(txtProductName, 1, 0);
        pane2.add(lblCalculate, 0, 1);
        pane2.add(txtCalculate, 1, 1);
        pane2.add(btnCreateOrder, 0, 2);
        pane2.add(btnCalculate, 1, 2);
        pane2.add(btnRemove, 0, 3);

        
        
        // Button actions for cashier scene
        btnCreateOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Create Order clicked");
                newCashier.createOrder(); // Add logic for create order
            }
        });

       btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Calculate clicked");

        // Assuming you have an order object (or you can retrieve it from the selected order)
        Order orderToCalculate = null; // Fetch or get the order you want to calculate payment for

        if (orderToCalculate != null) {
            // Call the payment method to calculate the payment for this order
            double calculatedPayment = newCashier.payment(orderToCalculate);

            // Set the calculated payment in the txtCalculate text field
            txtCalculate.setText(String.format("%.2f", calculatedPayment)); // Display the payment formatted to 2 decimal places
        } else {
            System.out.println("No order selected to calculate payment");
            txtCalculate.setText("No order selected");
        }
    }
});


        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // When Remove button is clicked, remove the order
                // First, let's assume you have a way to get the order to remove
                Order orderToRemove = null; // Get the order to remove (you can fetch from list, etc.)
                if (orderToRemove != null) {
                    newCashier.cancel(orderToRemove);  // Call the cancel method from Cashier to remove the order
                } else {
                    System.out.println("No order selected to remove");
                }
            }
        });

        // VBox for secondary layout with payment options
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Button btnCash = new Button("Cash");
        Button btnVisa = new Button("Visa");
        Button btnCancelOrder = new Button("Cancel Order");

        vbox.getChildren().addAll(btnCash, btnVisa, btnCancelOrder);

        Scene vboxScene = new Scene(vbox, 300, 200);

        // Action for btnCash (switch to VBox layout)
        btnCash.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(vboxScene);
            }
        });

        // Create the cashier scene
        Scene cashierScene = new Scene(pane2, 500, 400);
        //primaryStage.setScene(cashierScene);
    
        
       // mahmoud end 
       
       
       GridPane pane = new GridPane();
       pane.setAlignment(Pos.CENTER);
       pane.setPadding(new Insets(11.5,2.5,13.5,14.5));
       pane.setHgap(5.5);
       pane.setVgap(5.5);
       
       TextField user = new TextField();
       TextField pass = new PasswordField();

       pane.add(new Label("username"),0,0);
       pane.add(user, 1, 0);
       pane.add(new Label("password"), 0, 1);
       pane.add(pass, 1, 1);
       Button log = new Button("Log in");
       pane.add(log,1,3);
       
       GridPane.setHalignment(log, HPos.RIGHT);
       
       Scene logScene = new Scene(pane,250,250);  // this scene for username, password, log in
      
       Button museradmin = new Button("Managing Users (admins)");
       Button musercashier = new Button("Managing Users (cashiers)");
       Button musercustomer = new Button("Managing Users (customers)");
       Button mproduct = new Button("Managing Products");
       Button viewuser = new Button("View Reports (Users)");
       Button viewproduct = new Button("View Reports (Products)");
       Button vieworder = new Button("View Reports (Orders)");
       
       VBox root2 = new VBox(15);
       root2.getChildren().addAll(museradmin,musercashier,musercustomer,mproduct,viewuser,viewproduct,vieworder);
       Scene adminScene = new Scene(root2,350,350);  // this scene log in as admin
       
       log.setOnAction(new EventHandler<ActionEvent>(){
           
           @Override
           public void handle(ActionEvent t) {
               
               String result = user.getText();
               String password =  pass.getText();
                             
               if(newAdmin.logIn(result, password)){
                System.out.println("Admin logged in.");
                   primaryStage.setScene(adminScene);
               }
               
               else if(newCashier.logIn(result, password)){
                   System.out.println("Cashier logged in.");
                   primaryStage.setScene(cashierScene);
               }
               
               else if(newCustomer.logIn(result, password)){
                   System.out.println("Customer logged in.");
                   primaryStage.setScene(mainScene);
               }
               
               else 
                    System.out.println("Invalid username or password. Try again.");               
           }
       });
       
        Button addadmin = new Button("Add admin");
        Button deleteadmin = new Button("Delete admin");
        Button editadmin = new Button("Edit admin");
        Button listadmin = new Button("List admins");
        Button backToAdminScene = new Button("Back");
        
             
        VBox root3 = new VBox(15);
        root3.getChildren().addAll(addadmin,deleteadmin,editadmin,listadmin,backToAdminScene);
        
        Scene mUserScene = new Scene(root3,350,350);  // this scene manage users
        museradmin.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
             
             primaryStage.setScene(mUserScene);
           }
       });
       
        backToAdminScene.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
             
             primaryStage.setScene(adminScene);
           }
       });
        
       // GridPane for adding admin
       Label labelFeedback = new Label(); // Create a feedback label
       labelFeedback.setTextFill(Color.RED); // Set the text color to indicate an error
       
       GridPane Addpane = new GridPane();
       Addpane.setAlignment(Pos.CENTER);
       Addpane.setPadding(new Insets(11.5,2.5,13.5,14.5));
       Addpane.setHgap(5.5);
       Addpane.setVgap(5.5);

       TextField auser = new TextField();
       TextField apass = new PasswordField();
       TextField id = new TextField();

       Addpane.add(new Label("username:"),0,0);
       Addpane.add(auser, 1, 0);
       Addpane.add(new Label("password:"), 0, 1);
       Addpane.add(apass, 1, 1);
       Addpane.add(new Label("id:"), 0, 2);
       Addpane.add(id, 1, 2);
       Button save1 = new Button("save");
       Addpane.add(save1,1,3);
       Button back1 = new Button("Back");
       Addpane.add(back1,1,3);

       Addpane.add(labelFeedback, 0, 4, 2, 1); // Adjust the GridPane to include this label
       GridPane.setHalignment(save1, HPos.RIGHT);
       GridPane.setHalignment(back1, HPos.LEFT);

// Create the scene only once
       Scene addAdminScene = new Scene(Addpane,350,350);
       addadmin.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(addAdminScene);
           }
       });


       save1.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent t) {
        String idd = id.getText();
        try {
            int x = Integer.parseInt(idd);
            Admin admin = new Admin(auser.getText(), apass.getText(), x);
            newAdmin.addAdmin(admin);
            labelFeedback.setText("Admin added successfully."); // Success feedback
            labelFeedback.setStyle("-fx-text-fill: green;"); // Change text color to 
            
            auser.clear();
            apass.clear();
            id.clear();
        } catch (NumberFormatException e) {
            labelFeedback.setText("Invalid ID format. Please enter a valid number."); // Error feedback
        }
    }
});


       back1.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
              primaryStage.setScene(mUserScene);
           }
       });

        // delete admin
        Label labelFeedbackDelete = new Label(); // Create a feedback label
        labelFeedbackDelete.setTextFill(Color.RED); // Set the text color to indicate an error
       
        Label deletelabel = new Label("Enter admin id you want to delete");
        TextField deletedId = new TextField();
        Button deleteAdminSave = new Button("Save");
        Button deleteAdminBack = new Button("Back"); 
        
        VBox deleteAdmin = new VBox(15);
        deleteAdmin.getChildren().addAll(deletelabel,deletedId,deleteAdminSave,deleteAdminBack,labelFeedbackDelete);
        
        Scene deleteAdminScene = new Scene(deleteAdmin,350,350);
        
        deleteadmin.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(deleteAdminScene);
           }
       });
        
        deleteAdminSave.setOnAction(new EventHandler<ActionEvent>(){
    @Override
    public void handle(ActionEvent t) {
        String did = deletedId.getText();
        try {
            int v = Integer.parseInt(did);
            if (newAdmin.adminExists(v)) {
             newAdmin.deleteAdmin(v);
              labelFeedbackDelete.setText("Admin deleted successfully."); // Success feedback
             labelFeedbackDelete.setStyle("-fx-text-fill: green;"); // Change text color to 
            }

            else {
            labelFeedbackDelete.setText("Sorry, admin not found."); // Admin not found feedback
            labelFeedbackDelete.setStyle("-fx-text-fill: red;"); // Change text color to red
            }
            
           deletedId.clear();
           
        } catch (NumberFormatException e) {
            labelFeedbackDelete.setText("Invalid ID format. Please enter a valid number."); // Error feedback
        }
    }
});
        
        deleteAdminBack.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(mUserScene);
           }
       });
        
       //edit admin
       
       Label labelFeedbackEdit = new Label(); // Create a feedback label
       labelFeedbackEdit.setTextFill(Color.RED); // Set the text color to indicate an error
        
       GridPane Editpane = new GridPane();
       Editpane.setAlignment(Pos.CENTER);
       Editpane.setPadding(new Insets(11.5,2.5,13.5,14.5));
       Editpane.setHgap(5.5);
       Editpane.setVgap(5.5);
       
       TextField edituser = new TextField();
       TextField editpass = new PasswordField();
       TextField editid = new TextField();
       
       Editpane.add(new Label("Enter admin id:"),0,0);
       Editpane.add(editid, 1, 0);
       
       Editpane.add(new Label("new username:"),0,1);
       Editpane.add(edituser, 1, 1);
       
       Editpane.add(new Label("new password:"), 0, 3);
       Editpane.add(editpass, 1, 3);
       
       Button editAdminsave = new Button("save");
       Editpane.add(editAdminsave,1,4);
       
       Button editAdminback = new Button("Back");
       Editpane.add(editAdminback,1,4);
       
       Editpane.add(labelFeedbackEdit,0,5);

       
       GridPane.setHalignment(editAdminsave, HPos.RIGHT);
       GridPane.setHalignment(editAdminback, HPos.LEFT);
        
        Scene editAdminScene = new Scene(Editpane,350,350);
        
        editadmin.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(editAdminScene);
           }
       });
        
        editAdminsave.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent t) {
        String did = editid.getText();
        try {
            int v = Integer.parseInt(did);
            // Assume this method checks if the admin exists by ID
            if (newAdmin.adminExists(v)) {
                newAdmin.editadmin(v, edituser.getText(), editpass.getText());
                labelFeedbackEdit.setText("Admin edited successfully."); // Success feedback
                labelFeedbackEdit.setStyle("-fx-text-fill: green;"); // Change text color to green
            } else {
                labelFeedbackEdit.setText("Sorry, admin not found."); // Admin not found feedback
                labelFeedbackEdit.setStyle("-fx-text-fill: red;"); // Change text color to red
            }
            
            editid.clear();
            edituser.clear();
            editpass.clear();
            
        } catch (NumberFormatException e) {
            labelFeedbackEdit.setText("Invalid ID format. Please enter a valid number."); // Error feedback
            labelFeedbackEdit.setStyle("-fx-text-fill: red;"); // Change text color to red
        }
    }
});
        
        editAdminback.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
            primaryStage.setScene(adminScene);
           }
       });
       
        
        // list admin
       Button backListAdmin = new Button("Back");
       VBox listAdminLayout = new VBox(15);
       listAdminLayout.setPadding(new Insets(10));

       Label listAdminLabel = new Label("List of Admins:");
       VBox adminList = new VBox(10);

       listAdminLayout.getChildren().addAll(listAdminLabel, adminList, backListAdmin);

       Scene listAdminScene = new Scene(listAdminLayout, 350, 350);

       listadmin.setOnAction(new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent t) {
        adminList.getChildren().clear(); // Clear any previous data

        if (Admin.ad == null || Admin.ad.isEmpty()) {
            adminList.getChildren().add(new Label("No admins available."));
        } else {
            for (Admin admin : Admin.ad) {
                Label adminInfo = new Label("ID: " + admin.getId() + ", Username: " + admin.getUsername());
                adminList.getChildren().add(adminInfo);
            }
        }
       
        primaryStage.setScene(listAdminScene);
       }
       });
        
       backListAdmin.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
            primaryStage.setScene(adminScene);
           }
       });
       
       // managing products
       Button addProduct = new Button("Add Product");
       Button deleteProduct = new Button("Delete Product");
       Button editProduct = new Button("Edit Product");
       Button listProduct = new Button("List Products");
       Button productBack = new Button("back");
       
       VBox manageproduct = new VBox(15);
       manageproduct.getChildren().addAll(addProduct,deleteProduct,editProduct,listProduct,productBack);
       
       Scene ProductScene = new Scene(manageproduct,350,350);
       
       mproduct.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
             primaryStage.setScene(ProductScene);
           }
       });
       
       productBack.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
             
             primaryStage.setScene(adminScene);
           }
       });
       
       // Add product
       GridPane AddProductpane = new GridPane();
       AddProductpane.setAlignment(Pos.CENTER);
       AddProductpane.setPadding(new Insets(11.5,2.5,13.5,14.5));
       AddProductpane.setHgap(5.5);
       AddProductpane.setVgap(5.5);
       
       TextField pid = new TextField();
       TextField pname = new TextField();
       TextField price = new TextField();
       TextField quantity = new TextField();
       TextField supplier = new TextField();

       AddProductpane.add(new Label("Product id:"), 0, 0);
       AddProductpane.add(pid, 1, 0);
       
       AddProductpane.add(new Label("Product name:"), 0, 1);
       AddProductpane.add(pname, 1, 1);
       
       AddProductpane.add(new Label("Product price:"), 0, 2);
       AddProductpane.add(price, 1, 2);
       
       AddProductpane.add(new Label("Product quantity:"), 0, 3);
       AddProductpane.add(quantity, 1, 3);
       
       AddProductpane.add(new Label("Product supplier:"), 0, 4);
       AddProductpane.add(supplier, 1, 4);
       
       Button productsave = new Button("Save");
       AddProductpane.add(productsave, 1, 5);
       Button productback = new Button("Back");
       AddProductpane.add(productback, 1, 5);
       
       GridPane.setHalignment(productsave, HPos.RIGHT);
       GridPane.setHalignment(productback, HPos.LEFT);
       
       Scene addProductScene = new Scene(AddProductpane,400,400);
       
       addProduct.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               
             primaryStage.setScene(addProductScene);
           }
       });
       
       productsave.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
             String productid = pid.getText();
               int v = Integer.parseInt(productid);
               
               String pprice = price.getText();
               double p = Double.parseDouble(pprice);
               
               String pquantity = quantity.getText();
               int q = Integer.parseInt(pquantity);
               
               Product pro = new Product(v,pname.getText(),p,q,supplier.getText());
               newAdmin.addProduct(pro);
           }
       });
       

        productback.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(ProductScene);
           }
       });
       
        // delete product
        Label deleteProductLabel = new Label("Enter product id you want to delete");
        TextField deletedProductId = new TextField();
        Button deleteProductSave = new Button("Save");
        Button deleteProductBack = new Button("Back"); 
        
        VBox deleteproduct = new VBox(15);
        deleteproduct.getChildren().addAll(deleteProductLabel,deletedProductId,deleteProductSave,deleteProductBack);
        
        Scene deleteProductScene = new Scene(deleteproduct,350,350);
        
        deleteProduct.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(deleteProductScene);
           }
       });
        
        deleteProductSave.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               String pid = deletedProductId.getText();
               int v = Integer.parseInt(pid);
               newAdmin.deleteProduct(v);
           }
       });
        
        deleteProductBack.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
            primaryStage.setScene(ProductScene);
           }
       });
        
        //edit product
        
       GridPane EditProductpane = new GridPane();
       EditProductpane.setAlignment(Pos.CENTER);
       EditProductpane.setPadding(new Insets(11.5,2.5,13.5,14.5));
       EditProductpane.setHgap(5.5);
       EditProductpane.setVgap(5.5);
       
       TextField editproductid = new TextField();
       TextField editproductprise = new TextField();
       
       EditProductpane.add(new Label("Enter product id:"),0,0);
       EditProductpane.add(editproductid, 1, 0);
       
       EditProductpane.add(new Label("new price:"),0,1);
       EditProductpane.add(editproductprise, 1, 1);
       
       Button editProductsave = new Button("save");
       EditProductpane.add(editProductsave,1,3);
       
       Button editProductback = new Button("Back");
       EditProductpane.add(editProductback,1,3);
       
       GridPane.setHalignment(editProductsave, HPos.RIGHT);
       GridPane.setHalignment(editProductback, HPos.LEFT);
       
       Scene editProductScene = new Scene(EditProductpane,350,350);
       
       editProduct.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               primaryStage.setScene(editProductScene);
           }
       });
        
       editProductsave.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
               String pid = editproductid.getText();
               int v = Integer.parseInt(pid);
               
               String pri = editproductprise.getText();
               double p = Double.parseDouble(pri);
               
               newAdmin.editProduct(v, p);
           }
       });
       
      editProductback.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
            primaryStage.setScene(ProductScene);
           }
       });
       
      // list product
       Button backListProduct = new Button("Back");
       VBox listProductLayout = new VBox(15);
       listProductLayout.setPadding(new Insets(10));

       Label listProductLabel = new Label("List of Admins:");
       VBox productList = new VBox(10);

       listProductLayout.getChildren().addAll(listProductLabel, productList, backListProduct);

       Scene listProductScene = new Scene(listProductLayout, 350, 350);

       listProduct.setOnAction(new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent t) {
        productList.getChildren().clear(); // Clear any previous data

        if (Product.product == null ||Product.product.isEmpty()) {
            productList.getChildren().add(new Label("No products available."));
        } else {
            for (Product pro : Product.product) {
                Label productInfo = new Label("ID: " + pro.getId() + ", name: " + pro.getName() + ", price: " + pro.getPrice());
                productList.getChildren().add(productInfo);
            }
        }
        primaryStage.setScene(listProductScene);
    }
    });
       
       backListProduct.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent t) {
            primaryStage.setScene(ProductScene);
           }
       });
       
       
       //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
       // Manage customer
    Button addCustomer = new Button("Add Customer");
    Button deleteCustomer = new Button("Delete Customer");
    Button editCustomer = new Button("Edit Customer");
    Button listCustomer = new Button("List Customers");
    Button backToAdmin = new Button("Back");

    VBox rootCustomer = new VBox(15);
    rootCustomer.getChildren().addAll(addCustomer, deleteCustomer, editCustomer, listCustomer,backToAdmin);

    Scene manageCustomerScene = new Scene(rootCustomer, 350, 350); // This scene manages customers
    musercustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
        primaryStage.setScene(manageCustomerScene);
    }
        
    });

    backToAdmin.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
        primaryStage.setScene(adminScene);
    }
        
    });
    
// Add customer
    GridPane addCustomerPane = new GridPane();
    addCustomerPane.setAlignment(Pos.CENTER);
    addCustomerPane.setPadding(new Insets(11.5, 2.5, 13.5, 14.5));
    addCustomerPane.setHgap(5.5);
    addCustomerPane.setVgap(5.5);

    TextField cuser = new TextField();
    TextField cpass = new PasswordField();
    TextField cid = new TextField();

    addCustomerPane.add(new Label("Username:"), 0, 0);
    addCustomerPane.add(cuser, 1, 0);
    addCustomerPane.add(new Label("Password:"), 0, 1);
    addCustomerPane.add(cpass, 1, 1);
    addCustomerPane.add(new Label("ID:"), 0, 2);
    addCustomerPane.add(cid, 1, 2);
    Button saveCustomer = new Button("Save");
    addCustomerPane.add(saveCustomer, 1, 3);
    Button backCustomer = new Button("Back");
    addCustomerPane.add(backCustomer, 1, 3);

    GridPane.setHalignment(saveCustomer, HPos.RIGHT);
    GridPane.setHalignment(backCustomer, HPos.LEFT);

    addCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
        Scene addCustomerScene = new Scene(addCustomerPane, 350, 350);
        primaryStage.setScene(addCustomerScene);
        }
    });

    saveCustomer.setOnAction(new EventHandler<ActionEvent>() {
         @Override
          public void handle(ActionEvent t) {
               String idd = cid.getText();
               int x = Integer.parseInt(idd);
               Customer customer = new Customer(cuser.getText(), cpass.getText(), x);
               newAdmin.addCustomer(customer);
            }
    });

    backCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
             primaryStage.setScene(manageCustomerScene);
            }
    });

    // Delete customer
    Label deleteCustomerLabel = new Label("Enter customer ID you want to delete");
    TextField deleteCustomerId = new TextField();
    Button deleteCustomerSave = new Button("Save");
    Button deleteCustomerBack = new Button("Back");

    VBox deleteCustomerLayout = new VBox(15);
    deleteCustomerLayout.getChildren().addAll(deleteCustomerLabel, deleteCustomerId, deleteCustomerSave, deleteCustomerBack);

    Scene deleteCustomerScene = new Scene(deleteCustomerLayout, 350, 350);

    deleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
             primaryStage.setScene(deleteCustomerScene);
             }
        });

    deleteCustomerSave.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String did = deleteCustomerId.getText();
            int v = Integer.parseInt(did);
            newAdmin.deleteCustomer(v);
            }
        });

    deleteCustomerBack.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            primaryStage.setScene(manageCustomerScene);
             }
    });

// Edit customer
    GridPane editCustomerPane = new GridPane();
    editCustomerPane.setAlignment(Pos.CENTER);
    editCustomerPane.setPadding(new Insets(11.5, 2.5, 13.5, 14.5));
    editCustomerPane.setHgap(5.5);
    editCustomerPane.setVgap(5.5);

    TextField editCustomerUser = new TextField();
    TextField editCustomerPass = new PasswordField();
    TextField editCustomerId = new TextField();

    editCustomerPane.add(new Label("Enter customer ID:"), 0, 0);
    editCustomerPane.add(editCustomerId, 1, 0);

    editCustomerPane.add(new Label("New username:"), 0, 1);
    editCustomerPane.add(editCustomerUser, 1, 1);

    editCustomerPane.add(new Label("New password:"), 0, 3);
    editCustomerPane.add(editCustomerPass, 1, 3);

    Button editCustomerSave = new Button("Save");
    editCustomerPane.add(editCustomerSave, 1, 4);

    Button editCustomerBack = new Button("Back");
    editCustomerPane.add(editCustomerBack, 1, 4);

    GridPane.setHalignment(editCustomerSave, HPos.RIGHT);
    GridPane.setHalignment(editCustomerBack, HPos.LEFT);

    Scene editCustomerScene = new Scene(editCustomerPane, 350, 350);

    editCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
         public void handle(ActionEvent t) {
             primaryStage.setScene(editCustomerScene);
             }
    });

    editCustomerSave.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            String did = editCustomerId.getText();
            int v = Integer.parseInt(did);
            newAdmin.editCustomer(v, editCustomerUser.getText(), editCustomerPass.getText());
        }
    });

    editCustomerBack.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            primaryStage.setScene(manageCustomerScene);
        }
    });

// List customers
    Button backListCustomer = new Button("Back");
    VBox listCustomerLayout = new VBox(15);
    listCustomerLayout.setPadding(new Insets(10));

    Label listCustomerLabel = new Label("List of Customers:");
    VBox customerList = new VBox(10);

    listCustomerLayout.getChildren().addAll(listCustomerLabel, customerList, backListCustomer);

    Scene listCustomerScene = new Scene(listCustomerLayout, 350, 350);

    listCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            customerList.getChildren().clear(); // Clear any previous data
            if (Customer.customers== null || Customer.customers.isEmpty()) {
            customerList.getChildren().add(new Label("No customers available."));
        } 
            else {
                for (Customer customer : Customer.customers) {
                Label customerInfo = new Label("ID: " + customer.getId() + ", Username: " + customer.getUsername());
                customerList.getChildren().add(customerInfo);
            }
                }
            primaryStage.setScene(listCustomerScene);
        }
    
    });

    backListCustomer.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent t) {
            primaryStage.setScene(manageCustomerScene);
        }
    });

       ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        // Loay start

        Button btn1 = new Button("Number of pices Sold");
        Button btn2 = new Button("list of suppliers");
        Button btn3 = new Button("most Revenue");
        Button btn4 = new Button("best seller");
        Button BackToAdminScene = new Button("Back");

        VBox viewProduct = new VBox(10, btn1, btn2, btn3, btn4,BackToAdminScene);
        viewProduct.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene viewproductscene = new Scene(viewProduct, 300, 200);
        viewproduct.setOnAction(e -> {
            primaryStage.setScene(viewproductscene);
        });

        btn1.setOnAction(e -> {

        });

        TextArea textArea = new TextArea();
        textArea.setEditable(false); // Make it read-only
        textArea.setWrapText(true); // Optional, to wrap text 
        Button BackToProduct = new Button("Back");
        
        VBox viewSupp = new VBox(15);
        viewSupp.getChildren().addAll(textArea,BackToProduct);
        
        Scene viewSupplier = new Scene(viewSupp,400,300);
        
        btn2.setOnAction(e -> {
            viewSuppliersAndPricing(textArea);
            primaryStage.setScene(viewSupplier);
        });
        
        BackToProduct.setOnAction(e -> {
            primaryStage.setScene(viewproductscene);
        });
    
        btn3.setOnAction(e -> {

            
        });

    
        btn4.setOnAction(e -> {
    
        });
        
        BackToAdminScene.setOnAction(e -> {
            primaryStage.setScene(adminScene);
        });

        Button noOrderC = new Button("Number of orders per each cashier");
        Button maxNoOrder = new Button("cashier with max number of orders");
        Button maxRevOrder = new Button("cashier with max revenue");
        Button noOrderSupp = new Button("Number of orders per each supplier");
        Button maxNoOrderS = new Button("supplier with max number of orders ");
        Button maxRevSupp = new Button(" supplier with max revenue");
        Button noOrder = new Button("number of order per each customers ");
        Button maxNoOrdercustomer = new Button("customer max orders ");
        Button maxRevOrdercustomer = new Button("customer max revenue ");
        Button BackToAdmin = new Button("Back");

        VBox viewUsers = new VBox(10, noOrderC, maxNoOrder, maxRevOrder
                , noOrderSupp , maxNoOrderS , maxRevSupp , noOrder,maxNoOrdercustomer,maxRevOrdercustomer,BackToAdmin);

        Scene scene2 = new Scene(viewUsers, 500, 500);
        viewuser.setOnAction(e -> {
            primaryStage.setScene(scene2);
    
        });
        
        noOrderC.setOnAction(e -> {


        });


        maxNoOrder.setOnAction(e -> {


        });


        maxRevOrder.setOnAction(e -> {


        });


        noOrderSupp.setOnAction(e -> {


        });


        maxNoOrderS.setOnAction(e -> {


        });


        maxRevSupp.setOnAction(e -> {


        });


        noOrder.setOnAction(e -> {


        });


        maxNoOrdercustomer.setOnAction(e -> {


        });
        
        maxRevOrdercustomer.setOnAction(e -> {


        });
        
        BackToAdmin.setOnAction(e -> {
        primaryStage.setScene(adminScene);
        });
        
        
        //order button


        Button orderDetl = new Button("order details");
        Button avrTOTALrev = new Button("average and total revenue");
        

//back to main admin button
        Button BackToadminScene = new Button("Back");

//
        VBox viewOrder = new VBox(10, orderDetl, avrTOTALrev,BackToadminScene);
        viewOrder.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene vieworderscene = new Scene(viewOrder, 300, 200);

        vieworder.setOnAction(e -> {
        primaryStage.setScene(vieworderscene);

        });

        orderDetl.setOnAction(e -> {

        });

        avrTOTALrev.setOnAction(e -> {

        });
       
        
        BackToadminScene.setOnAction(e -> {
            primaryStage.setScene(adminScene);
        });
        
        
        //Ahmed start
        
        //Manage Cashier
        
        Button addCashier = new Button("Add Cashier");
        Button deleteCashier = new Button("Delete Cashier");
        Button editCashier = new Button("Edit Cashier");
        Button listCashier = new Button("List Cashiers");
        Button backTOAdmin = new Button ("Back");

        VBox rootCashier = new VBox(15);
        rootCashier.getChildren().addAll(addCashier, deleteCashier, editCashier, listCashier,backTOAdmin);

        Scene manageCashierScene = new Scene(rootCashier, 350, 350); // This scene manages Cashiers
        musercashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(manageCashierScene);
            }
        });
        
        backTOAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(adminScene);
            }
        });

// Add Cashier

        Label labelcashierFeedback = new Label(); // Create a feedback label
        labelcashierFeedback.setTextFill(Color.RED); // Set the text color to indicate an error
        
        GridPane addCashierPane = new GridPane();
        addCashierPane.setAlignment(Pos.CENTER);
        addCashierPane.setPadding(new Insets(11.5, 2.5, 13.5, 14.5));
        addCashierPane.setHgap(5.5);
        addCashierPane.setVgap(5.5);


        TextField cUser = new TextField();
        TextField cPass = new PasswordField();
        TextField cId = new TextField();

        addCashierPane.add(new Label("Username:"), 0, 0);
        addCashierPane.add(cUser, 1, 0);
        addCashierPane.add(new Label("Password:"), 0, 1);
        addCashierPane.add(cPass, 1, 1);
        addCashierPane.add(new Label("ID:"), 0, 2);
        addCashierPane.add(cId, 1, 2);
        Button saveCashier = new Button("Save");
        addCashierPane.add(saveCashier, 1, 3);
        Button backCashier = new Button("Back");
        addCashierPane.add(backCashier, 1, 3);
        addCashierPane.add(labelcashierFeedback,0,4,2,1);

        GridPane.setHalignment(saveCashier, HPos.RIGHT);
        GridPane.setHalignment(backCashier, HPos.LEFT);
        
        Scene addCashierScene = new Scene(addCashierPane, 350, 350);

        addCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(addCashierScene);
            }
        });


        saveCashier.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override
            public void handle(ActionEvent t) {
                String idd = cId.getText();
                try {
                    int x = Integer.parseInt(idd);
                    Cashier cashier = new Cashier(cUser.getText(), cPass.getText(), x);
                    newAdmin.addcashier(cashier);
                    labelcashierFeedback.setText("Cashier added successfully."); // Success feedback
                    labelcashierFeedback.setStyle("-fx-text-fill: green;"); // Change text color to 

                    cUser.clear();
                    cPass.clear();
                    cId.clear();
                } catch (NumberFormatException e) {
                    labelcashierFeedback.setText("Invalid ID format. Please enter a valid number."); // Error feedback
                }
            }
        });

        
        backCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(manageCashierScene);
            }
        });

// Delete Cashier

        Label labelcashierFeedbackDelete = new Label(); // Create a feedback label
        labelcashierFeedbackDelete.setTextFill(Color.RED); // Set the text color to indicate an error
        
        Label deleteCashierLabel = new Label("Enter Cashier ID you want to delete");
        TextField deleteCashierId = new TextField();
        Button deleteCashierSave = new Button("Save");
        Button deleteCashierBack = new Button("Back");

        VBox deleteCashierLayout = new VBox(15);
        deleteCashierLayout.getChildren().addAll(deleteCashierLabel, deleteCashierId, deleteCashierSave
                , deleteCashierBack,labelcashierFeedbackDelete);
        Scene deleteCashierScene = new Scene(deleteCashierLayout, 350, 350);
        
        deleteCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(deleteCashierScene);
            }
        });

        deleteCashierSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String did = deleteCashierId.getText();
                try {
                    int v = Integer.parseInt(did);
                    if (newAdmin.cashierExists(v)) {
                        newAdmin.deletecashier(v);
                        labelcashierFeedbackDelete.setText("Cashier deleted successfully."); // Success feedback
                        labelcashierFeedbackDelete.setStyle("-fx-text-fill: green;"); // Change text color to 
                    }
                    
                    else {
                        labelcashierFeedbackDelete.setText("Sorry, admin not found."); // Admin not found feedback
                        labelcashierFeedbackDelete.setStyle("-fx-text-fill: red;"); // Change text color to red
                    }
                    deleteCashierId.clear();
                } catch (NumberFormatException e) {
                    labelcashierFeedbackDelete.setText("Invalid ID format. Please enter a valid number."); // Error feedback
                }
            }
        });

        deleteCashierBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(manageCashierScene);
            }
        });

// Edit Cashier

        Label labelcashierFeedbackEdit = new Label(); // Create a feedback label
        labelcashierFeedbackEdit.setTextFill(Color.RED); // Set the text color to indicate an error
        
        GridPane editCashierPane = new GridPane();
        editCashierPane.setAlignment(Pos.CENTER);
        editCashierPane.setPadding(new Insets(11.5, 2.5, 13.5, 14.5));
        editCashierPane.setHgap(5.5);
        editCashierPane.setVgap(5.5);

        TextField editCashierUser = new TextField();
        TextField editCashierPass = new PasswordField();
        TextField editCashierId = new TextField();

        editCashierPane.add(new Label("Enter Cashier ID:"), 0, 0);
        editCashierPane.add(editCashierId, 1, 0);
        editCashierPane.add(new Label("New username:"), 0, 1);
        editCashierPane.add(editCashierUser, 1, 1);
        editCashierPane.add(new Label("New password:"), 0, 3);
        editCashierPane.add(editCashierPass, 1, 3);
        Button editCashierSave = new Button("Save");
        editCashierPane.add(editCashierSave, 1, 4);
        Button editCashierBack = new Button("Back");
        editCashierPane.add(editCashierBack, 1, 4);
        editCashierPane.add(labelcashierFeedbackEdit, 0,5,2 ,1);

        GridPane.setHalignment(editCashierSave, HPos.RIGHT);
        GridPane.setHalignment(editCashierBack, HPos.LEFT);
        Scene editCashierScene = new Scene(editCashierPane, 350, 350);

        editCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(editCashierScene);
            }
        });


        editCashierSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String did = editCashierId.getText();
                try {
                    int v = Integer.parseInt(did);
                    if (newAdmin.cashierExists(v)) {
                        newAdmin.editcashier(editCashierUser.getText(), editCashierPass.getText(),v);
                        labelcashierFeedbackEdit.setText("Admin edited successfully."); // Success feedback
                        labelcashierFeedbackEdit.setStyle("-fx-text-fill: green;"); // Change text color to green
                    } else {
                        labelcashierFeedbackEdit.setText("Sorry, admin not found."); // Admin not found feedback
                        labelcashierFeedbackEdit.setStyle("-fx-text-fill: red;"); // Change text color to red
                    }
                    editCashierId.clear();
                    editCashierUser.clear();
                    editCashierPass.clear();

                } catch (NumberFormatException e) {
                    labelcashierFeedbackEdit.setText("Invalid ID format. Please enter a valid number."); // Error feedback
                    labelcashierFeedbackEdit.setStyle("-fx-text-fill: red;"); // Change text color to red
                }
            }
        });


        editCashierBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(manageCashierScene);
            }
        });

// List Cashiers
        Button backListCashier = new Button("Back");
        VBox listCashierLayout = new VBox(15);
        listCashierLayout.setPadding(new Insets(10));

        Label listCashierLabel = new Label("List of Cashiers:");
        VBox cashierList = new VBox(10);

        listCashierLayout.getChildren().addAll(listCashierLabel, cashierList, backListCashier);
        Scene listCashierScene = new Scene(listCashierLayout, 350, 350);


        listCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                cashierList.getChildren().clear(); // Clear any previous data
                if (Cashier.ca == null || Cashier.ca.isEmpty()) {
                    cashierList.getChildren().add(new Label("No Cashiers available."));
                } else {
                    for (Cashier cashier : Cashier.ca) {
                        Label cashierInfo = new Label("ID: " + cashier.getId() + ", Username: " + cashier.getUsername());
                        cashierList.getChildren().add(cashierInfo);
                    }
                }
                primaryStage.setScene(listCashierScene);
            }
        });

    
        backListCashier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                primaryStage.setScene(manageCashierScene);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////

        primaryStage.setTitle("Pharmacy Managment System");
        primaryStage.setScene(logScene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        saveAdminToFile("Admins.dat");
        saveCashierToFile("Cashier.dat");
        saveCustomerToFile("Customer.dat");
        saveProductToFile("Product.dat");

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
    
    public void viewSuppliersAndPricing(TextArea textArea) {
    if (Product.getProduct().isEmpty()) {
        textArea.appendText("No products available.\n");
        return;
    }

    textArea.appendText("List of Suppliers and Pricing:\n");
    textArea.appendText("Supplier       Product Name        Price\n");
    textArea.appendText("----------------------------------------------\n");

    for (Product p : Product.getProduct()) {
        textArea.appendText(
            p.getSupplier() + "          " +
            p.getName() + "                " +
            p.getPrice() + "\n"
        );
    }
}
}

