package apap.tugas.sibat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "obat")
public class ObatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false, unique = true)
    private String kode;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Double harga;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_registrasi", nullable = false, unique = true)
    private String nomor_registrasi;

    @NotNull
    @Size(max = 255)
    @Column(name = "bentuk", nullable = false)
    private String bentuk;

    @NotNull
    @Column(name = "tanggal_terbit", nullable = false)
    private Date tanggal_terbit;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_jenis", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisModel jenis;

    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangObatModel> listGudangObat;

    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ObatSupplierModel> listObatSupplier;
}
