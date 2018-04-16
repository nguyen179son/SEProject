$( document ).ready(function() {
    var editProfile = (function ($, window, document) {

        var my = {};

        my.setDatePicker = function () {
            $('#birthday').datetimepicker({
                format: 'YYYY-MM-DD'
            });
        };

        return my;
    }(window.jQuery, window, document));

    $(function () {
        editProfile.setDatePicker();


    });

});


