package service.impl;

import entity.user.UserTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.user.UserTranslateRepo;
import service.product.UserTranslateService;

@Service
public class UserTranslateServiceImpl implements UserTranslateService {

    @Autowired
    private UserTranslateRepo userTranslateRepo;

    @Override
    public UserTranslate findByUserId(Long id) {
        return userTranslateRepo.findByUserId(id);
    }

    @Override
    public UserTranslate findByFullName(String name) {
        return userTranslateRepo.findByFullName(name);
    }
}
