package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751322.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751322.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

}
