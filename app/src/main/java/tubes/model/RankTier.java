package tubes.model;

public enum RankTier {
    WARRIOR("Warrior", 3, 3, 2000),
    ELITE("Elite", 3, 4, 3000),
    MASTER("Master", 4, 4, 4000),
    GRANDMASTER("Grandmaster", 5, 5, 6000),
    EPIC("Epic", 5, 5, 8000),
    LEGEND("Legend", 5, 5, 10000),
    MYTHIC("Mythic", 1, 25, 20000),
    MYTHICAL_HONOR("Mythical Honor", 1, 25, 25000),
    MYTHICAL_GLORY("Mythical Glory", 1, 50, 35000),
    MYTHICAL_IMMORTAL("Mythical Immortal", 1, 101, 50000);

    private final String namaRank;
    private final int jumlahDivisi;
    private final int bintangPerDivisi;
    private final double hargaPerBintang;

    RankTier(String namaRank, int jumlahDivisi, int bintangPerDivisi, double hargaPerBintang) {
        this.namaRank = namaRank;
        this.jumlahDivisi = jumlahDivisi;
        this.bintangPerDivisi = bintangPerDivisi;
        this.hargaPerBintang = hargaPerBintang;
    }

    public String getNamaRank() { return namaRank; }
    public int getJumlahDivisi() { return jumlahDivisi; }
    public int getBintangPerDivisi() { return bintangPerDivisi; }
    public double getHargaPerBintang() { return hargaPerBintang; }

    public boolean punyaDivisi() {
        return jumlahDivisi > 1;
    }

    @Override
    public String toString() {
        return namaRank;
    }
}
