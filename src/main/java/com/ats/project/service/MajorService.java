package com.ats.project.service;

import com.ats.project.model.Faculty;
import com.ats.project.model.Major;

import java.util.List;

public interface MajorService {
    Major saveMajor(Major major);
    List<Major> findAll();
    Major findById(Long id);
    Major deleteMajor(Long id) throws Exception;
}
