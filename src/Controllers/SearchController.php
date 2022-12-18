<?php

namespace Controllers;

use Models\BookService;
use Views\View;

class SearchController implements Controller
{
    protected BookService $model;
    protected View $view;
    protected bool $error = false;

    public function __construct()
    {
        $this->view = new View();
        $this->model = new BookService();
    }

    public function generateContent($search)
    {
        $data = $this->model->searchBook($search);
        $this->view->generateView(View::VIEWS['MAIN'], $data);
    }
}