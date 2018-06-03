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
                                var time = friend['sending_time'] ?
                                    friend['sending_time'].split(" ")[1].split(":")[0] + ":" + friend['sending_time'].split(" ")[1].split(":")[1] : "";
                                htmlText += "<li class=\"left clearfix contact-box\" data-room-id='" + friend['roomID'] + "'>\n" +
                                    "                     <span class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\"image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\" style='overflow: hidden'>\n" +
                                    "                                    <strong class=\"primary-font\" style='white-space: nowrap'>";
                                friend["user_list"].forEach(function (user, i, array) {
                                    if (i < array.length - 1) {
                                        htmlText += user["user_name"] + ",";
                                    }
                                    else {
                                        htmlText += user["user_name"];
                                    }
                                });

                                if (friend['unread_message'] > 0) {
                                    htmlText += "</strong> <strong class=\"pull-right\">\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['last_message'] +
                                        "</strong> <span\n" +
                                        "                                        class=\"badge pull-right\" style='background-color: red'>"
                                        + friend['unread_message'] + "</span>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>";
                                }

                                else {
                                    htmlText += "</strong> <strong class=\"pull-right\">\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\" style='font-weight: 500'>"
                                        + friend['last_message'] + "</strong>\n" +
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


        chat.searchChatRoom = function () {
            $('#pleaseWaitDialog').modal();

            $.ajax({
                url: "/search-chat-room",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    search_key: $("#search-chat-room-key").val()
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            response["room_list"].forEach(function (friend) {
                                var time = friend['sending_time'] ?
                                    friend['sending_time'].split(" ")[1].split(":")[0] + ":" + friend['sending_time'].split(" ")[1].split(":")[1] : "";
                                htmlText += "<li class=\"left clearfix  contact-box\" data-room-id='" + friend['roomID'] + "'>\n" +
                                    "                     <span class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\"image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\" style='overflow: hidden'>\n" +
                                    "                                    <strong class=\"primary-font\" style='white-space: nowrap'>";
                                friend["user_list"].forEach(function (user, i, array) {
                                    if (i < array.length - 1) {
                                        htmlText += user["user_name"] + ",";
                                    }
                                    else {
                                        htmlText += user["user_name"];
                                    }
                                });

                                if (friend['unread_message'] > 0) {
                                    htmlText += "</strong> <strong class=\"pull-right\">\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\">" + friend['last_message'] +
                                        "</strong> <span\n" +
                                        "                                        class=\"badge pull-right\" style='background-color: red'>"
                                        + friend['unread_message'] + "</span>\n" +
                                        "                                </div>\n" +
                                        "                            </div>\n" +
                                        "                        </li>";
                                }

                                else {
                                    htmlText += "</strong> <strong class=\"pull-right\">\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\" style='font-weight: 500'>"
                                        + friend['last_message'] + "</strong>\n" +
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

            });
        };

        chat.getRecentMessage = function (id) {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/load-message",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    roomID: id,
                    number_of_messages: 10
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            var userID = window.localStorage.getItem("userID");
                            console.log(userID);
                            var time;
                            var previousUserID = -1;
                            response["message_list"].forEach(function (mess) {
                                var time = mess['sending_time'] ?
                                    mess['sending_time'].split(" ")[1].split(":")[0] + ":" + mess['sending_time'].split(" ")[1].split(":")[1] : "";
                                if (mess["from_userID"] != previousUserID) {
                                    if (mess["from_userID"] == userID) {
                                        htmlText += "<li class=\"left clearfix admin_chat chat-box\" >\n" +
                                            "                     <div class=\"chat-img1 pull-right\" style='width: 3%'>\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </div>\n" +
                                            "                            <span class=\"chat-body1 clearfix\ pull-right\" style='display: inline-flex'>\n" +
                                            "                                <p class='chat-message' style='background-color: pink;color: black'>" + mess["message"] +
                                            "</p>\n" +
                                            "                                <div class=\"chat_time pull-left\">" + time +
                                            "</div>\n" +
                                            "                            </span>\n" +
                                            "                        </li>";
                                    }

                                    else {
                                        htmlText += "<li class=\"left clearfix chat-box\">\n" +
                                            "                     <div class=\"chat-img1 pull-left\" style='width: 3%'>\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </div>\n" +
                                            "                            <span class=\"chat-body1 clearfix\" style='display: inline-flex'>\n" +
                                            "                                <p class='chat-message' style='background-color: pink;color: black'>" + mess["message"] +
                                            "</p>\n" +
                                            "                                <div class=\"chat_time pull-right\">" + time +
                                            "                            </div>\n" +
                                            "</span>" +
                                            "                        </li>";
                                    }
                                } else {
                                    if (mess["from_userID"] == userID) {
                                        htmlText += "<li class=\"left clearfix admin_chat chat-box\">\n" +
                                            "<div class='chat-img1 pull-right' style='width: 3%'><p></p></div>" +
                                            "                            <span class=\"chat-body1 clearfix\ pull-right\" style='display: inline-flex'>\n" +
                                            "                                <p class='chat-message' style='background-color: pink;color: black'>" + mess["message"] +
                                            "</p>\n" +
                                            "                                <div class=\"chat_time pull-left\">" + time +
                                            "</div>\n" +
                                            "                            </span>\n" +
                                            "                        </li>";
                                    }

                                    else {
                                        htmlText += "<li class=\"left clearfix chat-box\">\n" +
                                            "<div class='chat-img1 pull-left' style='width: 3%'><p></p></div>" +
                                            "                            <span class=\"chat-body1 clearfix\" style='display: inline-flex'>\n" +
                                            "                                <p class='chat-message' style='background-color: pink;color: black'>" + mess["message"] +
                                            "</p>\n" +
                                            "                                <div class=\"chat_time pull-right\">" + time +
                                            "                            </div>\n" +
                                            "</span>" +
                                            "                        </li>";
                                    }
                                }

                                previousUserID = mess["from_userID"];
                                $("#list-message").data("previousUserSentID", previousUserID);
                            });

                            $("#list-message").html(htmlText);
                            $("#list-message").data("roomID", response["roomID"]);
                            $("#list-message").data("numOfMess", response["roomID"]);

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


        return chat;
    }(window.jQuery, window, document));

    $(function () {
        $("body").onload = Chat.listChat();

        $("body").on("click", "#search-chat-room", function () {
            Chat.searchChatRoom();
        });

        $("body").on("click", ".contact-box", function (e) {
            Chat.getRecentMessage($(this).data("room-id"));
            e.stopPropagation();
        });
    });
});