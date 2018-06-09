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

    var Request = (function ($, window, document) {
        var request = {};

        request.listRequest = function () {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/get-request-list",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token")
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            response["request_list"].forEach(function (friend) {
                                htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                    "                     <span class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\""+friend['profile_picture']+"\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\">\n" +
                                    "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                    "                                    <strong class=\"pull-right\" >\n" +
                                    "<button  class='btn btn-primary accept' style='padding: 2px 3px; margin-right: 2px' data-id=\"" + friend['userID'] + "\">" +
                                    "                                       accept </button>" +
                                    "<button  class='btn btn-danger deny1' style='padding: 2px 3px'  data-toggle=\"modal\" data-target=\"#confirm-delete\" data-id=\"" + friend['userID'] + "\">" +
                                    "                                       delete </button>" +
                                    " </strong>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"contact_sec\">\n" +
                                    "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </li>"
                            });

                            $("#listFriend").html(htmlText);
                            $('#pleaseWaitDialog').modal('hide');

                        }
                        else {
                            alert("INTERNAL ERROR");
                            $('#pleaseWaitDialog').modal('hide');

                        }
                    }
                    else {
                        window.location = "/login";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })
        };

        request.acceptRequest = function (id) {
            $('#pleaseWaitDialog').modal();

            $.ajax({
                url: "accept-friend-request",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            request.searchUsers(typeof $("#saveName").val() == undefined ? "" : $("#saveName").val());
                            $('#pleaseWaitDialog').modal('hide');
                        }
                        $('#pleaseWaitDialog').modal('hide');
                    }
                    else {
                        window.location = "/login";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    $('#pleaseWaitDialog').modal('hide');

                }

            })

        };

        request.denyRequest = function (id) {

            $.ajax({
                url: "deny-friend-request",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {

                        }

                    }
                    else {
                        window.location = "/login";

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })

        };

        request.sendRequest = function (id) {
            $.ajax({
                url: "add-friend-request",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {

                        }
                    }
                    else {
                        window.location = "/login";

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })
        };

        request.deleteRequest = function (id) {
            $.ajax({
                url: "remove-friend-request",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {

                        }

                    }
                    else {
                        window.location = "/login";

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })
        };

        request.searchUsers = function (name) {
            $('#pleaseWaitDialog').modal();
            if ($("#search_user_name").val() == "")
                request.listRequest();
            else {
                $.ajax({
                    url: "/search-user",
                    type: "post",
                    data: {
                        token: window.localStorage.getItem("token"),
                        user_name: name
                    },
                    success: function (response) {
                        console.log(response);
                        if (response["verify_token"]) {
                            if (response["success"]) {
                                var htmlText = "<input type='hidden' id='saveName' value=" + name + ">";
                                console.log(response);
                                response["user_list"].forEach(function (friend) {
                                    if (friend["relationship_code"] == 0) {

                                        htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                            "                     <span class=\"chat-img pull-left\">\n" +
                                            "                     <img src=\"./image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                    <strong class=\"pull-right\" >\n" + "<a href='#' class='sendRequest'  data-favorite='1' data-id=\"" + friend['userID'] + "\">" +
                                            "                                        <i class=\"fa fa-user-plus\" style=\"color: #f05a6b;\"></i></a></strong>\n" +
                                            "                                </div>\n" +
                                            "                                <div class=\"contact_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"
                                    }
                                    else if (friend["relationship_code"] == 1) {
                                        htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                            "                     <span class=\"chat-img pull-left\">\n" +
                                            "                     <img src=\""+friend['profile_picture']+"\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                </div>\n" +
                                            "                                <div class=\"contact_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"
                                    }

                                    else if (friend["relationship_code"] == 2) {
                                        htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                            "                     <span class=\"chat-img pull-left\">\n" +
                                            "                     <img src=\""+friend['profile_picture']+"\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                    <strong class=\"pull-right\" >\n" +
                                            "<div class='btn-group' data-id=\""+friend["userID"]+"\" ><button type=\"button\" class=\"dropdown-toggle deleteRequestDropdown\" " +
                                            "data-toggle=\"dropdown\"  aria-haspopup=\"true\" aria-expanded=\"false\" data-id=\"" + friend["userID"] + "\">\n" +
                                            "    <i class=\"fa fa-location-arrow\" ></i>\n" +
                                            "</button>\n" +
                                            "<div class=\"dropdown-menu\" data-id=\"" + friend["userID"] + "\">\n" +


                                            "    <a class=\"dropdown-item deleteRequest\" data-id=\"" + friend['userID'] + "\" href=\"#\">Delete Request</a>\n" +
                                            "</div></div></strong>\n" +
                                            "                                </div>\n" +
                                            "                                <div class=\"contact_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"
                                    }

                                    else if (friend["relationship_code"] == 3) {
                                        htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                            "                     <span class=\"chat-img pull-left\">\n" +
                                            "                     <img src=\""+friend['profile_picture']+"\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                    <strong class=\"pull-right\" >\n" + "<a href='#' class='accept' data-id=\"" + friend['userID'] + "\">" +
                                            "                                        <i class=\"fa fa-check\" style=\"color: green;\"></i></a>" + "<a href='#' class='deny' data-id=\"" + friend['userID'] + "\">" +
                                            "                                        <i class=\"fa fa-times-circle\" style=\"color: red;\"></i></a>" + " </strong>\n" +
                                            "                                </div>\n" +
                                            "                                <div class=\"contact_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                            "                                </div>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"
                                    }
                                });

                                $("#listFriend").html(htmlText);
                                $('#pleaseWaitDialog').modal('hide');

                            }
                            else {
                                alert("INTERNAL ERROR");
                                $('#pleaseWaitDialog').modal('hide');

                            }
                        }
                        else {
                            window.location = "/login";
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                    }

                });
            }
        };

        request.getProfile = function (id) {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/get-friend-profile",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            $("#profile-div").show();
                            $("#profile-picture").attr("src",response["profile_picture"]);
                            $("#nick-name").html(response["user_name"]);
                            $("#email").html(response["email"]);
                            $("#phone").html(response["phone_number"] == null ? "" : response["phone_number"]);
                            $("#dob").html(response["DOB"] == null ? "" : response["DOB"]);
                            $("#delete-friend").data("record-id", id);
                            $('#pleaseWaitDialog').modal('hide');
                        }
                        else {
                            alert("INTERNAL ERROR");
                            $('#pleaseWaitDialog').modal('hide');
                        }
                    } else {
                        window.location = "/login";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        };


        return request;
    }(window.jQuery, window, document));

    $(function () {

        $("body").onload = Request.listRequest();


        $("body").on("click", "#searchUsers", function () {
            Request.searchUsers($("#search_user_name").val());

        });

        $("body").on("click", ".contact-box", function (e) {
            $("#listFriend li").css("background-color", "");
            $(this).css("background-color", "#dddddd");
            Request.getProfile($(this).data("id"));
            e.stopPropagation();
        });


        $("body").on("click", ".accept", function (e) {

            e.preventDefault();
            e.stopPropagation();
            $(this).html(" <i class=\"fa fa-user-plus\" style=\"color: #f18c99;\"></i>");
            $(this).attr('class', 'sendRequest');
            Request.acceptRequest($(this).data("id"))
        });

        $("body").on("click", ".deny", function (e) {
            e.preventDefault();
            e.stopPropagation();
            $(this).html(" <i class=\"fa fa-user-plus\" style=\"color: #f18c99;\"></i>");
            $(this).attr('class', 'sendRequest');
            Request.denyRequest($(this).data("id"));
        });

        $("body").on("click", ".deny1", function (e) {
            e.preventDefault();
            e.stopPropagation();
            Request.denyRequest($(this).data("id"));
            $(this).parent().parent().parent().parent().remove();
        });
        $("body").on("click", ".sendRequest", function (e) {
            var id = $(this).data('id');
            e.preventDefault();
            e.stopPropagation();
            $(this).replaceWith("<div class='btn-group'><button type=\"button\" class=\"dropdown-toggle deleteRequestDropdown\" " +
                "data-toggle=\"dropdown\"  aria-haspopup=\"true\" aria-expanded=\"false\" data-id=\"" + id + "\">\n" +
                "    <i class=\"fa fa-location-arrow\" ></i>\n" +
                "</button>\n" +
                "<div class=\"dropdown-menu\" data-id=\"" + id + "\">\n" +


                "    <a class=\"dropdown-item deleteRequest\" data-id=\"" + id + "\" href=\"#\">Delete Request</a>\n" +
                "</div></div>");
            $(this).attr('class', '');
            Request.sendRequest($(this).data("id"));
        });

        $("body").on("click", ".dropdown-menu", function (e) {
            e.preventDefault();
            e.stopPropagation();

            var id = $(this).data('id');
            $(this).toggle();
            $(".btn-group").first().data("id",id).replaceWith("<a href='#' class='sendRequest'  data-favorite='1' data-id=\"" + id + "\">" +
                "                                        <i class=\"fa fa-user-plus\" style=\"color: #f05a6b;\"></i></a>");
            Request.deleteRequest(id);
        });

        $("body").on("click", ".deleteRequestDropdown", function (e) {
            e.stopPropagation();
            var id = $(this).data['id'];
            $('.dropdown-menu').first().data("id", id).toggle();
        });

    });
});