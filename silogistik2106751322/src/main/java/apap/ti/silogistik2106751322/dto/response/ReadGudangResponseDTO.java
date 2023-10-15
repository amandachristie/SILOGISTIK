package apap.ti.silogistik2106751322.dto.response;

import java.util.ArrayList;
import java.util.List;

import apap.ti.silogistik2106751322.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    
    private Long id;

    private String nama;

    private String alamatGudang;

    private List<GudangBarang> listGudangBarang = new ArrayList<>();
}
