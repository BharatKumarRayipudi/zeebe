package io.flowing.retail.zeebe.inventory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.ZeebeClientBuilder;
import io.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

@Configuration
public class ZeebeClientConfiguration {

//	@Value("${camunda.cloud.clusterId:#{null}}")
	private String clusterId = "f5818bbe-4a56-43a3-aefd-ff01ed4d6531";

//	@Value("${camunda.cloud.baseUrl:zeebe.camunda.io}")
	private String baseUrl = "zeebe.camunda.io";

//	@Value("${camunda.cloud.clientId:#{null}}")
	private String clientId = "Z6_2OePwEc6yhyaLH25dK93K92jNgyoB";

//	@Value("${camunda.cloud.clientSecret:#{null}}")
	private String clientSecret = "LsvAafVF-h0GPs6MI0H.aeotZQ.NjAGNXR5s0EHgtCF6BvTD2bl1C.-TWE1qJdzN";

//	@Value("${camunda.cloud.authUrl:'https://login.cloud.camunda.io/oauth/token'}")
	private String authUrl = "https://login.cloud.camunda.io/oauth/token";

	@Bean
	public ZeebeClient zeebe() {
		if (clusterId==null) {
			System.out.println("*** Connect to Zeebe locally (no Camunda Cloud cluster id set) ***");
			
			ZeebeClient client = ZeebeClient.newClientBuilder().usePlaintext().build();
			System.out.println("*** Connected sucessfully to Zeebe locally ***");
			return client;
		} else {
			System.out.println("*** Connect to Camunda Cloud (cluster id "+clusterId+") ***");
			
			final String broker = clusterId + "." + baseUrl + ":443";
	
			final OAuthCredentialsProviderBuilder c = new OAuthCredentialsProviderBuilder();
			final OAuthCredentialsProvider cred = c.audience(clusterId + "." + baseUrl).clientId(clientId)
					.clientSecret(clientSecret).authorizationServerUrl(authUrl).build();
	
			final ZeebeClientBuilder clientBuilder = ZeebeClient.newClientBuilder().brokerContactPoint(broker)
					.credentialsProvider(cred);
	
			ZeebeClient client = clientBuilder.build();
	
			System.out.println("*** Connected sucessfully to Camunda Cloud ***");
	
			return client;
		}
	}

}
