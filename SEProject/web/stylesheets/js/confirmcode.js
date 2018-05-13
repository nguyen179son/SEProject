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

    $("body").on("click", "#confirm_button", function () {
        $.ajax({
            url: "http://localhost:8080/check-confirmation-code",
            type: "post",
            data: {
                email: $("#email").val(),
                confirmation_code: $("#confirmation_code").val()
            },
            success: function (response) {
                if (response["success"]) {
                    window.localStorage.setItem("token", response["token"]);
                    window.location = "http://localhost:8080"
                }
                else {
                    $("#error").show();
                    $("#error").html("Wrong confirm code");
                }


            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    });
});