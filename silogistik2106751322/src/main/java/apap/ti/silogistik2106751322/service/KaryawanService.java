package apap.ti.silogistik2106751322.service;

import java.util.List;

import apap.ti.silogistik2106751322.model.Karyawan;

public interface KaryawanService {
    
    void saveKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();

}
