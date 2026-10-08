package controller;

import java.util.ArrayList;
import model.Bahan;
import model.BahanCair;
import model.BahanPadat;

public class StokController {
    private final ArrayList<Bahan> listBahan;

    public StokController() {
        this.listBahan = new ArrayList<>();
        initDummyData();
    }

    private void initDummyData() {
        listBahan.add(new BahanCair("C01", "Susu Fresh Milk", 15, "Liter", "Susu"));
        listBahan.add(new BahanPadat("P01", "Biji Kopi Espresso", 10, "Kg", "Biji"));
    }

    public void tambahBahan(Bahan bahan) {
        listBahan.add(bahan);
        System.out.println("Berhasil menambahkan bahan: " + bahan.getNamaBahan());
    }

    public void tampilkanSemuaBahan() {
        if (listBahan.isEmpty()) {
            System.out.println("Data bahan baku masih kosong.");
        } else {
            System.out.println("\n=== Daftar Stok Bahan Baku ===");
            for (Bahan bahan : listBahan) {
                bahan.tampilkanInfo();
            }
        }
    }

    public Bahan getBahanById(String idBahan) {
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
        Bahan bahan = getBahanById(idBahan);
        if (bahan != null) {
            bahan.setNamaBahan(namaBaru);
            bahan.setKemasan(kemasanBaru);
            return true;
        }
        return false;
    }

    public boolean hapusBahan(String idBahan) {
        Bahan bahan = getBahanById(idBahan);
        if (bahan != null) {
            listBahan.remove(bahan);
            return true;
        }
        return false;
    }
}