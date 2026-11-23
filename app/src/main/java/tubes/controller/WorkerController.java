package tubes.controller;

import java.util.List;
import tubes.config.SessionManager;
import tubes.model.TransaksiRecord;
import tubes.model.enumm.StatusJoki;
import tubes.repository.TransaksiRepository;

public class WorkerController {
    private TransaksiRepository transaksiRepo;

    public WorkerController() {
        this.transaksiRepo = new TransaksiRepository();
    }

    public List<TransaksiRecord> getTugasWorker() throws Exception {
        String workerUsername = SessionManager.getInstance().getCurrentUser().getUsername();
        return transaksiRepo.ambilByWorker(workerUsername);
    }

    public void updateProgresJoki(int idTransaksi, StatusJoki statusBaru) throws Exception {
        transaksiRepo.updateStatus(idTransaksi, statusBaru.name());
    }
}
