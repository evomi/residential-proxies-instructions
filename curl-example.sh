#!/bin/bash

# Ensure that proxy credentials are set
if [ -z "$proxy_username" ] || [ -z "$proxy_password" ]; then
    echo "Error: Proxy credentials not set. Please set proxy_username and proxy_password."
    echo "Example:"
    echo "export proxy_username=your_username"
    echo "export proxy_password=your_password"
    exit 1
fi

# Use curl to make a request through the proxy
curl -x rp.evomi.com:1000 -U "${proxy_username}:${proxy_password}" https://ip.evomi.com/s

# Note: This will output the response from ip.evomi.com, which should show the IP address being used
