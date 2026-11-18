package com.tang.missu.service;

import com.tang.missu.model.Theme;
import com.tang.missu.respoitory.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<Theme> findByNames(List<String> names){
//        return themeRepository.findThemesByOrderByIdDesc();
        return themeRepository.findByNames(names);
    }

    public Optional<Theme> findByName(String name) {
        return  themeRepository.findByName(name);
    }

}
