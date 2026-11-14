package tubes.model;

public interface Pembayaran {
    boolean prosesPembayaran(double jumlahBayar);
    String getMetodePembayaran();
}