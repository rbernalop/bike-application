package org.rbernalop.bikeapplication.it.shared;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import io.cucumber.java.en.When;
import org.rbernalop.bikeapplication.it.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public class SharedWhenSteps extends IntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    @When("^a call is made to (.+) endpoint with (.+) method$")
    public void makeHttpRequest(String path, String method) throws Exception {
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        MvcResult serverResponse = mockMvc.perform(request(httpMethod, path)).andReturn();
      context.setResponseEntity(serverResponse);
    }

    @When("^a call is made to (.+) endpoint with (.+) method and (.+) body")
    public void makeHttpRequestWithBody(String path, String method, String bodyFileName) throws Exception {
        String body = getContentByFileName(bodyFileName + ".json");
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        MvcResult serverResponse = mockMvc.perform(
                request(httpMethod, path)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        context.setRequestBody(body);
        context.setResponseEntity(serverResponse);
    }
}
