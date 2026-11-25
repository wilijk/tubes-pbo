package tubes.model;

import tubes.model.interfacee.Calculable;

public abstract class Layanan implements Calculable {
    protected String customerUsername;
    protected String namaGame;
    protected String idAkunGame;

    public Layanan(String customerUsername, String namaGame, String idAkunGame) {
        this.customerUsername = customerUsername;
        this.namaGame = namaGame;
        this.idAkunGame = idAkunGame;
    }

    public String getCustomerUsername() { return customerUsername; }
    public String getNamaGame() { return namaGame; }
    public String getIdAkunGame() { return idAkunGame; }

    public abstract String getJenisLayanan();
    
    public abstract String getDeskripsiLayanan();
}
