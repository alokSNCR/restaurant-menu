package com.presto.menu.controller;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.model.response.Response;
import com.presto.menu.repository.CategoryItemRepository;
import com.presto.menu.repository.CategoryRepository;
import com.presto.menu.repository.ItemRepository;
import com.presto.menu.repository.entity.CategoryItems;
import com.presto.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurant")
@Api(value = "The controller provides to perform all the admin related operations.")
@ApiResponses(
    value = {
        @ApiResponse(code = 202, message = "Request has been accepted without any error"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
        @ApiResponse(code = 500, message = "Internal server Error. Contact System administrator")
    })
public class MenuController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

  @Autowired
  private MenuService menuService;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private CategoryRepository categoryRepository;

  @ApiOperation(
      value = "",
      nickname = "createMenu",
      notes = "This api will create menu details for the restaurant.",
      response = Response.class)
  @RequestMapping(value = "/createMenu", method = RequestMethod.POST)
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

      if (menuService.saveMenu(menuRequest)) {
        apiResponse.setMessage("Menu created.");
        apiResponse.setMenu(menuRequest);
      }
    } catch (Exception ex) {
      LOGGER.error("Error occurred while creating menu {}", ex);
    }
    return apiResponse;
  }


  @ApiOperation(
      value = "",
      nickname = "fetchItem",
      notes = "This api will fetch all the items from the menu.",
      response = Response.class)
  @RequestMapping(value = "/get_items", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public Object getItems(HttpServletRequest request,
                         HttpServletResponse response) {
    Response apiResponse = new Response();
    try {
      return categoryRepository.findAll();
    } catch (Exception ex) {
      LOGGER.error("Error occurred while fetching items {}", ex);
    }
    return "No items are exit in system.";
  }

  @ApiOperation(
      value = "",
      nickname = "fetchCategories",
      notes = "This api will fetch all the categories from the menu.",
      response = Response.class)
  @RequestMapping(value = "/get_categories", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public Object getCategories() {
    try {
      return itemRepository.findAll();
    } catch (Exception ex) {
      LOGGER.error("Error occurred while fetching categories {}", ex);
    }
    return "No categories are exit in system.";
  }
}
