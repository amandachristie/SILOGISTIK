package apap.ti.silogistik2106751322.dto.request;

import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Merk harus diisi")
    @Size(max = 255, message = "Merk harus maksimal 255 karakter")
    private String merk;

    @Positive(message = "Harga barang harus positif")
    private BigInteger hargaBarang;
}
