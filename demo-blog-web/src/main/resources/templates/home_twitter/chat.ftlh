<#import "/spring.ftl" as spring/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="<@spring.url '/css/chat.css'/>"/>
<!------ Include the above in your HEAD tag ---------->

<script>
    const connect = () => {
        const Stomp = require("stompjs");
        var SockJS = require("sockjs-client");
        SockJS = new SockJS("http://localhost:8080/ws");
        stompClient = Stomp.over(SockJS);
        stompClient.connect({}, onConnected, onError);
    };

    const onConnected = () => {
        console.log("connected");

        stompClient.subscribe(
            "/user/" + ${userDTO.userId} + "/queue/messages",
            onMessageReceived
        );
    };

    const sendMessage = (msg) => {
        if (msg.trim() !== "") {
            const message = {
                senderId: ${userDTO.userId},
                recipientId: ${followerDTO.userId},
                senderName: ${userDTO.userName},
                recipientName: ${followerDTO.userName},
                content: msg,
                timestamp: new Date(),
            };

            stompClient.send("/app/chat", {}, JSON.stringify(message));
        }
    };
</script>

<!DOCTYPE html>
<html>
<body>
<div class="col-sm-3 col-sm-offset-4 frame">
    <ul></ul>
    <div>
        <div class="msj-rta macro" style="margin:auto">
            <div class="text text-r" style="background:whitesmoke !important">
                <input class="mytext" placeholder="Type a message"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>