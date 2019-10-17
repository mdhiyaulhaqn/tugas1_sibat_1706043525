package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ObatDb extends JpaRepository<ObatModel, Long>{
}
