package br.com.takenet.takeio.client;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import br.com.takenet.takeio.entities.EntitiesUtil;
import br.com.takenet.takeio.entities.Message;
import br.com.takenet.takeio.entities.MessageCollection;
import br.com.takenet.takeio.entities.MessageItem;
import br.com.takenet.takeio.entities.RecipientsResource;

public class MessagesClient implements ResourceOperations<Message, MessageParameters> {
	private RestClientInterface client;
	public static final String PATH = "messages";

	/*
	 * package friendly
	 * Only br.com.takenet.takeio.client.RestClient can instantiate
	 */
	MessagesClient(RestClientInterface client) {
		this.client = client;
	}

	public Response<MessageCollection> listMessages(MessageParameters parameters) {
		return get(parameters);
	}

	public Response<MessageItem> getMessage(String guid) {
		return get(guid);
	}

	public Response<MessageItem> getMessage(UUID guid) {
		return get(guid);
	}

	public Response<URI> sendMessage(Message message) {
		return post(message);
	}

	public Response<URI> sendMessage(List<RecipientsResource> recipients, String sender, String body) {
		Message message = new Message();
		message.setSender(sender);
		message.setBody(body);
		message.setRecipients(recipients);
		message.setType("sms");
		return post(message);
	}

	public Response<URI> sendSchedulledMessage(Message message, Date datetime) {
		message.setTime(datetime);
		return post(message);
	}

	public Response<URI> sendSchedulledMessage(List<RecipientsResource> recipients, String sender, String body,
			Date datetime) {
		Message message = new Message();
		message.setSender(sender);
		message.setBody(body);
		message.setRecipients(recipients);
		message.setTime(datetime);
		message.setType("sms");
		return post(message);
	}

	public Response<MessageItem> updateMessage(Message message, UUID guid) {
		return put(message, guid);
	}

	public Response<MessageItem> updateMessage(Message message, String guid) {
		return put(message, guid);
	}

	@Override
	public Response<MessageItem> get(String guid) {
		return get(EntitiesUtil.uuidFromString(guid));
	}

	@Override
	public Response<MessageItem> get(UUID guid) {
		return client.get(MessageItem.class, PATH, guid);
	}

	@Override
	public Response<MessageCollection> get(MessageParameters parameters) {
		return client.get(MessageCollection.class, PATH, parameters.getParametersList());
	}

	@Override
	public Response<URI> post(Message message) {
		MessageItem item = new MessageItem();
		item.setEntry(message);
		return client.post(MessageItem.class, PATH, item);
	}

	@Override
	public Response<MessageItem> put(Message message, String guid) {
		return put(message, EntitiesUtil.uuidFromString(guid));
	}

	@Override
	public Response<MessageItem> put(Message message, UUID guid) {
		MessageItem item = new MessageItem();
		item.setEntry(message);
		return client.put(MessageItem.class, PATH, item, guid);
	}

}
