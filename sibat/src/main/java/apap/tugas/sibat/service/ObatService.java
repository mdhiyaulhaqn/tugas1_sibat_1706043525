package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;

import java.util.List;
import java.util.Optional;

public interface ObatService {
    List<ObatModel> getObatList();
    void addObat(ObatModel obat);
    void delete(ObatModel obatModel);
    Optional<ObatModel> getObatByNomorRegistrasi(String nomorRegistrasi);
    ObatModel generateKodeObat(ObatModel obat);
    Optional<ObatModel> getObatById(String id);
    ObatModel changeObat(ObatModel obat);
}
