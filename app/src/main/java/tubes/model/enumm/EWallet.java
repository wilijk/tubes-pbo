package tubes.model.enumm;

public enum EWallet {
    DANA("3901"),
    OVO("3902"),
    GOPAY("3903"),
    SHOPEEPAY("3904");

    private final String kodePrefix;

    EWallet(String kodePrefix) {
        this.kodePrefix = kodePrefix;
    }

    public String getKodePrefix() {
        return kodePrefix;
    }
}
