package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.service.GudangObatService;
import apap.tugas.sibat.service.GudangService;
import apap.tugas.sibat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;

    @Autowired
    ObatService obatService;

    @Autowired
    GudangObatService gudangObatService;

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

        List<ObatModel> listObat = obatService.getObatList();
        model.addAttribute("listObat", listObat);

        GudangObatModel gudangObatNew = new GudangObatModel();
        model.addAttribute("gudangObatNew", gudangObatNew);

        model.addAttribute("gudang", gudang);
        return "view-gudang";
    }

    @RequestMapping(value="/gudang/tambah-obat", method = RequestMethod.POST)
    public String addObatInGudang(@ModelAttribute GudangObatModel gudangObatModel, Model model, RedirectAttributes redirectAttributes){
        gudangObatService.addGudangObat(gudangObatModel);

        GudangModel gudang = gudangObatModel.getGudang();
        ObatModel obat = gudangObatModel.getObat();

        redirectAttributes.addFlashAttribute("obat", obat);

        return "redirect:/gudang/view?idGudang=" + gudang.getId();
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

    @RequestMapping(value = "/gudang/expired-obat/cari", method = RequestMethod.GET)
    public String findExpiredObatByGudangForm(Model model){
        List<GudangModel> listGudangModel = gudangService.getGudangList();

        GudangModel gudangTarget = new GudangModel();

        model.addAttribute("gudangTarget", gudangTarget);
        model.addAttribute("listGudangModel", listGudangModel);
        return "find-obat-expired";
    }

    @RequestMapping(value = "/gudang/expired-obat/cari", method = RequestMethod.POST)
    public String findExpiredObatByGudangSubmit(@ModelAttribute GudangModel gudangTarget,Model model){
        // Redirect ke ke findExpiredObatHasil()
        return "redirect:/gudang/expired-obat?idGudang=" + gudangTarget.getId();
    }

    @RequestMapping(value = "/gudang/expired-obat", method = RequestMethod.GET)
    public String findExpiredObatHasil(@RequestParam(value = "idGudang") Long idGudang,Model model){
        GudangModel gudang = gudangService.getGudangById(idGudang).get();
        List<ObatModel> listObatExpired = new ArrayList<>();

        for(GudangObatModel gudangObat: gudang.getListGudangObat()){
            ObatModel obat = gudangObat.getObat();
            if(obatService.getUmurObat(obat) > 5){
                listObatExpired.add(obat);
            }
        }

        model.addAttribute("gudang", gudang);
        model.addAttribute("listObatExpired", listObatExpired);

        return "list-obat-expired";
    }

}
