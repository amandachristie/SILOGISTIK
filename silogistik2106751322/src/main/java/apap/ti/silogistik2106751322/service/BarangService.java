package apap.ti.silogistik2106751322.service;

import java.util.List;

import apap.ti.silogistik2106751322.model.Barang;

public interface BarangService {
    
    void saveBarang(Barang barang);

    Integer getNextIncrementForTipe(Integer tipeBarang);

    String generateSKU(Integer tipeBarang);

    List<Barang> getAllBarang();

    Barang getBarangBySku(String sku);

    Barang updateBarang(Barang barang);

}
