<?php

namespace Src;
use Controllers\BookController;
use Controllers\Paths;
use Controllers\RandomBookController;
use Controllers\RegistrationController;
use Controllers\SearchController;

require_once __DIR__ . '/../vendor/autoload.php';


$request_parts = explode('?', $_SERVER['REQUEST_URI']);
$request = $request_parts[0];

switch ($request) {

    case '':
    case '/':
        require Paths::PATHS['Views'] . 'MainPageView.phtml';
        break;

    case '/signin':
        require Paths::PATHS['Views'] . 'SignInView.phtml';
        break;

    case '/signup':
        require Paths::PATHS['Views'] . 'SignUpView.phtml';
        break;

    case '/random':
        $controller = new RandomBookController();
        $controller->generateContent();
        break;

    case '/registration':
        $controller = new RegistrationController();
        $controller->generateContent();
        break;

    case '/book':
        $controller = new BookController();
        $controller->generateContent($_GET['id']);
        break;

    case '/search':
        $controller = new SearchController();
        $controller->generateContent($_GET['value']);
        break;

    case '/api/get_username':
        require Paths::PATHS['Controllers'] . 'checkSignUpData.php';
        break;

    default:
        http_response_code(404);
        echo "<p style='font-family: monospace'>404 Not Found</p>";
        break;
}
