var ws = new WebSocket("ws://localhost:8080/websocket?token=" + localStorage.getItem("token"));
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

        chat.checkTime = function (t) {
            var splitedTime = t.split(" ");
            var date1 = splitedTime[0].split("-");
            var time1 = splitedTime[1].split(":");

            var curdate = new Date();
            var date2 = curdate.getDate();
            var month2 = curdate.getMonth() + 1;
            var year2 = curdate.getFullYear();

            if (date2 == date1[2] && month2 == date1[1] && year2 == date1[0]) {
                return time1[0] + ":" + time1[1];
            }
            else if (year2 == date1[0]) {
                return date1[1] + "-" + date1[2] + " " + time1[0] + ":" + time1[1];
            }
            return date1[0] + "-" + date1[1] + "-" + date1[2] + " " + time1[0] + ":" + time1[1];

        };

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
                                var time = friend['sending_time'] == null ? "" : chat.checkTime(friend['sending_time']);
                                htmlText += "<li class=\"left clearfix contact-box\" data-room-id='" + friend['roomID'] + "'>\n" +
                                    "                     <div class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\"image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </div>\n" +
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
                                    htmlText += "</strong> <strong class=\"pull-right\" style='font-weight: 500'>\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\" style='font-weight: 500'>"
                                        + friend['last_message'] + "</strong> <span\n" +
                                        "                                        class=\"badge pull-right\" style='background-color: red'>"
                                        + "</span>\n" +
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
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            response["room_list"].forEach(function (friend) {
                                var time = friend['sending_time'] != null ? chat.checkTime(friend["sending_time"]) : "";
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
                                    htmlText += "</strong> <strong class=\"pull-right\" style='font-weight: 500'>\n"
                                        + time +
                                        "                                    </strong>\n" +
                                        "                                </div>\n" +
                                        "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                        "                                    <strong class=\"primary-font\" style='font-weight: 500'>"
                                        + friend['last_message'] + "</strong> <span\n" +
                                        "                                        class=\"badge pull-right\" style='background-color: red'>"
                                        + "</span>\n" +
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
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            var userID = window.localStorage.getItem("userID");
                            var time;
                            var previousUserID = -1;
                            $("#list-message").data("previousUserSentID", previousUserID);
                            response["message_list"].forEach(function (mess) {
                                var time = chat.checkTime(mess["sending_time"]);
                                if (mess["from_userID"] != previousUserID) {
                                    if (mess["from_userID"] == userID) {
                                        htmlText = "<li class=\"left clearfix admin_chat\">\n" +
                                            "                     <span class=\"chat-img1 pull-right\">\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-right mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>" + htmlText;
                                    }

                                    else {
                                        htmlText = "<li class=\"left clearfix\">\n" +
                                            "                     <span class=\"chat-img1 pull-left\">\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }
                                } else {
                                    if (mess["from_userID"] == userID) {
                                        htmlText = "<li class=\"left clearfix admin_chat\">\n" +

                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-right mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }

                                    else {
                                        htmlText = "<li class=\"left clearfix\">\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }
                                }

                                previousUserID = mess["from_userID"];
                                $("#list-message").data("previousUserSentID", previousUserID);
                            });

                            $("#list-message").html(htmlText);
                            $("#list-message").data("roomID", response["roomID"]);
                            $("#list-message").data("numOfMess", response["number_of_messages"]);

                            $('#pleaseWaitDialog').modal('hide');
                            $('.chat_area').scrollTop($('.chat_area')[0].scrollHeight);
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
            $.ajax({
                url: "/seen-message",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    roomID: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                        }
                        else {
                            alert("INTERNAL ERROR");
                        }

                    } else {
                        window.location = "/login";

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        };

        chat.sendMessage = function (message) {
            ws.send(JSON.stringify({
                roomID: $("#list-message").data("roomID"),
                message: message,
                userID: window.localStorage.getItem("userID")
            }));
            chat.updateChatScreenAfterSent(message, $("#list-message").data("roomID"));
            $("#list-message").data("previousUserSentID", window.localStorage.getItem("userID"));
            $("#message-content").val("");
            $("#message-content").focus();
            var numOfMess = $("#list-message").data("numOfMess");
            $("#list-message").data("numOfMess", numOfMess + 1);
        };

        chat.updateChatScreenAfterSent = function (mess, roomID) {
            var currentdate = new Date();
            var time = currentdate.getHours() + ":" + currentdate.getMinutes();

            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .contact_sec strong").html(mess);
            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .header_sec .pull-right").html(time);
            var htmlText = "<li class=\"left clearfix contact-box\" data-room-id='" + roomID + "'>\n";

            htmlText += $("#list-chat li[data-room-id='" + roomID + "']").html();
            htmlText += "</li>";
            $("#list-chat li[data-room-id='" + roomID + "']").remove();

            htmlText += $("#list-chat").html();

            $("#list-chat").html(htmlText);


            htmlText = $("#list-message").html();


            var userID = window.localStorage.getItem("userID");

            var previousUserID = $("#list-message").data("previousUserSentID");

            if (userID != previousUserID) {
                htmlText += "<li class=\"left clearfix admin_chat\">\n" +
                    "                     <span class=\"chat-img1 pull-right\">\n" +
                    "                     <img src=\"image/profile.png\"\n" +
                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                    "                     </span>\n" +
                    "                            <div class=\"chat-body1 clearfix\">\n" +
                    "                                <span class='pull-right mess-span' title=\"" + time + "\">"
                    + mess +
                    "</span>\n" +
                    "                            </div>\n" +
                    "                        </li>";
            } else {
                htmlText += "<li class=\"left clearfix admin_chat\">\n" +

                    "                            <div class=\"chat-body1 clearfix\">\n" +
                    "                                <span class='pull-right mess-span' title=\"" + time + "\">"
                    + mess +
                    "</span>\n" +
                    "                            </div>\n" +
                    "                        </li>";
            }

            $("#list-message").html(htmlText);
        };

        chat.handleMessage = function (message) {
            var curID = window.localStorage.getItem("userID");
            if ($("li[data-room-id='" + message['roomID'] + "']").val() != null) {

                if (message["roomID"] == $("#list-message").data("roomID")) {
                    chat.receiveMessfromCurrentChatWindow(message);
                }

                else {

                    chat.receiveMessfromOtherChatWindow(message);
                }
            }
        };

        chat.receiveMessfromCurrentChatWindow = function (mess) {

            var time = mess['sending_time'] ?
                mess['sending_time'].split(" ")[1].split(":")[0] + ":" + mess['sending_time'].split(" ")[1].split(":")[1] : "";

            var room = mess["roomID"];
            $("#list-chat li[data-room-id='" + room + "'] .chat-body .contact_sec strong").html(mess["message"]);
            $("#list-chat li[data-room-id='" + room + "'] .chat-body .header_sec .pull-right").html(time);
            var htmlText = "<li class=\"left clearfix contact-box\" data-room-id='" + room + "'>\n";

            htmlText += $("#list-chat li[data-room-id='" + room + "']").html();
            htmlText += "</li>";
            $("#list-chat li[data-room-id='" + room + "']").remove();


            htmlText += $("#list-chat").html();
            $("#list-chat").html(htmlText);

            var previousUserID = $("#list-message").data("previousUserSentID");
            var htmlText = $("#list-message").html();
            if (mess["from_userID"] != previousUserID) {

                htmlText += "<li class=\"left clearfix\">\n" +
                    "                     <span class=\"chat-img1 pull-left\">\n" +
                    "                     <img src=\"image/profile.png\"\n" +
                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                    "                     </span>\n" +
                    "                            <div class=\"chat-body1 clearfix\">\n" +
                    "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                    + mess["message"] +
                    "</span>\n" +
                    "                            </div>\n" +
                    "                        </li>";
            } else {
                htmlText += "<li class=\"left clearfix\">\n" +
                    "                            <div class=\"chat-body1 clearfix\">\n" +
                    "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                    + mess["message"] +
                    "</span>\n" +
                    "                            </div>\n" +
                    "                        </li>";
            }

            $("#list-message").html(htmlText);

            $("#list-message").data("previousUserSentID", mess["from_userID"]);
            var numOfMess = $("#list-message").data("numOfMess");
            $("#list-message").data("numOfMess", numOfMess + 1);
            $("li[data-room-id='" + room + "'] .chat-body .contact_sec strong").html(mess["message"]);
            $("li[data-room-id='" + room + "'] .chat-body .header_sec .pull-right").html(time);

            $.ajax({
                url: "/seen-message",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    roomID: $("#list-message").data("roomID")
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                        }
                        else {
                            alert("INTERNAL ERROR");
                        }

                    } else {
                        window.location = "/login";

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });

        };

        chat.receiveMessfromOtherChatWindow = function (mess) {
            var currentdate = new Date();
            var time = currentdate.getHours() + ":" + currentdate.getMinutes();
            var roomID = mess["roomID"];
            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .contact_sec strong").html(mess["message"]);
            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .contact_sec strong").css("font-weight", 700);
            var unread = $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .contact_sec span").html();
            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .contact_sec span").html(1 + (+unread));
            $("#list-chat li[data-room-id='" + roomID + "'] .chat-body .header_sec .pull-right").html(time);
            var htmlText = "<li class=\"left clearfix contact-box\" data-room-id='" + roomID + "'>\n";

            htmlText += $("#list-chat li[data-room-id='" + roomID + "']").html();
            htmlText += "</li>";
            $("#list-chat li[data-room-id='" + roomID + "']").remove();


            htmlText += $("#list-chat").html();
            $("#list-chat").html(htmlText);
        };

        chat.loadFriendToChatRoom = function () {
            $.ajax({
                url: "/get-friend-list",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token")
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "<ul class='list-unstyled' id='friends-select'>";
                            response["friend_list"].forEach(function (friend) {
                                htmlText += "<li class='left clearfix'><div class='pull-left'> <input type='checkbox' data-name=" + friend['user_name'] + " value=\"" + friend["userID"] + "\">" +
                                    "</div> <div class='pull-left'>" + friend['user_name'] + "</div> </li>";
                            });
                            htmlText += "</ul>";
                            $("#newChatRoom .modal-dialog .modal-content .modal-body").html(htmlText);

                            $("#listFriend").html(htmlText);

                        }
                        else {
                            alert("INTERNAL ERROR");
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

        chat.createChatRoom = function () {
            var userSelected = [];
            var userName = [];
            $('#friends-select input:checked').each(function () {
                userSelected.push($(this).val());
                userName.push($(this).data('name'));
            });
            $.ajax({
                url: "/create-chat-room",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    userID_list: userSelected
                },
                success: function (response) {
                    var htmlText = "";
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            $("#newChatRoom").modal("hide");
                            if ($("li[data-room-id='" + response['roomID'] + "']").val() != null) {
                                $("li[data-room-id='" + response['roomID'] + "']").click();
                            }
                            else {
                                htmlText += "<li class=\"left clearfix contact-box\" data-room-id='" + response['roomID'] + "'>\n" +
                                    "                     <span class=\"chat-img pull-left\">\n" +
                                    "                     <img src=\"image/profile.png\"\n" +
                                    "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                    "                     </span>\n" +
                                    "                            <div class=\"chat-body clearfix\">\n" +
                                    "                                <div class=\"header_sec\" style='overflow: hidden'>\n" +
                                    "                                    <strong class=\"primary-font\" style='white-space: nowrap'>";
                                userName.forEach(function (user, i, array) {
                                    if (i < array.length - 1) {
                                        htmlText += user + ",";
                                    }
                                    else {
                                        htmlText += user;
                                    }
                                });

                                htmlText += "</strong> <strong class=\"pull-right\">\n"
                                    + "                                    </strong>\n" +
                                    "                                </div>\n" +
                                    "                                <div class=\"contact_sec\" style='max-width: 100%'>\n" +
                                    "                                    <strong class=\"primary-font\">" +
                                    "</strong> <span\n" +
                                    "                                        class=\"badge pull-right\" style='background-color: red'>"
                                    + "</span>\n" +
                                    "                                </div>\n" +
                                    "                            </div>\n" +
                                    "                        </li>";

                                htmlText += $("#list-chat").html();
                                $("#list-chat").html(htmlText);
                                $("li[data-room-id='" + response['roomID'] + "']").click();
                            }
                        }
                        else {
                            alert("INTERNAL ERROR");
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

        chat.loadMoreMessage = function (height) {
            $.ajax({
                url: "/load-message",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    roomID: $("#list-message").data("roomID"),
                    number_of_messages: $("#list-message").data("numOfMess") + 10
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
                            var userID = window.localStorage.getItem("userID");
                            var time;
                            var previousUserID = -1;
                            $("#list-message").data("previousUserSentID", previousUserID);
                            response["message_list"].forEach(function (mess) {
                                var time = chat.checkTime(mess["sending_time"]);
                                if (mess["from_userID"] != previousUserID) {
                                    if (mess["from_userID"] == userID) {
                                        htmlText = "<li class=\"left clearfix admin_chat\">\n" +
                                            "                     <span class=\"chat-img1 pull-right\">\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-right mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }

                                    else {
                                        htmlText = "<li class=\"left clearfix\">\n" +
                                            "                     <span class=\"chat-img1 pull-left\">\n" +
                                            "                     <img src=\"image/profile.png\"\n" +
                                            "                          alt=\"User Avatar\" class=\"img-circle\">\n" +
                                            "                     </span>\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }
                                } else {
                                    if (mess["from_userID"] == userID) {
                                        htmlText = "<li class=\"left clearfix admin_chat\">\n" +

                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-right mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }

                                    else {
                                        htmlText = "<li class=\"left clearfix\">\n" +
                                            "                            <div class=\"chat-body1 clearfix\">\n" +
                                            "                                <span class='pull-left mess-span' title=\"" + chat.checkTime(mess["sending_time"]) + "\">"
                                            + mess["message"] +
                                            "</span>\n" +
                                            "                            </div>\n" +
                                            "                        </li>"+htmlText;
                                    }
                                }

                                previousUserID = mess["from_userID"];
                                $("#list-message").data("previousUserSentID", previousUserID);
                            });

                            $("#list-message").html(htmlText);
                            $("#list-message").data("roomID", response["roomID"]);
                            $("#list-message").data("numOfMess", response["number_of_messages"]);
                            $(".chat_area").scrollTop($(".chat_area")[0].scrollHeight-height);

                        }
                        else {
                            alert("INTERNAL ERROR");
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
            $("#list-chat li").css("background-color", "white");
            $("#list-chat li").css("background-color", "");
            $(this).css("background-color", "#dddddd");
            $(this).find(".contact_sec strong").css("font-weight", 500);
            $(this).find(".contact_sec span").html("");

            Chat.getRecentMessage($(this).data("room-id"));

            e.stopPropagation();
            $('.chat_area').scrollTop($('.chat_area')[0].scrollHeight);
        });

        $("body").on("click", "#send-message", function (e) {
            e.preventDefault();
            var message = $.trim($("#message-content").val());
            Chat.sendMessage(message);

        });

        $("body").on("keypress", "#message-content", function (e) {
            if (e.which == 13) {
                e.preventDefault();
                var message = $.trim($("#message-content").val());
                Chat.sendMessage(message);
            }
        });

        ws.onmessage = function (event) {
            var data = $.parseJSON(event.data);
            Chat.handleMessage(data);
        };

        $("body").on("click", "#new-message", function (e) {
            $("#newChatRoom").modal();
            Chat.loadFriendToChatRoom();
        });

        $("body").on("click", "#create-chat-room", function (e) {
            Chat.createChatRoom();
        });

        $('.chat_area').scroll(function () {
            var pos = $('.chat_area').scrollTop();
            if (pos == 0) {

                var height = $('.chat_area')[0].scrollHeight;
                Chat.loadMoreMessage(height);
                $(".chat_area").scrollTop($(".chat_area")[0].scrollHeight-height);
            }
        });


    });
});