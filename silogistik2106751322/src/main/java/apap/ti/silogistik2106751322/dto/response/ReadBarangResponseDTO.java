package apap.ti.silogistik2106751322.dto.response;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    
    private String sku;

    private Integer tipeBarang;

    private String merk;

    private BigInteger hargaBarang;

    private Integer stok = 0;

}
