package br.com.patickcuppi.job_platform.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.patickcuppi.job_platform.modules.company.dto.CreateJobDTO;
import br.com.patickcuppi.job_platform.modules.company.repositories.CompanyRepository;
import br.com.patickcuppi.job_platform.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private CompanyRepository companyRepository;

  @Before
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @Test
  public void shouldNotBeAbleToCreateANewJobIfCompanyUnauthorized() throws Exception {

    var createJobDTO = CreateJobDTO.builder()
        .description("DESCRIPTION_TEST")
        .benefits("BENEFITS_TEST")
        .level("LEVEL_TEST")
        .build();

    mvc.perform(
        MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJSON(createJobDTO))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "TEST_SECRET")))
        .andExpect(MockMvcResultMatchers.status().isUnauthorized());
  }
}
