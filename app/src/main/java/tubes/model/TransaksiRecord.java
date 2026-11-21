package tubes.model;

public class TransaksiRecord {
    private int id;
    private String customerUsername;
    private String workerUsername;
    private String jenisLayanan;
    private String namaGame;
    private String idAkunGame;
    private String detailPesanan;
    private double totalHarga;
    private String metodePembayaran;
    private String nomorVirtual;
    private String status;

    public TransaksiRecord(int id, String customerUsername, String workerUsername, String jenisLayanan,
                           String namaGame, String idAkunGame, String detailPesanan, double totalHarga,
                           String metodePembayaran, String nomorVirtual, String status) {
        this.id = id;
        this.customerUsername = customerUsername;
        this.workerUsername = workerUsername;
        this.jenisLayanan = jenisLayanan;
        this.namaGame = namaGame;
        this.idAkunGame = idAkunGame;
        this.detailPesanan = detailPesanan;
        this.totalHarga = totalHarga;
        this.metodePembayaran = metodePembayaran;
        this.nomorVirtual = nomorVirtual;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCustomerUsername() { return customerUsername; }
    public String getWorkerUsername() { return workerUsername; }
    public String getJenisLayanan() { return jenisLayanan; }
    public String getNamaGame() { return namaGame; }
    public String getIdAkunGame() { return idAkunGame; }
    public String getDetailPesanan() { return detailPesanan; }
    public double getTotalHarga() { return totalHarga; }
    public String getMetodePembayaran() { return metodePembayaran; }
    public String getNomorVirtual() { return nomorVirtual; }
    public String getStatus() { return status; }
}
