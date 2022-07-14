package httpRequestLearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PostRequestExample {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		HttpPost httpPostRequest = new HttpPost("http://localhost:8088/api/users");
		
		String jsonBody = "{\"firstName:\"John\",\"lastName\":\"Doe\",\"sports\":\"TableTennis\",\"country\":\"USA\",\"salary\":50000}";
		
		StringEntity requestEntity = new StringEntity (jsonBody, ContentType.APPLICATION_JSON);
		httpPostRequest.setEntity(requestEntity);                     
		
		httpPostRequest.setHeader("Content-Type", "application/json");
		
		CloseableHttpResponse httpPostResponse = closeableHttpClient.execute(httpPostRequest);
		
		System.out.println(httpPostResponse.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(httpPostResponse.getEntity().getContent()));
		
		StringBuffer response = new StringBuffer();
		String responseLine = null;
		
		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine);
		}
		System.out.println(response);
	}

}
