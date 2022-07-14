package httpRequestLearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetRequestExample {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		HttpGet httpGetRequest = new HttpGet("http://localhost:8088/api/users/7");
		
		CloseableHttpResponse httpGetResponse = closeableHttpClient.execute(httpGetRequest);
		
		BufferedReader br = new 
				BufferedReader(new InputStreamReader(httpGetResponse.getEntity().getContent()));
		
		StringBuffer response = new StringBuffer();
		String responseLine = null;
		
		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine);
		}
		System.out.println(httpGetResponse.getStatusLine().getStatusCode());
		System.out.println(response);
		
		
	}

}
