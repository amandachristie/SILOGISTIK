package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751322.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadPermintaanPengirimanBarangResponseDTO;
import apap.ti.silogistik2106751322.model.PermintaanPengirimanBarang;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanBarangMapper {
    
    PermintaanPengirimanBarang createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(CreatePermintaanPengirimanBarangRequestDTO createPermintaanPengirimanBarangRequestDTO);

    ReadPermintaanPengirimanBarangResponseDTO permintaanPengirimanBarangToReadPermintaanPengirimanBarangResponseDTO(PermintaanPengirimanBarang permintaanPengirimanBarang);

}
