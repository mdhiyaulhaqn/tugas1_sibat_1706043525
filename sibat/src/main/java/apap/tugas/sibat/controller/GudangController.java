package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;

    @RequestMapping(value = "/gudang")
    private String findAllGudang(Model model){
        List<GudangModel> listGudang = gudangService.getGudangList();
        model.addAttribute("gudangList", listGudang);
        return "view-all-gudang";
    }

    @RequestMapping(value = "/gudang/view", method = RequestMethod.GET)
    private String findGudangById(@RequestParam(value = "idGudang") Long idGudang ,Model model){
        GudangModel gudang = gudangService.getGudangById(idGudang).get();
        List<GudangObatModel> listGudangObat = gudang.getListGudangObat();
        model.addAttribute("listGudangObat", listGudangObat);
        model.addAttribute("gudang", gudang);
        return "view-gudang";
    }

    @RequestMapping(value="/gudang/tambah", method = RequestMethod.GET)
    public String addGudangForm(Model model){
        GudangModel newGudang = new GudangModel();

        model.addAttribute("gudang", newGudang);

        return "form-add-gudang";
    }

    @RequestMapping(value="/gudang/tambah", method = RequestMethod.POST)
    public String addGudangSubmit(@ModelAttribute GudangModel gudang, Model model){
        gudangService.addGudang(gudang);

        model.addAttribute("gudang", gudang);
        return "add-gudang-submitted";
    }

    @RequestMapping(value="/gudang/hapus/{idGudang}")
    public String deleteGudang(@PathVariable(value = "idGudang") Long idGudang, Model model){
        GudangModel gudang = gudangService.getGudangById(idGudang).get();
        List<GudangObatModel> listGudangObat = gudang.getListGudangObat();

        boolean isDeleted = false;

        if(listGudangObat.size() == 0){
            gudangService.deleteGudang(gudang);
            isDeleted = true;
        }

        model.addAttribute("gudang", gudang);
        model.addAttribute("isDeleted", isDeleted);
        model.addAttribute("idGudang", idGudang);

        return "delete-gudang";
    }

}
