require 'rubygems'
require 'bundler/setup'
Bundler.require

module TakeIoClient
  ENTITIES = ['Message']

  class << self
    attr_accessor :configuration
  end

  def self.configure
    self.configuration ||= Configuration.new
    yield(configuration)

    api = Her::API.setup url: "http://api.take.io/rest/1.0" do |c|
      # Request
      c.use FaradayMiddleware::OAuth, configuration.to_hash
      c.use FaradayMiddleware::EncodeJson

      # Response
      c.use TakeIoClient::ResponseParser

      # Adapter
      c.use Faraday::Adapter::NetHttp

      c.proxy 'http://127.0.0.1:8888'
    end

    ENTITIES.each do |e|
      "TakeIoClient::#{e}".constantize.use_api api
    end
  end

  class Configuration
    attr_accessor :consumer_key, :consumer_secret, :token, :token_secret

    def to_hash
      hash = {}

      instance_variables.each do |var| 
        hash[var.to_s.delete("@")] = instance_variable_get(var)
      end

      hash.symbolize_keys!
    end
  end
end

require_relative 'take_io_client/message'
require_relative 'take_io_client/response_parser'