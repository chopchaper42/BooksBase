<?php

namespace Models;

class TypedException extends \Exception
{
    public ExceptionType $errorType;
    public function __construct(ExceptionType $exceptionType, string $message = "", int $code = 0, ?Throwable $previous = null)
    {
        parent::__construct($message, $code, $previous);
        $this->errorType = $exceptionType;
    }
}