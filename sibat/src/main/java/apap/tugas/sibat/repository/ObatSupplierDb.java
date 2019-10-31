package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.ObatSupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObatSupplierDb extends JpaRepository<ObatSupplierModel, Long> {
    List<ObatSupplierModel> getAllBySupplierId(Long id);
}
