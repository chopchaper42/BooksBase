<?php

namespace Models;

use Exception;
use PDO;
use PDOStatement;

class StudentService
{
    protected PDO $pdo;

    /**
     * @throws Exception
     */
    public function __construct() {
        try {
            $pdoService = new PDOService();
            $this->pdo = $pdoService->getPdo();
        } catch (Exception $e) {
            throw new Exception("Cannot connect to database");
        }
    }

    public function getStudents(): PDOStatement
    {
        return $this->getAllStudentsFromDB();
    }

    public function getAllStudentsFromDB(): PDOStatement{
        $query = "SELECT * FROM students";
        return $this->pdo->query($query);
    }

    public function addStudentToDB(string $name, string $lastName, string $sex, string $groupNumber,
                                   string $email, int $points, int $birthYear, bool $local): void {
        $query = $this->pdo->prepare("INSERT INTO students(name, lastName, sex, groupNumber, email, points, yearOfBirth, isLocal) " .
                        "VALUES(:name, :lastname, :sex, :groupnumber, :email, :points, :birthyear, :local)");

        $query->bindParam(':name', $name);
        $query->bindParam(':lastname', $lastName);
        $query->bindParam(':sex', $sex);
        $query->bindParam(':groupnumber', $groupNumber);
        $query->bindParam(':email', $email);
        $query->bindParam(':points', $points);
        $query->bindParam(':birthyear', $birthYear);
        $query->bindParam(':local', $local);
        $query->execute();
    }
}