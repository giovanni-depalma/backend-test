# backend-test

## Summary
Backend Test

### Get Started Immediately

In order to start the project  follow these simple instructions:

- Run 'docker-compose build' from project folder
- Run 'docker-compose up -d' from project folder
- Wait for services initialization
- Launch Browser on page: http://localhost:8081/swagger-ui/index.html

### Enjoy 

Use swagger-ui to create/find pupils. 

These endpoints are "secured".
From Swagger UI clicking on the padlock you have two options:

- insert bearer token: you can get the token with the following command
```
curl -X POST "http://localhost:8080/realms/test/protocol/openid-connect/token" \
 --insecure \
 -H "Content-Type: application/x-www-form-urlencoded" \
 -d "username=admin" \
 -d "password=admin" \
 -d 'grant_type=password' \
 -d 'client_secret=UqfVKfO5ReJoDjjZgW24ITdj8WUoHbel' \
 -d "client_id=test-gateway" | jq -r '.access_token'
```
- insert username/password (admin/admin)

Now you can make requests (The token has a 15-minute expiration)

Have fun!