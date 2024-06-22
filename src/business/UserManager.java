package business;

import dao.UserDAO;
import entity.User;

import java.util.List;

/**
 * Business logic class for managing users.
 */
public class UserManager {
    private final UserDAO userDAO;

    /**
     * Constructs a new UserManager instance and initializes the UserDAO.
     */
    public UserManager() {
        this.userDAO = new UserDAO();
    }

    /**
     * Validates a user's credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user exists and the password matches, false otherwise
     */
    public boolean validateUser(String username, String password) {
        User user = this.userDAO.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user
     * @return the user with the specified username, or null if not found
     */
    public User getUserByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    /**
     * Retrieves users by their role.
     *
     * @param role the role of the users
     * @return a list of users with the specified role
     */
    public List<User> getUsersByRole(String role) {
        return this.userDAO.getUsersByRole(role);
    }

    /**
     * Adds a new user.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    /**
     * Updates an existing user.
     *
     * @param user the user to update
     */
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete
     */
    public void deleteUser(int id) {
        this.userDAO.deleteUser(id);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return the user with the specified id, or null if not found
     */
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }
}
