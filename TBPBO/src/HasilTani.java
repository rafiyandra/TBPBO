import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class HasilTani implements HargaTotalCalculator {
    protected String kodeHasilTani;
    protected String jenisHasilTani;
    protected double beratPanen;
    protected double hargaPerKg;
    protected String tanggalPanen;

    private static final Logger logger = Logger.getLogger(HasilTani.class.getName());

    public HasilTani(String kodeHasilTani, String jenisHasilTani, double beratPanen, double hargaPerKg, String tanggalPanen) {
        this.kodeHasilTani = kodeHasilTani;
        this.jenisHasilTani = jenisHasilTani;
        this.beratPanen = beratPanen;
        this.hargaPerKg = hargaPerKg;
        this.tanggalPanen = tanggalPanen;
    }

    @Override
    public double hitungTotalHarga() {
        return beratPanen * hargaPerKg;
    }

    // Tambahkan validasi dan parsing tanggal
    public static Date parseTanggal(String tanggal) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(tanggal);
    }

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
        if (beratPanen < 0) throw new IllegalArgumentException("Berat panen tidak boleh negatif");
        this.beratPanen = beratPanen;
    }

    public double getHargaPerKg() {
        return hargaPerKg;
    }

    public void setHargaPerKg(double hargaPerKg) {
        if (hargaPerKg < 0) throw new IllegalArgumentException("Harga per kg tidak boleh negatif");
        this.hargaPerKg = hargaPerKg;
    }

    public String getTanggalPanen() {
        return tanggalPanen;
    }

    public void setTanggalPanen(String tanggalPanen) {
        try {
            parseTanggal(tanggalPanen);
        } catch (Exception e) {
            throw new IllegalArgumentException("Format tanggal salah! Gunakan yyyy-MM-dd.");
        }
        this.tanggalPanen = tanggalPanen;
    }

    // Format jenis hasil tani
    public String formatJenisHasilTani() {
        return jenisHasilTani.substring(0, 1).toUpperCase() + jenisHasilTani.substring(1).toLowerCase();
    }

    // Logging pada objek
    public void logDetail() {
        logger.info("Detail Hasil Tani: " + this.toString());
    }

    @Override
    public String toString() {
        return "HasilTani {" +
                "kodeHasilTani='" + kodeHasilTani + '\'' +
                ", jenisHasilTani='" + jenisHasilTani + '\'' +
                ", beratPanen=" + beratPanen +
                ", hargaPerKg=" + hargaPerKg +
                ", tanggalPanen='" + tanggalPanen + '\'' +
                '}';
    }
}
