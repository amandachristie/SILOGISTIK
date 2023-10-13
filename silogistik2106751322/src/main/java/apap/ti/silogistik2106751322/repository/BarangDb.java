package apap.ti.silogistik2106751322.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751322.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String>{
    
    // Mencari angka increment terakhir untuk tipeBarang
    @Query("SELECT MAX(SUBSTRING(b.sku, 5, 3)) FROM Barang b WHERE b.tipeBarang = :tipeBarang")
    Integer findLastIncrementByTipeBarang(@Param("tipeBarang") Integer tipeBarang);
    
    @Query("SELECT b FROM Barang b ORDER BY LOWER(b.merk) ASC")
    List<Barang> findAllOrderedByMerk();
}
