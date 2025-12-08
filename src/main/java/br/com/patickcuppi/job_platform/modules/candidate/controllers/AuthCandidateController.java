package br.com.patickcuppi.job_platform.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.patickcuppi.job_platform.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.patickcuppi.job_platform.modules.candidate.useCases.AuthCandidateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Endpoints for Candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  @Operation(summary = "Authenticate Candidate", description = "Authenticate a candidate and generate a JWT token.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token returned."),
      @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials.")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {

    try {

      var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);

      return ResponseEntity.ok().body(token);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

  }
}
