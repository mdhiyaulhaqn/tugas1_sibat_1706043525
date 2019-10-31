package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatSupplierModel;

import java.util.List;

public interface ObatSupplierService {
    void addObatSupplier(ObatSupplierModel obatSupplierModel);
    List<ObatSupplierModel> getAllObatSupplierByIdSupplier(Long idSupplier);
}
