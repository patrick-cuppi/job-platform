package br.com.patickcuppi.job_platform.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;
  private String name;

  @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
  private String username;

  @Email(message = "Invalid email format")
  private String email;

  @Length(min = 8, max = 100, message = "Password must be at least 8 characters long")
  private String password;
  private String description;
  private String curriculum;

}
