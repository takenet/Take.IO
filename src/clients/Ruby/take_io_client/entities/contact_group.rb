module TakeIoClient
	module Entities
		class ContactGroup < Base
			attribute :id, String
			attribute :name, String
			attribute :owner, String
		end
	end
end