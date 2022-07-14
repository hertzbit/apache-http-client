package httpRequestLearning;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class UserAuthenticationExample {

	public static void main(String[] args) throws Exception {
		
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		AuthScope authScope = new AuthScope("http://localhost:8088/api/users", 80);
		Credentials credentials = new UsernamePasswordCredentials("someUser", "somePassword");
		
		credentialsProvider.setCredentials(authScope, credentials);
		
		/*HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();*/
		
		CloseableHttpClient closeableHttpClient = HttpClients.custom()
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();
		
		HttpGet httpGetRequest = new HttpGet("http://localhost:8088/api/users");
		CloseableHttpResponse httpGetResponse = closeableHttpClient.execute(httpGetRequest);
		
		System.out.println(httpGetResponse.getStatusLine().getStatusCode());
	}

}
