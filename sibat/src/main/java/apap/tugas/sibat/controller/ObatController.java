package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.ObatService;
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

    @RequestMapping(value = "/")
    private String findAllObat(Model model){
        List<ObatModel> listObat = obatService.getObatList();
        model.addAttribute("obatList", listObat);
        return "beranda";
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
