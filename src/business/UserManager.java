package business;

import dao.UserDAO;
import entity.User;

import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByUsername(String username) {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean validateUser(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
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
}
