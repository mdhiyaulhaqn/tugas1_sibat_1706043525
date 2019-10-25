package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.SupplierModel;
import apap.tugas.sibat.service.JenisService;
import apap.tugas.sibat.service.ObatService;
import apap.tugas.sibat.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ObatController {
    @Autowired
    ObatService obatService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    JenisService jenisService;

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

        List<SupplierModel> listSupplier = supplierService.getSupplierList();
        model.addAttribute("listSupplier", listSupplier);

        List<JenisModel> listJenis = jenisService.getJenisList();
        model.addAttribute("listJenis", listJenis);

        return "form-add-obat";
    }

    @RequestMapping(value="/obat/tambah", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model){
        obat = obatService.generateKodeObat(obat);
        obatService.addObat(obat);

        model.addAttribute("obat", obat);

        return "add-obat-submitted";
    }

    @RequestMapping(value="/obat/view", method = RequestMethod.GET)
    public String viewObat(@RequestParam(value="noReg") String noReg, Model model){
        ObatModel obat = obatService.getObatByNomorRegistrasi(noReg).get();

        model.addAttribute("obat", obat);
        return "view-obat";
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
