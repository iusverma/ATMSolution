# RELEASE NOTES
This API is built using
1. JAVA 1.8.
2. Maven 3.5.2

# How to build the application
1. Clone the project on any local disk
2. Go in ATMSolution directory
3. Run mvn clean install

# How to start 
1. Go in ATMSolution director
2. Run java -jar target/ATMSolution-0.0.1-SNAPSHOT.jar, this will start the server

### API Documentation

Use Case | Http Method | API |
| ------ | ------ | ------ | 
| Check Balance | GET | [http://localhost:8080/balance] |
| Withdraw Amount | POST | [http://localhost:8080/withdraw] |
| Deposit Amount | PUT | [http://localhost:8080/deposit]|

or use [Postman Collection](https://www.getpostman.com/collections/8a62dbc9a9fb995760ca) for fetching my collection.

Refer [API documentation](https://github.com/iusverma/ATMSolution/blob/master/api_documentation.md) for details.

# Notes
1. With each balance call, API will available balance and available number of denominations for each note. Check following for details.
* [Balance response sample](https://github.com/iusverma/ATMSolution/blob/master/samples/get-balance-response.json)
2. With each withdraw call, API will return status, withdrawn amount and number of notes given to user. Check following for details.
* [Withdraw request sample](https://github.com/iusverma/ATMSolution/blob/master/samples/post-withdraw-request.json)
* [Withdraw response sample](https://github.com/iusverma/ATMSolution/blob/master/samples/post-withdraw-response.json)
3. With each deposit call, API will return status, new balance and new number of denominations for each note. Check following for details.
* [Deposit request sample](https://github.com/iusverma/ATMSolution/blob/master/samples/put-deposit-request.json)
* [Deposit response sample](https://github.com/iusverma/ATMSolution/blob/master/samples/put-deposit-response.json)
