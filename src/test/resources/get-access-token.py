import webbrowser

client_id = '82358e21-2576-4ef0-af19-4e8f06af0396'
tenant_id = '61f30b8e-4f6b-44fe-9bc2-041e3a9f7346'
redirect_uri = 'http://localhost:8080'
scope = 'api://82358e21-2576-4ef0-af19-4e8f06af0396/.default'
auth_url = f'https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/authorize'

# Construct the authorization URL
authorization_url = f'{auth_url}?client_id={client_id}&response_type=token&redirect_uri={redirect_uri}&scope={scope}'

print(authorization_url)

# Open the browser to log in and get the token
webbrowser.open(authorization_url)

# In a real-world scenario, you would set up a local web server to capture the token from the redirect URL
print("After logging in, extract the token from the redirect URL.")
