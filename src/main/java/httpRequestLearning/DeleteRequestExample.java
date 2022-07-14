package httpRequestLearning;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DeleteRequestExample {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		HttpDelete httpDeleteRequest = new HttpDelete("http://localhost:8088/api/users/7");
		
		CloseableHttpResponse httpDeleteResponse = closeableHttpClient.execute(httpDeleteRequest);
		
		System.out.println(httpDeleteResponse.getStatusLine().getStatusCode());	
	}
}
