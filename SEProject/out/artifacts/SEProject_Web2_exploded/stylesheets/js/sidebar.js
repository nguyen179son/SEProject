var Sidebar = (function ($, window, document) {
    var sidebar = {};

    sidebar.checkExist = function (id) {
        console.log(id);
        var url ="/image/profile/" + id;
        console.log(url);
        $.get(url)
            .done(function () {
                console.log(1);
                return true;
            }).fail(function () {
            console.log(2);
            return false;
        });
    };

    sidebar.loadAva = function () {
        var userID = window.localStorage.getItem("userID");
        console.log(userID);
        if (sidebar.checkExist(userID)) {
            $("#sidebar-avatar").attr("src", "image/profile/" + userID);
        }
    };

    return sidebar;
}(window.jQuery, window, document));
$(document).ready(function () {


    $(function () {
        $("body").on("click", "#log-out", function () {
            window.localStorage.removeItem("token");
            window.location = "/";
        });
        $("body").onload = Sidebar.loadAva();

    });

});