package apap.ti.silogistik2106751322.dto.response;

import java.math.BigInteger;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.PermintaanPengiriman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanBarangResponseDTO {
    
    private PermintaanPengiriman permintaanPengiriman;

    private Barang barang;

    private Integer kuantitasPesanan;

    private BigInteger totalHarga;

}
