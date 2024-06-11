package com.travis.jobapp.companyms.company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<Company> getAll();

    UUID create(Company company);

    Company getById(UUID id);

    void deleteById(UUID id);

    Company update(UUID id, Company updatedCompany);

}