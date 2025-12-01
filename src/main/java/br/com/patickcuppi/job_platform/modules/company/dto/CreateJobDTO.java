package br.com.patickcuppi.job_platform.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

  private String description;
  private String benefits;
  private String level;
}
