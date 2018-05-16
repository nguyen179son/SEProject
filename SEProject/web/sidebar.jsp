<div class="wrapper">
    <!-- Sidebar Holder -->
    <nav id="sidebar" class="active">
        <div class="sidebar-header">
            <h3>Bootstrap Sidebar</h3>
            <strong>ICT</strong>
        </div>

        <ul class="list-unstyled components">
            <li class=<%=request.getRequestURL().toString().contains("Chat.jsp")?"active":""%>>
                <a href="#homeSubmenu">
                    <i class="glyphicon glyphicon-comment"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("Contact.jsp")?"active":""%>>
                <a href="#pageSubmenu">
                    <i class="fa fa-address-book-o"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("get-friend-request")?"active":""%>>
                <a href="#">
                    <i class="icon fa fa-user-plus"></i>
                </a>
            </li>
            <li class=<%=request.getRequestURL().toString().contains("setting")?"active":""%>>
                <a href="#">
                    <i class="icon fa fa-ellipsis-h"></i>
                </a>
            </li>
        </ul>
    </nav>

    <!-- Page Content Holder -->

</div>