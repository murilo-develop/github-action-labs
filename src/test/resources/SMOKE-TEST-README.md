# Smoke Test

## Authentication process

Every endpoint requires that the caller is authenticated. We are integrated with CTW Active Directory and using Microsoft Authentication Library.

To test the endpoints you need a valid access token.

Since CTW forces MFA we need to follow company authorization flow. Perform the following steps to get a token and enhance the request header for each request.

1) Run:

``` 
python3 get-access-token.py
```
This will open a browser after the login.

2) Copy the access token to **http-client.env.json**.

```
  "local": {
    ...
    "accessToken": "REPLACE_ME"
  }
```

## Performing the request

1) Open the file [application-service.http](application-service.http)
2) Search for "Run with: No Environment" in the file top bar.
3) Change it to local.
4) Click up on the play button on the left of any http resource described, for instance, "Create a Team".

To create a booking it is requested to inform the team member (it infers to create a team as well) and the workstation rack. What leads to create previously these resources beforehand to try to create a booking.

To do so, after perform each request to create the needed resources, copy the resource's id and replace it in the **http-client.env.json**. As it was done with the token. 

```
  "local": {
    ...
    "teamId": "REPLACE_ME",
    "teamMemberId": "REPLACE_ME",
    "workStationId": "REPLACE_ME",
    "bookingId": "REPLACE_ME",
    "accessToken": "REPLACE_ME"
  }
```


