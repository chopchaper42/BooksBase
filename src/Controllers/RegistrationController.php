<?php

namespace Controllers;

use Models\User;
use Models\UserService;
use Views\View;

class RegistrationController implements Controller
{
    protected View $view;
    protected UserService $model;

    public function __construct()
    {
        $this->view = new View();
        $this->model = new UserService();
    }

    public function generateContent() {
        $this->model->registerUser();

        $email = $_POST['email'];
        $username = $_POST['username'];
        $send_mail = $_POST['send_mail'];

        $data = [
            'email' => $email,
            'username' => $username,
            'send_mail' => $send_mail
        ];

        $this->view->generateView(View::VIEWS['SIGNUP'], $data);
    }
}