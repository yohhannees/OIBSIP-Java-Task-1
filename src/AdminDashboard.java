import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminDashboard extends JFrame {
    private JList<String> reservationList;
    private DefaultListModel<String> listModel;
    private UserDashboard userDashboard;

    public AdminDashboard(UserDashboard userDashboard) {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.userDashboard = userDashboard;

        listModel = new DefaultListModel<>();
        reservationList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(reservationList);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add a table-like header row
        JPanel headerPanel = new JPanel(new GridLayout(1, 3));
        JLabel nameLabel = new JLabel("Name");
        JLabel pnrLabel = new JLabel("PNR Code");
        JLabel actionLabel = new JLabel("Action");
        headerPanel.add(nameLabel);
        headerPanel.add(pnrLabel);
        headerPanel.add(actionLabel);
        panel.add(headerPanel, BorderLayout.NORTH);

        // Add sample data (7 names and PNR codes)
        addSampleData();

        JButton deleteButton = new JButton("Delete Reservation");
        panel.add(deleteButton, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the selected PNR number to delete
                int selectedIndex = reservationList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedPNR = listModel.getElementAt(selectedIndex);
                    if (deleteReservation(selectedPNR)) {
                        listModel.removeElementAt(selectedIndex);
                        JOptionPane.showMessageDialog(null, "Reservation with PNR " + selectedPNR + " has been deleted.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Unable to delete the reservation.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to update the reservation list
    public void updateReservationList() {
        listModel.clear();
        List<Reservation> reservations = userDashboard.getReservations();
        for (Reservation reservation : reservations) {
            listModel.addElement(reservation.getPnr());
        }
    }

    // Method to delete a reservation by PNR number
    private boolean deleteReservation(String pnr) {
        List<Reservation> reservations = userDashboard.getReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    // Helper method to add sample data
    private void addSampleData() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace"};
        Random random = new Random();

        for (String name : names) {
            String pnr = String.format("%04d", random.nextInt(10000));
            listModel.addElement(name + "\t\t" + pnr);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDashboard userDashboard = new UserDashboard();
            new AdminDashboard(userDashboard);
        });
    }
}
