document.addEventListener("DOMContentLoaded", function() {
    var navbar = document.getElementById("navbar");
    var navControl = document.getElementById("nav-control");

    navControl.addEventListener("click", function() {
        navbar.classList.toggle("show");
    });
});