<?php


namespace Controllers;
use PDO;

class DBLogin
{
    public string $user = 'root';
    public string $pass = 'xL0EOOY4srPHROjB9JG5';
    public string $attr = "mysql:host=localhost;dbname=student_list;charset=utf8mb4";
    public array $opts =
        [
            PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES => false
        ];
}