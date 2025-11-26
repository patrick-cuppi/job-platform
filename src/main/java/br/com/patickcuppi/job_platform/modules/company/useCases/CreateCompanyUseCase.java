package br.com.patickcuppi.job_platform.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patickcuppi.job_platform.exceptions.CompanyFoundException;
import br.com.patickcuppi.job_platform.modules.company.entities.CompanyEntity;
import br.com.patickcuppi.job_platform.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((company) -> {
          throw new CompanyFoundException();
        });

    return this.companyRepository.save(companyEntity);
  }
}
