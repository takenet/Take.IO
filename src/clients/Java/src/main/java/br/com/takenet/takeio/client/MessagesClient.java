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

public class MessagesClient implements ResourceOperations<Message> {
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
		return client.get(MessageCollection.class, PATH, parameters.getParametersList());
	}

	public Response<MessageItem> getMessage(String guid) {
		return getMessage(EntitiesUtil.uuidFromString(guid));
	}

	public Response<MessageItem> getMessage(UUID guid) {
		return client.get(MessageItem.class, PATH, guid);
	}

	public Response<URI> sendMessage(Message message) {
		MessageItem item = new MessageItem();
		item.setEntry(message);
		return client.post(MessageItem.class, PATH, item);
	}

	public Response<URI> sendMessage(List<RecipientsResource> recipients, String sender, String body) {
		Message message = new Message();
		message.setSender(sender);
		message.setBody(body);
		message.setRecipients(recipients);
		message.setType("sms");
		return sendMessage(message);
	}

	public Response<URI> sendSchedulledMessage(Message message, Date datetime) {
		message.setTime(datetime);
		return sendMessage(message);
	}

	public Response<URI> sendSchedulledMessage(List<RecipientsResource> recipients, String sender, String body,
			Date datetime) {
		Message message = new Message();
		message.setSender(sender);
		message.setBody(body);
		message.setRecipients(recipients);
		message.setTime(datetime);
		message.setType("sms");
		return sendMessage(message);
	}

	public Response<MessageItem> updateMessage(Message message, UUID guid) {
		MessageItem item = new MessageItem();
		item.setEntry(message);
		return client.put(MessageItem.class, PATH, item, guid);
	}

	public Response<MessageItem> updateMessage(Message message, String guid) {
		return updateMessage(message, EntitiesUtil.uuidFromString(guid));
	}

	@Override
	public Response<MessageItem> get(String guid) {
		Response<MessageItem> response = getMessage(guid);
		return response;
	}

	@Override
	public Response<MessageItem> get(UUID guid) {
		return getMessage(guid);
	}

	@Override
	public Response<MessageCollection> get(ParameterInterface parameters) {
		return client.get(MessageCollection.class, PATH, parameters.getParametersList());
	}

	@Override
	public Response<URI> post(Message message) {
		return sendMessage(message);
	}

	@Override
	public Response<MessageItem> put(Message message, String guid) {
		return updateMessage(message, guid);
	}

	@Override
	public Response<MessageItem> put(Message message, UUID guid) {
		return updateMessage(message, guid);
	}
}
