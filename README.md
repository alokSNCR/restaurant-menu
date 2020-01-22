# Introduction

This is the Restaurant Menu Services source code repository. This Services are back-end micro-services that provide REST API for user to add menu, get item and categories in a restaurant.

# Development

All the api details are available in the swagger document.

http://localhost:9051/swagger-ui.html#/

All the API can be tested on the swagger UI. Below is the endpoint and request body for menu creation. 

    **POST API: http://localhost:9051/restaurant/createMenu**
    **Request Body :**
     {
       "category": [
         {"name": "Appetizer"},
         {"name": "Entree"}
       ],
       "item": [
         {
           "name": "French Fries",
           "category": "Appetizer"
         },
         {
           "name": "Onion Rings",
           "category": "Appetizer"
         },
         {
           "name": "Sandwich",
           "category": "Entree"
         },
         {
           "name": "Tacos",
           "category": "Entree"
         },
         {
           "name": "Ice Cream Sundae",
           "category": "Dessert"
         }
       ],
       "restaurant": "Joe's Grill"
     }

Below are the GET api to fetch the item and categories details for the menu.
        
    **GET Items API:** http://localhost:9051/restaurant/get_items
    **GET Categories API:** http://localhost:9051/restaurant/get_categories
       

In-memory h2 database used to demonstrate the restaurant menu. Below is the url for in-memory database details, no password required to connect with database.

http://localhost:9051/h2-console/login.jsp

    Note** : Please use this url to connect in-memory databse. If any issue occurred.
    URL: jdbc:h2:mem:testdb
