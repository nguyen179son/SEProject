$.ajax({
    url: "http://localhost:8080/verify-token",
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

    var Contact = (function ($, window, document) {
        var contact = {};

        contact.listContact = function () {
            $.ajax({
                url: "/get-friend-list",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token")
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            var htmlText = "";
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
                    alert("Error");
                }

            })
        };

        contact.addFavorite = function (id, favorite) {
            $.ajax({
                url: favorite == 0 ? "/add-friend-favorite" : "/remove-friend-favorite",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            contact.listContact();
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
                    alert("Error");
                }

            })

        };

        contact.searchFriends = function () {
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
                    alert("Error");
                }

            });
        };

        contact.getFriendProfile = function (id) {
            $.ajax({
                url: "/get-friend-profile",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            $("#profile-div").show();
                            $("#nick-name").html(response["user_name"]);
                            $("#email").html(response["email"]);
                            $("#phone").html(response["phone_number"]==null?"":response["phone_number"]);
                            $("#dob").html(response["DOB"]==null?"":response["DOB"]);
                        }
                        else {
                            alert("INTERNAL ERROR");
                        }
                    } else {
                        window.location = "/login";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Error");
                }
            });
        };
        return contact;
    }(window.jQuery, window, document));

    $(function () {
        $("body").onload = Contact.listContact();
        $("body").on("click", ".favorite", function (e) {
            e.preventDefault();
            Contact.addFavorite($(this).data("id"), $(this).data("favorite"));
        });

        $("body").on("click", "#searchFriends", function () {
            Contact.searchFriends();
        })

        $("body").on("click", ".contact-box", function () {
            Contact.getFriendProfile($(this).data("id"));
        })

    });
});