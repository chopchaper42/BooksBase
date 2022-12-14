export const storage = window.localStorage;
export const theme = document.getElementById("theme-link");
export const stylesPath = "/Styles/";
export const themeButton = document.getElementById("theme-change-button");
let currentTheme = storage.getItem('theme');


if (currentTheme) {
    theme.href = stylesPath + currentTheme + ".css";
    if (themeButton) {
        themeButton.value = currentTheme;
        themeButton.textContent = currentTheme.charAt(0).toUpperCase() + currentTheme.slice(1);
    }
}
