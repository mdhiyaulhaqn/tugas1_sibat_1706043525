package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JenisDb extends JpaRepository<JenisModel, Long> {
}
