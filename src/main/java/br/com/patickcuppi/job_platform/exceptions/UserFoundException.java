package br.com.patickcuppi.job_platform.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException() {
    super("User already exists.");
  }
}
