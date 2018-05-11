package service.product;

import entity.product.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repo.product.ManufacturerRepo;

import java.util.List;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    public Manufacturer addManufacturer(Manufacturer manufacturer){
        return manufacturerRepo.save(manufacturer);
    }

    public List<Manufacturer> getAll() {
        return manufacturerRepo.findAll();
    }
}
