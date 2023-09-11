import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationForm extends JFrame {
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JComboBox<String> classTypeComboBox;
    private JTextField dateField;
    private JTextField fromField;
    private JTextField toField;
    private JButton insertButton;
    private UserDashboard userDashboard;

    public ReservationForm(UserDashboard userDashboard) {
        this.userDashboard = userDashboard;

        setTitle("Reservation Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel trainNumberLabel = new JLabel("Train Number:");
        trainNumberField = new JTextField();
        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameField = new JTextField();
        JLabel classTypeLabel = new JLabel("Class Type:");
        String[] classTypes = {"First Class", "Second Class", "Third Class"};
        classTypeComboBox = new JComboBox<>(classTypes);
        JLabel dateLabel = new JLabel("Date of Journey:");
        dateField = new JTextField();
        JLabel fromLabel = new JLabel("From:");
        fromField = new JTextField();
        JLabel toLabel = new JLabel("To:");
        toField = new JTextField();
        insertButton = new JButton("Insert");

        panel.add(trainNumberLabel);
        panel.add(trainNumberField);
        panel.add(trainNameLabel);
        panel.add(trainNameField);
        panel.add(classTypeLabel);
        panel.add(classTypeComboBox);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(fromLabel);
        panel.add(fromField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get reservation details from the form
                String trainNumber = trainNumberField.getText();
                String trainName = trainNameField.getText();
                String classType = (String) classTypeComboBox.getSelectedItem();
                String date = dateField.getText();
                String from = fromField.getText();
                String to = toField.getText();

                // Generate a unique PNR number
                String pnr = generatePNR(userDashboard);

                // Create a reservation object and add it to the UserDashboard
                Reservation reservation = new Reservation(pnr, trainNumber, trainName, classType, date, from, to);
                userDashboard.addReservation(reservation);

                // Display the generated PNR number
                JOptionPane.showMessageDialog(null, "Reservation successful! Your PNR is: " + pnr);

                // Close the reservation form
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to generate a unique PNR based on the user's first name initial
    private String generatePNR(UserDashboard userDashboard) {
        String initials = userDashboard.getUserInitials(); // Replace this with your logic to get user initials
        int nextPNRNumber = userDashboard.getNextPNRNumber();
        return initials + String.format("%03d", nextPNRNumber);
    }
}
