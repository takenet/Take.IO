<?php

namespace TakeIoClient\Clients;

use GuzzleHttp\Client;
use GuzzleHttp\HandlerStack;
use GuzzleHttp\Subscriber\Oauth\Oauth1;

use TakeIoClient\Clients\MessagesClient;

class TakeIoClient
{
    private $_httpClient;
    public $messages;

    public function __construct($params)
    {
        $stack = HandlerStack::create();

        $middleware = new Oauth1([
            'consumer_key'    => $params['consumer_key'],
            'consumer_secret' => $params['consumer_secret'],
            'token'           => $params['token'],
            'token_secret'    => $params['token_secret']
        ]);

        $stack->push($middleware);

        $this->_httpClient = new Client([
            'base_uri' => 'http://api.take.io/rest/1.0/',
            'handler' => $stack,
            'proxy' => 'http://127.0.0.1:8888'
        ]);

        $this->messages = new MessagesClient($this->_httpClient);
    }

    public function messages()
    {
        return $this->messages;
    }
}
