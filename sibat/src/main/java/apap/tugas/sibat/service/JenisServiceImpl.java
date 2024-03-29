package apap.tugas.sibat.service;

import apap.tugas.sibat.model.JenisModel;
import apap.tugas.sibat.repository.JenisDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JenisServiceImpl implements JenisService {
    @Autowired
    JenisDb jenisDb;
    @Override
    public List<JenisModel> getJenisList() {
        return jenisDb.findAll();
    }

    @Override
    public Optional<JenisModel> getJenisById(Long id) {
        return jenisDb.findById(id);
    }
}
