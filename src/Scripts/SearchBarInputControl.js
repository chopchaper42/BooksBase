import {warn} from "/Scripts/Warning.js";

const input = document.querySelector('#search_bar');
const form = document.querySelector('#form');

input.addEventListener('invalid', showValidPattern);

function showValidPattern(event) {
    let warningText = 'Input should be 3 symbols at least and must not contain special symbols!';
    let existingWarns = document.querySelector(".warning");
    if (!existingWarns) {
        warn(warningText, form, "warning");
    }
    event.preventDefault();
}