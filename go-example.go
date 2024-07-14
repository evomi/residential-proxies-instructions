package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
	"os"
)

func main() {
	// Get proxy credentials from environment variables
	proxyUsername := os.Getenv("proxy_username")
	proxyPassword := os.Getenv("proxy_password")

	if proxyUsername == "" || proxyPassword == "" {
		fmt.Println("Error: Proxy credentials not set. Please set proxy_username and proxy_password.")
		fmt.Println("Example:")
		fmt.Println("export proxy_username=your_username")
		fmt.Println("export proxy_password=your_password")
		os.Exit(1)
	}

	// Set up the proxy URL
	proxyURL, err := url.Parse(fmt.Sprintf("http://customer-%s:%s@rp.evomi.com:1000", proxyUsername, proxyPassword))
	if err != nil {
		fmt.Println("Error parsing proxy URL:", err)
		return
	}

	// Create a new HTTP client with the proxy
	client := &http.Client{
		Transport: &http.Transport{
			Proxy: http.ProxyURL(proxyURL),
		},
	}

	// Make a request through the proxy
	resp, err := client.Get("https://ip.evomi.com/")
	if err != nil {
		fmt.Println("Error making request:", err)
		return
	}
	defer resp.Body.Close()

	// Read and print the response
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Error reading response:", err)
		return
	}

	fmt.Println(string(body))
}
