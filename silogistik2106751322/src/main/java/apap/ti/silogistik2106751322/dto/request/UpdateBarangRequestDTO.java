package apap.ti.silogistik2106751322.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBarangRequestDTO extends CreateBarangRequestDTO{

    @NotNull
    private String sku;
    
}
