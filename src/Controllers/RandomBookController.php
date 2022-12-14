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
        $error = boolval($data);

        $this->view->generateView(View::VIEWS['BOOK'], $data, $error);
        header("Location: /book?id={$data['id']}", true, 301);
    }
}