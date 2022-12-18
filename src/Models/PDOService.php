<?php

namespace Models;

use Controllers\DBLogin;
use Exception;
use PDO;
use PDOException;

class PDOService /* Connects to the Database using PDO object. Creates PDO obj. using data from DBLogin class. */
{
    public static function getPdo(): PDO
    {
        try {
            $pdo = PDOService::createPDO();
            $query = "USE library";
            $pdo->query($query);
            return $pdo;
        } catch (Exception $e) {
            throw new PDOException($e->getMessage(), $e->getCode());
        }
    }

    private static function createPDO(): PDO {
        $db_login = new DBLogin();
        try {
            return new PDO($db_login->attr, $db_login->user, $db_login->pass, $db_login->opts);
        } catch (PDOException $e) {
            throw new PDOException($e->getMessage(), $e->getCode());
        }
    }

}