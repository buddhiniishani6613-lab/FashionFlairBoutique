package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoryDAO {

    // ==========================================
    // DISPLAY ALL CATEGORIES
    // ==========================================
    public void displayCategories() {

        String sql = "SELECT * FROM Categories";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database connection failed.");
                return;
            }

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            System.out.println("================================");
            System.out.println("       FASHION CATEGORIES");
            System.out.println("================================");

            while (rs.next()) {

                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");
                String description = rs.getString("Description");

                System.out.println("ID          : " + categoryID);
                System.out.println("Category    : " + categoryName);
                System.out.println("Description : " + description);
                System.out.println("--------------------------------");
            }

            rs.close();
            pst.close();

        } catch (Exception e) {

            System.out.println("Error loading categories.");
            e.printStackTrace();
        }
    }


    // ==========================================
    // GET CATEGORY NAME BY ID
    // ==========================================
    public String getCategoryName(int categoryID) {

        String sql = "SELECT CategoryName FROM Categories WHERE CategoryID = ?";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                return null;
            }

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, categoryID);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String categoryName = rs.getString("CategoryName");

                rs.close();
                pst.close();

                return categoryName;
            }

            rs.close();
            pst.close();

        } catch (Exception e) {

            System.out.println("Error finding category.");
            e.printStackTrace();
        }

        return null;
    }
}