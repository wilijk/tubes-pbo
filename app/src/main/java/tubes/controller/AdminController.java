package tubes.controller;

import java.util.List;
import tubes.exception.TransactionException;
import tubes.model.TransaksiRecord;
import tubes.repository.TransaksiRepository;
import tubes.repository.UserRepository;

public class AdminController {
    private TransaksiRepository transaksiRepo;
    private UserRepository userRepo;

    public AdminController() {
        this.transaksiRepo = new TransaksiRepository();
        this.userRepo = new UserRepository();
    }

    public List<TransaksiRecord> getSemuaTransaksi() throws Exception {
        return transaksiRepo.ambilSemua();
    }

    public List<String> getDaftarWorker() throws Exception {
        return userRepo.getAllWorkers();
    }

    public void tugaskanWorker(int idTransaksi, String jenisLayanan, String workerUsername) throws Exception {
        if (!"JOKI".equals(jenisLayanan)) {
            throw new TransactionException("Top Up tidak butuh worker, langsung ubah status ke SUDAH!");
        }
        if (workerUsername == null || workerUsername.trim().isEmpty()) {
            throw new TransactionException("Pilih worker terlebih dahulu!");
        }
        transaksiRepo.assignWorker(idTransaksi, workerUsername);
    }

    public void ubahStatusTransaksi(int idTransaksi, String statusBaru) throws Exception {
        transaksiRepo.updateStatus(idTransaksi, statusBaru);
    }

    public void hapusTransaksi(int idTransaksi) throws Exception {
        transaksiRepo.hapus(idTransaksi);
    }
}