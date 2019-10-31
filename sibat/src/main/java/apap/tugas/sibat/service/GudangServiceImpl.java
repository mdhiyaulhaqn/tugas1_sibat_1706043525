package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Override
    public List<GudangModel> getGudangList() {
        return gudangDb.findAll();
    }

    @Override
    public Optional<GudangModel> getGudangById(Long id) {
        return gudangDb.findById(id);
    }

    @Override
    public void addGudang(GudangModel gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public void deleteGudang(GudangModel gudang) {
        gudangDb.delete(gudang);
    }
}
