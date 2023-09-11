import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelReservationForm extends JFrame {
    private JTextField pnrField;
    private JButton cancelButton;
    private UserDashboard userDashboard;

    public CancelReservationForm(UserDashboard userDashboard) {
        setTitle("Cancel Reservation");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.userDashboard = userDashboard;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel pnrLabel = new JLabel("PNR Number:");
        pnrField = new JTextField();
        cancelButton = new JButton("Cancel Reservation");

        panel.add(pnrLabel);
        panel.add(pnrField);
        panel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the entered PNR number
                String pnr = pnrField.getText();

                // Check if the PNR exists and remove it
                if (cancelReservation(pnr)) {
                    JOptionPane.showMessageDialog(null, "Reservation with PNR " + pnr + " has been not found.");
                } else {
                    JOptionPane.showMessageDialog(null, "Reservation with PNR " + pnr + " successfully deleted.");
                }

                // Close the cancel reservation form
                dispose();
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to cancel a reservation by PNR number
    private boolean cancelReservation(String pnr) {
        for (Reservation reservation : userDashboard.getReservations()) {
            if (reservation.getPnr().equals(pnr)) {
                userDashboard.getReservations().remove(reservation);
                return true;
            }
        }
        return false;
    }
}
