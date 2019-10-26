package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;
import apap.tugas.sibat.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Override
    public List<GudangModel> getGudangList() {
        return gudangDb.findAll();
    }
}
