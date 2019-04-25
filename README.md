# TraderApiSpringH2

API rest para trade





=============================--Create a User
REQUEST:
POST
http://localhost:8000/api/user/

body
{
"name":"carlo"
}
RESPONSE:
201

header
date →Thu, 25 Apr 2019 05:16:09 GMT
location →http://localhost:8000/api/user/1



=============================-delete User
REQUEST:
DELETE
http://localhost:8000/api/User/{id}

RESPONSE:
204 no content



=============================getAlluserr
REQUEST:
GET
http://localhost:8000/api/user/

RESPONSE:
200
[
    {
        "id": 1,
        "name": "Super User 661"
    }
]



=============================--Create a trade--
REQUEST:
POST
http://localhost:8000/api/trade/

body
{
  "id" : 122,
  "type" : "venda",
  "user" : {
    "id" : 1
  },
  "stockSymbol" : "V",
  "stockQuantity" : 10,
  "stockPrice" : 15223,
  "tradeTimestamp" : 1556113511000
}

RESPONSE:
201

header
date →Thu, 25 Apr 2019 05:16:09 GMT
location →http://localhost:8000/api/trade/122


=============================-delete um trade
REQUEST:
DELETE
http://localhost:8000/api/trade/{id}

RESPONSE:
204 no content



=============================delete all  trade
REQUEST:
DELETE
http://localhost:8000/api/trade/

RESPONSE:
204 no content


=============================getAllTrader
REQUEST:
GET
http://localhost:8000/api/trade/

RESPONSE:
[
    {
        "id": 1,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 16.35,
        "tradeTimestamp": "2019-04-24T01:02:21.000+0000"
    },
    {
        "id": 39,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 54.35,
        "tradeTimestamp": "2019-04-25T16:18:21.000+0000"
    }
]


=============================getFindAllTraderBySymbolByTypeAndDatePeriod
REQUEST:
GET
http://localhost:8000/api/stocks/V/trade?type=venda&start=24-04-2019 14:00:00&end=24-04-2019 19:00:00

RESPONSE:
[
    {
        "id": 1,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 16.35,
        "tradeTimestamp": "2019-04-24T01:02:21.000+0000"
    },
    {
        "id": 39,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 54.35,
        "tradeTimestamp": "2019-04-25T16:18:21.000+0000"
    }
]


=============================get max and min Price TraderBySymbolByDatePeriod
REQUEST:
GET
http://localhost:8000/api/stocks/V/price?start=24-04-2019 01:00:00&end=25-04-2019 19:00:00

RESPONSE:
[
    {
        "id": 1,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 16.35,
        "tradeTimestamp": "2019-04-24T01:02:21.000+0000"
    },
    {
        "id": 39,
        "type": "venda",
        "user": {
            "id": 1,
            "name": "Super User 661"
        },
        "stockSymbol": "V",
        "stockQuantity": 30,
        "stockPrice": 54.35,
        "tradeTimestamp": "2019-04-25T16:18:21.000+0000"
    }
]