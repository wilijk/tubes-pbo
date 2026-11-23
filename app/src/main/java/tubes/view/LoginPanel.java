package tubes.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tubes.controller.AuthController;
import tubes.model.User;
import tubes.model.enumm.Role;

public class LoginPanel extends JPanel {
    
    private MainFrame mainFrame;
    private AuthController authController;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblStatus;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.authController = new AuthController();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblJudul = new JLabel("Lgin WNStore");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblJudul, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);

        txtUsername = new JTextField(18);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        txtPassword = new JPasswordField(18);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtPassword, gbc);

        JButton btnLogin = new JButton("login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnLogin, gbc);

        JButton btnKeRegister = new JButton("Daftar customer baru");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnKeRegister, gbc);

        lblStatus = new JLabel("masukkan akun anda.");
        lblStatus.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(lblStatus, gbc);

        btnLogin.addActionListener(e -> prosesLogin());
        btnKeRegister.addActionListener(e -> {
            lblStatus.setText(" ");
            mainFrame.showPage("REGISTER");
        });
    }

    private void prosesLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        try {
            User user = authController.login(username, password);
            txtUsername.setText("");
            txtPassword.setText("");
            lblStatus.setText("login berhasil.");

            if (user.getRole() == Role.ADMIN) mainFrame.showPage("ADMIN");
            else if (user.getRole() == Role.WORKER) mainFrame.showPage("WORKER");
            else mainFrame.showPage("CUSTOMER");
        } catch (Exception ex) {
            lblStatus.setForeground(Color.RED);
            lblStatus.setText(ex.getMessage());
        }
    }
}