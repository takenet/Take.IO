Her::API.setup url: 'http://api.take.io/rest/1.0' do |c|
  # Request
  c.use FaradayMiddleware::OAuth, {
  	consumer_key: 't3KJh2b5',
  	consumer_secret: 'fcPJ3TLi',
  	token: '7IwP+to8ES6PBjq2hact0X+h/YA=',
  	token_secret: 'wovtEfTd8YS0P8woEP0S/nAV1g8='
  }

  # Response
  c.use Her::Middleware::DefaultParseJSON

  # Adapter
  c.use Faraday::Adapter::NetHttp

  c.proxy 'http://127.0.0.1:8888'
end