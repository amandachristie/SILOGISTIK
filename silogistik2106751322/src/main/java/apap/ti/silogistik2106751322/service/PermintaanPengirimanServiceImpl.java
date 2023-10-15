package apap.ti.silogistik2106751322.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import apap.ti.silogistik2106751322.model.PermintaanPengiriman;
import apap.ti.silogistik2106751322.repository.PermintaanPengirimanDb;


@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    
    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, int jumlahPesananBarang) {
        permintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(jumlahPesananBarang, permintaanPengiriman.getJenisLayanan()));
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        permintaanPengirimanDb.save(permintaanPengiriman); 
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc();
    }

    @Override
    public String generateNomorPengiriman(int jumlahPesananBarang, int jenisLayanan) {
        
        if (jumlahPesananBarang > 99) {  jumlahPesananBarang %= 100; }

        String kodeJenisLayanan = "";
        switch (jenisLayanan) {
            case 1:
                kodeJenisLayanan = "SAM";
                break;
            case 2:
                kodeJenisLayanan = "KIL";
                break;
            case 3:
                kodeJenisLayanan = "REG";
                break;
            case 4:
                kodeJenisLayanan = "HEM";
                break;
            default:
                throw new IllegalArgumentException("Jenis layanan tidak valid");
        }
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktuPermintaan = dtf.format(LocalDateTime.now());
        
        String nomorPengiriman = String.format("REQ%02d%s%s", jumlahPesananBarang, kodeJenisLayanan, waktuPermintaan);

        return nomorPengiriman;
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getId().equals(id)) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setIsCancelled(true);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

}