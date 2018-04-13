$(document).ready(function () {

    var Profile = (function ($, window, document) {
        var profile = {};
        profile.getDetail = function (id) {
            var condition = {};
            condition['id'] = id;
            var getInfoAjax = $.ajax({
                url: "/SEProject_war_exploded/detail",
                type: "get",
                data: condition,
                success: function (responseText) {
                    var result = $.parseJSON(responseText);
                    console.log(result[0]['email']);
                    console.log(result[1]['user_name']);
                    $("#email").text(result[0]['email']);

                    $("#nick-name").text(result[1]['user_name']);

                    $("#phone").text(result[2]['phone_number']);

                    $("#dob").text(result[3]['DOB']);
                }
            })
        };
        return profile;
    }(window.jQuery, window, document));

    $(function () {
        $('body').onload = Profile.getDetail($("#id").val());
    });
});