import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JButton loginButton;
    private JButton cancelButton;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDashboard userDashboard;

    public LoginForm() {
        setTitle("Home Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        ButtonGroup buttonGroup = new ButtonGroup();
        adminRadioButton = new JRadioButton("Admin Mode");
        userRadioButton = new JRadioButton("User Mode");
        buttonGroup.add(adminRadioButton);
        buttonGroup.add(userRadioButton);
        adminRadioButton.setSelected(true); // Set admin mode as default

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        panel.add(adminRadioButton);
        panel.add(userRadioButton);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);
        panel.add(cancelButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (adminRadioButton.isSelected()) {
                    // Admin login
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (authenticateAdmin(username, password)) {
                        AdminDashboard adminDashboard = new AdminDashboard(userDashboard);
                        userDashboard.setAdminDashboard(adminDashboard);
                        dispose(); // Close the home page
                    } else {
                        JOptionPane.showMessageDialog(null, "Admin login failed. Please check your credentials.");
                    }
                } else {
                    // User login
                    new UserDashboard();
                    dispose(); // Close the home page
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to authenticate the admin (You can implement your own authentication logic)
    private boolean authenticateAdmin(String username, String password) {
        // Replace this with your authentication logic
        return username.equals("admin") && password.equals("12345");
    }

    public void setUserDashboard(UserDashboard userDashboard) {
        this.userDashboard = userDashboard;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm();
            }
        });
    }
}
