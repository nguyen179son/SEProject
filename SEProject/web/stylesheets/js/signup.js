$.ajax({
    url: "/verify-token",
    async: false,
    type: "post",
    data: {
        token: window.localStorage.getItem("token")
    },
    success: function (response) {
        if (response["verify_token"]) {
            window.location = "/"
        }
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert("Error");
    }
});

$(document).ready(function () {

    $("body").on("click", "#signupButton", function () {
        $.ajax({
            url: "/signup",
            type: "post",
            data: {
                email: $("#email").val(),
                name: $("#name").val(),
                password: $("#password").val(),
                confirm_password: $("#confirm_password").val()
            },
            success: function (response) {
                if (response["success"]) {
                    if (response["valid"]) {
                        window.location = "/login"
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
                else {
                    alert("Internal error");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    });
});