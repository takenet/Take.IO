package br.com.takenet.takeio.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import br.com.takenet.takeio.entities.adapters.DateAdapter;

//Never send empty properties in JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {	
	private UUID id;
	private UUID schedule;
	private UUID owner;
	private String sender;
	private List<RecipientsResource> recipients;
	private List<ContactGroup> contactGroups;
	private String body;
	private String subject;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date time;
	private UUID wallet;
	private Boolean urgent;
	private String AckUri;
	private String ReplyUri;
	private String Ref;
	private String Folder;
	private String Status;
	private String Category;
	private String type;
	private Integer validity;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date created;
	private String updated;
	private String audioBase64;
	private Boolean async;
	private String specificId;
	private UUID idDomain;
	private String largeAccount;
	private Boolean read;
	
	public Message() {
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getSchedule() {
		return schedule;
	}
	public void setSchedule(UUID schedule) {
		this.schedule = schedule;
	}
	public UUID getOwner() {
		return owner;
	}
	public void setOwner(UUID owner) {
		this.owner = owner;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public List<RecipientsResource> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<RecipientsResource> recipients) {
		this.recipients = recipients;
	}
	public List<ContactGroup> getContactGroups() {
		return contactGroups;
	}
	public void setContactGroups(List<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public UUID getWallet() {
		return wallet;
	}
	public void setWallet(UUID wallet) {
		this.wallet = wallet;
	}
	public Boolean getUrgent() {
		return urgent;
	}
	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}
	public String getAckUri() {
		return AckUri;
	}
	public void setAckUri(String ackUri) {
		AckUri = ackUri;
	}
	public String getReplyUri() {
		return ReplyUri;
	}
	public void setReplyUri(String replyUri) {
		ReplyUri = replyUri;
	}
	public String getRef() {
		return Ref;
	}
	public void setRef(String ref) {
		Ref = ref;
	}
	public String getFolder() {
		return Folder;
	}
	public void setFolder(String folder) {
		Folder = folder;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getAudioBase64() {
		return audioBase64;
	}
	public void setAudioBase64(String audioBase64) {
		this.audioBase64 = audioBase64;
	}
	public Boolean getAsync() {
		return async;
	}
	public void setAsync(Boolean async) {
		this.async = async;
	}
	public String getSpecificId() {
		return specificId;
	}
	public void setSpecificId(String specificId) {
		this.specificId = specificId;
	}
	public UUID getIdDomain() {
		return idDomain;
	}
	public void setIdDomain(UUID idDomain) {
		this.idDomain = idDomain;
	}
	public String getLargeAccount() {
		return largeAccount;
	}
	public void setLargeAccount(String largeAccount) {
		this.largeAccount = largeAccount;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}

}
