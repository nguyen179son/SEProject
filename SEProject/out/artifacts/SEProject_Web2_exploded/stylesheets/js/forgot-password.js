$(document).ready(function () {
    $("body").on('load', function () {
        $.ajax({
            url: "http://localhost:8080/verify-token",
            type: "post",
            data: {
                token: window.localStorage.getItem("token")
            },
            success: function (response) {
                if (response["success"]) {
                    if (response["verify_token"]) {
                        window.location = "http://localhost:8080"
                    }
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    });

    $("body").on("click", "#reset-password", function () {
        $.ajax({
            url: "http://localhost:8080/get-new-password",
            type: "post",
            data: {
                email: $("#email").val(),
                confirm_code: $("#confirm_code").val(),
                password: $("#password").val(),
                confirm_password: $("#confirm_password").val()
            },
            success: function (response) {
                if (response["success"]) {
                    window.location = "http://localhost:8080/login"
                }
                else {
                    $("#error").show();
                    $("#error").html(response.error_message);
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    });

    $("body").on("click", "#send-confirm-code", function (e) {
        e.preventDefault();
        $.ajax({
            url: "http://localhost:8080/send-confirm-email",
            type: "post",
            data: {
                email: $("#email").val()
            },
            success: function (response) {
                $("#send-confirm-code").hide();
                $("#sent-code").show();

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    })
});