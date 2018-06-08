
$(document).ready(function () {


    $("body").on("click", "#log-out", function () {
        window.localStorage.removeItem("token");
        window.location="/";
    })


});