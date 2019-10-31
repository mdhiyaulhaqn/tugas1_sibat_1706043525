package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;

import java.util.List;

public interface GudangObatService {
    void addGudangObat(GudangObatModel gudangObat);

    List<GudangObatModel> getGudangObatByIdGudang(Long idGudang);
}
