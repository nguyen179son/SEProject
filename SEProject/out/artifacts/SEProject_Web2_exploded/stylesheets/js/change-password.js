$.ajax({
    url: "/verify-token",
    async: false,
    type: "post",
    data: {
        token: window.localStorage.getItem("token")
    },
    success: function (response) {
        if (!response["verify_token"]) {
            window.location = "/login"
        }
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert("Error");
    }
});
$(document).ready(function () {

    var Update = (function ($, window, document) {
        var update = {};

        update.changePassword = function () {
            $("#pleaseWaitDialog").modal();
            $.ajax({
                url: "/change-password",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    current_password: $("#old-password").val(),
                    new_password: $("#new-password").val(),
                    confirn_password: $("#confirm-new-password").val(),
                },
                success: function (response) {
                    // alert("Data: " + JSON.stringify(response));
                    console.log(response);
                    if (response["success"]) {
                        if (response["confirm"]) {
                        }
                    }
                    else {
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error");
                }
            });
            $("#pleaseWaitDialog").modal('hide');
        };
        return update;
    }(window.jQuery, window, document));

    $(function () {
        $("body").on("click","#update-password",function () {
           Update.changePassword();
        });
    });
});