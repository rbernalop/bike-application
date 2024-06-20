package org.rbernalop.bikeapplication.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.CucumberContextConfiguration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegrationTest {
  private static final String FILES_BASE_PATH = "src/test/resources/body_samples/";

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  protected CucumberContext context;

  @Autowired
  protected MockMvc mockMvc;

  protected String getContentByFileName(String fileName) throws IOException {
    return Files.readString(Paths.get(FILES_BASE_PATH + fileName));
  }

  protected <T> T  getContentByFileNameAsObject(String fileName, Class<T> bodyClass) throws IOException {
    String content = getContentByFileName(fileName);
    return objectMapper.readValue(content, bodyClass);
  }
}
