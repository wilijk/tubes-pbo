package tubes.model;

public abstract class Joki extends Layanan {
    public Joki(String customerUsername, String namaGame, String idAkunGame) {
        super(customerUsername, namaGame, idAkunGame);
    }

    @Override
    public String getJenisLayanan() {
        return "JOKI";
    }
}
