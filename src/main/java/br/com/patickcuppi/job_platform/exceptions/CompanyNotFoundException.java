package br.com.patickcuppi.job_platform.exceptions;

public class CompanyNotFoundException extends RuntimeException {
  public CompanyNotFoundException() {
    super("Company not found.");
  }
}
