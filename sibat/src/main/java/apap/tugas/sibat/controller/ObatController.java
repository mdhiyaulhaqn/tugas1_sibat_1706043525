package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.*;
import apap.tugas.sibat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ObatController {
    @Autowired
    ObatService obatService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    JenisService jenisService;

    @Autowired
    GudangService gudangService;

    @Autowired
    ObatSupplierService obatSupplierService;

    @Autowired
    GudangObatService gudangObatService;

    @RequestMapping(value = "/")
    private String findAllObat(Model model){
        List<ObatModel> listObat = obatService.getObatList();
        model.addAttribute("obatList", listObat);
        return "beranda";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.GET)
    public String addObatForm(Model model){
        ObatModel newObat = new ObatModel();
        model.addAttribute("obat", newObat);

        List<ObatSupplierModel> listObatSupplier = new ArrayList<>();
        ObatSupplierModel obatSupplierModel = new ObatSupplierModel();

        listObatSupplier.add(obatSupplierModel);
        newObat.setListObatSupplier(listObatSupplier);

        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listJenis", listJenis);

        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        model.addAttribute("listSupplier", listSupplier);

        return "form-add-obat";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.POST, params={"addRow"})
    public String addRowSupplier(@ModelAttribute ObatModel obat, Model model){
        obat.getListObatSupplier().add(new ObatSupplierModel());

        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listJenis", listJenis);

        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        model.addAttribute("listSupplier", listSupplier);

        model.addAttribute("obat", obat);
        return "form-add-obat";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model){
        obat = obatService.generateKodeObat(obat);

        for (ObatSupplierModel obatSupplier: obat.getListObatSupplier()) {
            obatSupplier.setObat(obat);
        }

        obatService.addObat(obat);

        for (ObatSupplierModel obatSupplier: obat.getListObatSupplier()){
            obatSupplierService.addObatSupplier(obatSupplier);
        }
        model.addAttribute("obat", obat);

        return "add-obat-submitted";
    }

    @RequestMapping(value="/obat/view", method = RequestMethod.GET)
    public String viewObat(@RequestParam(value="noReg") String noReg, Model model){
        ObatModel obat = obatService.getObatByNomorRegistrasi(noReg).get();

        model.addAttribute("obat", obat);
        return "view-obat";
    }

    @RequestMapping(value="/obat/ubah", method = RequestMethod.GET)
    public String changeObatForm(@RequestParam(value="id") String idObat, Model model){
        ObatModel obat = obatService.getObatById(idObat).get();
        model.addAttribute("obat", obat);
        return "form-change-obat";
    }

    @RequestMapping(value="/obat/ubah", method = RequestMethod.POST)
    public String changeObatSubmit(@ModelAttribute ObatModel obat, Model model){
        obat = obatService.generateKodeObat(obat);
        ObatModel newObat = obatService.changeObat(obat);
        model.addAttribute("obat", newObat);
        return "change-obat-submitted";
    }

    @RequestMapping(value="/obat/filter", method = RequestMethod.GET)
    public String filterObatForm(
            @RequestParam(value = "idGudang", required = false) Long idGudang,
            @RequestParam(value = "idSupplier", required = false) Long idSupplier,
            @RequestParam(value = "idJenis", required = false) Long idJenis,
            Model model
    ){
        List<GudangModel> listGudang = gudangService.getGudangList();
        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("listSupplier", listSupplier);
        model.addAttribute("listJenis", listJenis);


        String namaGudang = "";
        List<ObatModel> listObatTemp = new ArrayList<>();
        List<ObatModel> listObat = obatService.getObatList();

        if(idGudang != null && idGudang != -1L){
            for(GudangObatModel gudangObat: gudangObatService.getGudangObatByIdGudang(idGudang)){
                listObatTemp.add(gudangObat.getObat());
            }
            listObat.retainAll(listObatTemp);

            namaGudang = gudangService.getGudangById(idGudang).get().getNama();
        }

        listObatTemp = new ArrayList<>(); // Kosongin buat nanti dibawah

        if(idJenis != null && idJenis != -1L){
            JenisModel jenis = jenisService.getJenisById(idJenis).get();
            listObat.retainAll(jenis.getListObat());
        }

        if(idSupplier != null && idSupplier != -1L){
            for(ObatSupplierModel obatSupplier: obatSupplierService.getAllObatSupplierByIdSupplier(idSupplier)){
                listObatTemp.add(obatSupplier.getObat());
            }
            listObat.retainAll(listObatTemp);
        }

        model.addAttribute("idGudang", idGudang);
        model.addAttribute("idJenis", idJenis);
        model.addAttribute("idSupplier", idSupplier);
        model.addAttribute("namaGudang", namaGudang);
        model.addAttribute("listObat", listObat);

        return "filter-obat";
    }

    @RequestMapping(value = "/bonus")
    private String obatWithjumlahSupplier(Model model){
        List<ObatModel> listObat = obatService.getObatList();
        model.addAttribute("obatList", listObat);

        return "bonus";
    }


    //    Remove Obat dari list beranda
//    @RequestMapping(value = "/", params=("hapusObat"))
//    private String removeObat(Model model, HttpServletRequest req) {
//        Integer rowId =  Integer.valueOf(req.getParameter("hapusObat"));
//        model.addAttribute("test", rowId);
//        obatService.getObatList().remove(rowId.intValue());
//
//        List<ObatModel> listObat = obatService.getObatList();
//        model.addAttribute("obatList", listObat);
//        return "beranda";
//    }
}

