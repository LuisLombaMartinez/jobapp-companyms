package com.travis.jobapp.companyms.company.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.travis.jobapp.companyms.company.Company;
import com.travis.jobapp.companyms.company.CompanyRepository;
import com.travis.jobapp.companyms.company.CompanyService;
import com.travis.jobapp.companyms.exception.ResourceNotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public UUID create(Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("A new company cannot already have an ID");
        }
        return companyRepository.save(company).getId();
    }

    @Override
    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    @Override
    public void deleteById(UUID id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Company not found with id " + id);
        }
    }

    @Override
    public Company update(UUID id, Company updatedCompany) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.updateFrom(updatedCompany);
                    return companyRepository.save(company);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

}
