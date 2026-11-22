package tubes.controller;

import tubes.config.SessionManager;
import tubes.model.User;
import tubes.repository.UserRepository;

public class AuthController {
    private UserRepository userRepo;

    public AuthController() {
        this.userRepo = new UserRepository();
    }

    public User login(String username, String password) throws Exception {
        User user = userRepo.login(username, password);
        SessionManager.getInstance().setCurrentUser(user);
        return user;
    }

    public void registerCustomer(String username, String password, String confirmPassword) throws Exception {
        userRepo.registerCustomer(username, password, confirmPassword);
    }

    public void logout() {
        SessionManager.getInstance().logout();
    }
}