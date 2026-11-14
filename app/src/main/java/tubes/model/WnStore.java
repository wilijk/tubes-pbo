package tubes.model;

public abstract class WnStore implements Pembayaran {
    protected String idTransaksi;
    protected double hargaDasar;
    protected String metodePembayaran;

    public WnStore(String idTransaksi, double hargaDasar, String metodePembayaran) {
        this.idTransaksi = idTransaksi;
        this.hargaDasar = hargaDasar;
        this.metodePembayaran = metodePembayaran;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    @Override
    public boolean prosesPembayaran(double jumlahBayar) {
        return jumlahBayar >= hitungTotalHarga();
    }

    @Override
    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public abstract double hitungTotalHarga();
    
    public abstract String getOrderDetails();
}