package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatSupplierModel;
import apap.tugas.sibat.repository.ObatDb;
import apap.tugas.sibat.repository.ObatSupplierDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObatSupplierServiceImpl implements ObatSupplierService {
    @Autowired
    ObatSupplierDb obatSupplierDb;

    @Override
    public void addObatSupplier(ObatSupplierModel obatSupplierModel) {
        obatSupplierDb.save(obatSupplierModel);
    }

    @Override
    public List<ObatSupplierModel> getAllObatSupplierByIdSupplier(Long idSupplier) {
        return obatSupplierDb.getAllBySupplierId(idSupplier);
    }
}
