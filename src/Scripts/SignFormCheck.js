import {removeWarnings, warn} from "/Scripts/Warning.js";

const container = document.querySelector(".container");
const username = document.querySelector("#username_input");
const password = document.querySelector("#password_input");
const email = document.querySelector('#mail_input');
const form = document.querySelector("#sign_in_form");
const confirmPassword = document.querySelector("#confirm_password_input");

const mailRegx = /[a-z0-9]+@[a-z0-9]+\.[a-z0-9]+/;
const badInputWarning = "warning";
const badUsernameWarning = "username_availability";
const badEmailWarning = "email_availability";

form.addEventListener("submit", checkValues);
if (email) {
    email.addEventListener('blur', checkEmailAvailability);
}
username.addEventListener('blur', checkUsernameAvailability);

function checkValues(event) {
    removeWarnings(`.${badInputWarning}`);
    
    checkIfPasswordsMatch();
    checkMail();
    checkUsername();
    checkPassword();

    if (warningsExist()) {
        event.preventDefault();
    }
}

function warningsExist() {
    let warnings = document.querySelectorAll(`.${badInputWarning}, .${badEmailWarning}, .${badUsernameWarning}`);
    return warnings.length !== 0;
}

function checkIfPasswordsMatch() {
    if (confirmPassword !== null) {
        if (confirmPassword.value !== password.value) {
            let warningText = 'Passwords do not match!';
            warn(warningText, container, badInputWarning);
        }
    }
}

function checkMail() {
    if (email !== null) {
        if (!email.value.toLowerCase().match(mailRegx)) {
            let warningText = 'Email should match example: email@server.domain';
            warn(warningText, container, badInputWarning);
        }
    }
}

function checkUsername() {
    if (username.value.length < 5) {
        let warningText = 'Username is too short!';
        warn(warningText, container, badInputWarning);
    }
}

function checkPassword() {
    if (password.value.length < 5) {
        let warningText = 'Password is too short!';
        warn(warningText, container, badInputWarning);
    }
}

function checkUsernameAvailability() {
    const url = `/api/get_username?username=${username.value}`;
    const method = 'post';
    const warningText = 'Sorry, this username isn\'t available.';
    if (username.value !== '') {
        sendHttpRequest(method, url, warningText, badUsernameWarning);
    }
}

function checkEmailAvailability() {
    const url = `/api/get_username?email=${email.value}`;
    const method = 'post';
    const warningText = 'There is already an account with this email.';
    if (email.value !== '') {
        sendHttpRequest(method, url, warningText, badEmailWarning);
    }
}

function sendHttpRequest(method, url, warningText, warningType) {
    const xhr = new XMLHttpRequest();

    xhr.open(method, url, true);
    xhr.responseType = 'json';

    xhr.addEventListener('readystatechange', () => {
        removeWarnings(`.${warningType}`);
        let data = xhr.response;
        if (xhr.readyState === 4 && xhr.status === 500) {
            removeWarnings('.warning');
            warn("Sorry, server isn't available now. Try later.", container, badInputWarning);

        } else if (data && !data['response']) {
            warn(warningText, container, warningType);
        }
    });
    xhr.send();
}