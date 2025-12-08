package br.com.patickcuppi.job_platform.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patickcuppi.job_platform.exceptions.JobNotFoundException;
import br.com.patickcuppi.job_platform.exceptions.UserNotFoundException;
import br.com.patickcuppi.job_platform.modules.candidate.CandidateRepository;
import br.com.patickcuppi.job_platform.modules.candidate.entity.ApplyJobEntity;
import br.com.patickcuppi.job_platform.modules.candidate.repository.ApplyJobRepository;
import br.com.patickcuppi.job_platform.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  public ApplyJobEntity execute(UUID jobId, UUID candidateId) {
    this.candidateRepository.findById(candidateId)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    this.jobRepository.findById(jobId)
        .orElseThrow(() -> {
          throw new JobNotFoundException();
        });

    var applyJob = ApplyJobEntity.builder()
        .candidateId(candidateId)
        .jobId(jobId)
        .build();

    applyJob = applyJobRepository.save(applyJob);
    return applyJob;
  }
}
