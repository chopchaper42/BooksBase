<?php

namespace Models;

use Exception;
use PDO;
use PDOException;

class BookService
{
    protected PDO $pdo;

    public function getRandomBook(): array|bool {
        $pdo = null;
        try {
            $pdo = PDOService::getPdo();
        } catch (PDOException $e) {
            return false;
        }
        $query = "SELECT * FROM books ORDER BY RAND() LIMIT 1;";
        $result = $pdo->query($query);
        return $result->fetch(PDO::FETCH_ASSOC);
    }

    public function getBookById($id): array|bool {
        $pdo = null;
        try {
            $pdo = PDOService::getPdo();
        } catch (Exception $e) {
            return false;
        }
        $query = "SELECT * FROM books WHERE id=$id;";
        $result = $pdo->query($query);
        return $result->fetch(PDO::FETCH_ASSOC);
    }

    public function searchBook($search): array|bool
    {
        $pdo = null;
        try {
            $pdo = PDOService::getPdo();
        } catch (Exception $e) {
            return false;
        }
        $search_value = htmlspecialchars($search);
        $query = "SELECT * FROM books";// WHERE title LIKE '%$search_value%' OR
                                         //   author LIKE '%$search_value%'";
        $result = $pdo->query($query);
        return $result->fetchAll(PDO::FETCH_ASSOC);
    }

    private function useLibraryDB(): bool {
        $pdo = null;
        try {
            $pdo = PDOService::getPdo();
        } catch (Exception $e) {
            return false;
        }
        $query = "USE library";
        try {
            $this->pdo->query($query);
            return true;
        } catch (Exception $e) {
            return false;
        }
    }

}