package br.com.patickcuppi.job_platform.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

  private String password;
  private String username;
}
