$(document).ready(function () {

    var Profile = (function ($, window, document) {
        var profile = {};
        profile.getDetail = function () {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/get-my-profile",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {

                            $("#email").text(response['email']);
                            $("#email").css("font-weight", 500);

                            $("#nick-name").text(response['user_name']);
                            $("#nick-name").css("font-weight", 500);

                            $("#phone").text(response['phone_number']);
                            $("#phone").css("font-weight", 500);

                            $("#dob").text(response['DOB']);
                            $("#dob").css("font-weight", 500);
                        }
                        else {
                            alert("INTERNAL ERROR");
                        }
                        $('#pleaseWaitDialog').modal('hide');

                    } else {
                        window.location = "/login";
                        $('#pleaseWaitDialog').modal('hide');

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        };
        return profile;
    }(window.jQuery, window, document));

    $(function () {
        $("#edit-profile").attr("href", "/edit-my-profile");
        $('body').onload = Profile.getDetail();
    });
});