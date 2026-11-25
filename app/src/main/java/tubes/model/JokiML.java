package tubes.model;

public class JokiML extends Joki {
    private RankTier rankAwal;
    private int divisiAwal;
    private int bintangAwal;

    private RankTier rankTarget;
    private int divisiTarget;
    private int bintangTarget;

    public JokiML(String customerUsername, String idAkunGame,
                RankTier rankAwal, int divisiAwal, int bintangAwal,
                RankTier rankTarget, int divisiTarget, int bintangTarget) {
        super(customerUsername, "Mobile Legends", idAkunGame);
        this.rankAwal = rankAwal;
        this.divisiAwal = divisiAwal;
        this.bintangAwal = bintangAwal;
        this.rankTarget = rankTarget;
        this.divisiTarget = divisiTarget;
        this.bintangTarget = bintangTarget;
    }

    public static int hitungPosisiBintangGlobal(RankTier rank, int divisi, int bintang) {
        RankTier[] semuaRank = RankTier.values();
        int totalBintang = 0;

        for (int i = 0; i < semuaRank.length; i++) {
            if (semuaRank[i] == rank) {
                break;
            }
            totalBintang += semuaRank[i].getJumlahDivisi() * semuaRank[i].getBintangPerDivisi();
        }

        if (rank.punyaDivisi()) {
            int divisiSelesai = rank.getJumlahDivisi() - divisi;
            totalBintang += (divisiSelesai * rank.getBintangPerDivisi()) + bintang;
        } else {
            totalBintang += bintang;
        }
        return totalBintang;
    }

    @Override
    public double hitungTotalHarga() {
        int posAwal = hitungPosisiBintangGlobal(rankAwal, divisiAwal, bintangAwal);
        int posTarget = hitungPosisiBintangGlobal(rankTarget, divisiTarget, bintangTarget);

        RankTier[] semuaRank = RankTier.values();
        double totalHarga = 0;
        int cursorBintang = 0;

        for (int i = 0; i < semuaRank.length; i++) {
            int kapasitasTier = semuaRank[i].getJumlahDivisi() * semuaRank[i].getBintangPerDivisi();
            for (int b = 0; b < kapasitasTier; b++) {
                if (cursorBintang >= posAwal && cursorBintang < posTarget) {
                    totalHarga += semuaRank[i].getHargaPerBintang();
                }
                cursorBintang++;
            }
        }
        return totalHarga;
    }

    @Override
    public String getDeskripsiLayanan() {
        String strAwal;
        if (rankAwal.punyaDivisi()) {
            strAwal = rankAwal.getNamaRank() + " " + divisiAwal + " (*" + bintangAwal + ")";
        } else {
            strAwal = rankAwal.getNamaRank() + " (*" + getDisplayBintangMythic(rankAwal, bintangAwal) + ")";
        }

        String strTarget;
        if (rankTarget.punyaDivisi()) {
            strTarget = rankTarget.getNamaRank() + " " + divisiTarget + " (*" + bintangTarget + ")";
        } else {
            strTarget = rankTarget.getNamaRank() + " (*" + getDisplayBintangMythic(rankTarget, bintangTarget) + ")";
        }

        return "Joki ML: " + strAwal + " -> " + strTarget;
    }

    private int getDisplayBintangMythic(RankTier tier, int bintangOffset) {
        if (tier == RankTier.MYTHICAL_HONOR) {
            return 25 + bintangOffset;
        }
        if (tier == RankTier.MYTHICAL_GLORY) {
            return 50 + bintangOffset;
        }
        if (tier == RankTier.MYTHICAL_IMMORTAL) {
            return 100 + bintangOffset;
        }
        return bintangOffset;
    }
}
