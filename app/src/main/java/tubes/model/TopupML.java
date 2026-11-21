package tubes.model;

public class TopupML extends Topup {
    public TopupML(String customerUsername, String idAkunGame, int jumlahDiamond) {
        super(customerUsername, "Mobile Legends", idAkunGame, jumlahDiamond);
    }

    @Override
    public double hitungTotalHarga() {
        return jumlahItem * 300;
    }

    @Override
    public String getDeskripsiLayanan() {
        return "Top Up " + jumlahItem + " Diamonds ML";
    }
}
