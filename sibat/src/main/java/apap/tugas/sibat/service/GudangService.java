package apap.tugas.sibat.service;

import apap.tugas.sibat.model.GudangModel;

import java.util.List;
import java.util.Optional;

public interface GudangService {
    List<GudangModel> getGudangList();

    Optional<GudangModel> getGudangById(Long id);

    void addGudang(GudangModel gudang);
}
