module TakeIoClient
	module Entities
		class Message < Base
			attribute :id, String
			attribute :schedule, String
			attribute :owner, String
			attribute :sender, String
			attribute :recipients, RecipientsResource
			attribute :contactGroups, ContactGroup
			attribute :body, String
			attribute :subject, String
			attribute :time, DateTime
			attribute :wallet, String
			attribute :urgent, Boolean
			attribute :ackUri, String
			attribute :replyUri, String
			attribute :folder, String
			attribute :status, String
			attribute :category, String
			attribute :type, String
			attribute :validity, Integer
			attribute :created, DateTime
			attribute :updated, DateTime
			attribute :audioBase64, String
			attribute :async, Boolean
			attribute :specificId, String
			attribute :idDomain, String
			attribute :largeAccount, String
			attribute :read, Bool
		end
	end
end