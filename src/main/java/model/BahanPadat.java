package model;

public class BahanPadat extends Bahan implements StokOperasi {
    private String bentukFisik; 

    public BahanPadat(String idBahan, String namaBahan, int stok, String kemasan, String bentukFisik) {
        super(idBahan, namaBahan, stok, kemasan);
        setBentukFisik(bentukFisik);
    }

    public String getBentukFisik() {
        return bentukFisik;
    }


    public void setBentukFisik(String bentukFisik) {
        if (bentukFisik == null || bentukFisik.trim().isEmpty()) {
            this.bentukFisik = "Tanpa Bentuk";
        } else {
            this.bentukFisik = bentukFisik;
        }
    }


    @Override
    public void tampilkanInfo() {
        System.out.println("ID: " + getIdBahan() + " | [Padat] " + getNamaBahan() + " | Bentuk: " + bentukFisik + " | Stok: " + getStok() + " " + getKemasan());
    }


    @Override
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            setStok(getStok() + jumlah);
            System.out.println("Stok " + getNamaBahan() + " berhasil ditambahkan.");
        }
    }


    public void tambahStok(int jumlah, String kondisiCatatan) {
        if (jumlah > 0) {
            setStok(getStok() + jumlah);
            System.out.println("Stok " + getNamaBahan() + " ditambah sejumlah " + jumlah + " dengan catatan: " + kondisiCatatan);
        }
    }

    @Override
    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && getStok() >= jumlah) {
            setStok(getStok() - jumlah);
            return true;
        }
        return false;
    }
}