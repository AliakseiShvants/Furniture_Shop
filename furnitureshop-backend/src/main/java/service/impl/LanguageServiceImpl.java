package service.impl;

import entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.LanguageRepo;
import service.LanguageService;

/**
 * A implementation of {@link LanguageService} interface
 */
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepo languageRepo;

    @Override
    public Language findByName(String name) {
        return languageRepo.findByName(name);
    }
}
