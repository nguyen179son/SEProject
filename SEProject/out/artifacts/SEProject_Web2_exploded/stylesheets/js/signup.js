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
                console.log(response);
                if (response["success"]) {
                    window.location = "/"
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