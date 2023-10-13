package apap.ti.silogistik2106751322.dto.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import apap.ti.silogistik2106751322.model.Karyawan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    
    private Long id;

    private String nomorPengiriman;

    private Boolean isCancelled;

    private String namaPenerima;

    private String alamatPenerima;

    private String tanggalPengiriman;

    private Integer biayaPengiriman;

    private Integer jenisLayanan;

    private String waktuPermintaan;

    private Karyawan karyawan;

    private List<ReadPermintaanPengirimanBarangResponseDTO> listPermintaanPengirimanBarang = new ArrayList<>();

    private BigInteger totalHarga;
}
