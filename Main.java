import java.util.*;
import java.util.concurrent.*;

class User {
    private String username;
    private String password;
    private String profile;
    private List<String> selectedAnswers;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = "";
        this.selectedAnswers = new ArrayList<>();
    }

    // Getters and setters for profile and selectedAnswers

    public void updateProfile(String newProfile) {
        this.profile = newProfile;
    }

    public void selectAnswer(String answer) {
        selectedAnswers.add(answer);
    }
}

class MCQQuiz {
    private List<String> questions;

    public MCQQuiz(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getQuestions() {
        return questions;
    }
}

public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        // Create sample user
        users.put("user1", new User("user1", "password"));

        // Simulate a session
        String currentUser = "user1";

        // Login
        boolean loggedIn = login(currentUser, "password");
        if (loggedIn) {
            // Update profile
            updateProfile(currentUser, "New profile information");

            // Start timer
            startTimer(60);

            // Simulate selecting answers
            selectAnswer(currentUser, "Answer 1");
            selectAnswer(currentUser, "Answer 2");

            // Wait for timer to expire
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Logout
            logout(currentUser);
        }
    }

    private static boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Login failed");
            return false;
        }
    }

    private static void updateProfile(String username, String newProfile) {
        User user = users.get(username);
        if (user != null) {
            user.updateProfile(newProfile);
            System.out.println("Profile updated: " + newProfile);
        } else {
            System.out.println("User not found");
        }
    }

    private static void selectAnswer(String username, String answer) {
        User user = users.get(username);
        if (user != null) {
            user.selectAnswer(answer);
            System.out.println("Answer selected: " + answer);
        } else {
            System.out.println("User not found");
        }
    }

    private static void startTimer(int durationInSeconds) {
        executorService.schedule(() -> {
            System.out.println("Time's up!");
        }, durationInSeconds, TimeUnit.SECONDS);
        System.out.println("Timer started for " + durationInSeconds + " seconds");
    }

    private static void logout(String username) {
        System.out.println("Logging out " + username);
        // Perform any necessary cleanup
    }
}
