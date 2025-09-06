package com.ats.project.service;

import com.ats.project.model.Nationality;


import java.util.List;

public interface NationalityService {
    Nationality saveNationality(Nationality nationality);
    List<Nationality> findAll();
    Nationality findById(Long id);
}
