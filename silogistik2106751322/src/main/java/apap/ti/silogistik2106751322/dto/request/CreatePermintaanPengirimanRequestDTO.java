package apap.ti.silogistik2106751322.dto.request;

import apap.ti.silogistik2106751322.model.Karyawan;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CreatePermintaanPengirimanRequestDTO {
   
    private String nomorPengiriman;

    @NotBlank(message = "Nama penerima harus diisi")
    private String namaPenerima;

    @NotEmpty(message = "Alamat penerima harus diisi")
    private String alamatPenerima;

    @NotNull(message = "Tanggal pengiriman harus dipilih")
    private String tanggalPengiriman;

    @Min(value = 0, message = "Biaya pengiriman harus lebih besar dari atau sama dengan 0")
    private Integer biayaPengiriman;

    @NotNull(message = "Jenis layanan harus dipilih")
    private Integer jenisLayanan;

    @NotNull(message = "Karyawan harus dipilih")
    private Karyawan karyawan;
}
