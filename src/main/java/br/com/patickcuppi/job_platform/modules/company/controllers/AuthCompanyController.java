package br.com.patickcuppi.job_platform.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.patickcuppi.job_platform.modules.company.dto.AuthCompanyDTO;
import br.com.patickcuppi.job_platform.modules.company.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
@Tag(name = "Company", description = "Endpoints for company")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;

  @PostMapping("/auth")
  @Operation(summary = "Authenticate Company", description = "Authenticate a company and generate a JWT token.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token returned."),
      @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials.")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> signIn(@Valid @RequestBody AuthCompanyDTO authCompanyDTO) {

    try {
      var result = this.authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
