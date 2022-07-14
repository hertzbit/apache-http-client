package httpRequestLearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PutRequestExample {

	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		
		HttpPut httpPutRequest = new HttpPut("http://localhost:8088/api/users/7");
		
		String jsonBody = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"sports\":\"Hockey\",\"country\":\"USA\",\"salary\":50000}";
		
		StringEntity requestEntity = new StringEntity (jsonBody, ContentType.APPLICATION_JSON);
		httpPutRequest.setEntity(requestEntity);                     
		httpPutRequest.setHeader("Content-Type", "application/json");
		
		CloseableHttpResponse httpPutResponse = closeableHttpClient.execute(httpPutRequest);
		
		System.out.println(httpPutResponse.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(httpPutResponse.getEntity().getContent()));
		
		StringBuffer response = new StringBuffer();
		String responseLine = null;
		
		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine);
		}
		System.out.println(response);
	}

}
