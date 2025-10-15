let darkmode = localStorage.getItem('darkmode');
const themeSwitch = document.getElementById("theme-switch");

const enableDarkMode = () => {
    document.body.classList.remove("main-page");
    document.body.classList.add("dark-main-page");
    document.body.classList.add("darkmode");
    localStorage.setItem("darkmode", "active");
}

const disableDarkMode = () => {
    document.body.classList.remove("darkmode");
    document.body.classList.remove("dark-main-page");
    document.body.classList.add("main-page");
    localStorage.setItem("darkmode", null);
}

if(darkmode === "active") enableDarkMode();
themeSwitch.addEventListener("click", () => {
    darkmode = localStorage.getItem("darkmode");
    darkmode !== "active" ? enableDarkMode() : disableDarkMode()
});