package tubes.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tubes.config.SessionManager;
import tubes.controller.AuthController;
import tubes.controller.WorkerController;
import tubes.model.TransaksiRecord;
import tubes.model.enumm.StatusJoki;

public class WorkerPanel extends JPanel {
    private MainFrame mainFrame;
    private WorkerController workerController;
    private AuthController authController;

    private JTable tableTugas;
    private DefaultTableModel tableModel;
    private JComboBox<StatusJoki> cbStatus;
    private JLabel lblStatus;

    public WorkerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.workerController = new WorkerController();
        this.authController = new AuthController();
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogout = new JButton("Logout");
        topPanel.add(new JLabel("Dashboard Worker Joki WnStore"));
        topPanel.add(btnLogout);
        add(topPanel, BorderLayout.NORTH);

        String[] kolom = {"ID", "Customer", "Game", "ID Akun", "Target Joki", "Status"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTugas = new JTable(tableModel);
        add(new JScrollPane(tableTugas), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbStatus = new JComboBox<>(StatusJoki.values());
        JButton btnUpdate = new JButton("Simpan Progres Joki");
        JButton btnRefresh = new JButton("Refresh");
        lblStatus = new JLabel("Pilih tugas joki untuk mengubah status.");
        lblStatus.setForeground(Color.BLUE);

        bottomPanel.add(new JLabel("Progres Akun:"));
        bottomPanel.add(cbStatus);
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnRefresh);
        bottomPanel.add(lblStatus);

        add(bottomPanel, BorderLayout.SOUTH);

        btnUpdate.addActionListener(e -> {
            int row = tableTugas.getSelectedRow();
            if (row == -1) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText("Pilih tugas pada tabel terlebih dahulu!");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            StatusJoki statusBaru = (StatusJoki) cbStatus.getSelectedItem();
            try {
                workerController.updateProgresJoki(id, statusBaru);
                lblStatus.setForeground(new Color(0, 128, 0));
                lblStatus.setText("Progres tugas #" + id + " berhasil diperbarui menjadi " + statusBaru.name());
                refreshData();
            } catch (Exception ex) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText(ex.getMessage());
            }
        });

        btnRefresh.addActionListener(e -> refreshData());
        btnLogout.addActionListener(e -> {
            authController.logout();
            mainFrame.showPage("LOGIN");
        });
    }

    public void refreshData() {
        if (SessionManager.getInstance().getCurrentUser() == null) return;
        tableModel.setRowCount(0);
        try {
            List<TransaksiRecord> list = workerController.getTugasWorker();
            for (TransaksiRecord t : list) {
                tableModel.addRow(new Object[]{
                    t.getId(), t.getCustomerUsername(), t.getNamaGame(),
                    t.getIdAkunGame(), t.getDetailPesanan(), t.getStatus()
                });
            }
        } catch (Exception e) {
            lblStatus.setText("Gagal memuat tugas: " + e.getMessage());
        }
    }
}
