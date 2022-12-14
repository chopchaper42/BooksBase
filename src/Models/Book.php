<?php

namespace Models;

class Book
{
    protected int $id;
    protected string $title;
    protected string $author;
    protected int $year;
    protected string $language;
    protected string $description;

    public function __construct(int $id, string $author, string $title, string $description,
                                int $year, string $language)
    {
        $this->id = $id;
        $this->author = $author;
        $this->title = $title;
        $this->year = $year;
        $this->description = $description;
        $this->language = $language;
    }
}