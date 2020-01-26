package com.presto.menu.controller;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.model.response.Response;
import com.presto.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/restaurant")

public class MenuController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);


  @Autowired
  private MenuService menuService;

  @PostMapping(value = "/createMenu")
  @ResponseStatus(HttpStatus.CREATED)
  public Response createMenu(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestBody JoeMenuRequest menuRequest) {
    Response apiResponse = new Response();
    try {
      // validate the request
      if (menuRequest == null) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing request body.");
        apiResponse.setMessage("Missing request body.");
        return apiResponse;
      }
      apiResponse = menuService.addMenu(menuRequest);
      if (apiResponse.getItems() != null && apiResponse.getCategories() != null) {
        apiResponse.setMessage("Today menu added.");
      } else {
        apiResponse.setMessage("No item/categories added.");
      }
    } catch (Exception ex) {
      LOGGER.error("Error occurred while creating menu {}", ex);
    }
    return apiResponse;
  }

  @GetMapping(value = "/get_items")
  @ResponseStatus(HttpStatus.OK)
  public Object getItems(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestBody(required = false) JoeMenuRequest menuRequest) {
    try {
      return menuService.getItems(menuRequest);
    } catch (Exception ex) {
      LOGGER.error("Error occurred while fetching items {}", ex);
    }
    return null;
  }

  @GetMapping(value = "/get_categories")
  @ResponseStatus(HttpStatus.OK)
  public Object getCategories(@RequestBody(required = false) JoeMenuRequest menuRequest) {
    try {
      return menuService.getCategories(menuRequest);
    } catch (Exception ex) {
      LOGGER.error("Error occurred while fetching categories {}", ex);
    }
    return "No categories exit in system";
  }
}
