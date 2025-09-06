package com.ats.project.service;


import com.ats.project.model.Faculty;
import com.ats.project.repository.MajorRepository;
import com.ats.project.model.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    private final MajorRepository majorRepository;

    @Autowired
    public MajorServiceImpl(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public List<Major> findAll() {
        return majorRepository.findAll();
    }


    @Override
    public Major saveMajor(Major major) {
        return majorRepository.findByName(major.getName())
                .orElseGet(() -> majorRepository.save(major));
    }

    public Major findById(Long id) {
        return majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major not found with id: " + id));
    }


    public Major deleteMajor(Long id) {
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major not found with id: " + id));
        majorRepository.delete(major);
        return major;
    }


}