package apap.ti.silogistik2106751322.dto.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import apap.ti.silogistik2106751322.model.GudangBarang;
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

    private List<GudangBarang> listGudangBarang = new ArrayList<>();

}
