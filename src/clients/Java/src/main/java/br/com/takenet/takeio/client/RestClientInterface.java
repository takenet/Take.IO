package br.com.takenet.takeio.client;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import br.com.takenet.takeio.entities.Collection;
import br.com.takenet.takeio.entities.Item;

public interface RestClientInterface {
	
	<T extends Collection<?>> Response<T> get(Class<T> type, String path, List<String> parameters);

	<T extends Item<?>> Response<T> get(Class<T> type, String path, UUID guid);

	<T> Response<URI> post(Class<T> type, String path, T entity);

	<T extends Item<?>> Response<T> put(Class<T> type, String path, T entity, UUID guid);
}
