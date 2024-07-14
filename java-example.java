import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;

public class JavaExample {
    public static void main(String[] args) {
        String proxyUsername = System.getenv("proxy_username");
        String proxyPassword = System.getenv("proxy_password");

        if (proxyUsername == null || proxyPassword == null) {
            System.out.println("Error: Proxy credentials not set. Please set proxy_username and proxy_password.");
            System.out.println("Example:");
            System.out.println("export proxy_username=your_username");
            System.out.println("export proxy_password=your_password");
            System.exit(1);
        }

        try {
            // Set up the proxy
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("rp.evomi.com", 1000));

            // Set up the authenticator
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("customer-" + proxyUsername, proxyPassword.toCharArray());
                }
            });

            // Create the connection
            URL url = new URL("https://ip.evomi.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println(content.toString());

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
