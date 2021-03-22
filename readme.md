#Project Title
Recipe Manager

#Project Description
Recipe Manger project is to give access to authenticated user to add,
update and delete recipes. This also enables to shows the listing of available recipe.

#Getting Started
This is a spring boot web appliaction built with JSP for UI.
This generates deployable war file.
Configuration are stored in application.properties file

#Prerequisites
Java 8,
Spring Boot 2.4,
MySQL database,
JSP

#Datasource configuration
spring.datasource.url=jdbc:mysql://localhost:3306/recipes
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

#Installation
Ready to deploy and install

#Repository
https://github.com/sanjeevweb7/Recipes.git
 
#Database SQL script
recipe_db.sql
