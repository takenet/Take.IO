package br.com.takenet.takeio.client;

import java.net.URI;
import java.util.UUID;
import br.com.takenet.takeio.entities.Collection;
import br.com.takenet.takeio.entities.Item;

public interface ResourceOperations <T> {
	Response<? extends Item<T>> get(String guid);
	Response<? extends Item<T>> get(UUID guid);
	Response<? extends Collection<T>> get(ParameterInterface parameters);
	Response<URI> post(T entity);
	Response<? extends Item<T>> put (T entity, String guid);
	Response<? extends Item<T>> put (T entity, UUID guid);
}
