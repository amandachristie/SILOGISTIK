package apap.ti.silogistik2106751322.service;

import java.util.List;

import apap.ti.silogistik2106751322.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, int jumlahPesananBarang);

    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    String generateNomorPengiriman(int jumlahPesananBarang, int jenisLayanan);

    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    void cancelPermintaanPengiriman(Long id);
}
