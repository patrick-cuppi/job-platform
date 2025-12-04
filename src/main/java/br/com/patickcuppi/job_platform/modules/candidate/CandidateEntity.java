package br.com.patickcuppi.job_platform.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(description = "Full name of the candidate", example = "John Doe")
  private String name;

  @NotBlank(message = "Username cannot be blank")
  @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
  @Schema(description = "Username of the candidate", example = "john_doe123", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;

  @Email(message = "Invalid email format")
  @Schema(description = "Email address of the candidate", example = "john.doe@example.com")
  private String email;

  @Length(min = 8, max = 100, message = "Password must be at least 8 characters long")
  @Schema(description = "Password of the candidate", example = "P@ssw0rd123", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
  private String password;

  @Schema(description = "Description of the candidate", example = "Experienced software developer with a focus on backend technologies.")
  private String description;

  @Schema(description = "Curriculum vitae or resume of the candidate", example = "Resume_document.pdf")
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
