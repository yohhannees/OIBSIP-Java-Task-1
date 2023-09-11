import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends JFrame {
    private JButton reserveButton;
    private JButton cancelReservationButton;
    private List<Reservation> reservations = new ArrayList<>();
    private AdminDashboard adminDashboard;

    public UserDashboard() {
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        reserveButton = new JButton("Reserve a Train Ticket");
        cancelReservationButton = new JButton("Cancel Reservation");

        panel.add(reserveButton);
        panel.add(cancelReservationButton);

        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the reservation form
                new ReservationForm(UserDashboard.this);
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the cancel reservation form
                new CancelReservationForm(UserDashboard.this);
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to add a reservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        if (adminDashboard != null) {
            adminDashboard.updateReservationList();
        }
    }

    // Method to get all reservations
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setAdminDashboard(AdminDashboard adminDashboard) {
        this.adminDashboard = adminDashboard;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginForm();
            }
        });
    }

    public String getUserInitials() {
        return "JD";
    }

    public int getNextPNRNumber() {
        return 123456;
    }
}
