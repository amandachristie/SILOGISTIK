package apap.ti.silogistik2106751322.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106751322.dto.BarangMapper;
import apap.ti.silogistik2106751322.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106751322.model.Barang;
import apap.ti.silogistik2106751322.model.GudangBarang;
import apap.ti.silogistik2106751322.service.BarangService;
import apap.ti.silogistik2106751322.service.GudangBarangService;

@Controller
public class BarangController {

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping("/barang")
    public String listBarang(Model model) {
        
        List<ReadBarangResponseDTO> listBarang = new ArrayList<>();
        for (Barang barangExisting: barangService.getAllBarang()) {
            ReadBarangResponseDTO barang = barangMapper.barangToReadBarangResponseDTO(barangExisting);
            Integer totalStok = gudangBarangService.getTotalStokByIdBarang(barang.getSku());
            if (totalStok !=  null){ barang.setStok(totalStok); }
            listBarang.add(barang);
        }

        model.addAttribute("listBarang", listBarang);
        
        return "view-daftar-barang";
    }   
    
    @GetMapping("/barang/tambah")
    public String formAddBarang(Model model) {
        
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        
        return "form-create-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, Model model) {
        
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barangService.saveBarang(barang);

        model.addAttribute("sku", barang.getSku());
        return "success-create-barang";
    }

    @GetMapping("/barang/{idBarang}")
    public String detailGudang(@PathVariable(value="idBarang") String idBarang, Model model) {

        var barangSelected = barangService.getBarangBySku(idBarang);
        
        ReadBarangResponseDTO barang = barangMapper.barangToReadBarangResponseDTO(barangSelected);
        Integer totalStok = gudangBarangService.getTotalStokByIdBarang(barang.getSku());
        if (totalStok !=  null){ barang.setStok(totalStok); }

        List<GudangBarang> daftarGudangBarang = gudangBarangService.getDaftarGudangBarangByIdBarang(idBarang);


        model.addAttribute("barang", barang);
        model.addAttribute("daftarGudangBarang", daftarGudangBarang);

        return "view-detail-barang";
    }

    @GetMapping("/barang/{idBarang}/ubah")
    public String formUpdateBarang(@PathVariable("idBarang") String idBarang, Model model){

        //Mengambil barang dengan id tersebut
        var barang = barangService.getBarangBySku(idBarang);

        //Memindahkan data barang ke DTO untuk selanjutnya diubah di form pengguna
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("/barang/{idBarang}/ubah")
    public String updateBuku(@ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {

        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO) ;
        
        var barang = barangService.updateBarang(barangFromDto);
        
        //Add variabel kode buku ke 'kode' untuk dirender di thymeleaf
        model.addAttribute("sku", barang.getSku());
        
        return "success-update-barang" ;
    }
}
