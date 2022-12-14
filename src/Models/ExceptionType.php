<?php

namespace Models;

enum ExceptionType
{
    case DatabaseUnavailable;
    case InvalidInput;
}
