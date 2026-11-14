package tubes.model;

public class JokiGenshin extends Joki {
    private String jenisTask;

    public JokiGenshin(String idTransaksi, double hargaDasar, String akunGame, String password, String metodePembayaran, String jenisTask) {
        super(idTransaksi, hargaDasar, akunGame, password, metodePembayaran);
        this.jenisTask = jenisTask;
    }

    @Override
    public double hitungTotalHarga() {
        return hargaDasar + (hargaDasar * 0.10);
    }

    @Override
    public String getOrderDetails() {
        return "=== ORDER DETAILS [JOKI GENSHIN] ===\n" +
               "ID Transaksi : " + idTransaksi + "\n" +
               "Akun Game    : " + akunGame + "\n" +
               "Jenis Task   : " + jenisTask + "\n" +
               "Metode Bayar : " + getMetodePembayaran() + "\n" +
               "Status       : " + status + "\n" +
               "Total Harga  : Rp " + hitungTotalHarga();
    }
}