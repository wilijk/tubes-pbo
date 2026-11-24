package tubes.view;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tubes.controller.AuthController;

public class RegisterPanel extends JPanel {
    private MainFrame mainFrame;
    private AuthController authController;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JLabel status;

    public RegisterPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.authController = authController;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel judul = new JLabel("REGISTER");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(judul, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username Baru:"), gbc);

        username = new JTextField(18);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password Baru:"), gbc);

        password = new JPasswordField(18);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Konfirmasi Password:"), gbc);

        confirmPassword = new JPasswordField(18);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(confirmPassword, gbc);

        JButton btnDaftar = new JButton("Daftar Sekarang");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnDaftar, gbc);

        JButton btnKembali = new JButton("Kembali ke Login");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnKembali, gbc);

        status = new JLabel(" ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(status, gbc);

        btnDaftar.addActionListener(e -> {
            try {
                String user = username.getText().trim();
                String pass = new String(password.getPassword()).trim();
                String confirm = new String(confirmPassword.getPassword()).trim();

                authController.registerCustomer(user, pass, confirm);
                status.setForeground(new Color(0, 128, 0));
                status.setText("Registrasi berhasil! Silakan kembali ke halaman Login.");
                username.setText("");
                password.setText("");
                confirmPassword.setText("");
            } catch (Exception ex) {
                status.setForeground(Color.RED);
                status.setText(ex.getMessage());
            }
        });

        btnKembali.addActionListener(e -> {
            status.setText(" ");
            mainFrame.showPage("LOGIN");
        });
    }
}
