package apap.ti.silogistik2106751322.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751322.model.PermintaanPengirimanBarang;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    
        List<PermintaanPengirimanBarang> findByPermintaanPengiriman_WaktuPermintaanBetween(LocalDateTime startDate, LocalDateTime endDate);

        boolean existsByPermintaanPengiriman_WaktuPermintaanBetweenAndId(LocalDateTime startDate, LocalDateTime endDate, Long id);

}
