package tubes.model;

public class JokiML extends Joki implements Calculable {
    private int totalMatch;
    private int totalMenang;
    private double targetWinRate;

    public JokiML(String idTransaksi, double hargaDasar, String akunGame, String password, 
                  String metodePembayaran, int totalMatch, int totalMenang, double targetWinRate) {
        super(idTransaksi, hargaDasar, akunGame, password, metodePembayaran);
        this.totalMatch = totalMatch;
        this.totalMenang = totalMenang;
        this.targetWinRate = targetWinRate;
    }

    @Override
    public double hitungWinRateSaatIni(int totalMatch, int totalMenang) {
        if (totalMatch <= 0) return 0.0;
        return ((double) totalMenang / totalMatch) * 100.0;
    }

    @Override
    public int hitungKemenanganDibutuhkan(int totalMatch, int totalMenang, double targetWinRate) {
        if (targetWinRate >= 100.0) return 0;
        double targetDesimal = targetWinRate / 100.0;
        double wrSekarang = (double) totalMenang / totalMatch;

        if (targetDesimal <= wrSekarang) return 0;

        double butuhMenang = ((targetDesimal * totalMatch) - totalMenang) / (1.0 - targetDesimal);
        return (int) Math.ceil(butuhMenang);
    }

    @Override
    public double hitungFaktorKesulitan(int totalMatch) {
        if (totalMatch > 2000) return 1.5;
        if (totalMatch > 1000) return 1.25;
        if (totalMatch > 500) return 1.1;
        return 1.0;
    }

    @Override
    public double hitungTotalHarga() {
        int winNeeded = hitungKemenanganDibutuhkan(totalMatch, totalMenang, targetWinRate);
        double multiplier = hitungFaktorKesulitan(totalMatch);
        return (winNeeded * hargaDasar) * multiplier;
    }

    @Override
    public String getOrderDetails() {
        int winNeeded = hitungKemenanganDibutuhkan(totalMatch, totalMenang, targetWinRate);
        return "=== ORDER DETAILS [JOKI WIN RATE ML] ===\n" +
               "ID Transaksi   : " + idTransaksi + "\n" +
               "Akun Game      : " + akunGame + "\n" +
               "Match / Win    : " + totalMatch + " / " + totalMenang + "\n" +
               "WR Saat Ini    : " + String.format("%.2f", hitungWinRateSaatIni(totalMatch, totalMenang)) + "%\n" +
               "Target WR      : " + targetWinRate + "%\n" +
               "Win Dibutuhkan : " + winNeeded + " Match\n" +
               "Metode Bayar   : " + getMetodePembayaran() + "\n" +
               "Status         : " + status + "\n" +
               "Total Harga    : Rp " + hitungTotalHarga();
    }
}