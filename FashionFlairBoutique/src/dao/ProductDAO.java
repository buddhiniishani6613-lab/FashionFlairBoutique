package dao;

import database.DBConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
    
    
    // GET ALL PRODUCTS
    
    public ArrayList<Product> getAllProducts() {

        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM Products";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            while (rs.next()) {

                Product product = new Product();

                product.setProductID(
                        rs.getInt("ProductID")
                );

                product.setProductName(
                        rs.getString("ProductName")
                );

                product.setBrand(
                        rs.getString("Brand")
                );

                product.setCategoryID(
                        rs.getInt("CategoryID")
                );

                product.setSize(
                        rs.getString("Size")
                );

                product.setColor(
                        rs.getString("Color")
                );

                product.setPrice(
                        rs.getDouble("Price")
                );

                product.setStockQuantity(
                        rs.getInt("StockQuantity")
                );

                products.add(product);
            }

        } catch (Exception e) {

            System.out.println("Error loading products.");
            e.printStackTrace();
        }

        return products;
    }


    // ADD PRODUCT
    
    public boolean addProduct(Product product) {

        String sql =
                "INSERT INTO Products "
                + "(ProductName, Brand, CategoryID, Size, "
                + "Color, Price, StockQuantity) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    product.getProductName()
            );

            pst.setString(
                    2,
                    product.getBrand()
            );

            pst.setInt(
                    3,
                    product.getCategoryID()
            );

            pst.setString(
                    4,
                    product.getSize()
            );

            pst.setString(
                    5,
                    product.getColor()
            );

            pst.setDouble(
                    6,
                    product.getPrice()
            );

            pst.setInt(
                    7,
                    product.getStockQuantity()
            );

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Error adding product.");
            e.printStackTrace();

            return false;
        }
    }


    // UPDATE PRODUCT
   
    public boolean updateProduct(Product product) {

        String sql =
                "UPDATE Products SET "
                + "ProductName = ?, "
                + "Brand = ?, "
                + "CategoryID = ?, "
                + "Size = ?, "
                + "Color = ?, "
                + "Price = ?, "
                + "StockQuantity = ? "
                + "WHERE ProductID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    product.getProductName()
            );

            pst.setString(
                    2,
                    product.getBrand()
            );

            pst.setInt(
                    3,
                    product.getCategoryID()
            );

            pst.setString(
                    4,
                    product.getSize()
            );

            pst.setString(
                    5,
                    product.getColor()
            );

            pst.setDouble(
                    6,
                    product.getPrice()
            );

            pst.setInt(
                    7,
                    product.getStockQuantity()
            );

            pst.setInt(
                    8,
                    product.getProductID()
            );

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Error updating product.");
            e.printStackTrace();

            return false;
        }
    }


    // DELETE PRODUCT
   
    public boolean deleteProduct(int productID) {

        String sql =
                "DELETE FROM Products "
                + "WHERE ProductID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, productID);

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Error deleting product.");
            e.printStackTrace();

            return false;
        }
    }


    
    // SEARCH PRODUCTS BY CATEGORY
    
    public ArrayList<Product> searchByCategory(int categoryID) {

        ArrayList<Product> products = new ArrayList<>();

        String sql =
                "SELECT * FROM Products "
                + "WHERE CategoryID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, categoryID);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Product product = new Product();

                product.setProductID(
                        rs.getInt("ProductID")
                );

                product.setProductName(
                        rs.getString("ProductName")
                );

                product.setBrand(
                        rs.getString("Brand")
                );

                product.setCategoryID(
                        rs.getInt("CategoryID")
                );

                product.setSize(
                        rs.getString("Size")
                );

                product.setColor(
                        rs.getString("Color")
                );

                product.setPrice(
                        rs.getDouble("Price")
                );

                product.setStockQuantity(
                        rs.getInt("StockQuantity")
                );

                products.add(product);
            }

            rs.close();

        } catch (Exception e) {

            System.out.println("Error searching products.");
            e.printStackTrace();
        }

        return products;
    }


    
    // UPDATE PRODUCT PRICE
    
    public boolean updatePrice(
            int productID,
            double newPrice) {

        String sql =
                "UPDATE Products "
                + "SET Price = ? "
                + "WHERE ProductID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setDouble(1, newPrice);
            pst.setInt(2, productID);

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Update Price Error!");
            e.printStackTrace();

            return false;
        }
    }


    // APPLY DISCOUNT
    
    public boolean applyDiscount(
            int productID,
            double discountPercent) {

        String sql =
                "UPDATE Products "
                + "SET Price = Price - "
                + "(Price * ? / 100) "
                + "WHERE ProductID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setDouble(1, discountPercent);
            pst.setInt(2, productID);

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println("Apply Discount Error!");
            e.printStackTrace();

            return false;
        }
    }
}