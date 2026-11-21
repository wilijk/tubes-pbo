package tubes.payment;

import java.util.Random;
import tubes.model.enumm.EWallet;
import tubes.model.interfacee.Pembayaran;

public class PembayaranEWallet implements Pembayaran {
    private EWallet eWallet;

    public PembayaranEWallet(EWallet eWallet) {
        this.eWallet = eWallet;
    }

    @Override
    public String getNamaMetode() {
        return "E-Wallet " + eWallet.name();
    }

    @Override
    public String generateVirtualNumber(String idAkunGame) {
        int randomPhone = 1000000 + new Random().nextInt(9000000);
        return eWallet.getKodePrefix() + "-08" + randomPhone;
    }
}
