class TakeIoClient::ResponseParser < Faraday::Response::Middleware
  def on_complete(env)
    json = MultiJson.load(env[:body], symbolize_keys: true)

    data = if json[:entry].present?
      json[:entry]
    elsif json[:result].present?
      json[:result][:entry]
    elsif json[:location].present?      
      id = json[:location].split('/').last
      msg = TakeIoClient::Message.find(id)
      msg.attributes
    end

    env[:body] = {
      data: data,
      errors: json[:errors] || {},
      metadata: json[:metadata] || {}
    }
  end
end