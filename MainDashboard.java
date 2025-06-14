
package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("Health & Fitness Tracking - Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel("Health & Fitness Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JButton userButton = new JButton("User Management");
        JButton activityButton = new JButton("Activity Tracking");
        JButton nutritionButton = new JButton("Nutrition Management");
        JButton goalButton = new JButton("Goal Setting");
        JButton sleepButton = new JButton("Sleep Tracking");
        JButton medicalButton = new JButton("Medical Records");
        JButton mentalWellnessButton = new JButton("Mental Wellness Tracking");
        JButton logoutButton = new JButton("Logout");
        JButton healthCalculatorButton = new JButton("Health Calculators");

        // Add ActionListeners to each button to open respective frames
        userButton.addActionListener(e -> openUserManagement());
//        activityButton.addActionListener(e -> openActivityTracking());
//        nutritionButton.addActionListener(e -> openNutritionManagement());
//        goalButton.addActionListener(e -> openGoalSetting());
//        sleepButton.addActionListener(e -> openSleepTracking());
//        medicalButton.addActionListener(e -> openMedicalRecords());
//        mentalWellnessButton.addActionListener(e -> openMentalWellnessTracking());
        logoutButton.addActionListener(e -> logout());
        healthCalculatorButton.addActionListener(e -> healthCalculatorButtonActionListener());

        // Add buttons to the panel
        buttonPanel.add(userButton);
        buttonPanel.add(activityButton);
        buttonPanel.add(nutritionButton);
        buttonPanel.add(goalButton);
        buttonPanel.add(sleepButton);
        buttonPanel.add(medicalButton);
        buttonPanel.add(mentalWellnessButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(healthCalculatorButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openUserManagement() {
        new UserManagementFrame().setVisible(true);
    }

//    private void openActivityTracking() {
//        new ActivityTrackingFrame().setVisible(true);
//    }
//
//    private void openNutritionManagement() {
//        new NutritionManagementFrame().setVisible(true);
//    }
//
//    private void openGoalSetting() {
//        new GoalSettingFrame().setVisible(true);
//    }
//
//    private void openSleepTracking() {
//        new SleepTrackingFrame().setVisible(true);
//    }
//
//    private void openMedicalRecords() {
//        new MedicalRecordsFrame().setVisible(true);
//    }
//
//    private void openMentalWellnessTracking() {
//        new MentalWellnessTrackingFrame().setVisible(true);
//    }
//    
    private void healthCalculatorButtonActionListener() {
        new HealthCalculatorFrame().setVisible(true);
    }

    private void logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            dispose();  // Close the dashboard window
            JOptionPane.showMessageDialog(null, "Logged out successfully.");
            System.exit(0);  // Exit the application
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
    }
}

class UserManagementFrame extends JFrame {
    private JTextField UserIDField, NameField, AgeField, GenderField, HeightField, WeightField, EmailField;
    private JButton insertButton, updateButton, deleteButton, viewButton;

    public UserManagementFrame() {
        setTitle("User Management");
        setSize(500, 300);
        setLayout(new GridLayout(8, 2, 10, 10));

        UserIDField = new JTextField();
        NameField = new JTextField();
        AgeField = new JTextField();
        GenderField = new JTextField();
        HeightField = new JTextField();
        WeightField = new JTextField();
        EmailField = new JTextField();

        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View All");

        add(new JLabel("UserID:"));
        add(UserIDField);
        add(new JLabel("Name:"));
        add(NameField);
        add(new JLabel("Age:"));
        add(AgeField);
        add(new JLabel("Gender:"));
        add(GenderField);
        add(new JLabel("Height:"));
        add(HeightField);
        add(new JLabel("Weight:"));
        add(WeightField);
        add(new JLabel("Email:"));
        add(EmailField);
        
        add(insertButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        // Add ActionListeners
        insertButton.addActionListener(e -> insertUser());
        updateButton.addActionListener(e -> updateUser());
        deleteButton.addActionListener(e -> deleteUser());
        viewButton.addActionListener(e -> viewUsers());
    }

    private void insertUser() {
        int UserID = Integer.parseInt(UserIDField.getText());
        String Name = NameField.getText();
        int Age = Integer.parseInt(AgeField.getText());
        String Gender = GenderField.getText();
        float Height = Float.parseFloat(HeightField.getText());
        float Weight = Float.parseFloat(WeightField.getText());
        String Email = EmailField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "root")) {
            String sql = "INSERT INTO User (UserID, Name, Age, Gender, Height, Weight, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, UserID);
            stmt.setString(2, Name);
            stmt.setInt(3, Age);
            stmt.setString(4, Gender);
            stmt.setFloat(5, Height);
            stmt.setFloat(6, Weight);
            stmt.setString(7, Email);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting user.");
        }
    }

    private void updateUser() {
        int UserID = Integer.parseInt(UserIDField.getText());
        String Name = NameField.getText();
        int Age = Integer.parseInt(AgeField.getText());
        String Gender = GenderField.getText();
        float Height = Float.parseFloat(HeightField.getText());
        float Weight = Float.parseFloat(WeightField.getText());
        String Email = EmailField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "root")) {
            String sql = "UPDATE User SET Name = ?, Age = ?, Gender = ?, Height = ?, Weight = ?, Email = ? WHERE UserID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, Name);
            stmt.setInt(2, Age);
            stmt.setString(3, Gender);
            stmt.setFloat(4, Height);
            stmt.setFloat(5, Weight);
            stmt.setString(6, Email);
            stmt.setInt(7, UserID);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating user.");
        }
    }

    private void deleteUser() {
        String UserID = UserIDField.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "root")) {
            String sql = "DELETE FROM User WHERE UserID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, UserID);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting user.");
        }
    }

    private void viewUsers() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "root")) {
            String sql = "SELECT * FROM User";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            StringBuilder result = new StringBuilder("Users:\n");

            while (rs.next()) {
                result.append("UserID: ").append(rs.getString("UserID"))
                      .append(", Name: ").append(rs.getString("Name"))
                      .append(", Age: ").append(rs.getInt("Age"))
                      .append(", Gender: ").append(rs.getString("Gender"))
                      .append(", Height: ").append(rs.getFloat("Height"))
                      .append(", Weight: ").append(rs.getFloat("Weight"))
                      .append(", Email: ").append(rs.getString("Email"))
                      .append("\n");
            }
            JOptionPane.showMessageDialog(this, result.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving users.");
        }
    }
}

class HealthCalculatorFrame extends JFrame {
    private JTextField ageField, heightField, weightField;
    private JButton calculateBMIButton, calculateCaloriesButton;

    public HealthCalculatorFrame() {
        setTitle("Health Calculators");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        ageField = new JTextField();
        heightField = new JTextField();
        weightField = new JTextField();

        calculateBMIButton = new JButton("Calculate BMI");
        calculateCaloriesButton = new JButton("Calculate Calories");

        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Height (cm):"));
        add(heightField);
        add(new JLabel("Weight (kg):"));
        add(weightField);

        add(calculateBMIButton);
        add(calculateCaloriesButton);

        calculateBMIButton.addActionListener(e -> calculateBMI());
        calculateCaloriesButton.addActionListener(e -> calculateCalories());
    }

    private void calculateBMI() {
        try {
            float height = Float.parseFloat(heightField.getText());
            float weight = Float.parseFloat(weightField.getText());
            float bmi = weight / ((height / 100) * (height / 100));
            JOptionPane.showMessageDialog(this, "Your BMI is: " + bmi);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for height and weight.");
        }
    }

    private void calculateCalories() {
        try {
            int age = Integer.parseInt(ageField.getText());
            float weight = Float.parseFloat(weightField.getText());
            float height = Float.parseFloat(heightField.getText());
            // Basal Metabolic Rate (BMR) calculation for males
            float bmr = 10 * weight + 6.25f * height - 5 * age + 5;
            JOptionPane.showMessageDialog(this, "Your BMR is: " + bmr + " calories/day.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for age, weight, and height.");
        }
    }
}
