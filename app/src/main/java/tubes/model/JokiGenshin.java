package tubes.model;

public class JokiGenshin extends Joki {
    private int levelAwal;
    private int levelTarget;

    public JokiGenshin(String customerUsername, String idAkunGame, int levelAwal, int levelTarget) {
        super(customerUsername, "Genshin Impact", idAkunGame);
        this.levelAwal = levelAwal;
        this.levelTarget = levelTarget;
    }

    @Override
    public double hitungTotalHarga() {
        double total = 0;
        for (int lvl = levelAwal; lvl < levelTarget; lvl++) {
            if (lvl < 25) {
                total += 10000;
            } else if (lvl < 45) {
                total += 25000;
            } else if (lvl < 55) {
                total += 50000;
            } else {
                total += 100000;
            }
        }
        return total;
    }

    @Override
    public String getDeskripsiLayanan() {
        return "Joki Level Genshin: AR " + levelAwal + " -> AR " + levelTarget;
    }
}
