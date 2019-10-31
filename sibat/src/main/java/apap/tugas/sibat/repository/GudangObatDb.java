package apap.tugas.sibat.repository;

import apap.tugas.sibat.model.GudangObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GudangObatDb extends JpaRepository<GudangObatModel, Long> {
    List<GudangObatModel> getAllByGudang_Id(Long idGudang);
}
