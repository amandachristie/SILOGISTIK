package apap.ti.silogistik2106751322.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.Gudang;
import apap.ti.silogistik2106751322.model.GudangBarang;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    
    List<GudangBarang> findGudangBarangByGudang_Id(Long idGudang);

    List<GudangBarang> findGudangBarangByBarang_Sku(String sku);

    GudangBarang findByGudangAndBarang(Gudang gudang, Barang barang);

    @Query("SELECT SUM(g.stok) FROM GudangBarang g WHERE g.barang.sku = :sku")
    Integer countTotalStokBySkuBarang(@Param("sku") String sku);
    
}