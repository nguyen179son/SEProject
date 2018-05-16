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

    $("body").on("click", "#login_button", function () {
        $.ajax({
            url: "http://localhost:8080/login",
            type: "post",
            data: {
                email: $("#email").val(),
                password: $("#password").val()
            },
            success: function (response) {
                // alert("Data: " + JSON.stringify(response));
                console.log(response);
                if (response["success"]) {
                    if (response["confirm"]) {
                        window.localStorage.setItem("token", response["token"]);
                        window.location = response["redirect_url"];
                    }

                    window.location = response["redirect_url"] + "?email=" + $("#email").val();
                }
                else {
                    $("#message").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    })


});