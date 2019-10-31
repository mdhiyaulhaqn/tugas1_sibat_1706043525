package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangObatModel;
import apap.tugas.sibat.repository.GudangObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangObatServiceImpl implements GudangObatService{
    @Autowired
    GudangObatDb gudangObatDb;

    @Override
    public void addGudangObat(GudangObatModel gudangObat) {
        gudangObatDb.save(gudangObat);
    }

    @Override
    public List<GudangObatModel> getGudangObatByIdGudang(Long idGudang) {
        return gudangObatDb.getAllByGudang_Id(idGudang);
    }
}
