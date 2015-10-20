package br.com.takenet.takeio.client.messages;

import static org.junit.Assert.*;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;
import br.com.takenet.takeio.client.RestClient;
import br.com.takenet.takeio.client.MessageParameters;
import br.com.takenet.takeio.client.Response;
import br.com.takenet.takeio.entities.Message;
import br.com.takenet.takeio.entities.MessageCollection;
import br.com.takenet.takeio.entities.MessageItem;
import br.com.takenet.takeio.entities.RecipientsResource;

public class MessageTest {
	// Take.io RestClient
	private RestClient client;
	// Date Format
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	// put your take.io consumer key here
	public static final String CONSUMER_KEY = "";
	// put your take.io consumer secret here
	public static final String CONSUMER_SECRET = "";
	// put your take.io token here
	public static final String ACCESS_TOKEN = "";
	// put your take.io consumer secret here
	public static final String TOKEN_SECRET = "";
	// put a phone number for test purpose
	public static final String PHONE_NUMBER = "tel:+5531";
	public static final String MESSAGE_TEST_1 = "Teste de envio client java";
	public static final String UUID_TEST_GET = "";

	@Before
	public void setUp() throws Exception {
		client = new RestClient(CONSUMER_KEY, CONSUMER_SECRET,
				ACCESS_TOKEN, TOKEN_SECRET);
	}

	@Test
	public void listMessages() {
		MessageParameters parameters = new MessageParameters();
        parameters.addFilter("folder", "sent");
		Response<MessageCollection> response = client.messages.listMessages(parameters);
		assertEquals(response.getStatus(), Status.OK);
	}
	
	@Test
	public void getMessage() {
		UUID guid = UUID.fromString(UUID_TEST_GET);
		Response<MessageItem> response = client.messages.getMessage(guid);
		
		assertEquals(response.getStatus(), Status.OK);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void sendMessageFromObject() {
		Message message = new Message();
		message.setSender(PHONE_NUMBER);
		message.setBody("sendMessageFromObject: " + MESSAGE_TEST_1);
		message.setType("sms");
		message.setRecipients(new ArrayList<RecipientsResource>() {{ 
			add(new RecipientsResource(PHONE_NUMBER)); 
		}});
		
		Response<URI> response = client.messages.sendMessage(message);
		assertEquals(response.getStatus(), Status.CREATED);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void sendMessageScheduled() throws ParseException {
		Message message = new Message();
		message.setSender(PHONE_NUMBER);
		message.setBody("sendMessageScheduled: " + MESSAGE_TEST_1);
		message.setType("sms");
		message.setRecipients(new ArrayList<RecipientsResource>() {{ 
			add(new RecipientsResource(PHONE_NUMBER)); 
		}});
		
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, 5);
		Date futureDate = cal.getTime();
		
		Response<URI> response = client.messages.sendSchedulledMessage(message, futureDate);
		assertEquals(response.getStatus(), Status.CREATED);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void updateMessageScheduled() throws ParseException, InterruptedException {
		Message message = new Message();
		message.setSender(PHONE_NUMBER);
		message.setBody("updateMessageScheduled: " + MESSAGE_TEST_1);
		message.setType("sms");
		message.setRecipients(new ArrayList<RecipientsResource>() {{ 
			add(new RecipientsResource(PHONE_NUMBER)); 
		}});
		
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MINUTE, 2);
		Date futureDate = cal.getTime();
		
		Response<URI> response = client.messages.sendSchedulledMessage(message, futureDate);
		assertEquals(response.getStatus(), Status.CREATED);
		
		String createdUuid = Response.getUUIDComponent(response.getContent());
		Message updateMessage = new Message();
		
		updateMessage.setBody(String.format("Update message %s", df.format(futureDate)));
		Thread.sleep(2000);
		Response<MessageItem> reponseUpdated = client.messages.updateMessage(updateMessage, createdUuid);
		assertEquals(reponseUpdated.getStatus(), Status.OK);
	}

}
