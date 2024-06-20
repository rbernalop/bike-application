package org.rbernalop.bikeapplication.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@ScenarioScope
@Component
@Data
public class CucumberContext {
  private String requestBody;
  private MvcResult responseEntity;

  @Autowired
  private ObjectMapper objectMapper;

  public <T> T getRequestBodyAsObject(Class<T> bodyClass) throws JsonProcessingException {
    return objectMapper.readValue(requestBody, bodyClass);
  }
}
