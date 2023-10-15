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
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106751322.dto.GudangBarangMapper;
import apap.ti.silogistik2106751322.dto.GudangMapper;
import apap.ti.silogistik2106751322.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.request.CreateListGudangBarangRequestDTO;
import apap.ti.silogistik2106751322.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106751322.model.Gudang;
import apap.ti.silogistik2106751322.model.GudangBarang;
import apap.ti.silogistik2106751322.service.BarangService;
import apap.ti.silogistik2106751322.service.GudangBarangService;
import apap.ti.silogistik2106751322.service.GudangService;
import apap.ti.silogistik2106751322.service.KaryawanService;
import apap.ti.silogistik2106751322.service.PermintaanPengirimanService;
import jakarta.servlet.http.HttpSession;

@Controller
public class GudangController {
    
    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangMapper gudangBarangMapper;

    @Autowired
    private GudangBarangService gudangBarangService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;


    @GetMapping("/")
    public String beranda(Model model){

        model.addAttribute("jumlahBarang", barangService.getAllBarang().size());
        model.addAttribute("jumlahGudang", gudangService.getAllGudang().size());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanService.getAllPermintaanPengiriman().size());
        model.addAttribute("jumlahKaryawan", karyawanService.getAllKaryawan().size());

        return "beranda";
    }

    @GetMapping("/gudang")
    public String listGudang(Model model) {
        
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        
        return "view-daftar-gudang";
    } 
    
    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable(value="idGudang") Long idGudang, Model model) {

        var gudangSelected = gudangService.getGudangById(idGudang);
        
        ReadGudangResponseDTO gudang = gudangMapper.gudangToReadGudangResponseDTO(gudangSelected);

        model.addAttribute("gudang", gudang);

        return "view-detail-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String cariBarang(@RequestParam(name = "sku", required = false) String sku, Model model, HttpSession session) {
        
        // Mendapatkan daftar gudang yang memiliki barang dengan SKU tertentu
        if (sku != null) {
            List<GudangBarang> listGudangBarang = barangService.getBarangBySku(sku).getListGudangBarang();
            model.addAttribute("listGudangBarang", listGudangBarang);
        }

        session.setAttribute("selected", sku);
        model.addAttribute("listBarang", barangService.getAllBarang());

        return "view-cari-barang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockGudang(@PathVariable(value = "idGudang") Long idGudang, Model model) {
        
        Gudang gudangSelected = gudangService.getGudangById(idGudang);
        ReadGudangResponseDTO gudang = gudangMapper.gudangToReadGudangResponseDTO(gudangSelected);

        var listGudangBarangDTO = new CreateListGudangBarangRequestDTO();
        
        model.addAttribute("gudang", gudang);
        model.addAttribute("listGudangBarangDTO", listGudangBarangDTO);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());

        return "form-restock-gudang";
    }

    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String restockGudang(@PathVariable(value = "idGudang") Long idGudang, @ModelAttribute CreateListGudangBarangRequestDTO listGudangBarangDTO, Model model) {
        for (CreateGudangBarangRequestDTO gudangBarangDTO : listGudangBarangDTO.getListGudangBarang()) {
            gudangBarangDTO.setGudang(gudangService.getGudangById(idGudang));
           
            // Cek apakah sudah ada entri GudangBarang untuk barang ini di gudang tertentu
            GudangBarang existingGudangBarang = gudangBarangService.getByGudangAndBarang(gudangBarangDTO.getGudang(), gudangBarangDTO.getBarang());
            
            if (existingGudangBarang != null) {
                // Jika sudah ada, tambahkan stok baru ke stok yang ada
                existingGudangBarang.setStok(gudangBarangDTO.getStok());
                gudangBarangService.saveGudangBarang(existingGudangBarang);
            } else {
                // Jika belum ada, buat entri baru GudangBarang
                GudangBarang newGudangBarang = gudangBarangMapper.createGudangBarangRequestDTOToGudangBarang(gudangBarangDTO);
                gudangBarangService.saveGudangBarang(newGudangBarang);
            }
        }

        model.addAttribute("nama", gudangService.getGudangById(idGudang).getNama());
        
        return "success-restock-gudang";
    }
    
    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowRestockGudang(@PathVariable(value = "idGudang") Long idGudang, Model model, @ModelAttribute CreateListGudangBarangRequestDTO listGudangBarangDTO) {
        
        Gudang gudangSelected = gudangService.getGudangById(idGudang);
        ReadGudangResponseDTO gudang = gudangMapper.gudangToReadGudangResponseDTO(gudangSelected);

        if (listGudangBarangDTO.getListGudangBarang() == null || listGudangBarangDTO.getListGudangBarang().size() == 0){
            listGudangBarangDTO.setListGudangBarang(new ArrayList<>());
        }

        listGudangBarangDTO.getListGudangBarang().add(new CreateGudangBarangRequestDTO());

        model.addAttribute("listGudangBarangDTO", listGudangBarangDTO);
        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("gudang", gudang);

        return "form-restock-gudang";
    }

}
