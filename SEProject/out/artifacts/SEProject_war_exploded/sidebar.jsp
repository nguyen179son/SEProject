<div class="wrapper">
    <!-- Sidebar Holder -->
    <nav id="sidebar" class="active">
        <div class="sidebar-header">
            <h3>Bootstrap Sidebar</h3>
            <a href="/get-my-profile" id="profile-ava">
                <img src="image/profile.png" class="center img-responsive" alt="profilepic"
                     style="border-radius: 50%;height: 34px;width: 34px;" id="sidebar-avatar">
            </a>
        </div>

        <ul class="list-unstyled components">
            <li class=<%=request.getRequestURL().toString().contains("Chat.jsp") ? "active" : ""%>>
                <a href="/get-chat-room-list">
                    <i class="glyphicon glyphicon-comment"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("Contact.jsp") ? "active" : ""%>>
                <a href="/get-friend-list">
                    <i class="fa fa-address-book-o"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("Request.jsp") ? "active" : ""%>>
                <a href="/get-request-list">
                    <i class="icon fa fa-user-plus"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("setting") ? "active" : ""%>>
                <a href="#extra-menu" data-toggle="collapse" aria-expanded="false">
                    <i class="icon fa fa-ellipsis-h"></i>
                </a>
                <ul class="collapse list-unstyled" id="extra-menu">
                    <li><a href="/change-password">Change Password</a></li>
                    <li><a href="#">About us</a></li>
                    <li><a href="#" id="log-out">Sign out</a></li>
                </ul>
            </li>
        </ul>
    </nav>


</div>