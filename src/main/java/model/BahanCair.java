package model;

public class BahanCair extends Bahan implements StokOperasi {
    private String jenisCairan; 

    public BahanCair(String idBahan, String namaBahan, int stok, String kemasan, String jenisCairan) {
        super(idBahan, namaBahan, stok, kemasan);
        setJenisCairan(jenisCairan);
    }

    public String getJenisCairan() {
        return jenisCairan;
    }


    public void setJenisCairan(String jenisCairan) {
        if (jenisCairan == null || jenisCairan.trim().isEmpty()) {
            this.jenisCairan = "Tanpa Jenis";
        } else {
            this.jenisCairan = jenisCairan;
        }
    }


    @Override
    public void tampilkanInfo() {
        System.out.println("ID: " + getIdBahan() + " | [Cair] " + getNamaBahan() + " | Jenis: " + jenisCairan + " | Stok: " + getStok() + " " + getKemasan());
    }

    
    @Override
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            setStok(getStok() + jumlah);
            System.out.println("Stok " + getNamaBahan() + " berhasil ditambahkan.");
        }
    }

    public void tambahStok(int jumlah, String supplier) {
        if (jumlah > 0) {
            setStok(getStok() + jumlah);
            System.out.println("Stok " + getNamaBahan() + " ditambah sejumlah " + jumlah + " dari supplier: " + supplier);
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