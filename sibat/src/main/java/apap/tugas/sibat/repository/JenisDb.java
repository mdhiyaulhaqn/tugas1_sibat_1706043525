package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.JenisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JenisDb extends JpaRepository<JenisModel, Long> {
    @Override
    Optional<JenisModel> findById(Long aLong);
}
