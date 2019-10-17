package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getObatList();
    void addObat(ObatModel obat);
    void delete(ObatModel obatModel);
}
