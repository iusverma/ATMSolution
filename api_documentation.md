**API 1: Check Balance**  
* Method: GET
* URL: http://localhost:8080/balance
* Accepts: application/json
* Sample Response:
```
{
    "status": "SUCCESS",
    "message": "Balance enquiry executed successfully",
    "amount": 2260,
    "billsDetail": {
        "totalAmount": 2260,
        "billsDetail": {
            "10": 14,
            "20": 16,
            "50": 12,
            "100": 12
        }
    }
}
```

**API 2: Withdraw Amount**   
* Method: POST  
* URL: http://localhost:8080/withdraw
* Accepts: application/json
* Sample Request:
```
{
    "amount": 200
}
```
* Sample Response:
```
{
    "status": "SUCCESS",
    "message": "Amount withdrawan successfully.",
    "amount": 200,
    "billsDetail": {
        "totalAmount": 200,
        "billsDetail": {
            "10": 0,
            "20": 0,
            "50": 4,
            "100": 0
        }
    }
}
```

**API 3: Deposit Amount**  
* Method: PUT  
* URL:  http://localhost:8080/deposit
* Accepts: application/json
* Sample Request:
```
{
    "amount": 230,
    "notesToDeposit": {
        "10": 2,
        "20": 3,
        "50": 1,
        "100": 1
    }
}
```
* Sample Response:
```
{
    "status": "SUCCESS",
    "message": "Amount deposited successfully.",
    "amount": 200,
    "billsDetail": {
        "totalAmount": 2030,
        "billsDetail": {
            "10": 12,
            "20": 13,
            "50": 11,
            "100": 11
        }
    }
}
```