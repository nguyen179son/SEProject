<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Chat</title>
</head>
<body style="margin:0;">

<%
  if (session.getAttribute("userID") == null) {
      session.setAttribute("returnUrl","/");
      response.sendRedirect("/SEProject_war_exploded/home");
  }
%>

<textarea title="Chat Log" id="log" readonly
          style="display: block; width: 100%; height: 600px; resize: none; margin: 0; padding: 0; border: 0;"></textarea>
<input title="Chat Input" id="input" type="text" style="display: block; width: 100%; border-width: 1px 0 1px 0;"
       autofocus/>
<script>
    var ws = new WebSocket("ws://localhost:8080/SEProject_war_exploded/ws");
    ws.onmessage = function (event) {
        console.log(event.data);
        document.getElementById("log").value += "[" + timestamp() + "] " + event.data + "\n";
    };

    document.getElementById("input").addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
            ws.send(event.target.value);
            event.target.value = "";
        }
    });

    function timestamp() {
        var d = new Date(), minutes = d.getMinutes();
        if (minutes < 10) minutes = '0' + minutes;
        return d.getHours() + ':' + minutes;
    }
</script>
</body>
</html>