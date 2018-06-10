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


    $("body").on("click", "#login_button", function () {
        window.localStorage.setItem("email", $("#email").val());
        $("#pleaseWaitDialog").modal();
        $.ajax({
            url: "/login",
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
                        window.localStorage.setItem("userID",response["userID"]);
                        window.localStorage.setItem("profile_picture",response["profile_picture"]);
                    }
                    else {
                        window.location = "/check-confirmation-code"
                    }
                }
                else {
                    $("#message").show();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
        $("#pleaseWaitDialog").modal('hide');
    })


});