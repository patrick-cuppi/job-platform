package br.com.patickcuppi.job_platform.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.patickcuppi.job_platform.exceptions.JobNotFoundException;
import br.com.patickcuppi.job_platform.exceptions.UserNotFoundException;
import br.com.patickcuppi.job_platform.modules.candidate.CandidateEntity;
import br.com.patickcuppi.job_platform.modules.candidate.CandidateRepository;
import br.com.patickcuppi.job_platform.modules.candidate.entity.ApplyJobEntity;
import br.com.patickcuppi.job_platform.modules.candidate.repository.ApplyJobRepository;
import br.com.patickcuppi.job_platform.modules.company.entities.JobEntity;
import br.com.patickcuppi.job_platform.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
    UserNotFoundException ex = assertThrows(
        UserNotFoundException.class,
        () -> applyJobCandidateUseCase.execute(null, null));

    assertEquals("User not found", ex.getMessage());
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  public void shouldNotBeAbleToApplyJobWithJobNotFound() {

    var candidateIdMock = UUID.randomUUID();

    var candidate = new CandidateEntity();
    candidate.setId(candidateIdMock);

    when(candidateRepository.findById(candidateIdMock))
        .thenReturn(Optional.of(candidate));

    JobNotFoundException ex = assertThrows(
        JobNotFoundException.class,
        () -> applyJobCandidateUseCase.execute(null, candidateIdMock));
    assertEquals("Job not found", ex.getMessage());
  }

  @Test
  @DisplayName("Should be able to apply job")
  public void shouldBeAbleToApplyJob() {

    var candidateIdMock = UUID.randomUUID();
    var jobIdMock = UUID.randomUUID();

    var applyJob = ApplyJobEntity.builder()
        .candidateId(candidateIdMock)
        .jobId(jobIdMock)
        .build();

    applyJob.setId(UUID.randomUUID());

    var applyJobSaved = ApplyJobEntity.builder()
        .id(UUID.randomUUID())
        .build();

    when(candidateRepository.findById(candidateIdMock))
        .thenReturn(Optional.of(new CandidateEntity()));

    when(jobRepository.findById(jobIdMock))
        .thenReturn(Optional.of(new JobEntity()));

    when(applyJobRepository.save(argThat(entity -> entity.getCandidateId().equals(candidateIdMock) &&
        entity.getJobId().equals(jobIdMock)))).thenReturn(applyJobSaved);

    var result = applyJobCandidateUseCase.execute(jobIdMock, candidateIdMock);

    assertEquals(ApplyJobEntity.class, result.getClass());
    assertNotNull(result.getId());

  }
}
