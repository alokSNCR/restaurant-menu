package com.presto.menu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

@Service
public class MenuLoadOnStartUP {

  private static final Logger LOGGER = LoggerFactory.getLogger(MenuLoadOnStartUP.class);
  private ObjectMapper mapper = new ObjectMapper();


  public void menuLoadOnStartUp() {
    try {
      // load customer data
      ObjectNode customerData = getJsonObject("/json/menu.json");



    } catch (Exception ex) {
    }
  }

  /**
   * Read Json data from classpath.
   *
   * @param classpath Classpath file.
   * @return String
   * @throws IOException when classpath file does not exists
   */
  public ObjectNode getJsonObject(String classpath) {
    try {
      Resource resource = new ClassPathResource(classpath);
      InputStream inputStream = resource.getInputStream();
      byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
      return (ObjectNode) mapper.readTree(bdata);
    } catch (IOException e) {
      LOGGER.error("Exception occurred while reading the file from class path {}", e);
    }
    return null;
  }
}
