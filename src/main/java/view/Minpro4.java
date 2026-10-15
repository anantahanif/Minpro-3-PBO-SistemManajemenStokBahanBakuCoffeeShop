package view;

import controller.StokController;
import java.util.Scanner;
import model.Bahan;
import model.BahanCair;
import model.BahanPadat;

public class Minpro4 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StokController controller = new StokController();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=============================================");
            System.out.println("  SISTEM MANAJEMEN STOK BAHAN BAKU COFFEE SHOP");
            System.out.println("           (TERINTEGRASI DATABASE JDBC)");
            System.out.println("=============================================");
            System.out.println("1. Tambah Bahan Baku");
            System.out.println("2. Tampilkan Semua Bahan Baku");
            System.out.println("3. Update Data Bahan Baku");
            System.out.println("4. Kelola Stok (Interface & Database Update)");
            System.out.println("5. Hapus Bahan Baku");
            System.out.println("6. Keluar");
            System.out.println("=============================================");

            int pilihan = readIntInput("Pilih menu (1-6): ");

            switch (pilihan) {
                case 1:
                    menuTambahBahan();
                    break;
                case 2:
                    controller.tampilkanSemuaBahan();
                    break;
                case 3:
                    menuUpdateBahan();
                    break;
                case 4:
                    menuKelolaStok();
                    break;
                case 5:
                    menuHapusBahan();
                    break;
                case 6:
                    System.out.println("Terima kasih! Program selesai.");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih angka 1-6.");
                    break;
            }
        }
    }

    private static void menuTambahBahan() {
        System.out.println("\n--- Tambah Bahan Baku Baru ---");
        String idBahan = readNonEmptyString("Masukkan ID Bahan: ");

        if (controller.isIdExist(idBahan)) {
            System.out.println("Error: ID Bahan sudah terdaftar dalam database!");
            return;
        }

        String namaBahan = readNonEmptyString("Masukkan Nama Bahan: ");
        int stok = readIntInput("Masukkan Jumlah Stok Awal: ");
        String kemasan = readNonEmptyString("Masukkan Jenis Kemasan (contoh: Botol/Kg/Pack): ");

        System.out.println("Pilih Jenis Bahan:");
        System.out.println("1. Bahan Cair");
        System.out.println("2. Bahan Padat");
        int jenis = readIntInput("Pilihan (1/2): ");

        switch (jenis) {
            case 1:
                String jenisCairan = readNonEmptyString("Masukkan Detail Jenis Cairan (contoh: Sirup/Susu/Espresso): ");
                controller.tambahBahan(new BahanCair(idBahan, namaBahan, stok, kemasan, jenisCairan));
                break;
            case 2:
                String bentukFisik = readNonEmptyString("Masukkan Detail Bentuk Fisik (contoh: Bubuk/Biji/Batang): ");
                controller.tambahBahan(new BahanPadat(idBahan, namaBahan, stok, kemasan, bentukFisik));
                break;
            default:
                System.out.println("Pilihan jenis bahan tidak valid! Penambahan dibatalkan.");
                break;
        }
    }

    private static void menuUpdateBahan() {
        System.out.println("\n--- Update Data Bahan Baku ---");
        String idBahan = readNonEmptyString("Masukkan ID Bahan yang ingin diubah: ");

        if (!controller.isIdExist(idBahan)) {
            System.out.println("Data dengan ID tersebut tidak ditemukan di database.");
            return;
        }

        String namaBaru = readNonEmptyString("Masukkan Nama Bahan Baru: ");
        String kemasanBaru = readNonEmptyString("Masukkan Jenis Kemasan Baru: ");

        if (controller.updateBahan(idBahan, namaBaru, kemasanBaru)) {
            System.out.println("Data bahan baku di database berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui data bahan baku di database.");
        }
    }

    private static void menuKelolaStok() {
        System.out.println("\n--- Kelola Stok Bahan Baku ---");
        String idBahan = readNonEmptyString("Masukkan ID Bahan: ");
        Bahan bahan = controller.getBahanById(idBahan);

        if (bahan == null) {
            System.out.println("Bahan baku tidak ditemukan di database!");
            return;
        }

        System.out.println("Menu Kelola Stok:");
        System.out.println("1. Tambah Stok (Biasa)");
        System.out.println("2. Tambah Stok dengan Catatan/Supplier (Method Overloading)");
        System.out.println("3. Kurangi Stok (Interface StokOperasi)");
        int aksi = readIntInput("Pilihan (1-3): ");

        switch (aksi) {
            case 1:
                int jumlahTambah1 = readIntInput("Masukkan jumlah penambahan stok: ");
                if (bahan instanceof BahanCair) {
                    ((BahanCair) bahan).tambahStok(jumlahTambah1);
                } else if (bahan instanceof BahanPadat) {
                    ((BahanPadat) bahan).tambahStok(jumlahTambah1);
                }
                controller.updateStokDatabase(bahan.getIdBahan(), bahan.getStok());
                System.out.println("Perubahan stok berhasil disimpan ke database.");
                break;
            case 2:
                int jumlahTambah2 = readIntInput("Masukkan jumlah penambahan stok: ");
                String infoTambahan = readNonEmptyString("Masukkan Catatan / Nama Supplier: ");
                if (bahan instanceof BahanCair) {
                    ((BahanCair) bahan).tambahStok(jumlahTambah2, infoTambahan);
                } else if (bahan instanceof BahanPadat) {
                    ((BahanPadat) bahan).tambahStok(jumlahTambah2, infoTambahan);
                }
                controller.updateStokDatabase(bahan.getIdBahan(), bahan.getStok());
                System.out.println("Perubahan stok berhasil disimpan ke database.");
                break;
            case 3:
                int jumlahKurang = readIntInput("Masukkan jumlah pengurangan stok: ");
                boolean sukses = false;
                if (bahan instanceof BahanCair) {
                    sukses = ((BahanCair) bahan).kurangiStok(jumlahKurang);
                } else if (bahan instanceof BahanPadat) {
                    sukses = ((BahanPadat) bahan).kurangiStok(jumlahKurang);
                }

                if (sukses) {
                    controller.updateStokDatabase(bahan.getIdBahan(), bahan.getStok());
                    System.out.println("Stok " + bahan.getNamaBahan() + " berhasil dikurangi dan diperbarui di database.");
                } else {
                    System.out.println("Gagal mengurangi stok! Jumlah tidak valid atau melebihi stok yang ada.");
                }
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }
    }

    private static void menuHapusBahan() {
        System.out.println("\n--- Hapus Bahan Baku ---");
        String idBahan = readNonEmptyString("Masukkan ID Bahan yang ingin dihapus: ");

        if (controller.hapusBahan(idBahan)) {
            System.out.println("Bahan baku dengan ID " + idBahan + " berhasil dihapus dari database.");
        } else {
            System.out.println("Bahan baku dengan ID tersebut tidak ditemukan di database.");
        }
    }

    private static String readNonEmptyString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Peringatan: Input tidak boleh kosong! Silakan isi kembali.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String rawInput = scanner.nextLine().trim();
            try {
                return Integer.parseInt(rawInput);
            } catch (NumberFormatException e) {
                System.out.println("Peringatan: Input harus berupa angka bulat valid!");
            }
        }
    }
}