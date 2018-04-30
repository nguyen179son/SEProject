$(document).ready(function(){

    $("#login_button").click(function(){

        $.ajax({
            url: "http://localhost:8080/SEProject_war_exploded/home",
            type: "post",
            data: {
                user_name: $("#user_name").val(),
                password: $("#password").val()
            },
            success: function (response) {
                alert("Data: " + JSON.stringify(response));
                if(response["success"]) {
                    if (response["confirm"]) {
                        window.localStorage.setItem("token", response["token"]);
                    }
                    window.location = response["redirect_url"];
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error");
            }
        });
    });

});