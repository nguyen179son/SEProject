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

    var Contact = (function ($, window, document) {
        var contact = {};

        contact.listContact = function () {
            $('#pleaseWaitDialog').modal();
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
                                        "                     <img src=\""+friend["profile_picture"]+"\"\n" +
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
                                        "                     <img src=\""+friend["profile_picture"]+"\"\n" +
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
                    }
                    else {
                        window.location = "/login";
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }

            })

        };

        contact.searchFriends = function () {
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
                                        "                     <img src=\""+friend["profile_picture"]+"\"\n" +
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
                                        "                     <img src=\""+friend["profile_picture"]+"\"\n" +
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

        contact.getFriendProfile = function (id) {
            $('#pleaseWaitDialog').modal();
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

        contact.deleteFriend = function (id, $modalDiv) {
            $('#pleaseWaitDialog').modal();
            $.ajax({
                url: "/remove-friend",
                type: "post",
                data: {
                    token: window.localStorage.getItem("token"),
                    friend_id: id
                },
                success: function (response) {
                    console.log(response);
                    if (response["verify_token"]) {
                        if (response["success"]) {
                            contact.listContact();
                            $modalDiv.modal('hide').removeClass('loading');
                            $("#profile-div").hide();
                        }
                        else {
                            alert("INTERNAL ERROR");
                        }
                        $('#pleaseWaitDialog').modal('hide');

                    } else {
                        window.location = "/login";
                        $('#pleaseWaitDialog').modal('hide');

                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                }
            });
        };

        return contact;
    }(window.jQuery, window, document));

    $(function () {

        $("body").onload = Contact.listContact();
        $("body").on("click", ".favorite", function (e) {
            e.preventDefault();
            e.stopPropagation();
            var favorite = $(this).data("favorite");
            var id = $(this).data("id");
            if (favorite == 0) {
                $(this).html("<i class=\"fa fa-star\" style=\"color: yellow;\"></i>");
                $(this).data("favorite", 1)
            }

            else {
                $(this).html("<i class=\"fa fa-star\"></i>");
                $(this).data("favorite", 0)
            }
            Contact.addFavorite(id, favorite);
        });

        $("body").on("click", "#searchFriends", function () {
            Contact.searchFriends();
        });

        $("body").on("click", ".contact-box", function (e) {
            Contact.getFriendProfile($(this).data("id"));
            e.stopPropagation();
        });

        $('#confirm-delete').on('click', '.btn-ok', function (e) {
            var $modalDiv = $(e.delegateTarget);
            var id = $(this).data('recordId');
            // $.ajax({url: '/api/record/' + id, type: 'DELETE'})
            // $.post('/api/record/' + id).then()
            Contact.deleteFriend(id, $modalDiv);
            setTimeout(function () {
                $modalDiv.modal('hide').removeClass('loading');
            }, 1000)
        });
        $('#confirm-delete').on('show.bs.modal', function (e) {
            var data = $(e.relatedTarget).data();
            $('.title', this).text(data.recordTitle);
            $('.btn-ok', this).data('recordId', data.recordId);
        });
    });
});