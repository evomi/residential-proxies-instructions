const axios = require('axios');

// Get proxy credentials from environment variables
const proxyUsername = process.env.proxy_username;
const proxyPassword = process.env.proxy_password;

if (!proxyUsername || !proxyPassword) {
    console.error("Error: Proxy credentials not set. Please set proxy_username and proxy_password.");
    console.error("Example:");
    console.error("export proxy_username=your_username");
    console.error("export proxy_password=your_password");
    process.exit(1);
}

const proxy = {
    host: 'rp.evomi.com',
    port: 1000,
    auth: {
        username: `customer-${proxyUsername}`,
        password: proxyPassword
    }
};

axios.get('https://ip.evomi.com/', { proxy })
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error('An error occurred:', error.message);
    });
