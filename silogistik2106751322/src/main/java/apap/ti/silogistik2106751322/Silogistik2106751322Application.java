package apap.ti.silogistik2106751322;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106751322.dto.BarangMapper;
import apap.ti.silogistik2106751322.dto.GudangMapper;
import apap.ti.silogistik2106751322.dto.KaryawanMapper;
import apap.ti.silogistik2106751322.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751322.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751322.service.BarangService;
import apap.ti.silogistik2106751322.service.GudangService;
import apap.ti.silogistik2106751322.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106751322Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751322Application.class, args);
	}

	@Bean
    @Transactional
    CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, 
                        BarangService barangService, BarangMapper barangMapper, 
                        KaryawanService karyawanService, KaryawanMapper karyawanMapper) 
    {
        return args -> {
            var faker = new Faker (new Locale("in-ID"));

            // Membuat faker gudang
            var gudangDTO = new CreateGudangRequestDTO();
            gudangDTO.setNama("Gudang  " + faker.company().name());
            gudangDTO.setAlamatGudang(faker.address().fullAddress());

            var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
            gudangService.saveGudang(gudang);

            // // Membuat faker barang
            // var barangDTO = new CreateBarangRequestDTO();
            // barangDTO.setTipeBarang(faker.number().numberBetween(1, 5));
            // barangDTO.setMerk(faker.lorem().word());
            // barangDTO.setHargaBarang(new BigInteger(faker.number().digits(5)));

            // var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
            // barangService.saveBarang(barang);

            // Membuat faker karyawan
            var karyawanDTO = new CreateKaryawanRequestDTO();
            karyawanDTO.setNama(faker.name().fullName());
            karyawanDTO.setJenisKelamin(faker.number().numberBetween(1, 2));
            karyawanDTO.setTanggalLahir(faker.date().birthday());

            var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
            karyawanService.saveKaryawan(karyawan);

        };
	}
}
