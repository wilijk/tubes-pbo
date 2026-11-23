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

import tubes.controller.AdminController;
import tubes.controller.AuthController;
import tubes.model.TransaksiRecord;

public class AdminPanel extends JPanel {
    private MainFrame mainFrame;
    private AdminController adminController;
    private AuthController authController;

    private JTable tableTransaksi;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbWorker;
    private JComboBox<String> cbStatusAdmin;
    private JLabel lblStatus;

    public void refreshData() {
        tableModel.setRowCount(0);
        cbWorker.removeAllItems();

        try {
            List<String> workers = adminController.getDaftarWorker();

            for (String w : workers) {
                cbWorker.addItem(w);
            }

            List<TransaksiRecord> list = adminController.getSemuaTransaksi();

            for (TransaksiRecord t : list) {
                String namaWorker;

                if (t.getWorkerUsername() == null) namaWorker = "-";
                else namaWorker = t.getWorkerUsername();

                tableModel.addRow(new Object[]{t.getId(), t.getCustomerUsername(), t.getJenisLayanan(), t.getNamaGame(), t.getIdAkunGame(), t.getDetailPesanan(), "Rp " + (long) t.getTotalHarga(), namaWorker, t.getStatus()});
            }
        } catch (Exception e) {
            lblStatus.setText("error memuat data: " + e.getMessage());
        }
    }

    public AdminPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.adminController = new AdminController();
        this.authController = new AuthController();
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogout = new JButton("Logout");
        topPanel.add(new JLabel("panel admin wnstore"));
        topPanel.add(btnLogout);
        add(topPanel, BorderLayout.NORTH);

        String[] kolom = {"ID", "Customer", "Jenis", "Game", "ID Akun", "Detail", "Harga", "Worker", "Status"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTransaksi = new JTable(tableModel);
        add(new JScrollPane(tableTransaksi), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbWorker = new JComboBox<>();
        JButton btnAssign = new JButton("Tugaskan worker(joki)");

        cbStatusAdmin = new JComboBox<>(new String[]{"DIPROSES", "SUDAH", "CANCELED"});
        JButton btnUpdateStatus = new JButton("update status(topup/joki)");
        JButton btnHapus = new JButton("hapus pesanan");
        JButton btnRefresh = new JButton("referesh");

        lblStatus = new JLabel("Pilih baris transaksi pada tabel.");
        lblStatus.setForeground(Color.BLUE);

        controlPanel.add(new JLabel("Worker:"));
        controlPanel.add(cbWorker);
        controlPanel.add(btnAssign);
        controlPanel.add(new JLabel("Status:"));
        controlPanel.add(cbStatusAdmin);
        controlPanel.add(btnUpdateStatus);
        controlPanel.add(btnHapus);
        controlPanel.add(btnRefresh);
        controlPanel.add(lblStatus);

        add(controlPanel, BorderLayout.SOUTH);
        btnAssign.addActionListener(e -> {
            int row = tableTransaksi.getSelectedRow();
            if (row == -1) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText("pilih transaksi telebih dahulu");
                return;
            }
            int id = (int) tableModel.getValueAt(row, 0);
            String jenis = (String) tableModel.getValueAt(row, 2);
            String worker = (String) cbWorker.getSelectedItem();

            try {
                adminController.tugaskanWorker(id, jenis, worker);
                lblStatus.setForeground(new Color(0, 128, 0));
                lblStatus.setText("berhasil menugaskan worker " + worker);
                refreshData();
            } catch (Exception ex) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText(ex.getMessage());
            }
        });

        btnUpdateStatus.addActionListener(e -> {
            int row = tableTransaksi.getSelectedRow();
            if (row == -1) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText("pilih transaksi terlebih dahulu");
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);
            String statusBaru = (String) cbStatusAdmin.getSelectedItem();

            try {
                adminController.ubahStatusTransaksi(id, statusBaru);
                lblStatus.setForeground(new Color(0, 128, 0));
                lblStatus.setText("status pessanan id ke-" + id + " diubah menjadi " + statusBaru);
                refreshData();
            } catch (Exception ex) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText(ex.getMessage());
            }
        });

        btnHapus.addActionListener(e -> {
            int row = tableTransaksi.getSelectedRow();
            if (row == -1) {
                lblStatus.setForeground(Color.RED);
                lblStatus.setText("pilih transaksi yg ingin dihapus");
                return;
            }

            int id = (int) tableModel.getValueAt(row, 0);

            try {
                adminController.hapusTransaksi(id);
                lblStatus.setForeground(new Color(0, 128, 0));
                lblStatus.setText("pesanan id ke-" + id + " berhasil dihapus");
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
}