<?php

namespace Models;

use Exception;
use PDO;

class BookService
{
    protected PDO $pdo;

    public function __construct() {
        try {
            $pdoService = new PDOService();
            $this->pdo = $pdoService->getPdo();
            $this->useLibraryDB();
        } catch (Exception $e) {}
    }

    public function getRandomBook(): array|bool {
        $query = "SELECT * FROM books ORDER BY RAND() LIMIT 1;";
        $result = $this->pdo->query($query);
        return $result->fetch(PDO::FETCH_ASSOC);
    }

    public function getBookById($id): array|bool {
        $query = "SELECT * FROM books WHERE id=$id;";
        $result = $this->pdo->query($query);
        return $result->fetch(PDO::FETCH_ASSOC);
    }

    public function searchBook($search): array|\Models\TypedException
    {
        $search_value = htmlspecialchars($search);
        $query = "SELECT * FROM books";// WHERE title LIKE '%$search_value%' OR
                                         //   author LIKE '%$search_value%'";
        $result = $this->pdo->query($query);
        return $result->fetchAll(PDO::FETCH_ASSOC);
    }

    /*public function createBook(int $id, string $author, string $title, string $description,
                                int $year, string $language): Book {
        return new Book($id, $author, $title, $description, $year, $language);
    }*/

    private function useLibraryDB(): void {
        $query = "USE library";
        $this->pdo->query($query);
    }


}