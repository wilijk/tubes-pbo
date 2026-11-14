package tubes.model;

import tubes.exception.TransactionException;

public class LayananFactory {
    public static WnStore createLayanan(String jenis, String game, String id, double harga, String metode, String... data) throws TransactionException {
        try {
            if (jenis.equalsIgnoreCase("JOKI")) {
                if (game.equalsIgnoreCase("ML")) {
                    return new JokiML(id, harga, data[0], data[1], metode, 
                                      Integer.parseInt(data[2]), Integer.parseInt(data[3]), Double.parseDouble(data[4]));
                } else if (game.equalsIgnoreCase("GENSHIN")) {
                    return new JokiGenshin(id, harga, data[0], data[1], metode, data[2]);
                }
            } else if (jenis.equalsIgnoreCase("TOPUP")) {
                if (game.equalsIgnoreCase("ML")) {
                    return new TopupML(id, harga, data[0], metode, data[1], Integer.parseInt(data[2]));
                } else if (game.equalsIgnoreCase("GENSHIN")) {
                    return new TopupGenshin(id, harga, data[0], metode, Integer.parseInt(data[1]));
                }
            }
            throw new TransactionException("Jenis layanan atau game tidak valid.");
        } catch (Exception e) {
            throw new TransactionException("Format input parameter salah: " + e.getMessage());
        }
    }
}