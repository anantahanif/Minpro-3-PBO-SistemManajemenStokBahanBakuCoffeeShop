package model;

public abstract class Bahan {
    private final String idBahan;
    private String namaBahan;
    private int stok;
    private String kemasan;
    
    
    public Bahan(String idBahan, String namaBahan, int stok, String kemasan) {
        this.idBahan = idBahan;
        setNamaBahan(namaBahan);
        setStok(stok);
        setKemasan(kemasan);
    }

    public abstract void tampilkanInfo();

    public String getIdBahan() {
        return idBahan;
    }
    
    public String getNamaBahan() {
        return namaBahan;
    }

    public void setNamaBahan(String namaBahan) {
        if (namaBahan == null || namaBahan.trim().isEmpty()) {
            System.out.println("Peringatan: Nama bahan tidak boleh kosong! Disetel ke 'Tanpa Nama'.");
            this.namaBahan = "Tanpa Nama";
        } else {
            this.namaBahan = namaBahan;
        }
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            System.out.println("Peringatan: Stok tidak boleh negatif! Disetel ke 0.");
            this.stok = 0;
        } else {
            this.stok = stok;
        }
    }

    public String getKemasan() {
        return kemasan;
    }

    public void setKemasan(String kemasan) {
        if (kemasan == null || kemasan.trim().isEmpty()) {
            System.out.println("Peringatan: Jenis kemasan tidak boleh kosong! Disetel ke 'Default'.");
            this.kemasan = "Default";
        } else {
            this.kemasan = kemasan;
        }
    }
}