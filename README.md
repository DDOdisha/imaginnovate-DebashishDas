# imaginnovate-DebashishDas
Employee Addition and Tax calculation
Code uses H2 DB for testing the API end points
API 1 : localhost:8080/emp/addEmpWithPhone for Employee Addition 
   Sample Payload for Response for happy path testing  the above enpoint in post  
                { 
                "employee": {
        "fname": "Debashish",
        "lname": "Das",
        "dateOfJoin": "2020-01-02",
        "salary": 2883000.0,
        "email": "abcd@gmail.com"
    },
    "phoneNumbers": [
        "444567811",
        "123401110"
                   ]
         }
  API Response JSON
  {
    "id": 2,
    "dateOfJoin": "2020-01-02",
    "salary": 2883000.0,
    "phoneNumbers": [
        "123401110",
        "444567811"
    ],
    "fname": "Debashish",
    "lname": "Das"
}
Sample Payload for invalid payload
       { 
                "employee": {
       
        "lname": "Das",
        "dateOfJoin": "2025-01-02",
        "salary": 2883000.0,
        "email": "abcd@gmail.com"
    },
    "phoneNumbers": [
        "444567811",
        "123401110"
                   ]
         }
    API Response 
    [
    "fname: First name is required",
    "dateOfJoin: Date of join must be in the past"
    ]
    API 2 : localhost:8080/emp/allEmp to get all existing employee (Method Get)
    Sample JSON response
    [
    {
        "id": 1,
        "dateOfJoin": "2020-01-01",
        "salary": 2883000.0,
        "phoneNumbers": [
            "123401110",
            "444567811"
        ],
        "lname": "Das",
        "fname": "Debashish"
    },
    {
        "id": 2,
        "dateOfJoin": "2022-01-01",
        "salary": 2883000.0,
        "phoneNumbers": [
            "2334556",
            "123555550"
        ],
        "lname": "Das1",
        "fname": "Debashish1"
    }
]
  API -3 localhost:8080/tax/employees/2?financialYear=2023    (2 is the employee number and 2023 is the FY ) Method - Get
  {
    "id": 2,
    "firstName": "Debashish1",
    "lastName": "Das1",
    "yearlySalary": 1.1532E7,
    "taxAmount": 2168900.0,
    "cessAmount": 180640.0
  }
