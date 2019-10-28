package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.*;
import apap.tugas.sibat.service.JenisService;
import apap.tugas.sibat.service.ObatService;
import apap.tugas.sibat.service.ObatSupplierService;
import apap.tugas.sibat.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    ObatSupplierService obatSupplierService;

    @RequestMapping(value = "/")
    private String findAllObat(Model model){
        List<ObatModel> listObat = obatService.getObatList();
        model.addAttribute("obatList", listObat);
        return "beranda";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.GET)
    public String addObatForm(Model model){
        ObatModel newObat = new ObatModel();
//        model.addAttribute("listJenisSupplier", listJenisSupplier);

//        List<List<SupplierModel>> listSupplier = new ArrayList<>();
//        listSupplier.add(listJenisSupplier);

        model.addAttribute("obat", newObat);

        List<ObatSupplierModel> listObatSupplier = new ArrayList<>();

        ObatSupplierModel obatSupplierModel = new ObatSupplierModel();

        listObatSupplier.add(obatSupplierModel);
        newObat.setListObatSupplier(listObatSupplier);

        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listJenis", listJenis);

        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        model.addAttribute("listSupplier", listSupplier);

        System.out.println("MASUK ADDOBATFORM AKHIR");
        return "form-add-obat";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.POST, params={"addRow"})
    public String addRowSupplier(@ModelAttribute ObatModel obat, Model model){
        System.out.println("MASUK ADD ROW AWAL");
        obat.getListObatSupplier().add(new ObatSupplierModel());

        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listJenis", listJenis);

        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        model.addAttribute("listSupplier", listSupplier);

        model.addAttribute("obat", obat);
        System.out.println("MASUK ADD ROW AKHIR");
        return "form-add-obat";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model){
        System.out.println("MASUK SUBMIT AWAL");
        obat = obatService.generateKodeObat(obat);

        System.out.println("MASUKKKKKKKK");
        for (ObatSupplierModel obatSupplier: obat.getListObatSupplier()) {
            obatSupplier.setObat(obat);
        }

        obatService.addObat(obat);

        for (ObatSupplierModel obatSupplier: obat.getListObatSupplier()){
            obatSupplierService.addObatSupplier(obatSupplier);
        }
        model.addAttribute("obat", obat);

        System.out.println("MASUK SUBMIT AKHIR");
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
