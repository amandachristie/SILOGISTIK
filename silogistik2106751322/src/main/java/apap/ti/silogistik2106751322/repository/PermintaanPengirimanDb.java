package apap.ti.silogistik2106751322.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106751322.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    
    List<PermintaanPengiriman> findAllByOrderByWaktuPermintaanDesc();
    
}
