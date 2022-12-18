<?php

namespace Controllers;

use Models\BookService;
use Models\PDOService;
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

    public function generateContent($id) {
        $data = $this->model->getBookById($id);
        $this->view->generateView(View::VIEWS['BOOK'], $data);
    }


}