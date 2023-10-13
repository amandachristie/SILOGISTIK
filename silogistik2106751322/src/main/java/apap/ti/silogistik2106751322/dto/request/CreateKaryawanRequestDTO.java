package apap.ti.silogistik2106751322.dto.request;

import java.util.Date;

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

    private String nama;

    private Integer jenisKelamin;

    private Date tanggalLahir;

}
