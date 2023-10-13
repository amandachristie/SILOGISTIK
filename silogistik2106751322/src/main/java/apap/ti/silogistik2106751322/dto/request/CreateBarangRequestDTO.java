package apap.ti.silogistik2106751322.dto.request;

import java.math.BigInteger;

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
public class CreateBarangRequestDTO {

    private Integer tipeBarang;

    private String merk;

    private BigInteger hargaBarang;
}
