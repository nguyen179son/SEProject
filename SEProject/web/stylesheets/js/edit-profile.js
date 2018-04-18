$(document).ready(function () {
    var editProfile = (function ($, window, document) {
            var my = {};
            my.setDatePicker = function () {
                $('#birthday').datetimepicker({
                    format: 'YYYY-MM-DD'
                });
            };

            my.getDetail = function (id) {
                var condition = {};
                condition['id'] = id;
                var getInfoAjax = $.ajax({
                    url: "/SEProject_war_exploded/detail",
                    type: "get",
                    data: condition,
                    success: function (responseText) {
                        var result = $.parseJSON(responseText);
                        // console.log(typeof result[2]['phone_number'] == "undefined");
                        // console.log(result[2]['phone_number']);

                        $("#email").val(result[0]['email']);

                        $("#nick-name").val(result[1]['user_name']);

                        $("#phone").val(typeof result[2]['phone_number'] != "undefined" ? result[2]['phone_number'] : "");

                        $("#datepicker").val(typeof result[3]['DOB'] != "undefined" ? result[3]['DOB'] : "");
                    }
                })
            };
            return my;
        }(window.jQuery, window, document)
    );

    $(function () {
        editProfile.setDatePicker();
        $('body').onload = editProfile.getDetail($("#id").val());

    });

});


