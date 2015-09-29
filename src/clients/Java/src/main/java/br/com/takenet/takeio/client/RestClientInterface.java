package br.com.takenet.takeio.client;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public interface RestClientInterface {
	
	<T> Response<T> get(Class<T> type, String path, List<String> parameters);

	<T> Response<T> get(Class<T> type, String path, UUID guid);

	<T> Response<URI> post(Class<T> type, String path, T entity);

	<T> Response<T> put(Class<T> type, String path, T entity, UUID guid);
}
