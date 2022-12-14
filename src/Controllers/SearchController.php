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
        try {
            $this->model = new BookService();
        } catch (\Exception $e) {
            $this->error = true;
        }
    }

    public function generateContent($search)
    {
        if (!$this->error) {
            $data = $this->model->searchBook($search);
            $error = !$data;
            $this->view->generateView(View::VIEWS['MAIN'], $data, $this->error);
        }
    }
}