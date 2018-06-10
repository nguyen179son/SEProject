$.ajax({
    url: "/verify-token",
    async:false,
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

    $("body").on("click", "#reset-password", function () {
        $.ajax({
            url: "/get-new-password",
            type: "post",
            data: {
                email: $("#email").val(),
                confirm_code: $("#confirm_code").val(),
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
            url: "/send-confirm-email",
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