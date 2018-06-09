var Sidebar = (function ($, window, document) {
    var sidebar = {};


    sidebar.loadAva = function () {
        $("#sidebar-avatar").attr("src",window.localStorage.getItem("profile_picture"));
    };

    return sidebar;
}(window.jQuery, window, document));
$(document).ready(function () {


    $(function () {
        $("body").onload = Sidebar.loadAva();
        $("body").on("click", "#log-out", function () {
            window.localStorage.removeItem("token");
            window.location = "/";
        });

    });

});