package apap.tugas.sibat.controller;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.service.GudangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
