const form = document.querySelector("#form");

if (window.location.href.includes("value=")) {
    showSearchResults();
}

function showSearchResults() {
    const searchResults = document.querySelector('#search_results_container');
    searchResults.classList.remove("hidden");
}