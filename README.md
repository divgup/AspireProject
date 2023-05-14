# Loan Application
It is a SpringBoot app that allows authenticated users to go through a loan application. Users can request a loan with a specific loan amount and loan term. All the loans will be assumed to have a “weekly” repayment frequency.

## Technologies and Dependencies Used
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or newer
* [Maven](https://maven.apache.org/) used as a dependency management tool.
* [Spring Boot](https://spring.io/projects/spring-boot) used to build hassle free web applications and writing REST APIs.
* [Spring data JPA (Hibernate)](https://hibernate.org/) Used to reduce the time of writing hardcoded sql queries and instead allows to write much more readable and scalable code 
* [MySQL](https://www.mysql.com/) used as a Java persistence store
* [Project Lombok](https://projectlombok.org/) Reduces the time  of writing java boiler plate code.


# Steps to run application
1. Clone the project.
2. cd Project directory.
3. Run command 


```code
mvn package
```

Run jar file created using 

```code
java -jar <jar-filename>

```

# Relevant API endpoints
#### /loan/request
The /loan/request endpoint allows users to submit loan applications.

Sample POST Request
```json
{
   "amount":20,
   "loanTerm":4,
   "customer":{
       "name":"divanshu",
       "email":"divanshu@gmail.com"
   }
}

```

Sample Response
```json
{
   "id": 1,
   "amount": 20,
   "loanTerm": 4,
   "loanStatus": "PENDING",
   "customer": {
       "id": 1,
       "name": "divanshu",
       "email": "divanshu@gmail.com"
   },
   "createdOn": "2023-05-13",
   "listofRepayments": null
}
```



#### /repayment/create
The /repayment/create endpoint allows users to create repayment requests for a given loan.
