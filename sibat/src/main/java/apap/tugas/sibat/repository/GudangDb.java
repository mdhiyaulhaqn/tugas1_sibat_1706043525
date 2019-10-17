package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.GudangModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GudangDb extends JpaRepository<GudangModel, Long> {
}
