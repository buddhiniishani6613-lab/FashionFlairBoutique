package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * SaleDAO handles database operations related to sales.
 * It separates database logic from the user interface.
 */
public class SaleDAO {

    /*
     * Process a customer purchase.
     *
     * This method:
     * 1. Creates a new sale record.
     * 2. Adds the purchased product to SaleItems.
     * 3. Reduces the product stock.
     */
    public boolean processSale(
            int userID,
            int productID,
            int quantity,
            double unitPrice) {

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            if (con == null) {
                return false;
            }

            /*
             * Transaction is used because several database
             * operations must be completed together.
             */
            con.setAutoCommit(false);

            double totalAmount = unitPrice * quantity;

        // Insert the main sale record.
            String saleSQL =
                    "INSERT INTO Sales "
                    + "(SaleDate, UserID, TotalAmount) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement saleStatement =
                    con.prepareStatement(
                            saleSQL,
                            java.sql.Statement.RETURN_GENERATED_KEYS
                    );

            saleStatement.setTimestamp(
                    1,
                    new java.sql.Timestamp(
                            System.currentTimeMillis()
                    )
            );

            saleStatement.setInt(2, userID);
            saleStatement.setDouble(3, totalAmount);

            saleStatement.executeUpdate();

            ResultSet generatedKeys =
                    saleStatement.getGeneratedKeys();

            int saleID = 0;

            if (generatedKeys.next()) {
                saleID = generatedKeys.getInt(1);
            }

            generatedKeys.close();
            saleStatement.close();

            if (saleID == 0) {
                con.rollback();
                return false;
            }

        // Insert the purchased item.
            String itemSQL =
                    "INSERT INTO SaleItems "
                    + "(SaleID, ProductID, Quantity, UnitPrice) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement itemStatement =
                    con.prepareStatement(itemSQL);

            itemStatement.setInt(1, saleID);
            itemStatement.setInt(2, productID);
            itemStatement.setInt(3, quantity);
            itemStatement.setDouble(4, unitPrice);

            itemStatement.executeUpdate();
            itemStatement.close();

        // Reduce product stock after the purchase.
            String stockSQL =
                    "UPDATE Products "
                    + "SET StockQuantity = "
                    + "StockQuantity - ? "
                    + "WHERE ProductID = ?";

            PreparedStatement stockStatement =
                    con.prepareStatement(stockSQL);

            stockStatement.setInt(1, quantity);
            stockStatement.setInt(2, productID);

            stockStatement.executeUpdate();
            stockStatement.close();

    // Save all changes.
        con.commit();

        return true;

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }

            System.out.println("Sale Processing Failed!");
            e.printStackTrace();

            return false;
        }
    }

    // Get today's total sales amount.
    public double getDailySales() {

        String sql =
                "SELECT SUM(TotalAmount) AS DailyTotal "
                + "FROM Sales "
                + "WHERE DateValue(SaleDate) = DateValue(Date())";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble("DailyTotal");
            }

        } catch (Exception e) {

            System.out.println("Daily Sales Error!");
            e.printStackTrace();
        }

        return 0.0;
    }

    //Get the total sales amount for the current month.
    public double getMonthlySales() {

        String sql =
                "SELECT SUM(TotalAmount) AS MonthlyTotal "
                + "FROM Sales "
                + "WHERE Month(SaleDate) = Month(Date()) "
                + "AND Year(SaleDate) = Year(Date())";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble("MonthlyTotal");
            }

        } catch (Exception e) {

            System.out.println("Monthly Sales Error!");
            e.printStackTrace();
        }

        return 0.0;
    }

    //Get the number of sales made today.
    public int getDailySaleCount() {

        String sql =
                "SELECT COUNT(*) AS SaleCount "
                + "FROM Sales "
                + "WHERE DateValue(SaleDate) = DateValue(Date())";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt("SaleCount");
            }

        } catch (Exception e) {

            System.out.println("Daily Sale Count Error!");
            e.printStackTrace();
        }

        return 0;
    }

    //Get the number of sales made during the current month.
    public int getMonthlySaleCount() {

        String sql =
                "SELECT COUNT(*) AS SaleCount "
                + "FROM Sales "
                + "WHERE Month(SaleDate) = Month(Date()) "
                + "AND Year(SaleDate) = Year(Date())";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt("SaleCount");
            }

        } catch (Exception e) {

            System.out.println("Monthly Sale Count Error!");
            e.printStackTrace();
        }

        return 0;
    }
}