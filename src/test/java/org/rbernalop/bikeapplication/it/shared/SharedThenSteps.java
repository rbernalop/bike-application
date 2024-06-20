package org.rbernalop.bikeapplication.it.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.rbernalop.bikeapplication.it.IntegrationTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.web.servlet.MvcResult;

public class SharedThenSteps extends IntegrationTest {

  @Then("^the response status code is (\\d+)$")
  public void checkHttpStatus(int expectedStatusCode) {
    MvcResult serverResponse = context.getResponseEntity();
    final int actualStatusCode = serverResponse.getResponse().getStatus();
    assertEquals(expectedStatusCode, actualStatusCode);
  }

  @And("^response list contains data from file (.+)$")
  public void responseContainsData(String fileName) throws Exception {
    String expectedContent = getContentByFileName(fileName);
    String actualContent = context.getResponseEntity().getResponse().getContentAsString();

    JSONArray expectedJSON = new JSONArray(expectedContent);
    JSONArray actualJSON = new JSONArray(actualContent);

    JSONAssert.assertEquals(expectedJSON, actualJSON, false);
  }
}
