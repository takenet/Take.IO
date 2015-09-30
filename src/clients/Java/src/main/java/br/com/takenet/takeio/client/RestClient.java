package br.com.takenet.takeio.client;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import org.glassfish.jersey.jackson.JacksonFeature;
import br.com.takenet.takeio.entities.Collection;
import br.com.takenet.takeio.entities.Item;


public class RestClient implements RestClientInterface {
	public static final String SIGNATURE_METHOD = "HMAC-SHA1";
	public static final String BASE_URL = "http://api.take.io/rest/1.0/";
	private final Client client;
	public final MessagesClient messages;
	
	public RestClient(String consumerKey, String consumerSecret,
			String requestToken, String requestTokenSecret) {
        ConsumerCredentials consumerCredentials = new ConsumerCredentials(consumerKey, consumerSecret);
        AccessToken accessToken = new AccessToken(requestToken, requestTokenSecret);
        Feature filterFeature = OAuth1ClientSupport.builder(consumerCredentials).feature()
                .accessToken(accessToken).build();
        
        // create a new Jersey client and register filter feature that will add OAuth signatures and
        // JacksonFeature that will process returned JSON data.
        client = ClientBuilder.newBuilder()
                .register(filterFeature)
                .register(JacksonFeature.class)
                .build();
        messages = new MessagesClient(this);
	}

	@Override
	public <T extends Collection<?>> Response<T> get(Class<T> type, String path, List<String> parameters) {
		String url = BASE_URL + path;
		if (parameters != null)
			url = String.format("%s?%s", url, StringUtils.join(parameters, "&"));
		WebTarget target = client.target(url);		
		javax.ws.rs.core.Response resourceResponse = target.request().get();
		Response<T> response = new Response<>();
		response.setStatus(resourceResponse.getStatusInfo());
		response.setDescription(resourceResponse.getStatusInfo().toString());
		T queryResult = resourceResponse.readEntity(type);
		response.setContent(queryResult);
		return response;
	}
	
	@Override
	public <T extends Item<?>> Response<T> get(Class<T> type, String path, UUID guid) {
		javax.ws.rs.core.Response resourceResponse = client.target(BASE_URL).path(path).path(guid.toString()).request().get();
		Response<T> response = new Response<>();
		response.setStatus(resourceResponse.getStatusInfo());
		response.setDescription(resourceResponse.getStatusInfo().toString());
		T queryResult = resourceResponse.readEntity(type);
		response.setContent(queryResult);
		return response;
	}

	@Override
	public <T> Response<URI> post(Class<T> type, String path, T entity) {
		Entity<T> entityQuery = Entity.entity(entity, MediaType.APPLICATION_JSON);
		WebTarget target = client.target(BASE_URL).path(path);
		javax.ws.rs.core.Response resourceResponse = target.request(MediaType.APPLICATION_JSON).post(entityQuery);
		Response<URI> response = new Response<>();
		response.setContent(resourceResponse.getLocation());
		response.setDescription(resourceResponse.getStatusInfo().toString());
		response.setStatus(resourceResponse.getStatusInfo());
		return response;
	}

	@Override
	public <T extends Item<?>> Response<T> put(Class<T> type, String path, T entity, UUID guid) {
		Entity<T> entityQuery = Entity.entity(entity, MediaType.APPLICATION_JSON);
		WebTarget target = client.target(BASE_URL).path(path).path(guid.toString());
		javax.ws.rs.core.Response resourceResponse = target.request(MediaType.APPLICATION_JSON).put(entityQuery);
		Response<T> response = new Response<>();
		response.setStatus(resourceResponse.getStatusInfo());
		response.setDescription(resourceResponse.getStatusInfo().toString());
		T queryResult = resourceResponse.readEntity(type);
		response.setContent(queryResult);
		return response;
	}


}
