<?php

namespace Controllers;

use Models\StudentService;
use PDOStatement;
use Views\View;
use Faker\Factory;

class ListController implements Controller
{
    protected View $view;
    protected StudentService $model;

    public function __construct()
    {
        $this->view = new View();
        $this->model = new StudentService();
    }

    public function generateContent(): void {
        $template_view = __DIR__ . $this->view::VIEWS['LIST'];
        $data = $this->model->getStudents();
        $this->view->generateView($template_view, $data->fetchAll());
    }

}