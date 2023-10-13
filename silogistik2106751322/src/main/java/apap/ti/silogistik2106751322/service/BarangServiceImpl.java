package apap.ti.silogistik2106751322.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) { 
        barang.setSku(generateSKU(barang.getTipeBarang()));
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAllOrderedByMerk();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(sku)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if (barang != null){
                barang.setHargaBarang(barangFromDto.getHargaBarang());
                barang.setMerk(barangFromDto.getMerk());
                barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public String generateSKU(Integer tipeBarang) {
        String tipeText = "";
        switch (tipeBarang) {
            case 1:
                tipeText = "ELEC";
                break;
            case 2:
                tipeText = "CLOT";
                break;
            case 3:
                tipeText = "FOOD";
                break;
            case 4:
                tipeText = "COSM";
                break;
            case 5:
                tipeText = "TOOL";
                break;
            default:
                throw new IllegalArgumentException("Tipe barang tidak valid");
        }

        // Mendapatkan angka auto-increment
        String angkaIncrement = "";
        // Mengambil angka increment dari database atau tempat penyimpanan lainnya.
        Integer nextIncrement = getNextIncrementForTipe(tipeBarang);
        // Mengonversi angka auto-increment menjadi tiga karakter dengan padding nol
        angkaIncrement = String.format("%03d", nextIncrement);

        // Menggabungkan tipe barang dan angka auto-increment menjadi SKU
        String SKU = tipeText + angkaIncrement;
        
        return SKU;
    }

    @Override
    public Integer getNextIncrementForTipe(Integer tipeBarang) {

        Integer lastIncrement = barangDb.findLastIncrementByTipeBarang(tipeBarang);

        // Jika belum ada angka increment untuk tipeBarang, inisialisasi dengan 1
        if (lastIncrement == null) { lastIncrement = 0; }

        // Tingkatkan angka increment dan simpan ke database
        Integer nextIncrement = lastIncrement + 1;

        return nextIncrement;
    }
    
}
