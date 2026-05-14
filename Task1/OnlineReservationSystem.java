import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class OnlineReservationSystem extends JFrame implements ActionListener {

    JLabel loginLabel, passLabel;
    JTextField loginField;
    JPasswordField passField;
    JButton loginBtn;

    JButton reserveBtn, cancelBtn, viewBtn, submitBtn, backBtn, searchBtn, cancelTicketBtn;
    JTextField nameField, trainNoField, trainNameField, dateField, fromField, toField, pnrField;
    JComboBox<String> classBox;
    JTable table;
    DefaultTableModel model;
    ArrayList<String[]> bookings = new ArrayList<>();
    int pnrCounter = 1000;
    public OnlineReservationSystem() {

        setTitle("Online Reservation System");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        showLoginPage();

        setVisible(true);
    }
        public void showLoginPage() {

        getContentPane().removeAll();

        loginLabel = new JLabel("Login ID");
        loginLabel.setBounds(100, 100, 100, 30);
        add(loginLabel);

        loginField = new JTextField();
        loginField.setBounds(200, 100, 150, 30);
        add(loginField);

        passLabel = new JLabel("Password");
        passLabel.setBounds(100, 150, 100, 30);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(200, 150, 150, 30);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(180, 220, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        repaint();
        revalidate();
    }

        public void showMenu() {

        getContentPane().removeAll();

        reserveBtn = new JButton("Reserve");
        reserveBtn.setBounds(170, 100, 120, 30);
        reserveBtn.addActionListener(this);
        add(reserveBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(170, 150, 120, 30);
        cancelBtn.addActionListener(this);
        add(cancelBtn);

        viewBtn = new JButton("View Bookings");
        viewBtn.setBounds(170, 200, 120, 30);
        viewBtn.addActionListener(this);
        add(viewBtn);

        repaint();
        revalidate();
    }

        public void showReservationForm() {

        getContentPane().removeAll();

        JLabel l1 = new JLabel("Name");
        l1.setBounds(50, 40, 100, 20);
        add(l1);

        nameField = new JTextField();
        nameField.setBounds(170, 40, 150, 20);
        add(nameField);

        JLabel l2 = new JLabel("Train No");
        l2.setBounds(50, 70, 100, 20);
        add(l2);

        trainNoField = new JTextField();
        trainNoField.setBounds(170, 70, 150, 20);
        add(trainNoField);

        JLabel l3 = new JLabel("Train Name");
        l3.setBounds(50, 100, 100, 20);
        add(l3);

        trainNameField = new JTextField();
        trainNameField.setBounds(170, 100, 150, 20);
        add(trainNameField);

        JLabel l4 = new JLabel("Class");
        l4.setBounds(50, 130, 100, 20);
        add(l4);

        String classes[] = {"AC", "Sleeper", "First Class"};
        classBox = new JComboBox<>(classes);
        classBox.setBounds(170, 130, 150, 20);
        add(classBox);

        JLabel l5 = new JLabel("Date");
        l5.setBounds(50, 160, 100, 20);
        add(l5);

        dateField = new JTextField();
        dateField.setBounds(170, 160, 150, 20);
        add(dateField);

        JLabel l6 = new JLabel("From");
        l6.setBounds(50, 190, 100, 20);
        add(l6);

        fromField = new JTextField();
        fromField.setBounds(170, 190, 150, 20);
        add(fromField);

        JLabel l7 = new JLabel("To");
        l7.setBounds(50, 220, 100, 20);
        add(l7);

        toField = new JTextField();
        toField.setBounds(170, 220, 150, 20);
        add(toField);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(120, 280, 100, 30);
        submitBtn.addActionListener(this);
        add(submitBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(240, 280, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        repaint();
        revalidate();
    }

        public void showCancelForm() {

        getContentPane().removeAll();

        JLabel l1 = new JLabel("Enter PNR");
        l1.setBounds(80, 50, 100, 20);
        add(l1);

        pnrField = new JTextField();
        pnrField.setBounds(180, 50, 120, 20);
        add(pnrField);

        searchBtn = new JButton("Search");
        searchBtn.setBounds(80, 100, 100, 30);
        searchBtn.addActionListener(this);
        add(searchBtn);

        cancelTicketBtn = new JButton("Cancel Ticket");
        cancelTicketBtn.setBounds(200, 100, 120, 30);
        cancelTicketBtn.addActionListener(this);
        add(cancelTicketBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 250, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        repaint();
        revalidate();
    }
    public void showBookings() {

        getContentPane().removeAll();

        String columns[] = {"PNR", "Name", "Train No", "Train Name", "From", "To"};

        model = new DefaultTableModel(columns, 0);

        for (String[] booking : bookings) {
            model.addRow(booking);
        }

        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 30, 440, 200);
        add(pane);
        backBtn = new JButton("Back");
        backBtn.setBounds(180, 260, 100, 30);
        backBtn.addActionListener(this);
        add(backBtn);
        repaint();
        revalidate();
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == loginBtn) {

            String id = loginField.getText();
            String pass = new String(passField.getPassword());

            if (id.equals("admin") && pass.equals("admin123")) {

                JOptionPane.showMessageDialog(this, "Login Successful");

                showMenu();

            } else {

                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        }

        if (e.getSource() == reserveBtn) {
            showReservationForm();
        }

        if (e.getSource() == cancelBtn) {
            showCancelForm();
        }

        if (e.getSource() == viewBtn) {
            showBookings();
        }

            if (e.getSource() == submitBtn) {

            String pnr = String.valueOf(pnrCounter++);

            String data[] = {
                    pnr,
                    nameField.getText(),
                    trainNoField.getText(),
                    trainNameField.getText(),
                    fromField.getText(),
                    toField.getText()
            };

            bookings.add(data);

            JOptionPane.showMessageDialog(this,
                    "Reservation Successful\nPNR: " + pnr);

            showMenu();
        }

            if (e.getSource() == searchBtn) {

            String pnr = pnrField.getText();
            boolean found = false;

            for (String[] booking : bookings) {

                if (booking[0].equals(pnr)) {

                    JOptionPane.showMessageDialog(this,
                            "PNR Found\nName: " + booking[1] +
                                    "\nTrain: " + booking[3]);

                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "PNR Not Found");
            }
        }
            if (e.getSource() == cancelTicketBtn) {

            String pnr = pnrField.getText();
            boolean removed = false;

            for (int i = 0; i < bookings.size(); i++) {

                if (bookings.get(i)[0].equals(pnr)) {

                    bookings.remove(i);

                    JOptionPane.showMessageDialog(this,
                            "Ticket Cancelled");

                    removed = true;
                    break;
                }
            }

            if (!removed) {
                JOptionPane.showMessageDialog(this, "PNR Not Found");
            }
        }

        if (e.getSource() == backBtn) {
            showMenu();
        }
    }

    public static void main(String[] args) {

        new OnlineReservationSystem();
    }
}