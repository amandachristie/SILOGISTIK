package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751322.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751322.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    GudangBarang createGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
}
