package apap.ti.silogistik2106751322.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.Gudang;
import apap.ti.silogistik2106751322.model.GudangBarang;
import apap.ti.silogistik2106751322.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService{
    
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public GudangBarang getByGudangAndBarang(Gudang gudang, Barang barang) {
        return gudangBarangDb.findByGudangAndBarang(gudang, barang);
    }
    

    @Override
    public Integer getTotalStokByIdBarang(String idBarang) {
        return gudangBarangDb.countTotalStokBySkuBarang(idBarang);
    }
}
