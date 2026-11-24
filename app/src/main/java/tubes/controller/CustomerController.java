package tubes.controller;

import java.util.List;

import tubes.config.SessionManager;
import tubes.model.Layanan;
import tubes.model.LayananFactory;
import tubes.model.RankTier;
import tubes.model.TransaksiRecord;
import tubes.model.enumm.Bank;
import tubes.model.enumm.EWallet;
import tubes.model.interfacee.Pembayaran;
import tubes.payment.PembayaranBank;
import tubes.payment.PembayaranEWallet;
import tubes.repository.TransaksiRepository;

public class CustomerController {

    private TransaksiRepository transaksiRepo;

    public CustomerController() {
        this.transaksiRepo = new TransaksiRepository();
    }

    public void validasiIdAkun(String idAkun) throws Exception {
        if (idAkun == null || idAkun.trim().isEmpty())
            throw new Exception("Id Akun wajib diisi!");
    }

    public Layanan buatLayananTopUp(String game, String idAkun, int jumlah) throws Exception {
        validasiIdAkun(idAkun);
        String Customer = SessionManager.getInstance().getCurrentUser().getUsername();
        if ("Mobile Legends".equals(game))
            return LayananFactory.buatTopupML(Customer, idAkun, jumlah);
        return LayananFactory.buatTopupGenshin(Customer, idAkun, jumlah);
    }

    public int getOffSetBintangMythic(RankTier tier, int inputBintang) {
        if (tier == RankTier.MYTHICAL_HONOR)
            return inputBintang - 25;
        else if (tier == RankTier.MYTHICAL_GLORY)
            return inputBintang - 50;
        else if (tier == RankTier.MYTHICAL_IMMORTAL)
            return inputBintang - 100;
        return inputBintang;
    }
    
    public Pembayaran buatMetodePembayaran(String kategori, String provider) {
        if ("Bank".equals(kategori))
            return new PembayaranBank(Bank.valueOf(provider));
        return new PembayaranEWallet(EWallet.valueOf(provider));
    }

    public void simpanTransaksi(Layanan layanan, Pembayaran metodeBayar, String virtualNum) throws Exception {
        TransaksiRecord record = new TransaksiRecord(0, layanan.getCustomerUsername(), null, layanan.getJenisLayanan(),
                layanan.getNamaGame(), layanan.getIdAkunGame(), layanan.getDeskripsiLayanan(),
                layanan.hitungTotalHarga(), metodeBayar.getNamaMetode(), virtualNum, "DIPROSES");
        transaksiRepo.simpan(record);
    }

    public List<TransaksiRecord> getRiwayatCustomer() throws Exception {
        String username = SessionManager.getInstance().getCurrentUser().getUsername();
        return transaksiRepo.ambilByCustomer(username);
    }


    public Layanan buatLayananJokiGenshin(String idAkun, int lvlAwal, int lvlTarget) throws Exception {
        validasiIdAkun(idAkun);
        String Customer = SessionManager.getInstance().getCurrentUser().getUsername();
        return LayananFactory.buatJokiGenshin(Customer, idAkun, lvlAwal, lvlTarget);
    }

    public Layanan buatLayananJokiML(String idAkun, RankTier awal, int divAwal, int rawBtgAwal, RankTier target,
            int divTarget, int rawBtgTarget) throws Exception {
        validasiIdAkun(idAkun);
        String customer = SessionManager.getInstance().getCurrentUser().getUsername();

        int btgAwal;
        if (awal.punyaDivisi())
            btgAwal = rawBtgAwal;
        else
            btgAwal = getOffSetBintangMythic(target, rawBtgAwal);

        int btgTarget;
        if (target.punyaDivisi())
            btgTarget = rawBtgTarget;
        else
            btgTarget = getOffSetBintangMythic(target, rawBtgTarget);

        return LayananFactory.buatJokiML(customer, idAkun, awal, divAwal, btgAwal, target, divTarget, btgTarget);
    }
}