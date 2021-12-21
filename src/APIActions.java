import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class APIActions  {
	// Pass url as a string with input city and my API key
	public static String GetData (String urlString) {
		StringBuilder result = new StringBuilder();
		try {
			// Create object type URL
			URL url = new URL(urlString);
			// Create object type URLConnection to read data from url
			URLConnection connection = url.openConnection();
			// Buffer reader to read data from url
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));	
			String line;
			
			// Read data while not null
			while((line = br.readLine()) != null) {	
				result.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			System.out.println("City was not found");
		}
		// Return result as a string
		return result.toString();
	}
}
