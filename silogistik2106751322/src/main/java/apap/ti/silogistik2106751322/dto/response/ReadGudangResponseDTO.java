package apap.ti.silogistik2106751322.dto.response;

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
}