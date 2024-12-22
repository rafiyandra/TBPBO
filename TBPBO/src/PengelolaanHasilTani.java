import java.sql.Connection;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.sql.Statement;
  import java.util.Random;
  import java.util.Scanner;
  
  public class PengelolaanHasilTani{
      public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);
  
          try {
              // Login
              if (!login(scanner)) {
                  System.out.println("Login gagal! Program dihentikan.");
                  return;
              }
              System.out.println("Login berhasil");
              System.out.println("+----------------------------------------------------+\n");
  
              // Menu pilihan operasi
              while (true) {
                  System.out.println("+----------------------------------------------------+");  
                  System.out.println("                   PILIH OPERASI");
                  System.out.println("+----------------------------------------------------+");
                  System.out.println("1. Tambah Hasil Tani");
                  System.out.println("2. Lihat Hasil Tani");
                  System.out.println("3. Update Hasil Tani");
                  System.out.println("4. Hapus Hasil Tani");
                  System.out.println("5. Keluar");
                  System.out.print("Masukkan pilihan: ");
                  int pilihan = scanner.nextInt();
                  scanner.nextLine(); // Consume newline
  
                  switch (pilihan) {
                      case 1:
                          tambahHasilTani(scanner);
                          break;
                      case 2:
                          lihatHasilTani();
                          break;
                      case 3:
                          updateHasilTani(scanner);
                          break;
                      case 4:
                          hapusHasilTani(scanner);
                          break;
                      case 5:
                          System.out.println("Keluar dari program...");
                          return;
                      default:
                          System.out.println("Pilihan tidak valid.");
                  }
              }
  
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
          } finally {
              scanner.close();
          }
      }
  
          // Tambah Hasil Tani ke Database
    public static void tambahHasilTani(Scanner scanner) throws SQLException {
        System.out.println("\n+----------------------------------------------------+");
        System.out.println("                  TAMBAH HASIL TANI");
        System.out.println("+----------------------------------------------------+");
        System.out.println("Pilih kategori hasil tani:");
        System.out.println("1. Sayur-sayuran");
        System.out.println("2. Biji-bijian");
        System.out.print("Masukkan pilihan: ");
        int kategoriPilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String jenisHasilTani;
        double beratPanen;
        double hargaPerKg;
        String tanggalPanen;

        if (kategoriPilihan == 1) {
            System.out.println("Pilih jenis sayuran:");
            System.out.println("1. Kol");
            System.out.println("2. Bayam");
            System.out.println("3. Brokoli");
            System.out.print("Masukkan pilihan: ");
            int sayuranPilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validasi pilihan sayuran
            switch (sayuranPilihan) {
                case 1:
                    jenisHasilTani = "Kol";
                    break;
                case 2:
                    jenisHasilTani = "Bayam";
                    break;
                case 3:
                    jenisHasilTani = "Brokoli";
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Program dihentikan.");
                    return;
            }

            System.out.print("Masukkan Berat Panen (kg): ");
            beratPanen = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Masukkan Harga per Kg: ");
            hargaPerKg = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Masukkan Tanggal Panen (yyyy-mm-dd): ");
            tanggalPanen = scanner.nextLine();

            SayurSayuran sayur = new SayurSayuran("", jenisHasilTani, beratPanen, hargaPerKg, tanggalPanen);
            simpanKeDatabase(sayur);

        } else if (kategoriPilihan == 2) {
            System.out.println("Pilih jenis biji-bijian:");
            System.out.println("1. Gandum");
            System.out.println("2. Padi");
            System.out.println("3. Jagung");
            System.out.print("Masukkan pilihan: ");
            int bijiPilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validasi pilihan biji-bijian
            switch (bijiPilihan) {
                case 1:
                    jenisHasilTani = "Gandum";
                    break;
                case 2:
                    jenisHasilTani = "Padi";
                    break;
                case 3:
                    jenisHasilTani = "Jagung";
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Program dihentikan.");
                    return;
            }

            System.out.print("Masukkan Berat Panen (kg): ");
            beratPanen = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Masukkan Harga per Kg: ");
            hargaPerKg = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.print("Masukkan Tanggal Panen (yyyy-mm-dd): ");
            tanggalPanen = scanner.nextLine();

            BijiBijian biji = new BijiBijian("", jenisHasilTani, beratPanen, hargaPerKg, tanggalPanen);
            simpanKeDatabase(biji);

        } else {
            System.out.println("Pilihan tidak valid. Program dihentikan.");
        }
    }

    // Simpan Hasil Tani ke Database
    private static void simpanKeDatabase(HasilTani hasilTani) throws SQLException {
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO hasil_tani (jenis_hasil_tani, berat_panen, harga_per_kg, total_harga, tanggal_panen) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, hasilTani.jenisHasilTani);
                stmt.setDouble(2, hasilTani.beratPanen);
                stmt.setDouble(3, hasilTani.hargaPerKg);
                stmt.setDouble(4, hasilTani.beratPanen * hasilTani.hargaPerKg);
                stmt.setString(5, hasilTani.tanggalPanen);
                stmt.executeUpdate();
                System.out.println("+----------------------------------------------------+");
                System.out.println("Hasil tani berhasil ditambahkan.");
                System.out.println("+----------------------------------------------------+\n");
            }
        }
    }
  
  
     // Lihat Hasil Tani dari Database dengan Inheritance
public static void lihatHasilTani() throws SQLException {
    try (Connection conn = DatabaseHelper.getConnection()) {
        String sql = "SELECT * FROM hasil_tani";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            // Flag untuk mengecek apakah data ditemukan
            boolean dataDitemukan = false;

            System.out.println("\n+----------------------------------------------------+");
            System.out.println("                  LIHAT HASIL TANI");
            System.out.println("+----------------------------------------------------+");
            while (rs.next()) {
                dataDitemukan = true; // Jika ada data, ubah flag menjadi true

                String kodeHasilTani = rs.getString("kode_hasil_tani");
                String jenisHasilTani = rs.getString("jenis_hasil_tani");
                double beratPanen = rs.getDouble("berat_panen");
                double hargaPerKg = rs.getDouble("harga_per_kg");
                double totalHarga = rs.getDouble("total_harga");
                String tanggalPanen = rs.getString("tanggal_panen");

                // Tampilkan berdasarkan jenis hasil tani
                System.out.println("Kode Hasil Tani: " + kodeHasilTani);
                System.out.println("Jenis Hasil Tani: " + jenisHasilTani);
                System.out.println("Berat Panen (kg): " + beratPanen);
                System.out.println("Harga per Kg: " + hargaPerKg);
                System.out.println("Total Harga: " + totalHarga);
                System.out.println("Tanggal Panen: " + tanggalPanen);
                System.out.println("");
            }
            System.out.println("+----------------------------------------------------+\n");
            
            // Jika tidak ada data, tampilkan pesan di luar perulangan
            if (!dataDitemukan) {
                System.out.println("\n+----------------------------------------------------+");
                System.out.println("Data tidak ditemukan.");
                System.out.println("+----------------------------------------------------+\n");
            }
        }
    }
}


  
      // Update Hasil Tani di Database
public static void updateHasilTani(Scanner scanner) throws SQLException {
    System.out.println("\n+----------------------------------------------------+");
    System.out.println("                  UPDATE HASIL TANI");
    System.out.println("+----------------------------------------------------+");
    System.out.print("Masukkan Kode Hasil Tani yang ingin diubah: ");
    String kodeHasilTani = scanner.nextLine();
    System.out.println("+----------------------------------------------------+");

    System.out.println("Pilih kategori hasil tani:");
    System.out.println("1. Sayur-sayuran");
    System.out.println("2. Biji-bijian");
    System.out.print("Masukkan pilihan: ");
    int kategoriPilihan = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    String jenisHasilTani;
    double beratPanen;
    double hargaPerKg;
    String tanggalPanen;

    if (kategoriPilihan == 1) {
        System.out.println("Pilih jenis sayuran:");
        System.out.println("1. Kol");
        System.out.println("2. Bayam");
        System.out.println("3. Brokoli");
        System.out.print("Masukkan pilihan: ");
        int sayuranPilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validasi pilihan sayuran
        switch (sayuranPilihan) {
            case 1:
                jenisHasilTani = "Kol";
                break;
            case 2:
                jenisHasilTani = "Bayam";
                break;
            case 3:
                jenisHasilTani = "Brokoli";
                break;
            default:
                System.out.println("Pilihan tidak valid. Program dihentikan.");
                return;
        }

        System.out.print("Masukkan Berat Panen Baru (kg): ");
        beratPanen = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan Harga per Kg Baru: ");
        hargaPerKg = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan Tanggal Panen Baru (yyyy-mm-dd): ");
        tanggalPanen = scanner.nextLine();

    } else if (kategoriPilihan == 2) {
        System.out.println("Pilih jenis biji-bijian:");
        System.out.println("1. Gandum");
        System.out.println("2. Padi");
        System.out.println("3. Jagung");
        System.out.print("Masukkan pilihan: ");
        int bijiPilihan = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validasi pilihan biji-bijian
        switch (bijiPilihan) {
            case 1:
                jenisHasilTani = "Gandum";
                break;
            case 2:
                jenisHasilTani = "Padi";
                break;
            case 3:
                jenisHasilTani = "Jagung";
                break;
            default:
                System.out.println("Pilihan tidak valid. Program dihentikan.");
                return;
        }

        System.out.print("Masukkan Berat Panen Baru (kg): ");
        beratPanen = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan Harga per Kg Baru: ");
        hargaPerKg = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan Tanggal Panen Baru (yyyy-mm-dd): ");
        tanggalPanen = scanner.nextLine();

    } else {
        System.out.println("Pilihan tidak valid. Program dihentikan.");
        return;
    }

    double totalHarga = beratPanen * hargaPerKg;

    try (Connection conn = DatabaseHelper.getConnection()) {
        String sql = "UPDATE hasil_tani SET jenis_hasil_tani = ?, berat_panen = ?, harga_per_kg = ?, total_harga = ?, tanggal_panen = ? WHERE kode_hasil_tani = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jenisHasilTani);
            stmt.setDouble(2, beratPanen);
            stmt.setDouble(3, hargaPerKg);
            stmt.setDouble(4, totalHarga);
            stmt.setString(5, tanggalPanen);
            stmt.setString(6, kodeHasilTani);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("+----------------------------------------------------+");
                System.out.println("Hasil tani berhasil diperbarui.");
                System.out.println("+----------------------------------------------------+\n");
            } else {
                System.out.println("+----------------------------------------------------+");
                System.out.println("Hasil tani dengan kode tersebut tidak ditemukan.");
                System.out.println("+----------------------------------------------------+\n");
            }
        }
    }
}
  
      // Hapus Hasil Tani dari Database
      public static void hapusHasilTani(Scanner scanner) throws SQLException {
            System.out.println("\n+----------------------------------------------------+");
            System.out.println("                   HAPUS HASIL TANI");
            System.out.println("+----------------------------------------------------+");
            System.out.print("Masukkan Kode Hasil Tani yang ingin dihapus: ");
            String kodeHasilTani = scanner.nextLine();
  
          try (Connection conn = DatabaseHelper.getConnection()) {
              String sql = "DELETE FROM hasil_tani WHERE kode_hasil_tani = ?";
              try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                  stmt.setString(1, kodeHasilTani);
                  int rowsDeleted = stmt.executeUpdate();
                  if (rowsDeleted > 0) {
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Hasil tani berhasil dihapus.");
                        System.out.println("+----------------------------------------------------+\n");
                  } else {
                        System.out.println("+----------------------------------------------------+");
                        System.out.println("Hasil tani dengan kode tersebut tidak ditemukan.");
                        System.out.println("+----------------------------------------------------+\n");
                  }
              }
          }
      }
  
      // Login method (tidak berubah)
      public static boolean login(Scanner scanner) {
          String username = "Ganteng";
          String password = "098";
          int maxAttempts = 3;
  
          for (int attempt = 1; attempt <= maxAttempts; attempt++) {
              System.out.println("+----------------------------------------------------+");
              System.out.println("                       LOGIN");
              System.out.println("+----------------------------------------------------+");
              System.out.print("Username: ");
              String inputUsername = scanner.nextLine();
              System.out.print("Password: ");
              String inputPassword = scanner.nextLine();
  
              // Generate captcha
              String captcha = generateCaptcha(6);
              System.out.println("Captcha: " + captcha);
              System.out.print("Masukkan Captcha: ");
              String inputCaptcha = scanner.nextLine();
              System.out.println("+----------------------------------------------------+");
  
              // Validasi login
              if (inputUsername.equals(username) && inputPassword.equals(password) && inputCaptcha.equals(captcha)) {
                  return true;
              } else {
                  System.out.println("Login gagal! Silakan coba lagi");
                  System.out.println("+----------------------------------------------------+\n");
              }
          }
  
          return false; // Gagal login setelah beberapa percobaan
      }
  
      // Generate Captcha (tidak berubah)
      public static String generateCaptcha(int length) {
          String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
          StringBuilder captcha = new StringBuilder();
          Random random = new Random();
  
          for (int i = 0; i < length; i++) {
              captcha.append(chars.charAt(random.nextInt(chars.length())));
          }
  
          return captcha.toString();
      }
  }