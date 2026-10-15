package model;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BahanDAO {

    public ArrayList<Bahan> getAllBahan() {
        ArrayList<Bahan> list = new ArrayList<>();
        String sql = "SELECT * FROM bahan_baku";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_bahan");
                String nama = rs.getString("nama_bahan");
                int stok = rs.getInt("stok");
                String kemasan = rs.getString("kemasan");
                String kategori = rs.getString("kategori");
                String detail = rs.getString("detail_spesifik");

                if ("Cair".equalsIgnoreCase(kategori)) {
                    list.add(new BahanCair(id, nama, stok, kemasan, detail));
                } else if ("Padat".equalsIgnoreCase(kategori)) {
                    list.add(new BahanPadat(id, nama, stok, kemasan, detail));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data: " + e.getMessage());
        }
        return list;
    }

    public boolean tambahBahan(Bahan bahan) {
        String sql = "INSERT INTO bahan_baku (id_bahan, nama_bahan, stok, kemasan, kategori, detail_spesifik) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bahan.getIdBahan());
            pstmt.setString(2, bahan.getNamaBahan());
            pstmt.setInt(3, bahan.getStok());
            pstmt.setString(4, bahan.getKemasan());

            if (bahan instanceof BahanCair) {
                pstmt.setString(5, "Cair");
                pstmt.setString(6, ((BahanCair) bahan).getJenisCairan());
            } else if (bahan instanceof BahanPadat) {
                pstmt.setString(5, "Padat");
                pstmt.setString(6, ((BahanPadat) bahan).getBentukFisik());
            }

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error saat menambah bahan: " + e.getMessage());
            return false;
        }
    }

    public boolean updateBahan(String idBahan, String namaBaru, String kemasanBaru) {
        String sql = "UPDATE bahan_baku SET nama_bahan = ?, kemasan = ? WHERE id_bahan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, namaBaru);
            pstmt.setString(2, kemasanBaru);
            pstmt.setString(3, idBahan);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error saat mengupdate data: " + e.getMessage());
            return false;
        }
    }

    public boolean updateStok(String idBahan, int stokBaru) {
        String sql = "UPDATE bahan_baku SET stok = ? WHERE id_bahan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, stokBaru);
            pstmt.setString(2, idBahan);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error saat mengupdate stok: " + e.getMessage());
            return false;
        }
    }

    public boolean hapusBahan(String idBahan) {
        String sql = "DELETE FROM bahan_baku WHERE id_bahan = ?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idBahan);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error saat menghapus data: " + e.getMessage());
            return false;
        }
    }
}