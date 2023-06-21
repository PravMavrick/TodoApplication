# TodoApplication
Create simple working Todo application to get list of Todo's


## Project Run
1. Pull the code from below repo:
   https://github.com/PravMavrick/TodoApplication.git
2. Reload/update project with Maven And run the application.
3. All endpoints exposed to Swagger-UI, you can access it from below links.
    1. http://localhost:8081/v3/api-docs
    2. http://localhost:8081/swagger-ui/index.html
4. Please use PostMan or any API client application for testing the application.

## Project description
1. Application is using H2 in memory database.
   H2 database access link
   http://localhost:8081/h2-console
2. Application has below endpoints
    1. Create all books -  http://localhost:8081/todos
       POST call with below Json body -->
       {
       "title": "Divide you work in small tasks",
       "status": "false"
       }
    2. Get all Todos by - http://localhost:8081/todos
       
    3. Get Todo by its ID - http://localhost:8081/todos/1
       
### Possible Exceptions
1. If Todo is not present then you will get exceptions->
   "Todo is not found."

## Tests
1. Todo Controller test cases are written for POST, GET, PUT, DELETE.
2. Todo Service layer test cases are written for create, update, delete, fetch operations.


