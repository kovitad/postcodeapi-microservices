Backed-end Restful Web Services: 

To Search Australian PostCode/Suburb

Brief info:

This project using Spring Boot Version 1.4.5
And Securing a microservice With OAuth2


Build:

>mvn clean package

Run:

>java -jar target/microservices-api-postcode-0.0.1-SNAPSHOT.jar

Note:

This microservices will run on the port: 9000

Health check URL:http://localhost:9000/health
Welcome Page URL: http://localhost:9000
API Documentation (Using Swagger 2.0) URL: http://localhost:9000/swagger-ui.html
*** Please check postcode-controller : Postcode Controller section****

Spring Beans Info URL: http://localhost:9000/beans


Testing the Restful Web Services:

*Searching By PostCode Example:
$ curl -s http://localhost:9000/api/public/postcode/3000


{"status":{"code":"API-200","message":"Success"},"result":[{"postCode":"3000","suburb":"MELBOURNE","state":"VIC"}]}

*Searching By Suburb Example:
$ curl -s http://localhost:9000/api/public/suburb/MELBOURNE

{"status":{"code":"API-200","message":"Success"},"result":[{"postCode":"3000","suburb":"MELBOURNE","state":"VIC"}]}

*Add new Suburb/Postcode to the Repository

We need the access token to access the API

Step to run:
 1. Retrieve Access Token
 
$  curl -s myclient:myclientsecret@localhost:9000/oauth/token \
 -d grant_type=password \
 -d client_id=myclient \
 -d scope=openid \
 -d username=guest \
 -d password=guest
 
 
{"access_token":"5527d94b-0058-4788-81a8-4d93edf23903","token_type":"bearer","refresh_token":"f083241e-a224-43ac-a5df-87407cdc6e77","expires_in":43199,"scope":"openid"}
 
 
 2. Create Record by using the access token from the previous call
 
$  curl -d '{"postCode":"4006", "suburb":"Fortitude Valley 1", "state" : "QLD"}' \
  -H "Content-Type: application/json" \
  -H  "Authorization: Bearer 5527d94b-0058-4788-81a8-4d93edf23903" \
  -X POST http://localhost:9000/api/secure/addrecord 
 
 {"postCode":"4006","suburb":"Fortitude Valley","state":"QLD"}
 
 
 
 