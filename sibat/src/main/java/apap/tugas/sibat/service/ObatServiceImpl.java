package apap.tugas.sibat.service;

import apap.tugas.sibat.model.ObatModel;
import apap.tugas.sibat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ObatServiceImpl implements ObatService{
    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getObatList() {
        return obatDb.findAll();
    }

    @Override
    public void delete(ObatModel obatModel) {
        obatDb.delete(obatModel);
    }

    @Override
    public void addObat(ObatModel obat) {
        obatDb.save(obat);
    }

    @Override
    public Optional<ObatModel> getObatByNomorRegistrasi(String nomorRegistrasi) {
        return obatDb.findByNomorRegistrasi(nomorRegistrasi);
    }

    @Override
    public Optional<ObatModel> getObatById(String id) {
        return obatDb.findById(Long.parseLong(id));
    }

    @Override
    public ObatModel changeObat(ObatModel obat) {
        ObatModel targetObat = obatDb.findById(obat.getId()).get();

        try{
            targetObat.setNama(obat.getNama());
            targetObat.setTanggalTerbit(obat.getTanggalTerbit());
            targetObat.setHarga(obat.getHarga());
            targetObat.setBentuk(obat.getBentuk());
            targetObat.setKode(obat.getKode());
            obatDb.save(targetObat);
            return targetObat;
        } catch (NullPointerException npe){
            return null;
        }
    }

    @Override
    public ObatModel generateKodeObat(ObatModel obat) {
        String kode = "";

        if(obat.getJenis().getNama().equals("Generik"))
            kode += "1";
        else kode += "2";

        switch (obat.getBentuk()){
            case "Cairan":
                kode += "01";
                break;
            case "Kapsul":
                kode += "02";
                break;
            case "Tablet":
                kode += "03";
                break;
        }

        kode += Calendar.getInstance().get(Calendar.YEAR);
        kode += obat.getTanggalTerbit().getYear() + 1900 + 5;

        // Generate unique char
        Random r = new Random();
        char x = (char)(r.nextInt(26) + 'A');
        char y = (char)(r.nextInt(26) + 'A');
        String kodeTemp = kode + x + y;

        while(obatDb.existsByKode(kodeTemp)) {
            x = (char)(r.nextInt(26) + 'A');
            y = (char)(r.nextInt(26) + 'A');
            kodeTemp = kode + x + y;
        }
        kode = kodeTemp;

        obat.setKode(kode);
        return obat;
    }
}
