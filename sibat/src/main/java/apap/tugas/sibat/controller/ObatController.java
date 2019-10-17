package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.model.SupplierModel;
import apap.tugas.sibat.service.ObatService;
import apap.tugas.sibat.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ObatController {
    @Autowired
    ObatService obatService;

    @Autowired
    SupplierService supplierService;

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

        return "form-add-obat";
    }

    @RequestMapping(value="/obat/", method = RequestMethod.POST)
    public String addObatSubmit(@ModelAttribute ObatModel obat, Model model){
        obatService.addObat(obat);

        model.addAttribute("obat", obat);

        return "add-obat-submit";
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
