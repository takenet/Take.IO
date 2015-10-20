<?php

namespace TakeIoClient\Entities;

use TakeIoClient\Entities\Base;

class Message extends Base
{
    public $id;
    public $schedule;
    public $owner;
    public $sender;
    public $recipients;
    public $contactGroups;
    public $body;
    public $subject;
    public $time;
    public $wallet;
    public $urgent;
    public $ackUri;
    public $replyUri;
    public $folder;
    public $status;
    public $category;
    public $type;
    public $validity;
    public $created;
    public $updated;
    public $audioBase64;
    public $async;
    public $specificId;
    public $idDomain;
    public $largeAccount;
    public $read;
}
