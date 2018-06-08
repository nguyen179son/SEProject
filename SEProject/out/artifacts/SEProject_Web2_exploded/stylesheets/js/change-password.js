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
                    confirm_password: $("#confirm-new-password").val(),
                },
                success: function (response) {
                    // alert("Data: " + JSON.stringify(response));
                    if (response["verify_token"]) {
                        console.log(response);
                        if (response["success"]) {
                            var time = 1000;
                            if (response["valid"]) {
                                $("#error").hide()
                                $("#alert").html("Password updated successfully");
                                $("#alert").show();
                                $('#alert').delay(1000).fadeOut(400);
                                $("#content input[type=password]").val("");

                            }
                            else {
                                var htmlText = "";
                                response["error_message"].forEach(function (errorMess, i, array) {
                                    if (i < array.length - 1) {
                                        htmlText += errorMess + "<br>";
                                    }
                                    else {
                                        htmlText += errorMess;
                                    }

                                });
                                $("#error").html(htmlText);

                                $("#error").show();
                            }
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
        $("body").on("click", "#update-password", function () {
            Update.changePassword();
        });
    });
});