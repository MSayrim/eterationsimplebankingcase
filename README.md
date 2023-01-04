# eterationsimplebankingcase
//User create
http://localhost:8084/account/create
Noauth
ödeme için
{
"owner":"vodafone",
"accountNumber":"1234567"
}

{
"owner":"murat",
"accountNumber":"123456"
}
//Deposit
http://localhost:8084/account/credit/123456

{
"amount":1000.0
}

http://localhost:8084/account/debit/123456
//withdraw

{
"amount":100.0
}
//payment
http://localhost:8084/account/payment/123456

{
"amount":110.0,
"companyName":"vodafone",
"billCode":"55544433"
}
