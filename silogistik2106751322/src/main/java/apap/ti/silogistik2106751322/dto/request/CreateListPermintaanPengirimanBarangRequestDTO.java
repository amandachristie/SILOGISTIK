package apap.ti.silogistik2106751322.dto.request;

import java.util.List;

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
public class CreateListPermintaanPengirimanBarangRequestDTO {
    
    private List<CreatePermintaanPengirimanBarangRequestDTO> listPermintaanPengirimanBarang;

    private CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO;

}
