package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751322.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751322.model.Barang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

}
