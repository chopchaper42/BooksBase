import {storage, theme, themeButton, stylesPath} from "/Scripts/loadTheme.js";


themeButton.addEventListener('click', changeTheme);

function changeTheme() {
    if (themeIsLight()) {
        initDarkTheme();
        storage.setItem('theme', 'dark');
    } else {
        initLightTheme();
        storage.setItem('theme', 'light');
    }
}

function themeIsLight() {
    return themeButton.value === "light";
}

function initDarkTheme() {
    themeButton.value = "dark";
    themeButton.textContent = "Dark";
    theme.href = stylesPath + "dark.css";
}

function initLightTheme() {
    themeButton.value = "light";
    themeButton.textContent = "Light";
    theme.href = stylesPath + "light.css";
}