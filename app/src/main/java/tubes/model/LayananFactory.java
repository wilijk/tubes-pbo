package tubes.model;

import tubes.exception.TransactionException;

public class LayananFactory {
    public static Layanan buatJokiML(String customer, String idAkun,
                                     RankTier awal, int divAwal, int btgAwal,
                                     RankTier target, int divTarget, int btgTarget) throws TransactionException {
        int posAwal = JokiML.hitungPosisiBintangGlobal(awal, divAwal, btgAwal);
        int posTarget = JokiML.hitungPosisiBintangGlobal(target, divTarget, btgTarget);
        if (posTarget <= posAwal) {
            throw new TransactionException("Rank & Bintang target harus lebih tinggi dari posisi awal!");
        }
        return new JokiML(customer, idAkun, awal, divAwal, btgAwal, target, divTarget, btgTarget);
    }

    public static Layanan buatJokiGenshin(String customer, String idAkun, int lvlAwal, int lvlTarget) throws TransactionException {
        if (lvlAwal < 1 || lvlTarget > 60 || lvlTarget <= lvlAwal) {
            throw new TransactionException("Adventure Rank Genshin valid adalah 1 - 60 dan target harus > awal!");
        }
        return new JokiGenshin(customer, idAkun, lvlAwal, lvlTarget);
    }

    public static Layanan buatTopupML(String customer, String idAkun, int diamond) throws TransactionException {
        if (diamond <= 0) {
            throw new TransactionException("Jumlah Diamond harus lebih dari 0!");
        }
        return new TopupML(customer, idAkun, diamond);
    }

    public static Layanan buatTopupGenshin(String customer, String idAkun, int primogem) throws TransactionException {
        if (primogem <= 0) {
            throw new TransactionException("Jumlah Primogem harus lebih dari 0!");
        }
        return new TopupGenshin(customer, idAkun, primogem);
    }
}
