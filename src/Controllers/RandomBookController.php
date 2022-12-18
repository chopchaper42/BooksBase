<?php

namespace Controllers;

use Models\BookService;
use Views\View;

class RandomBookController implements Controller
{
    protected View $view;
    protected BookService $model;

    public function __construct()
    {
        $this->view = new View();
        $this->model = new BookService();
    }

    public function generateContent()
    {

        $data = $this->model->getRandomBook();

        $this->view->generateView(View::VIEWS['BOOK'], $data);
        header("Location: /book?id={$data['id']}", true, 301);
    }
}