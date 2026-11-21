package tubes.payment;

import java.util.Random;
import tubes.model.enumm.Bank;
import tubes.model.interfacee.Pembayaran;

public class PembayaranBank implements Pembayaran {
    private Bank bank;

    public PembayaranBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String getNamaMetode() {
        return "Bank " + bank.name();
    }

    @Override
    public String generateVirtualNumber(String idAkunGame) {
        int randomDigit = 10000 + new Random().nextInt(90000);
        return bank.getKodePrefix() + "-" + idAkunGame + "-" + randomDigit;
    }
}
