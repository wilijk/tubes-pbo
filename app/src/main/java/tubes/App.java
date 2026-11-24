package tubes;

import javax.swing.SwingUtilities;

import tubes.view.MainFrame;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame app = new MainFrame();
            app.setVisible(true);
        });
    }
}