<?php

// Get proxy credentials from environment variables
$proxyUsername = getenv('proxy_username');
$proxyPassword = getenv('proxy_password');

if (!$proxyUsername || !$proxyPassword) {
    echo "Error: Proxy credentials not set. Please set proxy_username and proxy_password.\n";
    echo "Example:\n";
    echo "export proxy_username=your_username\n";
    echo "export proxy_password=your_password\n";
    exit(1);
}

// Initialize cURL session
$ch = curl_init();

// Set cURL options
curl_setopt($ch, CURLOPT_URL, "https://ip.evomi.com/");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_PROXY, "rp.evomi.com:1000");
curl_setopt($ch, CURLOPT_PROXYUSERPWD, "customer-$proxyUsername:$proxyPassword");

// Execute cURL request
$response = curl_exec($ch);

if ($response === false) {
    echo "cURL Error: " . curl_error($ch);
} else {
    echo $response;
}

// Close cURL session
curl_close($ch);
