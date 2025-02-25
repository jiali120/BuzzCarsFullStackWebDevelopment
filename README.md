<<<<<<< HEAD
# cs6400-2023-03-Team011

## Project Technology Stack
### The project is developed using the following technologies:
Backend: Spring Boot 3

Database Operations: JdbcTemplate

Database: MySQL 8.0

Frontend: HTML, CSS, JavaScript


## Setup Steps
Initialize the database using the provided script.
Update the JDBC URL in ‘application.yml’ in the project. Also, modify the corresponding username and password.
In the frontend code, update the ‘rootUrl’ property in ‘js/base.js’ to the backend connection string. For example: http://127.0.0.1:8888/


## Running the Project
1. Compile and package the backend project into buzzcar.jar using Maven. Execute it using the Java command:
 ![image](https://github.gatech.edu/storage/user/76229/files/916af54f-af2a-4bd5-b96b-09248568affd)

2. Deploy the frontend project to Tomcat. Configure it by adding the following within the Host element in tomcat/conf/server.xml:
<Context docBase="path/to/frontend/code" path="/buzzcar" reloadable="true" />

3. Run the Tomcat server using:
 ![image](https://github.gatech.edu/storage/user/76229/files/78f8dbea-e2b4-4776-8aa3-a468ff6dd4c0)

4. If there are permission issues, grant permission with:
 ![image](https://github.gatech.edu/storage/user/76229/files/fef9f4bd-527a-4a4e-bebc-2c5355d0f3e8)


## API  Explanation:
1.URIs starting with public/** do not require authentication. For example, non-logged-in users can search available vehicles.

2.All users with private permissions must log in through the login interface, obtain the Auth-Token, and include this value in the request header. The default validity period is one hour.

3.Different roles can only access permissions within their role. For instance, inventory personnel cannot access the pages of sales personnel.


## Frond End
1. Used HTML and CSS to edit the text and style of the web pages.

2. Used a configuration object defining a base URL and connect the back end.
![image](https://github.gatech.edu/storage/user/76229/files/a93a9b16-6597-4074-ade0-e680838c7205)

3. Request data with method: 'GET'

4. We also used ajax functions that accepts options for customizing the request and provides callback functions for handling success and error responses.


### Five different roles:
1. Public user: search and view avaiable vehicles without logging in
2. InventoryClerk：      username:  user04     pw:pass04        
3. Salesperson：         username:  user01     pw: pass01
4. Manager：             username:  user02     pw: pass02
5. Owner：               username:  owner      pw:123456
=======
# BuzzCarsFullStackWebDevelopment
>>>>>>> 4de357b703d3d6071817a7abce48c74261879e5c
