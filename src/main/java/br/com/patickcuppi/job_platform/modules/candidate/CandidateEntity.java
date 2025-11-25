package br.com.patickcuppi.job_platform.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

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

  private String name;

  @NotBlank(message = "Username cannot be blank")
  @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers, and underscores")
  private String username;

  @Email(message = "Invalid email format")
  private String email;

  @Length(min = 8, max = 100, message = "Password must be at least 8 characters long")
  private String password;

  private String description;

  private String curriculum;

  private LocalDateTime createdAt;

}
