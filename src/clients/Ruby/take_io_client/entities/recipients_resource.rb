module TakeIoClient
	module Entities
		class RecipientsResource < Base
			attribute :status, String
			attribute :updated, DateTime
			attribute :carrier, String
			attribute :duration, Integer
			attribute :price, Float
			attribute :attempts, Integer
			attribute :time, DateTime
		end
	end
end