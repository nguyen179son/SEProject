$.ajax({
    url: "/verify-token",
    async: false,
    type: "post",
    data: {
        token: window.localStorage.getItem("token")
    },
    success: function (response) {
        if (!response["verify_token"]) {
            window.location = "/login"
        }
    },
    error: function (jqXHR, textStatus, errorThrown) {
        alert("Error");
    }
});
$(document).ready(function () {

    var Ava = (function ($, window, document) {
        var ava = {};

        ava.preview = function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        };

        ava.readURL = function (input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
            }
        };


        ava.upload = function () {
            var url = "upload-profile-picture";
            var image = $('#img-upload').attr('src');

            $.ajax({
                url: url,
                type: "POST",
                data: {
                    token: window.localStorage.getItem("token"),
                    imageBase64: image
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            window.location = "/get-my-profile";
                        }
                        else {
                            alert("Upload failed!!! Please try again later.");
                        }
                    }
                    else {
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error");
                }
            });
        };

        return ava;
    }(window.jQuery, window, document));

    $(function () {
        $("body").onload = $("#sidebar-avatar").attr("src", window.localStorage.getItem("profile_picture"));
        $("body").on("change", ".btn-file :file", function () {
            var input = $(this),
                label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $("body").on("fileselect", ".btn-file :file", function (event, label) {
            var input = $(this).parents('.input-group').find(':text'),
                log = label;

            if (input.length) {
                input.val(log);
            } else {
                if (log) alert(log);
            }
        });

        $("body").on("change", "#imgInp", function () {
            Ava.readURL(this);
        });

        $("body").on("click", "#upload", function (e) {
            Ava.upload();
        });
    });
});