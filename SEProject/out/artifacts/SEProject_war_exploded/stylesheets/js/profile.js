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
                    console.log(typeof result[2]['phone_number'] == "undefined");
                    console.log(result[2]['phone_number']);

                    $("#email").text(result[0]['email']);

                    $("#nick-name").text(result[1]['user_name']);

                    $("#phone").text(typeof result[2]['phone_number'] != "undefined"? result[2]['phone_number'] : "Not added");

                    $("#dob").text(typeof result[3]['DOB'] != "undefined" ? result[3]['DOB'] : "Not added");
                }
            })
        };
        return profile;
    }(window.jQuery, window, document));

    $(function () {
        $('body').onload = Profile.getDetail($("#id").val());
    });
});