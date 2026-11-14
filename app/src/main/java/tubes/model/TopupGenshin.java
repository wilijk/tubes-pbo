package tubes.model;

public class TopupGenshin extends Topup {
    private int jumlahGenesis;

    public TopupGenshin(String idTransaksi, double hargaDasar, String userIdGame, String metodePembayaran, int jumlahGenesis) {
        super(idTransaksi, hargaDasar, userIdGame, metodePembayaran);
        this.jumlahGenesis = jumlahGenesis;
    }

    @Override
    public double hitungTotalHarga() {
        return hargaDasar;
    }

    @Override
    public String getOrderDetails() {
        return "=== ORDER DETAILS [TOPUP GENSHIN] ===\n" +
               "ID Transaksi : " + idTransaksi + "\n" +
               "User ID GI   : " + userIdGame + "\n" +
               "Genesis      : " + jumlahGenesis + "\n" +
               "Metode Bayar : " + getMetodePembayaran() + "\n" +
               "Total Harga  : Rp " + hitungTotalHarga();
    }
}