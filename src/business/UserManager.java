package business;

import dao.UserDAO;
import entity.User;

import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {
        userDAO = new UserDAO();
    }

    public boolean validateUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> getUsersByRole(String role) {
        return userDAO.getUsersByRole(role);
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
