package br.com.patickcuppi.job_platform.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.patickcuppi.job_platform.exceptions.UserNotFoundException;
import br.com.patickcuppi.job_platform.modules.candidate.CandidateRepository;
import br.com.patickcuppi.job_platform.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private CandidateRepository candidateRepository;

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
    UserNotFoundException ex = assertThrows(
        UserNotFoundException.class,
        () -> applyJobCandidateUseCase.execute(null, null));

    assertEquals("User not found", ex.getMessage());
  }
}
