package business;

import dao.UserDAO;
import entity.User;

import java.util.List;

public class UserManager {
    private final UserDAO userDAO;

    public UserManager() {
        this.userDAO = new UserDAO();
    }

    public boolean validateUser(String username, String password) {
        User user = this.userDAO.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public User getUserByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    public List<User> getUsersByRole(String role) {
        return this.userDAO.getUsersByRole(role);
    }

    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    public void deleteUser(int id) {
        this.userDAO.deleteUser(id);
    }

    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }
}
