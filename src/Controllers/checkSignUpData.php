<?php

use Models\PDOService;
use Models\UserService;

$username = htmlspecialchars($_GET['username']);
$email = htmlspecialchars($_GET['email']);
$service = new UserService();
$pdo = null;
$error = false;
const CODES = [
    'BAD' => 500,
    'OK' => 200
];

try {
    $pdo = new PDOService();
} catch (\Exception $e) {
    sendRespond(CODES['BAD'], null);
}

if ($email) {
    $result = $service->emailAvailability($email);
    sendRespond(CODES['OK'], $result);
}

if ($username) {
    $result = $service->usernameAvailability($username);
    sendRespond(CODES['OK'], $result);
}


function sendRespond($code, $result): void {
    $respond = array(
        'status' => $code,
        'respond' => $result
    );
    http_response_code($code);
    print_r(json_encode($respond));
}