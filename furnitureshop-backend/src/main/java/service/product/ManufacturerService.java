package service.product;

import domain.product.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ManufacturerRepo;

@Service
@Transactional
public class ManufacturerService {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    public Manufacturer getByTitle(String data){
        String[] strings = data.split("[ ,]");
        return manufacturerRepo.findByTitleAndRequisite_Country(strings[0], strings[1]);
    }

    public Manufacturer addManufacturer(String data){
        String[] strings = data.split("[ ,]");
        Manufacturer newManufacturer = new Manufacturer(strings[0]);
        newManufacturer = manufacturerRepo.save(newManufacturer);
        return newManufacturer;
    }

    public boolean isManufacturerExists(String data){
        String[] strings = data.split("[ ,]");
        return manufacturerRepo.existsByTitleAndRequisite_Country(strings[0], strings[1]);
    }
}
