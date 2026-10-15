package controller;

import java.util.ArrayList;
import model.Bahan;
import model.BahanDAO;

public class StokController {
    private final BahanDAO bahanDAO;

    public StokController() {
        this.bahanDAO = new BahanDAO();
    }

    public void tambahBahan(Bahan bahan) {
        if (bahanDAO.tambahBahan(bahan)) {
            System.out.println("Berhasil menambahkan bahan ke database: " + bahan.getNamaBahan());
        } else {
            System.out.println("Gagal menambahkan bahan ke database.");
        }
    }

    public void tampilkanSemuaBahan() {
        ArrayList<Bahan> listBahan = bahanDAO.getAllBahan();
        if (listBahan.isEmpty()) {
            System.out.println("Data bahan baku di database masih kosong.");
        } else {
            System.out.println("\n=== Daftar Stok Bahan Baku (Database MySQL) ===");
            for (Bahan bahan : listBahan) {
                bahan.tampilkanInfo();
            }
        }
    }

    public Bahan getBahanById(String idBahan) {
        ArrayList<Bahan> listBahan = bahanDAO.getAllBahan();
        for (Bahan bahan : listBahan) {
            if (bahan.getIdBahan().equalsIgnoreCase(idBahan)) {
                return bahan;
            }
        }
        return null;
    }

    public boolean isIdExist(String idBahan) {
        return getBahanById(idBahan) != null;
    }

    public boolean updateBahan(String idBahan, String namaBaru, String kemasanBaru) {
        return bahanDAO.updateBahan(idBahan, namaBaru, kemasanBaru);
    }

    public boolean updateStokDatabase(String idBahan, int stokBaru) {
        return bahanDAO.updateStok(idBahan, stokBaru);
    }

    public boolean hapusBahan(String idBahan) {
        return bahanDAO.hapusBahan(idBahan);
    }
}