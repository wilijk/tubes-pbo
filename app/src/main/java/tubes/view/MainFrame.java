package tubes.view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private AdminPanel adminPanel;
    private WorkerPanel workerPanel;
    private CustomerPanel customerPanel;

    public MainFrame() {
        setTitle("WnStore - Jasa Joki & Top Up Game");
        setSize(950, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        adminPanel = new AdminPanel(this);
        workerPanel = new WorkerPanel(this);
        customerPanel = new CustomerPanel(this);

        mainContainer.add(loginPanel, "LOGIN");
        mainContainer.add(registerPanel, "REGISTER");
        mainContainer.add(adminPanel, "ADMIN");
        mainContainer.add(workerPanel, "WORKER");
        mainContainer.add(customerPanel, "CUSTOMER");

        add(mainContainer);
        showPage("LOGIN");
    }

    public void showPage(String pageName) {
        if (pageName.equals("ADMIN")) {
            adminPanel.refreshData();
        } else if (pageName.equals("WORKER")) {
            workerPanel.refreshData();
        } else if (pageName.equals("CUSTOMER")) {
            customerPanel.refreshData();
        }
        cardLayout.show(mainContainer, pageName);
    }
}
