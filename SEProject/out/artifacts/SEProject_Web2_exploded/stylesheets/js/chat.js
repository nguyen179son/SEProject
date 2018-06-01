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

    var Chat = (function ($, window, document) {
        var chat = {};

        chat.listChat = function () {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/get-chat-room-list",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token")
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            response["room_list"].forEach(function (friend) {
                                // var time = friend["sending_time"].split(" ")[1].split(":")[0] + ":" + friend["sending_time"].split(" ")[1].split(":")[0];
                                console.log(friend['sending_time']);
                                console.log(typeof friend['sending_time']);
                                htmlText += "<li class=\"left clearfix chat-box contact-box contact-box\" data-room-id='" + friend['roomID'] + "'>\n" +
                                    "                     <span class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\"image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\">\n" +
                                    "                                    <strong class=\"primary-font\">";
                                friend["user_list"].forEach(function (user, i, array) {
                                    if (i < array.length - 1) {
                                        htmlText += user["user_name"] + ",";
                                    }
                                    else {
                                        htmlText += user["userID"];
                                    }
                                });

                                if (friend['unread_message'] > 0) {

                                    htmlText += "</strong> <strong class=\"pull-right\">\n" +
                                        +friend['sending_time'] +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\">\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['last_message'] +
                                        "</strong> <span\n" +
                                        "                                        class=\"badge pull-right\" style='background-color: red'>" + friend['unread_message'] + "</span>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>";
                                }

                                else {
                                    htmlText += "</strong> <strong class=\"pull-right\">\n" +
                                        +friend['sending_time'] +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\">\n" +
                                        "                                    <strong class=\"primary-font\" style='font-weight: 500'>" + friend['last_message'] + "</strong>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>";
                                }

                            });

                            $("#list-chat").html(htmlText);
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


        chat.searchFriends = function () {
            $('#pleaseWaitDialog').modal();

            $.ajax({
                url: "/search-friend",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_name: $("#search_friend_name").val()
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            console.log(response);
                            response["friend_list"].forEach(function (friend) {
                                if (friend["favorite"]) {
                                    htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                        "                     <span class=\"chat-img pull-left\">\n" +
                                        "                     <img src=\"./image/profile.png\"\n" +
                                        "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                        "                     </span>\n" +
                                        "                            <div class=\"chat-body clearfix\">\n" +
                                        "                                <div class=\"header_sec\">\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                        "                                    <strong class=\"pull-right\">\n" + "<a href='#' class='favorite'  data-favorite='1' data-id=\"" + friend['userID'] + "\">" +
                                        "                                        <i class=\"fa fa-star\" style=\"color: yellow;\"></i></a></strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\">\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>"
                                }
                                else {
                                    htmlText += "<li class=\"left clearfix contact-box contact-box contact-box\" data-id='" + friend['userID'] + "'>\n" +
                                        "                     <span class=\"chat-img pull-left\">\n" +
                                        "                     <img src=\"./image/profile.png\"\n" +
                                        "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                        "                     </span>\n" +
                                        "                            <div class=\"chat-body clearfix\">\n" +
                                        "                                <div class=\"header_sec\">\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['user_name'] + "</strong>\n" +
                                        "                                    <strong class=\"pull-right\">\n" + "<a href='#' class='favorite' data-favorite='0' data-id=\"" + friend['userID'] + "\">" +
                                        "                                        <i class=\"fa fa-star\"></i> </a> </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\">\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['email'] + "</strong>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>"
                                }
                            });

                            $("#list-chat").html(htmlText);
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
        };

        chat.getRecentMessage = function (id) {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/load-message",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    roomID: id
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {

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


        return chat;
    }(window.jQuery, window, document));

    $(function () {
        $("body").onload = Chat.listChat();

        $("body").on("click", "#searchFriends", function () {
            Chat.searchFriends();
        });

        $("body").on("click", ".contact-box", function (e) {
            Chat.getRecentMessage($(this).data("room-id"));
            e.stopPropagation();
        });
    });
});