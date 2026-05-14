import javax.swing.*;
import java.awt.event.*;

public class OnlineExam extends JFrame implements ActionListener {

    JLabel l1, l2, question, timerLabel;
    JTextField tf;
    JPasswordField pf;
    JButton login, next, submit, update, logout;

    JRadioButton op1, op2, op3, op4;
    ButtonGroup bg;

    int current = 0;
    int score = 0;
    int time = 60;

    String user = "admin";
    String pass = "1234";

    Timer timer;

    String questions[][] = {
            {"Which language is platform independent?", "Java", "C", "C++", "Python"},
            {"Java was developed by?", "Sun Microsystems", "Google", "Microsoft", "Apple"},
            {"Which is used for GUI in Java?", "Swing", "Oracle", "MySQL", "Linux"},
            {"Which keyword is used for inheritance?", "extends", "implement", "import", "package"},
            {"Which company owns Java now?", "Oracle", "IBM", "Intel", "HP"}
    };

    int answers[] = {1, 1, 1, 1, 1};

    public OnlineExam() {

        // Login Page
        setTitle("Online Examination");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Username");
        l1.setBounds(150, 100, 100, 30);
        add(l1);

        tf = new JTextField();
        tf.setBounds(250, 100, 150, 30);
        add(tf);

        l2 = new JLabel("Password");
        l2.setBounds(150, 150, 100, 30);
        add(l2);

        pf = new JPasswordField();
        pf.setBounds(250, 150, 150, 30);
        add(pf);

        login = new JButton("Login");
        login.setBounds(240, 220, 100, 30);
        login.addActionListener(this);
        add(login);

        setVisible(true);
    }

    // Exam Page
    void startExam() {

        getContentPane().removeAll();
        repaint();

        setLayout(null);

        timerLabel = new JLabel("Time Left: 60");
        timerLabel.setBounds(420, 20, 150, 30);
        add(timerLabel);

        question = new JLabel();
        question.setBounds(50, 80, 500, 30);
        add(question);

        op1 = new JRadioButton();
        op1.setBounds(80, 130, 300, 30);
        add(op1);

        op2 = new JRadioButton();
        op2.setBounds(80, 170, 300, 30);
        add(op2);

        op3 = new JRadioButton();
        op3.setBounds(80, 210, 300, 30);
        add(op3);

        op4 = new JRadioButton();
        op4.setBounds(80, 250, 300, 30);
        add(op4);

        bg = new ButtonGroup();
        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        next = new JButton("Next");
        next.setBounds(150, 310, 100, 30);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(280, 310, 100, 30);
        submit.addActionListener(this);
        add(submit);

        update = new JButton("Update Password");
        update.setBounds(20, 20, 160, 30);
        update.addActionListener(this);
        add(update);

        logout = new JButton("Logout");
        logout.setBounds(220, 20, 100, 30);
        logout.addActionListener(this);
        add(logout);

        loadQuestion();

        // Timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time--;
                timerLabel.setText("Time Left: " + time);

                if (time == 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Time Up!");
                    showResult();
                }
            }
        });

        timer.start();

        setVisible(true);
    }

    // Load Questions
    void loadQuestion() {

        bg.clearSelection();

        question.setText((current + 1) + ". " + questions[current][0]);

        op1.setText(questions[current][1]);
        op2.setText(questions[current][2]);
        op3.setText(questions[current][3]);
        op4.setText(questions[current][4]);
    }

    // Check Answer
    void checkAnswer() {

        if (op1.isSelected() && answers[current] == 1)
            score++;

        if (op2.isSelected() && answers[current] == 2)
            score++;

        if (op3.isSelected() && answers[current] == 3)
            score++;

        if (op4.isSelected() && answers[current] == 4)
            score++;
    }

    // Result
    void showResult() {

        timer.stop();

        JOptionPane.showMessageDialog(this,
                "Exam Submitted\nYour Score: " + score + "/5");

        System.exit(0);
    }

    public void actionPerformed(ActionEvent e) {

        // Login
        if (e.getSource() == login) {

            String u = tf.getText();
            String p = new String(pf.getPassword());

            if (u.equals(user) && p.equals(pass)) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful");

                startExam();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Wrong Username or Password");
            }
        }

        // Next
        if (e.getSource() == next) {

            checkAnswer();

            current++;

            if (current < 5) {
                loadQuestion();
            } else {
                showResult();
            }
        }

        // Submit
        if (e.getSource() == submit) {

            checkAnswer();
            showResult();
        }

        // Update Password
        if (e.getSource() == update) {

            String newPass = JOptionPane.showInputDialog(
                    this,
                    "Enter New Password"
            );

            if (newPass != null && !newPass.equals("")) {

                pass = newPass;

                JOptionPane.showMessageDialog(this,
                        "Password Updated");
            }
        }

        // Logout
        if (e.getSource() == logout) {

            JOptionPane.showMessageDialog(this,
                    "Logged Out");

            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new OnlineExam();
    }
}