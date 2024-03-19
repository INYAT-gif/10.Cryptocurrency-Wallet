import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class CryptocurrencyWalletApp {

    // Simulated in-memory storage for user data
    private Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(CryptocurrencyWalletApp.class, args);
    }

    // API endpoint for user registration
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) {
        // Check if the username is already taken
        if (users.containsKey(request.getUsername())) {
            return "Username already exists";
        }

        // Create a new user and store in memory
        User newUser = new User(request.getUsername(), request.getPassword());
        users.put(newUser.getUsername(), newUser);

        return "User registered successfully";
    }

    // API endpoint for creating a wallet for a user
    @PostMapping("/createWallet")
    public String createWallet(@RequestParam String username) {
        // Check if the user exists
        if (!users.containsKey(username)) {
            return "User not found";
        }

        // Create and associate a wallet with the user
        Wallet newWallet = new Wallet();
        users.get(username).setWallet(newWallet);

        return "Wallet created successfully for user: " + username;
    }

    // DTO for user registration request
    static class UserRegistrationRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // User class representing a user entity
    static class User {
        private String username;
        private String password;
        private Wallet wallet;

        // Constructor, getters, and setters
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public void setWallet(Wallet wallet) {
            this.wallet = wallet;
        }
    }

    // Wallet class representing a user's cryptocurrency wallet
    static class Wallet {
        // Placeholder for wallet properties and functionalities
    }
}
