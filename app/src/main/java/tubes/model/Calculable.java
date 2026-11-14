package tubes.model;

public interface Calculable {
    double hitungWinRateSaatIni(int totalMatch, int totalMenang);
    int hitungKemenanganDibutuhkan(int totalMatch, int totalMenang, double targetWinRate);
    double hitungFaktorKesulitan(int totalMatch);
}