function createWarning(text, className) {
    const warningText = document.createElement("p");
    warningText.textContent = "⚠️" + text;
    warningText.classList.add(className);
    return warningText;
}

export function removeWarnings(selector) {
    while (true) {
        let warning = document.querySelector(selector);
        if (!warning)
            return;
        else
            warning.remove();
    }
}

export function warn(text, element, className) {
    let warning = createWarning(text, className);
    element.after(warning);
}