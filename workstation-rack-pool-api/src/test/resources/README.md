# Authentication process

Every endpoint requires that the caller is authenticated. We are integrated with CTW Active Directory and using Microsoft Authentication Library.

To test the endpoints you need a valid access token.

Since CTW forces MFA we need to follow company authorization flow, to do that you can user the following script to get the access token:

``` 
python3 get-access-token.py
```

This will open a browser after the login you need to copy the access token to **http-client.env.json**.

```
  "local": {
    "host": "http://localhost:8080",
    "teamId": "7dd95a52-46ba-4a5c-8e6e-3809aa6cc288",
    "teamMemberId": "afc1e775-96ed-400e-ac4b-1d13d312194d",
    "workStationId": "d5a47975-240f-4070-951d-9a90a6cf0638",
    "bookingId": "b0cfe397-100a-40e0-86c6-5f51778b37de",
    "accessToken": "REPLACE_ME"
  }
```




