package tubes.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tubes.config.DatabaseConnection;
import tubes.model.TransaksiRecord;

public class TransaksiRepository implements BaseRepository<TransaksiRecord> {

    @Override
    public void simpan(TransaksiRecord t) throws Exception {
        String sql = "INSERT INTO transaksi (customer_username, worker_username, jenis_layanan, nama_game, id_akun_game, detail_pesanan, total_harga, metode_pembayaran, nomor_virtual, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getCustomerUsername());
            stmt.setString(2, t.getWorkerUsername());
            stmt.setString(3, t.getJenisLayanan());
            stmt.setString(4, t.getNamaGame());
            stmt.setString(5, t.getIdAkunGame());
            stmt.setString(6, t.getDetailPesanan());
            stmt.setDouble(7, t.getTotalHarga());
            stmt.setString(8, t.getMetodePembayaran());
            stmt.setString(9, t.getNomorVirtual());
            stmt.setString(10, t.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<TransaksiRecord> ambilSemua() throws Exception {
        List<TransaksiRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM transaksi ORDER BY id DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    private TransaksiRecord mapRow(ResultSet rs) throws Exception {
        return new TransaksiRecord(
            rs.getInt("id"),
            rs.getString("customer_username"),
            rs.getString("worker_username"),
            rs.getString("jenis_layanan"),
            rs.getString("nama_game"),
            rs.getString("id_akun_game"),
            rs.getString("detail_pesanan"),
            rs.getDouble("total_harga"),
            rs.getString("metode_pembayaran"),
            rs.getString("nomor_virtual"),
            rs.getString("status")
        );
    } 

    public void assignWorker(int idTransaksi, String workerUsername) throws Exception {
        String sql = "UPDATE transaksi SET worker_username = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, workerUsername);
            stmt.setInt(2, idTransaksi);
            stmt.executeUpdate();
        }
    }

    public void updateStatus(int idTransaksi, String statusBaru) throws Exception {
        String sql = "UPDATE transaksi SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, statusBaru);
            stmt.setInt(2, idTransaksi);
            stmt.executeUpdate();
        }
    }

    public void hapus(int idTransaksi) throws Exception {
        String sql = "DELETE FROM transaksi WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTransaksi);
            stmt.executeUpdate();
        }
    }
}
