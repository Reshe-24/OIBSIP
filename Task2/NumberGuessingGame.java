import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {

    JLabel titleLabel, messageLabel, attemptsLabel, scoreLabel;
    JTextField guessField;
    JButton checkButton, newGameButton;

    int randomNumber;
    int attemptsLeft = 10;
    int score = 0;
    int round = 1;

    Random random = new Random();

    NumberGuessingGame() {

        setTitle("Number Guessing Game");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        titleLabel = new JLabel("NUMBER GUESSING GAME");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(80, 20, 300, 30);
        add(titleLabel);

        // Message
        messageLabel = new JLabel("Guess a number between 1 and 100");
        messageLabel.setBounds(100, 70, 250, 30);
        add(messageLabel);

        // Text Field
        guessField = new JTextField();
        guessField.setBounds(150, 110, 120, 35);
        add(guessField);

        // Check Button
        checkButton = new JButton("Check");
        checkButton.setBounds(90, 170, 100, 40);
        checkButton.addActionListener(this);
        add(checkButton);

        // New Game Button
        newGameButton = new JButton("New Round");
        newGameButton.setBounds(220, 170, 120, 40);
        newGameButton.addActionListener(this);
        add(newGameButton);

        // Attempts Label
        attemptsLabel = new JLabel("Attempts Left: 10");
        attemptsLabel.setBounds(80, 230, 150, 30);
        add(attemptsLabel);

        // Score Label
        scoreLabel = new JLabel("Score: 0   Round: 1");
        scoreLabel.setBounds(240, 230, 150, 30);
        add(scoreLabel);

        generateRandomNumber();

        setVisible(true);
    }

    // Generate random number
    void generateRandomNumber() {
        randomNumber = random.nextInt(100) + 1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Check Button Action
        if (e.getSource() == checkButton) {

            try {
                int userGuess = Integer.parseInt(guessField.getText());

                attemptsLeft--;

                if (userGuess == randomNumber) {

                    int points = attemptsLeft * 10;
                    score += points;

                    JOptionPane.showMessageDialog(this,
                            "Correct Guess!\nYou earned " + points + " points.");

                    scoreLabel.setText("Score: " + score + "   Round: " + round);

                    checkButton.setEnabled(false);

                } else if (userGuess < randomNumber) {
                    messageLabel.setText("Too Low! Try Again.");
                } else {
                    messageLabel.setText("Too High! Try Again.");
                }

                attemptsLabel.setText("Attempts Left: " + attemptsLeft);

                // No attempts left
                if (attemptsLeft == 0 && userGuess != randomNumber) {

                    JOptionPane.showMessageDialog(this,
                            "Game Over!\nCorrect Number was: " + randomNumber);

                    checkButton.setEnabled(false);
                }

                guessField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number.");
            }
        }

        // New Round Button Action
        if (e.getSource() == newGameButton) {

            round++;
            attemptsLeft = 10;

            generateRandomNumber();

            messageLabel.setText("Guess a number between 1 and 100");
            attemptsLabel.setText("Attempts Left: 10");
            scoreLabel.setText("Score: " + score + "   Round: " + round);

            guessField.setText("");

            checkButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}