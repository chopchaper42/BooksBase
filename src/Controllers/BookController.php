<?php

namespace Controllers;

use Models\BookService;
use PDOStatement;
use Views\View;

class BookController implements Controller
{
    protected BookService $model;
    protected View $view;

    public function __construct()
    {
        $this->model = new BookService();
        $this->view = new View();
    }

    public function generateContent(int $id) {
        $error = false;

        $data = $this->model->getBookById($id);
        $error = !$data;

        $this->view->generateView(View::VIEWS['BOOK'], $data, $error);
    }


}