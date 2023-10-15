package apap.ti.silogistik2106751322.dto.request;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
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
public class CreateKaryawanRequestDTO {

    @NotEmpty(message = "Nama karyawan harus diisi")
    private String nama;

    private Integer jenisKelamin;

    @NotEmpty(message = "Tanggal lahir harus diisi")
    private Date tanggalLahir;

}
