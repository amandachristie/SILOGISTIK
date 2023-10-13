package apap.ti.silogistik2106751322.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751322.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751322.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    
    Karyawan createKaryawanRequestDTOToKaryawan (CreateKaryawanRequestDTO createKaryawanRequestDTO);

}
