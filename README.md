# MoneyTransfer

Check AccountDetails API:
curl -X GET http://localhost:7000/account/1

Transfer Money from an account to another :
curl -X POST   http://localhost:7000/transaction   -F sender=1  -F receiver=2  -F amount=200
