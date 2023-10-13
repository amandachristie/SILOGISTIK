package apap.ti.silogistik2106751322.dto.request;

import apap.ti.silogistik2106751322.model.Karyawan;

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
public class CreatePermintaanPengirimanRequestDTO {
   
    private String nomorPengiriman;

    private String namaPenerima;

    private String alamatPenerima;

    private String tanggalPengiriman;

    private Integer biayaPengiriman;

    private Integer jenisLayanan;

    private Karyawan karyawan;
}
