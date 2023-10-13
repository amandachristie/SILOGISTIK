package apap.ti.silogistik2106751322.service;

import java.util.List;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.Gudang;
import apap.ti.silogistik2106751322.model.GudangBarang;

public interface GudangBarangService {
    
    void saveGudangBarang(GudangBarang gudangBarang);

    List<GudangBarang> getDaftarGudangBarangByIdGudang(Long idGudang);

    List<GudangBarang> getDaftarGudangBarangByIdBarang(String idBarang);

    GudangBarang getByGudangAndBarang(Gudang gudang, Barang barang);

    Integer getTotalStokByIdBarang(String idBarang);

}
