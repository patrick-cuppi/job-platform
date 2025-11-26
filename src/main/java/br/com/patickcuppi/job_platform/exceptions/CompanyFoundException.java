package br.com.patickcuppi.job_platform.exceptions;

public class CompanyFoundException extends RuntimeException {
  public CompanyFoundException() {
    super("Company already exists.");
  }
}
