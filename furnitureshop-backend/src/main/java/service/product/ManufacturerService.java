package service.product;

import domain.UIResponse;
import domain.product.Manufacturer;
import dto.product.ManufacturerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ManufacturerRepo;

import java.util.List;

@Service
@Transactional
public class ManufacturerService {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    public Manufacturer getByTitle(String data){
        String[] strings = data.split("[ ,]");
        return manufacturerRepo.findByTitleAndRequisite_Country(strings[0], strings[1]);
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer){
        return manufacturerRepo.save(manufacturer);
    }

    public boolean isManufacturerExists(String data){
        String[] strings = data.split("[ ,]");
        return manufacturerRepo.existsByTitleAndRequisite_Country(strings[0], strings[1]);
    }

    public List<Manufacturer> getAll() {
        return manufacturerRepo.findAll();
    }
}
