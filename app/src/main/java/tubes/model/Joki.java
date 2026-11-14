package tubes.model;

public abstract class Joki extends WnStore {
    protected String akunGame;
    protected String password;
    protected String status;

    public Joki(String idTransaksi, double hargaDasar, String akunGame, String password, String metodePembayaran) {
        super(idTransaksi, hargaDasar, metodePembayaran);
        this.akunGame = akunGame;
        this.password = password;
        this.status = "DIPROSES";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}