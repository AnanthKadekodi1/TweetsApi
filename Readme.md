# Tweets Api

Tweets Api Application written using Java Spring and Mysql Backend

# Applications required:
1) IDE like Intellij
2) MySql Backend. 

This github repository contains two folder:
1) On-Prem App which contains the Main Java Spring application running locally with MySQL
2) AWS App - Application with changes to deploy to AWS

# Steps to run the On-Prem App
1) Download the repo from github
2) In an IDE Open the application
3) Setup Mysql DB Version 8
4) Ensure that username and password of mysql is updated in the application.properties file
5) Using mysql workbench, create a schema called tweets-api. If creating a different named schema, update the application.properties file.
6) Now you can run the Tweets Api application on-prem version
7) To test the application, import tweets-api-postman-collection.json file. 
8) The application has been tested on-prem to work with postman and it does work with the requirements such as . 

**I attempted to add features such as likes and followers but realised the difficulty. This is unfinished**. 

# Steps to run the AWS App
Unfortunately, **I was unable to get the AWS App running on elastic beanstalk within time and within the 7 day time period**. I kept receiving a 502 Gateway error which upon investigation showed an issue with the app connection to MYSQL Database

However, to demonstrate that I have attempted to deploy the application to AWS, I have attached a **document with my screenshots of the app deployed to aws**. Additionally, you can also refer to the .elasticbeanstalk folder to see the resources deployed. 

I have used the following guides to deploy to AWS:
1) https://jstw.github.io/deploy-spring-rest-api-with-rds-db-on-elb/
2) https://www.bezkoder.com/deploy-spring-boot-aws-eb/

Overall, the application is a bit of a roughstate and this was because I had to learn new concepts on the go (such as using beanstalk and working with mysql) with a strict time period.

AWS Beanstalk URL (not working) - http://spring-boot-dev.ap-southeast-2.elasticbeanstalk.com/

# Given more time I would do the following:
1) Deploy successfully to AWS
2) Write more unit tests (a lot of my testing here has been manual due to time constraints)
3) Create a frontend for login etc - atm, it only adheres to option 2
4) Clean up my code such as better variable names, remove unused code (referencing to likes, followers etc).


