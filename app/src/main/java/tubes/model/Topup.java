package tubes.model;

public abstract class Topup extends Layanan {
    protected int jumlahItem;

    public Topup(String customerUsername, String namaGame, String idAkunGame, int jumlahItem) {
        super(customerUsername, namaGame, idAkunGame);
        this.jumlahItem = jumlahItem;
    }

    @Override
    public String getJenisLayanan() {
        return "TOPUP";
    }
}
