import os
import requests

# Get proxy credentials from environment variables
proxy_username = os.environ.get('proxy_username')
proxy_password = os.environ.get('proxy_password')

if not proxy_username or not proxy_password:
    print("Error: Proxy credentials not set. Please set proxy_username and proxy_password.")
    print("Example:")
    print("export proxy_username=your_username")
    print("export proxy_password=your_password")
    exit(1)

# Set up the proxy
proxy = {
    "http": f"http://customer-{proxy_username}:{proxy_password}@rp.evomi.com:1000",
    "https": f"http://customer-{proxy_username}:{proxy_password}@rp.evomi.com:1000"
}

try:
    # Make a request through the proxy
    response = requests.get("https://ip.evomi.com/", proxies=proxy)
    print(response.text)
except requests.exceptions.RequestException as e:
    print(f"An error occurred: {e}")
