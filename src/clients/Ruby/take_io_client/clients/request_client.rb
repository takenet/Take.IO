require 'rubygems'
require 'bundler/setup'
Bundler.require

# require_relative '../initializers/her.rb'
# require_relative 'messages_client'

require_relative '../entities/message'

# class RequestClient
# 	attr_reader :messages

# 	def initialize()
# 		@messages = MessagesClient.new()
# 	end
# end

# client = RequestClient.new()
# puts client.messages.get_message("f8db41a5-c32f-4e6a-bb83-eb23b73489e6")

binding.pry