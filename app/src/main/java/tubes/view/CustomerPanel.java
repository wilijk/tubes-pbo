package tubes.view;

import java.awt.*;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import tubes.config.SessionManager;
import tubes.controller.AuthController;
import tubes.controller.CustomerController;
import tubes.model.Layanan;
import tubes.model.RankTier;
import tubes.model.TransaksiRecord;
import tubes.model.enumm.Bank;
import tubes.model.enumm.EWallet;
import tubes.model.interfacee.Pembayaran;

public class CustomerPanel extends JPanel {
    private MainFrame mainFrame;
    private CustomerController customerController;
    private AuthController authController;

    private CardLayout subCardLayout;
    private JPanel subContainer;

    private String selectedLayanan = "JOKI";
    private String selectedGame = "Mobile Legends";

    private JLabel lblJudulPilihGame;
    private JLabel lblJudulForm;
    private JTextField txtIdAkun;

    private CardLayout formDetailLayout;
    private JPanel formDetailContainer;

    private JComboBox<RankTier> cbRankAwal;
    private JComboBox<Integer> cbDivisiAwal;
    private JComboBox<Integer> cbBintangAwal;
    private JComboBox<RankTier> cbRankTarget;
    private JComboBox<Integer> cbDivisiTarget;
    private JComboBox<Integer> cbBintangTarget;

    private JTextField txtLevelAwal;
    private JTextField txtLevelTarget;

    private JLabel lblSatuanTopup;
    private JTextField txtJumlahTopup;

    private JComboBox<String> cbKategoriBayar;
    private JComboBox<String> cbProviderBayar;
    private JLabel lblTotalHarga;
    private JLabel lblVirtualNumber;
    private JLabel lblStatusOrder;
    private JButton btnBayar;

    private JTable tableRiwayat;
    private DefaultTableModel tableModel;
    private JLabel lblStatusRiwayat;

    public CustomerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.customerController = new CustomerController();
        this.authController = new AuthController();
        setLayout(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        JLabel lblWelcome = new JLabel("WNSTORE");
        lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 16));

        JPanel rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnKeRiwayatGlobal = new JButton("Riwayat Pesanan");
        JButton btnLogout = new JButton("Logout");
        rightHeader.add(btnKeRiwayatGlobal);
        rightHeader.add(btnLogout);

        headerPanel.add(lblWelcome, BorderLayout.WEST);
        headerPanel.add(rightHeader, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        subCardLayout = new CardLayout();
        subContainer = new JPanel(subCardLayout);

        subContainer.add(buatPanelMenuUtama(), "MENU_UTAMA");
        subContainer.add(buatPanelPilihGame(), "PILIH_GAME");
        subContainer.add(buatPanelFormOrder(), "FORM_ORDER");
        subContainer.add(buatPanelRiwayat(), "RIWAYAT");

        add(subContainer, BorderLayout.CENTER);

        btnKeRiwayatGlobal.addActionListener(e -> {
            muatTabelRiwayat();
            subCardLayout.show(subContainer, "RIWAYAT");
        });

        btnLogout.addActionListener(e -> {
            authController.logout();
            mainFrame.showPage("LOGIN");
        });
    }

    private JPanel buatPanelMenuUtama() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        JLabel lblTitle = new JLabel("Pilih Layanan yang Anda Butuhkan");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        JButton btnJoki = new JButton("JASA JOKI AKUN");
        btnJoki.setFont(new Font("SansSerif", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 30;
        gbc.ipadx = 30;
        panel.add(btnJoki, gbc);

        JButton btnTopup = new JButton("TOP UP GAME");
        btnTopup.setFont(new Font("SansSerif", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnTopup, gbc);

        JButton btnRiwayat = new JButton("Lihat Riwayat Pesanan Saya");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        panel.add(btnRiwayat, gbc);

        btnJoki.addActionListener(e -> {
            selectedLayanan = "JOKI";
            lblJudulPilihGame.setText("Pilih Game untuk Layanan JOKI");
            subCardLayout.show(subContainer, "PILIH_GAME");
        });

        btnTopup.addActionListener(e -> {
            selectedLayanan = "TOPUP";
            lblJudulPilihGame.setText("Pilih Game untuk Layanan TOP UP");
            subCardLayout.show(subContainer, "PILIH_GAME");
        });

        btnRiwayat.addActionListener(e -> {
            muatTabelRiwayat();
            subCardLayout.show(subContainer, "RIWAYAT");
        });

        return panel;
    }

    private JPanel buatPanelPilihGame() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        lblJudulPilihGame = new JLabel("Pilih Game");
        lblJudulPilihGame.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblJudulPilihGame, gbc);

        gbc.gridwidth = 1;
        JButton btnML = new JButton("Mobile Legends");
        btnML.setFont(new Font("SansSerif", Font.BOLD, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 30;
        gbc.ipadx = 30;
        panel.add(btnML, gbc);

        JButton btnGenshin = new JButton("Genshin Impact");
        btnGenshin.setFont(new Font("SansSerif", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnGenshin, gbc);

        JButton btnKembali = new JButton("Kembali");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 8;
        gbc.ipadx = 15;
        panel.add(btnKembali, gbc);

        JButton btnRiwayat = new JButton("Ke Halaman Riwayat");
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(btnRiwayat, gbc);

        btnML.addActionListener(e -> {
            selectedGame = "Mobile Legends";
            siapkanFormOrder();
            subCardLayout.show(subContainer, "FORM_ORDER");
        });

        btnGenshin.addActionListener(e -> {
            selectedGame = "Genshin Impact";
            siapkanFormOrder();
            subCardLayout.show(subContainer, "FORM_ORDER");
        });

        btnKembali.addActionListener(e -> subCardLayout.show(subContainer, "MENU_UTAMA"));
        btnRiwayat.addActionListener(e -> {
            muatTabelRiwayat();
            subCardLayout.show(subContainer, "RIWAYAT");
        });

        return panel;
    }

    private JPanel buatPanelFormOrder() {
        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblJudulForm = new JLabel("Form Pemesanan");
        lblJudulForm.setFont(new Font("SansSerif", Font.BOLD, 18));
        wrapper.add(lblJudulForm, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel topForm = new JPanel(new BorderLayout(8, 8));
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.add(new JLabel("ID Akun Game:"));
        txtIdAkun = new JTextField(20);
        idPanel.add(txtIdAkun);
        topForm.add(idPanel, BorderLayout.NORTH);

        formDetailLayout = new CardLayout();
        formDetailContainer = new JPanel(formDetailLayout);

        JPanel panelJokiML = new JPanel(new GridLayout(2, 4, 8, 8));
        panelJokiML.setBorder(BorderFactory.createTitledBorder("Detail Rank Mobile Legends"));

        cbRankAwal = new JComboBox<>(RankTier.values());
        cbDivisiAwal = new JComboBox<>();
        cbBintangAwal = new JComboBox<>();

        cbRankTarget = new JComboBox<>(RankTier.values());
        cbDivisiTarget = new JComboBox<>();
        cbBintangTarget = new JComboBox<>();

        panelJokiML.add(new JLabel("Rank Saat Ini:"));
        panelJokiML.add(cbRankAwal);
        panelJokiML.add(cbDivisiAwal);
        panelJokiML.add(cbBintangAwal);

        panelJokiML.add(new JLabel("Rank Target:"));
        panelJokiML.add(cbRankTarget);
        panelJokiML.add(cbDivisiTarget);
        panelJokiML.add(cbBintangTarget);

        JPanel panelJokiGenshin = new JPanel(new GridLayout(2, 2, 8, 8));
        panelJokiGenshin.setBorder(BorderFactory.createTitledBorder("Detail Adventure Rank Genshin Impact"));
        txtLevelAwal = new JTextField("1");
        txtLevelTarget = new JTextField("2");
        panelJokiGenshin.add(new JLabel("Adventure Rank (AR) Saat Ini:"));
        panelJokiGenshin.add(txtLevelAwal);
        panelJokiGenshin.add(new JLabel("Adventure Rank (AR) Target:"));
        panelJokiGenshin.add(txtLevelTarget);

        JPanel panelTopup = new JPanel(new GridLayout(2, 2, 8, 8));
        panelTopup.setBorder(BorderFactory.createTitledBorder("Detail Top Up"));
        lblSatuanTopup = new JLabel("Jumlah Diamond:");
        txtJumlahTopup = new JTextField("100");
        panelTopup.add(lblSatuanTopup);
        panelTopup.add(txtJumlahTopup);
        panelTopup.add(new JLabel("Keterangan Harga:"));
        panelTopup.add(new JLabel("ML: Rp 300/Diamond | Genshin: Rp 250/Primogem"));

        formDetailContainer.add(panelJokiML, "JOKI_ML");
        formDetailContainer.add(panelJokiGenshin, "JOKI_GENSHIN");
        formDetailContainer.add(panelTopup, "TOPUP_ALL");

        topForm.add(formDetailContainer, BorderLayout.CENTER);
        centerPanel.add(topForm);

        JPanel bayarPanel = new JPanel(new GridLayout(5, 2, 8, 8));
        bayarPanel.setBorder(BorderFactory.createTitledBorder("Pembayaran"));

        cbKategoriBayar = new JComboBox<>(new String[]{"Bank", "E-Wallet"});
        cbProviderBayar = new JComboBox<>();
        lblTotalHarga = new JLabel("Rp 0");
        lblTotalHarga.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblVirtualNumber = new JLabel("-");
        lblVirtualNumber.setFont(new Font("SansSerif", Font.BOLD, 14));

        JButton btnCekHarga = new JButton("Hitung Total Harga");
        btnBayar = new JButton("Bayar Sekarang");

        bayarPanel.add(new JLabel("Pilih Kategori Pembayaran:"));
        bayarPanel.add(cbKategoriBayar);
        bayarPanel.add(new JLabel("Pilih Bank / E-Wallet:"));
        bayarPanel.add(cbProviderBayar);
        bayarPanel.add(new JLabel("Total Harga:"));
        bayarPanel.add(lblTotalHarga);
        bayarPanel.add(new JLabel("Nomor Virtual Account / Tujuan:"));
        bayarPanel.add(lblVirtualNumber);
        bayarPanel.add(btnCekHarga);
        bayarPanel.add(btnBayar);

        centerPanel.add(bayarPanel);
        wrapper.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        lblStatusOrder = new JLabel("Lengkapi data di atas lalu klik Hitung Total Harga atau Bayar Sekarang.");
        lblStatusOrder.setForeground(Color.BLUE);

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnKembaliGame = new JButton("Kembali Pilih Game");
        JButton btnKeRiwayat = new JButton("Lihat Riwayat");
        navButtons.add(btnKembaliGame);
        navButtons.add(btnKeRiwayat);

        bottomPanel.add(lblStatusOrder, BorderLayout.NORTH);
        bottomPanel.add(navButtons, BorderLayout.SOUTH);
        wrapper.add(bottomPanel, BorderLayout.SOUTH);

        cbRankAwal.addActionListener(e -> updateOpsiDivisiDanBintang(cbRankAwal, cbDivisiAwal, cbBintangAwal));
        cbRankTarget.addActionListener(e -> updateOpsiDivisiDanBintang(cbRankTarget, cbDivisiTarget, cbBintangTarget));
        cbKategoriBayar.addActionListener(e -> updateProviderBayar());

        btnCekHarga.addActionListener(e -> {
            try {
                Layanan layanan = ambilLayananDariInput();
                lblTotalHarga.setText("Rp " + (long) layanan.hitungTotalHarga());
                lblStatusOrder.setForeground(Color.BLUE);
                lblStatusOrder.setText("Rincian: " + layanan.getDeskripsiLayanan());
            } catch (Exception ex) {
                lblStatusOrder.setForeground(Color.RED);
                lblStatusOrder.setText(ex.getMessage());
            }
        });

        btnBayar.addActionListener(e -> prosesOrderDenganDelay());
        btnKembaliGame.addActionListener(e -> subCardLayout.show(subContainer, "PILIH_GAME"));
        btnKeRiwayat.addActionListener(e -> {
            muatTabelRiwayat();
            subCardLayout.show(subContainer, "RIWAYAT");
        });

        updateOpsiDivisiDanBintang(cbRankAwal, cbDivisiAwal, cbBintangAwal);
        updateOpsiDivisiDanBintang(cbRankTarget, cbDivisiTarget, cbBintangTarget);
        updateProviderBayar();

        return wrapper;
    }

    private JPanel buatPanelRiwayat() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblJudul = new JLabel("Riwayat Pesanan Joki & Top Up Anda");
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(lblJudul, BorderLayout.NORTH);

        String[] kolom = {"ID", "Layanan", "Game", "ID Akun", "Detail", "Harga", "VA", "Status"};
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableRiwayat = new JTable(tableModel);
        panel.add(new JScrollPane(tableRiwayat), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnKembaliMenu = new JButton("Kembali ke Menu Utama");
        JButton btnRefreshRiwayat = new JButton("Refresh Status Terbaru");
        lblStatusRiwayat = new JLabel("Status otomatis mengikuti progres dari Admin/Worker.");
        lblStatusRiwayat.setForeground(Color.BLUE);

        bottomPanel.add(btnKembaliMenu);
        bottomPanel.add(btnRefreshRiwayat);
        bottomPanel.add(lblStatusRiwayat);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnKembaliMenu.addActionListener(e -> subCardLayout.show(subContainer, "MENU_UTAMA"));
        btnRefreshRiwayat.addActionListener(e -> {
            muatTabelRiwayat();
            lblStatusRiwayat.setForeground(new Color(0, 128, 0));
            lblStatusRiwayat.setText("Data riwayat diperbarui!");
        });

        return panel;
    }

    private void siapkanFormOrder() {
        lblJudulForm.setText("Form " + selectedLayanan + " - " + selectedGame);
        lblTotalHarga.setText("Rp 0");
        lblVirtualNumber.setText("-");
        lblStatusOrder.setForeground(Color.BLUE);
        lblStatusOrder.setText("Silakan isi detail pesanan Anda.");

        if ("JOKI".equals(selectedLayanan)) {
            if ("Mobile Legends".equals(selectedGame)) {
                formDetailLayout.show(formDetailContainer, "JOKI_ML");
            } else {
                formDetailLayout.show(formDetailContainer, "JOKI_GENSHIN");
            }
        } else {
            if ("Mobile Legends".equals(selectedGame)) {
                lblSatuanTopup.setText("Jumlah Diamonds Mobile Legends:");
            } else {
                lblSatuanTopup.setText("Jumlah Primogems Genshin Impact:");
            }
            formDetailLayout.show(formDetailContainer, "TOPUP_ALL");
        }
    }

    private void updateOpsiDivisiDanBintang(JComboBox<RankTier> cbRank, JComboBox<Integer> cbDivisi, JComboBox<Integer> cbBintang) {
        RankTier tier = (RankTier) cbRank.getSelectedItem();
        if (tier == null) return;

        cbDivisi.removeAllItems();
        cbBintang.removeAllItems();

        if (tier.punyaDivisi()) {
            cbDivisi.setEnabled(true);
            for (int d = tier.getJumlahDivisi(); d >= 1; d--) {
                cbDivisi.addItem(d);
            }
            for (int b = 0; b <= tier.getBintangPerDivisi(); b++) {
                cbBintang.addItem(b);
            }
        } else {
            cbDivisi.addItem(1);
            cbDivisi.setEnabled(false);

            int startBintang = 0;
            int endBintang = 24;
            if (tier == RankTier.MYTHICAL_HONOR) {
                startBintang = 25;
                endBintang = 49;
            } else if (tier == RankTier.MYTHICAL_GLORY) {
                startBintang = 50;
                endBintang = 99;
            } else if (tier == RankTier.MYTHICAL_IMMORTAL) {
                startBintang = 100;
                endBintang = 200;
            }

            for (int b = startBintang; b <= endBintang; b++) {
                cbBintang.addItem(b);
            }
        }
    }

    private void updateProviderBayar() {
        cbProviderBayar.removeAllItems();
        if ("Bank".equals(cbKategoriBayar.getSelectedItem())) {
            for (Bank b : Bank.values()) {
                cbProviderBayar.addItem(b.name());
            }
        } else {
            for (EWallet ew : EWallet.values()) {
                cbProviderBayar.addItem(ew.name());
            }
        }
    }

    private Layanan ambilLayananDariInput() throws Exception {
        String idAkun = txtIdAkun.getText().trim();

        if ("JOKI".equals(selectedLayanan)) {
            if ("Mobile Legends".equals(selectedGame)) {
                RankTier awal = (RankTier) cbRankAwal.getSelectedItem();
                int divAwal = (Integer) cbDivisiAwal.getSelectedItem();
                int rawBtgAwal = (Integer) cbBintangAwal.getSelectedItem();

                RankTier target = (RankTier) cbRankTarget.getSelectedItem();
                int divTarget = (Integer) cbDivisiTarget.getSelectedItem();
                int rawBtgTarget = (Integer) cbBintangTarget.getSelectedItem();

                return customerController.buatLayananJokiML(idAkun, awal, divAwal, rawBtgAwal, target, divTarget, rawBtgTarget);
            } else {
                int lvlAwal = Integer.parseInt(txtLevelAwal.getText().trim());
                int lvlTarget = Integer.parseInt(txtLevelTarget.getText().trim());
                return customerController.buatLayananJokiGenshin(idAkun, lvlAwal, lvlTarget);
            }
        } else {
            int jumlah = Integer.parseInt(txtJumlahTopup.getText().trim());
            return customerController.buatLayananTopUp(selectedGame, idAkun, jumlah);
        }
    }

    private void prosesOrderDenganDelay() {
        try {
            Layanan layanan = ambilLayananDariInput();
            double totalHarga = layanan.hitungTotalHarga();
            lblTotalHarga.setText("Rp " + (long) totalHarga);

            String kategori = (String) cbKategoriBayar.getSelectedItem();
            String provider = (String) cbProviderBayar.getSelectedItem();
            Pembayaran metodeBayar = customerController.buatMetodePembayaran(kategori, provider);

            String virtualNumber = metodeBayar.generateVirtualNumber(layanan.getIdAkunGame());
            lblVirtualNumber.setText(virtualNumber);

            btnBayar.setEnabled(false);
            lblStatusOrder.setForeground(new Color(200, 100, 0));
            lblStatusOrder.setText("Memproses pembayaran...  (VA: " + virtualNumber + ")");

            Timer timer = new Timer(5000, evt -> {
                try {
                    customerController.simpanTransaksi(layanan, metodeBayar, virtualNumber);
                    lblStatusOrder.setForeground(new Color(0, 128, 0));
                    lblStatusOrder.setText("Pembayaran Berhasil! Silakan cek progres di halaman Riwayat.");
                    muatTabelRiwayat();
                } catch (Exception ex) {
                    lblStatusOrder.setForeground(Color.RED);
                    lblStatusOrder.setText("Gagal menyimpan transaksi: " + ex.getMessage());
                } finally {
                    btnBayar.setEnabled(true);
                }
            });
            timer.setRepeats(false);
            timer.start();

        } catch (NumberFormatException nfe) {
            lblStatusOrder.setForeground(Color.RED);
            lblStatusOrder.setText("Input level/jumlah top up harus berupa angka!");
        } catch (Exception ex) {
            lblStatusOrder.setForeground(Color.RED);
            lblStatusOrder.setText(ex.getMessage());
        }
    }

    public void muatTabelRiwayat() {
        if (SessionManager.getInstance().getCurrentUser() == null) return;
        tableModel.setRowCount(0);
        try {
            List<TransaksiRecord> list = customerController.getRiwayatCustomer();
            for (TransaksiRecord t : list) {
                tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getJenisLayanan(),
                    t.getNamaGame(),
                    t.getIdAkunGame(),
                    t.getDetailPesanan(),
                    "Rp " + (long) t.getTotalHarga(),
                    t.getNomorVirtual(),
                    t.getStatus()
                });
            }
        } catch (Exception e) {
            lblStatusRiwayat.setForeground(Color.RED);
            lblStatusRiwayat.setText("Gagal memuat riwayat: " + e.getMessage());
        }
    }

    public void refreshData() {
        muatTabelRiwayat();
        subCardLayout.show(subContainer, "MENU_UTAMA");
    }
}
