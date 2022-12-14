<?php

namespace Models;

use PDO;
use \Exception;

class UserService
{
    protected PDO $pdo;

    /**
     * @throws Exception
     */
    public function __construct()
    {
        try {
            $pdoService = new PDOService();
            $this->pdo = $pdoService->getPdo();
            $this->useLibraryDB();
        } catch (Exception $e) {
            throw new Exception("Cannot connect to database");
        }

    }

    public function registerUser(): void{
        $values = [
            'username' => htmlspecialchars($_POST['username']),
            'hash' => password_hash($_POST['password'], PASSWORD_DEFAULT),
            'email' => htmlspecialchars($_POST['email'])
        ];
        $statement = $this->pdo->prepare("INSERT INTO users(username, hash, email)" .
            "VALUES(:username, :hash, :email)");
        $statement->execute($values);
    }

    public function usernameAvailability(string $username): bool {
        $username = htmlspecialchars($usr);
        $query = "SELECT * FROM users WHERE username='{$username}'";
        $query_result = $this->pdo->query($query);
        return empty($query_result->fetchAll());
    }

    public function emailAvailability(string $email): bool {
        $email = htmlspecialchars($email);
        $query = "SELECT * FROM users WHERE email='{$email}'";
        $query_result = $this->pdo->query($query);
        return empty($query_result->fetchAll());
    }

    public function signin() {
        $username = htmlspecialchars($_POST['username']);
        $password = htmlspecialchars($_POST['password']);
        $query = "SELECT * FROM users WHERE username='{$username}'";
        $statement = $this->pdo->prepare($query);
        $statement->execute();
        $user = $statement->fetch();
        if (empty($user)) {

        }
    }

    private function useLibraryDB(): void {
        $query = "USE library";
        $this->pdo->query($query);
    }
}