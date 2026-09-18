package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import java.util.ArrayList;

public class UserDAO {

    // LOGIN

    public User login(String username, String password) {

        String sql =
                "SELECT * FROM Users "
                + "WHERE Username = ? AND Password = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserID(
                        rs.getInt("UserID")
                );

                user.setFullName(
                        rs.getString("FullName")
                );

                user.setUsername(
                        rs.getString("Username")
                );

                user.setPassword(
                        rs.getString("Password")
                );

                user.setUserRole(
                        rs.getString("UserRole")
                );

                return user;
            }

        } catch (Exception e) {

            System.out.println(
                    "Login Error!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // ADD USER

    public boolean addUser(User user) {

        String sql =
                "INSERT INTO Users "
                + "(FullName, Username, Password, UserRole) "
                + "VALUES (?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    user.getFullName()
            );

            pst.setString(
                    2,
                    user.getUsername()
            );

            pst.setString(
                    3,
                    user.getPassword()
            );

            pst.setString(
                    4,
                    user.getUserRole()
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "Add User Error!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // GET SALES ASSISTANTS

    public ArrayList<User> getSalesAssistants() {

        ArrayList<User> staffList =
                new ArrayList<>();

        String sql =
                "SELECT UserID, FullName, Username, "
                + "Password, UserRole "
                + "FROM Users "
                + "WHERE UserRole = ? "
                + "ORDER BY UserID";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    "Sales Assistant"
            );

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setUserID(
                        rs.getInt("UserID")
                );

                user.setFullName(
                        rs.getString("FullName")
                );

                user.setUsername(
                        rs.getString("Username")
                );

                user.setPassword(
                        rs.getString("Password")
                );

                user.setUserRole(
                        rs.getString("UserRole")
                );

                staffList.add(user);
            }

        } catch (Exception e) {

            System.out.println(
                    "Get Sales Assistants Error!"
            );

            e.printStackTrace();
        }

        return staffList;
    }

    // UPDATE USER

    public boolean updateUser(User user) {

        String sql =
                "UPDATE Users SET "
                + "FullName = ?, "
                + "Username = ?, "
                + "Password = ?, "
                + "UserRole = ? "
                + "WHERE UserID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setString(
                    1,
                    user.getFullName()
            );

            pst.setString(
                    2,
                    user.getUsername()
            );

            pst.setString(
                    3,
                    user.getPassword()
            );

            pst.setString(
                    4,
                    user.getUserRole()
            );

            pst.setInt(
                    5,
                    user.getUserID()
            );

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "Update User Error!"
            );

            e.printStackTrace();

            return false;
        }
    }

    // DELETE USER

    public boolean deleteUser(int userID) {

        String sql =
                "DELETE FROM Users "
                + "WHERE UserID = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)
        ) {

            pst.setInt(1, userID);

            int rows =
                    pst.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            System.out.println(
                    "Delete User Error!"
            );

            e.printStackTrace();

            return false;
        }
    }
}