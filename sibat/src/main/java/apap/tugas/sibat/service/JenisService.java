package apap.tugas.sibat.service;

import apap.tugas.sibat.model.JenisModel;

import java.util.List;
import java.util.Optional;

public interface JenisService {
    List<JenisModel> getJenisList();
    Optional<JenisModel> getJenisById(Long id);
}
