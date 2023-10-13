package apap.ti.silogistik2106751322.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751322.dto.PermintaanPengirimanBarangMapper;
import apap.ti.silogistik2106751322.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751322.dto.request.CreateListPermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadPermintaanPengirimanBarangResponseDTO;
import apap.ti.silogistik2106751322.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106751322.model.PermintaanPengiriman;
import apap.ti.silogistik2106751322.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751322.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106751322.service.BarangService;
import apap.ti.silogistik2106751322.service.KaryawanService;
import apap.ti.silogistik2106751322.service.PermintaanPengirimanBarangService;
import apap.ti.silogistik2106751322.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    PermintaanPengirimanBarangMapper permintaanPengirimanBarangMapper;
    
    @Autowired
    PermintaanPengirimanBarangService permintaanPengirimanBarangService;

    @GetMapping("/permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        
        List<ReadPermintaanPengirimanResponseDTO> listPermintaanPengiriman = new ArrayList<>();

        SimpleDateFormat tanggalPengirimanFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateTimeFormatter waktuPermintaanFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");

        for (PermintaanPengiriman permintaanPengiriman : permintaanPengirimanService.getAllPermintaanPengiriman()) {
            var permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
            
            //Membuat format tanggalPengiriman untuk ditampilkan 
            String outputDateStr = tanggalPengirimanFormat.format(permintaanPengiriman.getTanggalPengiriman());
            permintaanPengirimanDTO.setTanggalPengiriman(outputDateStr);
            
            //Membuat format waktuPermintaan untuk ditampilkan 
            String outputTimeStr = permintaanPengiriman.getWaktuPermintaan().format(waktuPermintaanFormat);
            permintaanPengirimanDTO.setWaktuPermintaan(outputTimeStr);

            listPermintaanPengiriman.add(permintaanPengirimanDTO);

        }
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        
        return "view-daftar-permintaan-pengiriman";
    }
    
    @GetMapping("/permintaan-pengiriman/tambah")
    public String formCreatPermintaanPengiriman(Model model) {

        CreateListPermintaanPengirimanBarangRequestDTO listPermintaanPengirimanBarangDTO = new CreateListPermintaanPengirimanBarangRequestDTO();
        
        model.addAttribute("listPermintaanPengirimanBarangDTO", listPermintaanPengirimanBarangDTO);
        model.addAttribute("listKaryawanExisting", karyawanService.getAllKaryawan());
        model.addAttribute("listBarangExisting", barangService.getAllBarang());

        return "form-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(@ModelAttribute CreateListPermintaanPengirimanBarangRequestDTO listPermintaanPengirimanBarangDTO, Model model) {
        
        var permintaanPengirimanDTO = listPermintaanPengirimanBarangDTO.getPermintaanPengirimanDTO();        
        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date tanggalPengiriman = dateFormat.parse(permintaanPengirimanDTO.getTanggalPengiriman());
            permintaanPengiriman.setTanggalPengiriman(tanggalPengiriman);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Mengambil jumlah pesanan barang
        int jumlahPesananBarang = 0;
        for (CreatePermintaanPengirimanBarangRequestDTO permintaanPengirimanBarangDTO : listPermintaanPengirimanBarangDTO.getListPermintaanPengirimanBarang()) {
            jumlahPesananBarang += permintaanPengirimanBarangDTO.getKuantitasPesanan();
        }

        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman, jumlahPesananBarang);

        // Membuat objek permintaan pengiriman barang
        for (CreatePermintaanPengirimanBarangRequestDTO permintaanPengirimanBarangDTO : listPermintaanPengirimanBarangDTO.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarangDTO.setPermintaanPengiriman(permintaanPengiriman);
            var permintaanPengirimanBarang = permintaanPengirimanBarangMapper.createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(permintaanPengirimanBarangDTO);
            permintaanPengirimanBarangService.savePermintaanPengirimanBarang(permintaanPengirimanBarang);
        }

        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());

        return "success-create-permintaan-pengiriman";
    }
    
    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPermintaanPengiriman(@ModelAttribute CreateListPermintaanPengirimanBarangRequestDTO listPermintaanPengirimanBarangDTO, Model model) {

        if (listPermintaanPengirimanBarangDTO.getListPermintaanPengirimanBarang() == null || listPermintaanPengirimanBarangDTO.getListPermintaanPengirimanBarang().size() == 0){
            listPermintaanPengirimanBarangDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        listPermintaanPengirimanBarangDTO.getListPermintaanPengirimanBarang().add(new CreatePermintaanPengirimanBarangRequestDTO());

        model.addAttribute("listPermintaanPengirimanBarangDTO", listPermintaanPengirimanBarangDTO);
        model.addAttribute("listKaryawanExisting", karyawanService.getAllKaryawan());
        model.addAttribute("listBarangExisting", barangService.getAllBarang());

        return "form-permintaan-pengiriman";
    }
    
    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable(value="idPermintaanPengiriman") Long idPermintaanPengiriman, Model model) {

        var permintaanPengirimanSelected = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        
        ReadPermintaanPengirimanResponseDTO permintaanPengiriman = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengirimanSelected);

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanSelected.getListPermintaanPengirimanBarang() ) {
            BigInteger kuantitas = BigInteger.valueOf(permintaanPengirimanBarang.getKuantitasPesanan().intValue());
            BigInteger hargaBarang = permintaanPengirimanBarang.getBarang().getHargaBarang();
            BigInteger totalHarga = kuantitas.multiply(hargaBarang);

            ReadPermintaanPengirimanBarangResponseDTO permintaanPengirimanBarangDTO = permintaanPengirimanBarangMapper.permintaanPengirimanBarangToReadPermintaanPengirimanBarangResponseDTO(permintaanPengirimanBarang);
            permintaanPengirimanBarangDTO.setTotalHarga(totalHarga);

            permintaanPengiriman.getListPermintaanPengirimanBarang().add(permintaanPengirimanBarangDTO);
        }

        SimpleDateFormat tanggalPengirimanFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateTimeFormatter waktuPermintaanFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
   
        //Membuat format tanggalPengiriman untuk ditampilkan 
        String outputDateStr = tanggalPengirimanFormat.format(permintaanPengirimanSelected.getTanggalPengiriman());
        permintaanPengiriman.setTanggalPengiriman(outputDateStr);
        
        //Membuat format waktuPermintaan untuk ditampilkan 
        String outputTimeStr = permintaanPengirimanSelected.getWaktuPermintaan().format(waktuPermintaanFormat);
        permintaanPengiriman.setWaktuPermintaan(outputTimeStr);
    
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        return "view-detail-permintaan-pengiriman";
    }
    
    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaan(@PathVariable(value="idPermintaanPengiriman") Long idPermintaanPengiriman, Model model) {

        permintaanPengirimanService.cancelPermintaanPengiriman(idPermintaanPengiriman);

        model.addAttribute("nomorPengiriman", 
                        permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman).getNomorPengiriman());

        return "success-cancel-permintaan-pengiriman";
    }
}
