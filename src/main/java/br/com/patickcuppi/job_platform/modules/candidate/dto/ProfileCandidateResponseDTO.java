package br.com.patickcuppi.job_platform.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

  private UUID id;
  @Schema(description = "The full name of the candidate.")
  private String name;
  @Schema(description = "The username chosen by the candidate for login purposes.")
  private String username;
  @Schema(description = "The email address of the candidate.")
  private String email;
  @Schema(description = "A brief description or summary about the candidate.")
  private String description;
}
