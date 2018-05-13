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

    $("body").on("click", "#signupButton", function () {
        $.ajax({
            url: "http://localhost:8080/signup",
            type: "post",
            data: {
                email: $("#email").val(),
                name: $("#name").val(),
                password: $("#password").val(),
                confirm_password: $("#confirm_password").val()
            },
            success: function (response) {
                console.log(response);
                if (response["success"]) {
                    window.location = "http://localhost:8080"
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
});