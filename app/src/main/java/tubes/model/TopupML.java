package tubes.model;

public class TopupML extends Topup {
    private String zoneId;
    private int jumlahDiamond;

    public TopupML(String idTransaksi, double hargaDasar, String userIdGame, String metodePembayaran, String zoneId, int jumlahDiamond) {
        super(idTransaksi, hargaDasar, userIdGame, metodePembayaran);
        this.zoneId = zoneId;
        this.jumlahDiamond = jumlahDiamond;
    }

    @Override
    public double hitungTotalHarga() {
        return hargaDasar;
    }

    @Override
    public String getOrderDetails() {
        return "=== ORDER DETAILS [TOPUP ML] ===\n" +
               "ID Transaksi : " + idTransaksi + "\n" +
               "User ID (Zone): " + userIdGame + " (" + zoneId + ")\n" +
               "Jumlah DM    : " + jumlahDiamond + "\n" +
               "Metode Bayar : " + getMetodePembayaran() + "\n" +
               "Total Harga  : Rp " + hitungTotalHarga();
    }
}