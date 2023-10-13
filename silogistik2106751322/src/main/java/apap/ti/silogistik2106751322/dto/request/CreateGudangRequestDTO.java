package apap.ti.silogistik2106751322.dto.request;

import java.util.List;

import apap.ti.silogistik2106751322.model.GudangBarang;
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
public class CreateGudangRequestDTO {

    private String nama;

    private String alamatGudang;

    private List<GudangBarang> listBarang;
}
