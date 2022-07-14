package httpRequestLearning;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

public class ResponseInterceptorExample {

	public static void main(String[] args) throws Exception {
		
		HttpResponseInterceptor responseInterceptor = new HttpResponseInterceptor() {

			@Override
			public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
				
				System.out.println("Adding Headers");
				response.setHeader("my-custom-header", "1234567890");
				response.setHeader("another-header", "ABCDEFGHIJK");
				System.out.println("Headers Added");
			}	
		};
		
		CloseableHttpClient closeableHttpClient = HttpClients.custom().addInterceptorFirst(responseInterceptor).build();
		
		HttpGet httpGetRequest = new HttpGet("http://localhost:8088/api/users");
		//httpGetRequest.setHeader(new BasicHeader("my-custom-header", "1234567890"));
		//httpGetRequest.setHeader(new BasicHeader("another-custom-header", "ABCDEFGHIJ"));
		
		CloseableHttpResponse httpGetResponse = closeableHttpClient.execute(httpGetRequest);
		
		//System.out.println(httpGetResponse.getStatusLine().getStatusCode());
		
		Header[] allHeaders = httpGetResponse.getAllHeaders();
		for (int i = 0; i < allHeaders.length; i++) {
			System.out.println(allHeaders[i].getName());
		}
	}

}
