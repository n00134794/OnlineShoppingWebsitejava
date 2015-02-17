package com.example.app.model;

import java.util.List;
import java.util.Scanner;

public class DemoApp {
    
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
                
        int menu;
        
        do{
            System.out.println("Welcome, What would you like to do?");
            System.out.println("1. CREATE new produnt.");
            System.out.println("2. DELETE current product.");
            System.out.println("3. EDIT current products");
            System.out.println("4. VIEW current products");
            System.out.println("5. EXIT");
            System.out.println();

            System.out.print("Enter option: ");
            String line = keyboard.nextLine();
            menu = Integer.parseInt(line);

            //System.out.println("You chose option " + menu);
            
            switch (menu){
                case 1:{
                    System.out.println("CREATE product");
                    createProduct(keyboard, model);
                    break;
                    } 
                
                case 2:{
                    System.out.println("DELETE product");                                      
                    deleteProduct(keyboard, model);
                    break;
                    }        
                
                case 3:{
                    System.out.println("EDIT products");
                    editProducts(keyboard, model);
                    break;                                          
                    }
                
                case 4:{
                    System.out.println("VIEW products");
                    viewProducts(model);
                    break;                                          
                    }
                
                case 5:{
                    System.out.println("Goodbye");                    
                    break; 
                    }
                
                default: {
                    System.out.println("Please enter a valid option (1-4)");
                }
            }
        }
        while (menu != 5);
    }
    
    private static void createProduct(Scanner keyboard, Model model){ 
        Product p = readProduct(keyboard);
        if (model.addProduct(p)){
            System.out.println("Product added to database.");
        }
        else{
            System.out.println("Failed to add to database.");
        }     
        System.out.println();
    }

    private static void deleteProduct(Scanner keyboard, Model model) {
        System.out.print ("Enter the id of the product to delete: ");
                int id = Integer.parseInt(keyboard.nextLine());
                Product p;

                p = model.findProductById(id);
                if (p != null) {
                    if (model.removeProduct(p)){
                        System.out.println("Product deleted.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Product not deleted.");
                        System.out.println();
                         }
                    }
                    else {
                        System.out.println("Product not found.");
                        System.out.println();
                    }                                                          
    }
    
    private static void editProducts(Scanner keyboard, Model model) {
        System.out.print ("Enter the id of the product to edit: ");
                int id = Integer.parseInt(keyboard.nextLine());
                Product p;

                p = model.findProductById(id);
                if (p != null) {
                    editProductDetails(keyboard, p);
                    if (model.updateProduct(p)){
                        System.out.println("Product updated.");
                        System.out.println();
                    }
                    else {
                        System.out.println("Product not updated.");
                        System.out.println();
                         }
                    }
                    else {
                        System.out.println("Product not found.");
                        System.out.println();
                    } 
        
    }
    
    private static void viewProducts(Model model) {
        List<Product> products = model.getProducts();
                        System.out.println();
                        if (products.isEmpty()) {
                            System.out.println("There are no products in database");
                        }
                        else{    
                            System.out.printf("%-10s %-15s %-30s %-10s %-10s %-10s\n",
                                "id",
                                "Name",
                                "Description",
                                "Cost Price",
                                "Sale Price",
                                "Quantity In Stock"
                        );
                        for (Product pr : products) {
                            System.out.printf("%-10s %-15s %-30s %10.2f %10.2f %6d\n", 
                                    pr.getId(),
                                    pr.getName(),
                                    pr.getDescription(),
                                    pr.getCostPrice(),
                                    pr.getSalePrice(),
                                    pr.getQuantity()
                            );
                        }
                        }
                        System.out.println();    
    }
    
    
    private static Product readProduct(Scanner keyb) {
        String name, description;
        int id, quantity;
        double costPrice, salePrice;
        String line;
        
        name = getString(keyb, "Enter name: ");
        description = getString(keyb, "Enter description: ");
        line = getString(keyb, "Enter cost price: ");
        costPrice = Double.parseDouble(line);
        line = getString(keyb, "Enter sale price: ");
        salePrice = Double.parseDouble(line);
        line = getString(keyb, "Enter quantity: ");
        quantity = Integer.parseInt(line);
        
        Product p =
                new Product(name, description, costPrice, salePrice, quantity);
        
        return p;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static void editProductDetails(Scanner keyboard, Product p) {
        String name, description;
        int id, quantity;
        double salePrice, costPrice;
        String line1, line2, line3, line4;
        
        line1 = getString(keyboard, "Enter id[" + p.getId() + "}: ");
        name = getString(keyboard, "Enter name[" + p.getName() + "}: ");
        description = getString(keyboard, "Enter description[" + p.getDescription() + "}: ");        
        line2 = getString(keyboard, "Enter cost price[" + p.getCostPrice() + "}: ");
        line3 = getString(keyboard, "Enter sale price[" + p.getSalePrice() + "}: ");
        line4 = getString(keyboard, "Enter quantity[" + p.getQuantity() + "}: ");
        
        if (line1.length() !=0)  {
            id = Integer.parseInt(line1);
            p.setId(id);      
        }
        
        if (name.length() !=0)  {
            p.setName(name);    
        }
        
        if (description.length() !=0)  {
            p.setDescription(description);    
        }
        
        if (line2.length() !=0)  {
            costPrice = Double.parseDouble(line2);
            p.setCostPrice(costPrice);      
        }
       
        if (line3.length() !=0)  {
            salePrice = Double.parseDouble(line3);
            p.setSalePrice(salePrice);      
        }
        
        if (line4.length() !=0)  {
            quantity = Integer.parseInt(line4);
            p.setQuantity(quantity);      
        }
    }
}
