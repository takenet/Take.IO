<?php

namespace TakeIoClient\Entities;

class Base
{
    public function __construct($params)
    {
    	$this->build($params);
    }

    public function build($params)
    {
        foreach ((array) $params as $property => $value) {
            $this->$property = $value;
        }
    }
}
