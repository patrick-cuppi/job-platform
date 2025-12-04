package br.com.patickcuppi.job_platform.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

  @Schema(example = "Senior Java Developer", requiredMode = Schema.RequiredMode.REQUIRED)
  private String description;
  @Schema(example = "Health insurance, 401k matching, remote work options", requiredMode = Schema.RequiredMode.REQUIRED)
  private String benefits;
  @Schema(example = "Senior", requiredMode = Schema.RequiredMode.REQUIRED)
  private String level;
}
