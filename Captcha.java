import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Captcha extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel captchaLabel;
    private JButton submitButton;
    private String captchaString;

    public Captcha() {
        setTitle("Sai's Captcha");
        setSize(500, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        captchaString = generateCaptchaString();

        JPanel panel = new JPanel(new GridLayout(2, 2));
        captchaLabel = new JLabel(captchaString);
        captchaLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panel.add(captchaLabel);

        textField = new JTextField();
        panel.add(textField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private String generateCaptchaString() {
        Random random = new Random();
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            captcha.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
        }

        return captcha.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = textField.getText();
        if (userInput.equalsIgnoreCase(captchaString)) {
            JOptionPane.showMessageDialog(null, "Captcha verification successful!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid captcha. Please try again.");
            captchaString = generateCaptchaString();
            captchaLabel.setText(captchaString);
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new Captcha();
    }
}
