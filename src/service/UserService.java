package service;
import exception.UserNotFoundException;
import model.User;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!user.getEmail().matches(emailRegex)) {
            System.out.println("Invalid email.");
            return;
        }

        String mobileRegex = "^[0-9]{10}$";

        for (String mobile : user.getMobileNumbers()) {

            if (!mobile.matches(mobileRegex)) {
                System.out.println("Invalid mobile number: " + mobile);
                return;
            }
        }

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            System.out.println("User already exists with this email.");
            return;
        }

        userRepository.addUser(user);

        System.out.println("User created successfully.");
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public  User getUserById(int id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return user;
    }


    public boolean updateUser(User user) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!user.getEmail().matches(emailRegex)) {
            System.out.println("Invalid email.");
            return false;
        }

        String mobileRegex = "^[0-9]{10}$";

        for (String mobile : user.getMobileNumbers()) {

            if (!mobile.matches(mobileRegex)) {
                System.out.println("Invalid mobile number: " + mobile);
                return false;
            }
        }


        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getId() != user.getId()) {
            System.out.println("User already exists with this email.");
            return false;
        }

        return userRepository.updateUser(user);
    }


    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }



}


