<?php

namespace Models;

use Controllers\DBLogin;
use Exception;
use PDO;
use PDOException;

class PDOService /* Connects to the Database using PDO object. Creates PDO obj. using data from DBLogin class. */
{
    protected PDO $pdo;

    /**
     * @throws Exception
     */
    public function __construct()
    {
        $db_login = new DBLogin();
        try {
            $this->connectToDB($db_login->attr, $db_login->user, $db_login->pass, $db_login->opts);
            $this->pdo->query("USE library;");
        } catch (Exception $e) {
            throw new Exception("Cannot connect to the database");
        }
    }

    public function getPdo(): PDO
    {
        return $this->pdo;
    }

    private function connectToDB($attr, $user, $pass, $opts) {
        try {
            $this->pdo = new PDO($attr, $user, $pass, $opts);
        } catch (PDOException $e) {
            throw new PDOException($e->getMessage(), $e->getCode());
        }
    }

}