package apap.ti.silogistik2106751322.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751322.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751322.repository.PermintaanPengirimanBarangDb;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;
    
    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang) {
        permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
    }

    @Override
    public boolean isPermintaanPengirimanInDateRange(LocalDateTime startDate, LocalDateTime endDate, Long id) {
        return permintaanPengirimanBarangDb.existsByPermintaanPengiriman_WaktuPermintaanBetweenAndId(startDate, endDate, id);
    }
}