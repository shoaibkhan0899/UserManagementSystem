//import model.User;
//import repository.UserRepository;
//import service.UserService;
//import java.util.Scanner;
//import java.util.List;
//
//public class Main {
//
//    public static void main (String[] args){
//
//        Scanner scanner = new Scanner(System.in);
//        UserRepository repository = new UserRepository();
//        UserService service = new UserService(repository);
//
//        User user  = new User(
//                1,
//                "Shoaib",
//                "shoaib@gmail.com",
//                List.of("9999999999", "8888888888")
//        );
//
//
//
//        service.createUser(user);
//
//
//
//        User updatedUser = new User();
//
//        updatedUser.setId(1);
//        updatedUser.setName("Ahmed");
//        updatedUser.setEmail("ahmed@gmail.com");
//        updatedUser.setMobileNumbers(
//                List.of("7777777777", "6666666666")
//        );
//
//        boolean updated = service.updateUser(updatedUser);
//
//        if (updated) {
//            System.out.println("User updated successfully.");
//        } else {
//            System.out.println("User not found.");
//        }
//
//
//
//        User foundUser = service.getUserById(1);
//        System.out.println("User found: " + foundUser);
//
//
//
//
//        List<User> users = service.getAllUsers();
//
//        for (User user1 : users) {
//            System.out.println(user1);
//        }
//
//
//
//        boolean deleted = service.deleteUser(1);
//
//        if (deleted) {
//            System.out.println("User deleted successfully.");
//        } else {
//            System.out.println("User not found.");
//        }
//
//    }
//}


import db.DatabaseConnection;
import model.User;
import repository.UserRepository;
import service.UserService;
import exception.UserNotFoundException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                System.out.println("Database connected successfully.");
            } else {
                System.out.println("Database connection failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== User Management System =====");
            System.out.println("1. Create User");
            System.out.println("2. Fetch User");
            System.out.println("3. Fetch All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {


                case 1:

                    scanner.nextLine(); // leftover newline consume

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    System.out.print("How many mobile numbers? ");
                    int numberCount = scanner.nextInt();

                    List<String> mobileNumbers = new ArrayList<>();

                    for (int i = 0; i < numberCount; i++) {

                        System.out.print("Enter mobile number " + (i + 1) + ": ");
                        mobileNumbers.add(scanner.next());
                    }

                    System.out.print("Enter user id: ");
                    int id = scanner.nextInt();

                    User user = new User(
                            id,
                            name,
                            email,
                            mobileNumbers
                    );

                    service.createUser(user);

                    break;

                case 2:
                    System.out.print("Enter user id: ");
                    int searchId = scanner.nextInt();

                    try {
                        User foundUser = service.getUserById(searchId);
                        System.out.println("User found: " + foundUser);
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    List<User> users = service.getAllUsers();

                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {

                        for (User user1 : users) {
                            System.out.println(user1);
                        }
                    }
                    break;

                case 4:

                    System.out.print("Enter user id: ");
                    int updateId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter new name: ");
                    String updateName = scanner.nextLine();

                    System.out.print("Enter new email: ");
                    String updateEmail = scanner.nextLine();

                    System.out.print("How many mobile numbers? ");
                    int updateNumberCount = scanner.nextInt();

                    List<String> updateMobileNumbers = new ArrayList<>();

                    for (int i = 0; i < updateNumberCount; i++) {

                        System.out.print("Enter mobile number " + (i + 1) + ": ");
                        updateMobileNumbers.add(scanner.next());
                    }

                    User updatedUser = new User(
                            updateId,
                            updateName,
                            updateEmail,
                            updateMobileNumbers
                    );

                    boolean updated = service.updateUser(updatedUser);

                    if (updated) {
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter user id: ");
                    int deleteId = scanner.nextInt();

                    boolean deleted = service.deleteUser(deleteId);

                    if (deleted) {
                        System.out.println("User deleted successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6:
                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
