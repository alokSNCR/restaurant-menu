package com.presto.menu.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.model.response.CategoryResponse;
import com.presto.menu.model.response.ItemResponse;
import com.presto.menu.model.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuServiceIT {

  private JoeMenuRequest menuRequest;
  private ObjectMapper mapper = new ObjectMapper();

  @LocalServerPort
  private int port;

  private String uri;

  HttpHeaders headers = new HttpHeaders();
  TestRestTemplate restTemplate = new TestRestTemplate();

  @Before
  public void init() throws Exception {
    File file = new ClassPathResource("/json/samplemenu.json").getFile();
    menuRequest = mapper.readValue(file, JoeMenuRequest.class);
    uri = uri("/restaurant");
  }

  protected String uri(String uri) {
    return "http://localhost:" + port + uri;
  }


  @Test
  public void testAddMenu() throws Exception {
    HttpEntity<JoeMenuRequest> entity = new HttpEntity<>(menuRequest, headers);
    ResponseEntity<Response> response = restTemplate.exchange(uri + "/createMenu",
        HttpMethod.POST,
        entity,
        Response.class);

    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    Assert.assertEquals(3, response.getBody().getCategories().size());
    Assert.assertEquals(5, response.getBody().getItems().size());
  }

  @Test
  public void testGetItems() throws Exception {
    HttpEntity<JoeMenuRequest> entity = new HttpEntity<>(menuRequest, headers);
    restTemplate.exchange(uri + "/createMenu", HttpMethod.POST, entity, Response.class);

    // test for fetch item functionality
    List<ItemResponse> response = (List<ItemResponse>) restTemplate.getForObject(
        uri + "/get_items", Object.class);

    Assert.assertNotNull(response);
    Assert.assertEquals(5, response.size());
  }

  @Test
  public void testGetCategories() throws Exception {
    HttpEntity<JoeMenuRequest> entity = new HttpEntity<>(menuRequest, headers);
    restTemplate.exchange(uri + "/createMenu", HttpMethod.POST, entity, Response.class);

    // test for fetch item functionality
    List<CategoryResponse> response = (List<CategoryResponse>) restTemplate.getForObject(
        uri + "/get_categories", Object.class);

    Assert.assertNotNull(response);
    Assert.assertEquals(3, response.size());
  }

}
