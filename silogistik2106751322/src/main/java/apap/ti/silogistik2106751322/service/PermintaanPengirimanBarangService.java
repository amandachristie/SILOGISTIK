package apap.ti.silogistik2106751322.service;

import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106751322.model.PermintaanPengirimanBarang;

public interface PermintaanPengirimanBarangService {
    
    void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang);

    boolean isPermintaanPengirimanInDateRange(LocalDateTime startDate, LocalDateTime endDate, Long id);;

}
