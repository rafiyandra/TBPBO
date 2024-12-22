public class BijiBijian extends HasilTani {
    public BijiBijian(String kodeHasilTani, String jenisHasilTani, double beratPanen, double hargaPerKg, String tanggalPanen) {
        super(kodeHasilTani, jenisHasilTani, beratPanen, hargaPerKg, tanggalPanen);
    }

    // Getters and Setters
    public String getKodeHasilTani() {
        return kodeHasilTani;
    }

    public void setKodeHasilTani(String kodeHasilTani) {
        this.kodeHasilTani = kodeHasilTani;
    }

    public String getJenisHasilTani() {
        return jenisHasilTani;
    }

    public void setJenisHasilTani(String jenisHasilTani) {
        this.jenisHasilTani = jenisHasilTani;
    }

    public double getBeratPanen() {
        return beratPanen;
    }

    public void setBeratPanen(double beratPanen) {
        this.beratPanen = beratPanen;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public void setHargaPerKg(double hargaPerKg) {
        this.hargaPerKg = hargaPerKg;
    }

    public String getTanggalPanen() {
        return tanggalPanen;
    }

    public void setTanggalPanen(String tanggalPanen) {
        this.tanggalPanen = tanggalPanen;
    }
}