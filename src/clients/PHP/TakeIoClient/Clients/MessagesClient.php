<?php

namespace TakeIoClient\Clients;

use TakeIoClient\Entities\Message;

class MessagesClient
{
    private $_httpClient;
    const RESOURCE = 'messages';

    public function __construct($_httpClient) 
    {
        $this->_httpClient = $_httpClient;
    }

    public function listMessages()
    {
        $response = $this->_httpClient->get(self::RESOURCE, ['auth' => 'oauth']);
        $parsed_response = json_decode($response->getBody());

        return array_map(function ($msg) {
            return new Message($msg);
        }, $parsed_response->result->entry);
    }

    public function getMessage($guid)
    {
        $response = $this->_httpClient->get(self::RESOURCE . "/{$guid}", ['auth' => 'oauth']);
        $parsed_response = json_decode($response->getBody());

        return new Message($parsed_response->entry);
    }

    public function sendMessage($msg)
    {
        $response = $this->_httpClient->post(self::RESOURCE, [
            'auth' => 'oauth', 
            'content-type' => 'application/json',
            'body' => json_encode(['entry' => array_merge($msg, ['type' => 'sms'])])
        ]);

        return json_decode($response->getBody());
    }

    public function sendSchedulledMessage($msg, $datetime)
    {
        $formatted_datetime = $datetime->format('Y-m-d') . "T" . $datetime->format('H:i:s');

        $response = $this->_httpClient->post(self::RESOURCE, [
            'auth' => 'oauth', 
            'content-type' => 'application/json',
            'body' => json_encode(['entry' => array_merge($msg, ['type' => 'sms', 'time' => $formatted_datetime])])
        ]);

        return json_decode($response->getBody());
    }

    public function updateMessage($guid, $msg)
    {
        $response = $this->_httpClient->put(self::RESOURCE . "/{$guid}", [
            'auth' => 'oauth', 
            'content-type' => 'application/json',
            'body' => json_encode(['entry' => $msg])
        ]);

        $parsed_response = json_decode($response->getBody());

        return new Message($parsed_response->entry);
    }
}