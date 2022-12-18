<?php

namespace Views;

use Models\TypedException;

class View
{
    public const VIEWS = [
        'SEARCH' => './Templates/SearchTemplate.phtml',
        'SIGNUP' => 'SignUpView.phtml',
        'SIGNIN' => 'SignInView.php',
        'PROFILE' => 'StudentsProfileView.php',
        'BOOK' => './Templates/BookTemplate.phtml',
        'MAIN' => 'MainPageView.phtml'
    ];

    public function generateView($template_view, $data): void {
        require_once $template_view;
    }
}