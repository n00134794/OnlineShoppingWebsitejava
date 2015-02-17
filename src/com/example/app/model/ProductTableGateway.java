package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductTableGateway {

    private final Connection mConnection;
    
   
    private static final String TABLE_NAME = "products"; 
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_COST_PRICE = "costPrice";
    private static final String COLUMN_SALE_PRICE = "salePrice";
    private static final String COLUMN_QUANTITY = "quantity";
    
    public ProductTableGateway(Connection connection){
        mConnection = connection;
    }
    
    public int insertProduct(String nm, String d, double cp, double sp, int qty) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;       
        
       query = "INSERT INTO " + TABLE_NAME + " (" +
               COLUMN_NAME + ", " +
               COLUMN_DESCRIPTION + ", " +
               COLUMN_COST_PRICE + ", " +
               COLUMN_SALE_PRICE + ", " +
               COLUMN_QUANTITY + 
               ") VALUES (?, ?, ?, ?, ?)";
               
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, nm);
        stmt.setString(2, d);
        stmt.setDouble(3, cp);
        stmt.setDouble(4, sp);
        stmt.setInt(5, qty);
        
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
        
        return id;       
        
    }
   
    public boolean deleteProduct (int id) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        
        return (numRowsAffected == 1);
    }
    
    
    
    /*
    private static void deleteProduct(Scanner kb, Model m){
        System.out.print ("Enter the name of the product to delete: ");
        
    */    
            
        
    public List<Product> getProducts() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Product> products;
        
                
        String name, description;
        int id, quantity;
        double costPrice, salePrice;
                
        Product p;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        
        products = new ArrayList<Product>();
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            name = rs.getString(COLUMN_NAME);
            description = rs.getString(COLUMN_DESCRIPTION);
            costPrice = rs.getDouble (COLUMN_COST_PRICE);
            salePrice = rs.getDouble (COLUMN_SALE_PRICE);
            quantity = rs.getInt (COLUMN_QUANTITY);
            
            p = new Product(id, name, description, costPrice, salePrice, quantity);
            products.add(p);
        }
        
        
        return products;
    }        

    boolean updateProduct(Product p) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +               
               COLUMN_NAME          + " = ?, " +
               COLUMN_DESCRIPTION   + " = ?, " +
               COLUMN_COST_PRICE    + " = ?, " +
               COLUMN_SALE_PRICE    + " = ?, " +
               COLUMN_QUANTITY      + " = ? " +
               " WHERE " + COLUMN_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);       
        stmt.setString(1, p.getName());
        stmt.setString(2, p.getDescription());
        stmt.setDouble(3, p.getCostPrice());
        stmt.setDouble(4, p.getSalePrice());
        stmt.setInt(5, p.getQuantity());
        stmt.setInt(6, p.getId());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
        
    }
           
}
