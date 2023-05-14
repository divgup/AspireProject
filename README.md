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
2. Navigate to project directory.
3. Run command 


```code
mvn package
```

Run jar file created inside target folder using 

```code
java -jar <jar-filename>

```

Server will start on port 8082

# Relevant API endpoints
#### /loan/request
The /loan/request endpoint allows users to submit loan applications.

Sample POST Request Body
```json
{
   "amount":20,
   "loanTerm":1,
   "customer":{
       "name":"abc",
       "email":"abc@gmail.com"
   }
}

```

Sample Response
```json
{
   "id": 1,
   "amount": 20,
   "loanTerm": 1,
   "loanStatus": "PENDING",
   "customer": {
       "id": 1,
       "name": "abc",
       "email": "abc@gmail.com"
   },
   "createdOn": "2023-05-13",
   "listofRepayments": null
}
```



#### /repayment/create
The /repayment/create endpoint allows users to create repayment requests for a given loan.

Sample POST request Body
```json
{
   "loanId":1
}
```

Sample Response
```
Repayment for loan id=1 created
```

#### /admin/approve
The  /admin/approve endpoint allows admin to approve loans with a given id. 

Sample PUT request Body
```json
{
   "loanId":1
}
```

Sample Response
```
Loan with id = 1 approved
```

#### /loan/customer/all
The /loan/customer/all endpoint allows customers to view their own loan.

Sample GET request
```http
GET http://localhost:8082/loan/customer/all?email=abc@gmail.com
```
Sample response
```json
{
       "id": 1,
       "amount": 20,
       "loanTerm": 1,
       "loanStatus": "APPROVED",
       "customer": {
           "id": 1,
           "name": "abc",
           "email": "abc@gmail.com"
       },
       "createdOn": "2023-05-13",
       "listofRepayments": [
           {
               "id": 1,
               "repaymentnumber": 1,
               "actualAmount": 5.0,
               "paidAmount": 0.0,
               "paymentstatus": "PENDING",
           }
      ]
}
```

| Parameter | Type | Description |
| :--- | :--- | :--- |
| `email` | `string` | **Required**. customer email Id |

#### /repayment/pay
The /repayment/pay endpoint allows them to make an installment towards repaying their loan.

Sample POST request Body
```json
{
    "loanId":1,
    "amount":1
}
```

Sample Response
```
Repayment for loan id = 1 success.
```

