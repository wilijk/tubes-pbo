package tubes.model;

public class TopupGenshin extends Topup {
    public TopupGenshin(String customerUsername, String idAkunGame, int jumlahPrimogem) {
        super(customerUsername, "Genshin Impact", idAkunGame, jumlahPrimogem);
    }

    @Override
    public double hitungTotalHarga() {
        return jumlahItem * 250;
    }

    @Override
    public String getDeskripsiLayanan() {
        return "Top Up " + jumlahItem + " Primogems Genshin";
    }
}
