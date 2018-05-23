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
                                    "                     <img src=\"./image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\">\n" +
                                    "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                    "                                    <strong class=\"pull-right\" >\n" +
                                    "<button  class='btn btn-primary accept' style='padding: 2px 3px; margin-right: 2px' data-id=\"" + friend['userID'] + "\">" +
                                    "                                       accept </button>" +
                                    "<button  class='btn btn-danger delete' style='padding: 2px 3px'  data-toggle=\"modal\" data-target=\"#confirm-delete\" data-id=\"" + friend['userID'] + "\">" +
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

        request.deleteRequest = function (id) {
            $('#pleaseWaitDialog').modal();

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
                            request.searchUsers(typeof $("#saveName").val() == undefined ? "" : $("#saveName").val());
                            $('#pleaseWaitDialog').modal('hide');

                        }
                        $('#pleaseWaitDialog').modal('hide');

                    }
                    else {
                        window.location = "/login";
                        $('#pleaseWaitDialog').modal('hide');

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })

        };

        request.sendRequest = function (id) {
            $('#pleaseWaitDialog').modal();
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
                            request.searchUsers(typeof $("#saveName").val() == undefined ? "" : $("#saveName").val());
                            $('#pleaseWaitDialog').modal('hide');
                        }
                        else
                            $('#pleaseWaitDialog').modal('hide');

                    }
                    else {
                        window.location = "/login";
                        $('#pleaseWaitDialog').modal('hide');

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
                                            "                                        <i class=\"fa fa-user-plus\" style=\"color: yellow;\"></i></a></strong>\n" +
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
                                            "                     <img src=\"./image/profile.png\"\n" +
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
                                            "                     <img src=\"./image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                    <strong class=\"pull-right\" >\n" + "<a href='#' class=''  data-favorite='1' data-id=\"" + friend['userID'] + "\">" +
                                            "                                        <i class=\"fa fa-location-arrow\" ></i></a></strong>\n" +
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
                                            "                     <img src=\"./image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body clearfix\">\n" +
                                            "                                <div class=\"header_sec\">\n" +
                                            "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                            "                                    <strong class=\"pull-right\" >\n" + "<a href='#' class='accept' data-id=\"" + friend['userID'] + "\">" +
                                            "                                        <i class=\"fa fa-check\" style=\"color: green;\"></i></a>" + "<a href='#' class='delete' data-id=\"" + friend['userID'] + "\">" +
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
            Request.getProfile($(this).data("id"));
            e.stopPropagation();
        });


        $("body").on("click", ".accept", function (e) {
            e.stopPropagation();
            Request.acceptRequest($(this).data("id"))
        });

        $("body").on("click", ".delete", function (e) {
            e.stopPropagation();
            Request.deleteRequest($(this).data("id"))
        });
        $("body").on("click", ".sendRequest", function (e) {
            e.stopPropagation();
            Request.sendRequest($(this).data("id"))
        });


    });
});