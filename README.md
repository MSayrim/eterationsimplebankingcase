# eterationsimplebankingcase
H2 DB kullanıldı
Java 17
spring boot 3.0.1
![image](https://user-images.githubusercontent.com/11701050/210464533-6ea03a3d-0b65-491a-9483-2947b18c3f6e.png)

url: http://localhost:8084/h2-console/login.jsp?jsessionid=bf714302285bfad35656578cb7016911

jdbc url : jdbc:h2:file:./subdirectory/demodb

username : sa 
password : password


API lere istek atabilmek için 
No Auth seçilmeli

![image](https://user-images.githubusercontent.com/11701050/210464684-b3e9a078-414d-4057-8656-5f834e2837e3.png)


//User create
http://localhost:8084/account/create
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
