package tubes.model.enumm;

public enum Bank {
    BCA("8801"),
    BRI("8802"),
    MANDIRI("8803"),
    BNI("8804");

    private final String kodePrefix;

    Bank(String kodePrefix) {
        this.kodePrefix = kodePrefix;
    }

    public String getKodePrefix() {
        return kodePrefix;
    }
}
