package tubes.model;

public abstract class Topup extends WnStore {
    protected String userIdGame;

    public Topup(String idTransaksi, double hargaDasar, String userIdGame, String metodePembayaran) {
        super(idTransaksi, hargaDasar, metodePembayaran);
        this.userIdGame = userIdGame;
    }
}