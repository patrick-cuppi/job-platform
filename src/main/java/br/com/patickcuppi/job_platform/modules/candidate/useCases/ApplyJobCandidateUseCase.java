package br.com.patickcuppi.job_platform.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.patickcuppi.job_platform.exceptions.JobNotFoundException;
import br.com.patickcuppi.job_platform.exceptions.UserNotFoundException;
import br.com.patickcuppi.job_platform.modules.candidate.CandidateRepository;
import br.com.patickcuppi.job_platform.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CandidateRepository candidateRepository;

  public void execute(UUID jobId, UUID candidateId) {
    this.candidateRepository.findById(candidateId)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    this.jobRepository.findById(jobId)
        .orElseThrow(() -> {
          throw new JobNotFoundException();
        });
  }
}
