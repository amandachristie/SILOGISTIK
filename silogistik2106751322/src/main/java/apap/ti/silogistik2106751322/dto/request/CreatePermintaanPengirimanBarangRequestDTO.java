package apap.ti.silogistik2106751322.dto.request;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.PermintaanPengiriman;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CreatePermintaanPengirimanBarangRequestDTO {
   
    private PermintaanPengiriman permintaanPengiriman;

    private Barang barang;

    private Integer kuantitasPesanan;
}
