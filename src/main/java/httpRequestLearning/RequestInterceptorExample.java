package httpRequestLearning;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

class Vehicle {}

public class RequestInterceptorExample {

	public static void main(String[] args) throws Exception {
		
		/*Vehicle vehicle = new Vehicle();
		System.out.println(vehicle.toString());
		//Step 1 : 
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(i);
				}
				
			}	
		};
		
		Runnable runnable2 = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(i);
				}
				
			}	
		};
		
		System.out.println(runnable.toString());
		System.out.println(runnable2.toString());*/
		
		HttpRequestInterceptor requestInterceptorRemoveHeader = new HttpRequestInterceptor() {

			@Override
			public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
				
				if (request.containsHeader("my-custom-header")) {
					System.out.println("Removing my-custom-header");
					request.removeHeaders("my-custom-header");
					System.out.println("Removed my-custom-header");
				}
				
			}	
		};
		
		HttpRequestInterceptor requestInterceptorPrintHeader = new HttpRequestInterceptor() {
			
			@Override
			public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
				
				System.out.println("Print Header Started");
				Header[] remainingHeader = request.getAllHeaders();
				for (int i = 0; i < remainingHeader.length; i++) {
					System.out.println(remainingHeader[i].getName());
				}
				System.out.println("Print Header Completed");
			}
		};
		
		
		CloseableHttpClient closeableHttpClient = HttpClients.custom()
							.addInterceptorFirst(requestInterceptorRemoveHeader)
							.addInterceptorLast(requestInterceptorPrintHeader)
							.build();
		
		HttpGet httpGetRequest = new HttpGet("http://localhost:8088/api/users");
		httpGetRequest.setHeader(new BasicHeader("my-custom-header", "1234567890"));
		httpGetRequest.setHeader(new BasicHeader("another-custom-header", "ABCDEFGHIJ"));
		
		CloseableHttpResponse httpGetResponse = closeableHttpClient.execute(httpGetRequest);
		
		System.out.println(httpGetResponse.getStatusLine().getStatusCode());
	}

}
