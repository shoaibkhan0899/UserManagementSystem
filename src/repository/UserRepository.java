package repository;

import db.DatabaseConnection;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // CREATE
    public void addUser(User user) {

        String sql = "INSERT INTO users (id, name, email, mobile_numbers) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, String.join(",", user.getMobileNumbers()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // FIND BY EMAIL
    public User findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));

                String mobiles = resultSet.getString("mobile_numbers");

                if (mobiles != null && !mobiles.isEmpty()) {
                    user.setMobileNumbers(
                            new ArrayList<>(List.of(mobiles.split(",")))
                    );
                } else {
                    user.setMobileNumbers(new ArrayList<>());
                }

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // FIND BY ID
    public User findById(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));

                String mobiles = resultSet.getString("mobile_numbers");

                if (mobiles != null && !mobiles.isEmpty()) {
                    user.setMobileNumbers(
                            new ArrayList<>(List.of(mobiles.split(",")))
                    );
                } else {
                    user.setMobileNumbers(new ArrayList<>());
                }

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // UPDATE
    public boolean updateUser(User updatedUser) {

        String sql = "UPDATE users SET name = ?, email = ?, mobile_numbers = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {





            statement.setString(1, updatedUser.getName());
            statement.setString(2, updatedUser.getEmail());
            statement.setString(3, String.join(",", updatedUser.getMobileNumbers()));
            statement.setInt(4, updatedUser.getId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return false;
    }


    // FETCH ALL
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));

                String mobiles = resultSet.getString("mobile_numbers");

                if (mobiles != null && !mobiles.isEmpty()) {
                    user.setMobileNumbers(
                            new ArrayList<>(List.of(mobiles.split(",")))
                    );
                } else {
                    user.setMobileNumbers(new ArrayList<>());
                }

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    // DELETE
    public boolean deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}