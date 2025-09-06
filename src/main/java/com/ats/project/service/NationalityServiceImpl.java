package com.ats.project.service;

import com.ats.project.model.Students;
import com.ats.project.repository.NationalityRepository;
import com.ats.project.model.Nationality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalityServiceImpl implements NationalityService {
    private final NationalityRepository nationalityRepository;

    @Autowired
    public NationalityServiceImpl(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    public List<Nationality> findAll() {
        return nationalityRepository.findAll();
    }


    @Override
    public Nationality saveNationality(Nationality nationality) {
        return nationalityRepository.findByName(nationality.getName())
                .orElseGet(() -> nationalityRepository.save(nationality));
    }

    public Nationality findById(Long id) {
        return nationalityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nationalty not found with id: " + id));
    }


}


