var Sidebar = (function ($, window, document) {
    var sidebar = {};

    sidebar.checkExist = function(id)
    {
        var img = new Image();
        console.log(id);
        img.src = "/image/profile/"+id;
        console.log(img.height);
        return img.height != 0;
    };

    sidebar.loadAva = function () {
        var userID = window.localStorage.getItem("userID");
        console.log(userID);
        console.log(sidebar.checkExist(userID));
        if (sidebar.checkExist(userID)) {
            $("#sidebar-avatar").attr("src", "image/profile/" + userID);
        }
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