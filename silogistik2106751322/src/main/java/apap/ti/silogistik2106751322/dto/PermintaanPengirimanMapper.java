package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apap.ti.silogistik2106751322.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751322.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {

    @Mapping(target = "tanggalPengiriman", ignore = true)
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    
    @Mapping(target = "tanggalPengiriman", ignore = true)
    @Mapping(target = "waktuPermintaan", ignore = true)
    @Mapping(target = "listPermintaanPengirimanBarang", ignore = true)
    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);
}
