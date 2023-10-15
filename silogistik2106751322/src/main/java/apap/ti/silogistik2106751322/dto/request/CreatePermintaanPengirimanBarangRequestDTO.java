package apap.ti.silogistik2106751322.dto.request;

import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.PermintaanPengiriman;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Kuantitas pesanan harus diisi")
    @Min(value = 0, message = "Kuantitas Pesanan harus positif")
    private Integer kuantitasPesanan;
}
