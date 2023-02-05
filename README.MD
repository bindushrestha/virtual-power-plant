# Virtual Power Plant
## Application makes use of H2 in-memory database. 

## Contains apis:
### 1. Save list of Batteries
   - POST /api/v1/batteries
   - Responses 201
   ###  Sample Request Body:
   [{
   "name":"abat34",
   "postCode":123,
   "wattCapacity":100
   },
   {
   "name":"abat2",
   "postCode":124,
   "wattCapacity":100
   },
   {
   "name":"bat3",
   "postCode":125,
   "wattCapacity":100
   },
   {
   "name":"bat4",
   "postCode":126,
   "wattCapacity":100
   },
   {
   "name":"bat5",
   "postCode":127,
   "wattCapacity":100
   }
   ]
### 2. Api to get stat of batteries within post code range
   - GET api/v1/batteries-stat?startRangePostCode=120&endRangePostCode=125
   - Responses 200  
###  Sample response:
   {
   "totalCapacity": 300.0,
   "averageCapacity": 100.0,
   "batteryNames": [
   "abat2",
   "abat34",
   "bat3"
   ]
   }