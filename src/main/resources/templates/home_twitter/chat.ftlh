<#import "/spring.ftl" as spring/>
<#import "ui.ftl" as ui/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Twitter</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<@spring.url '/css/style.min.css'/>"/>
    <#import "../layout/head.ftlh" as s>
    <@s.head/>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="<@spring.url '//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js'/>"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="<@spring.url '/css/chat.css'/>"/>

    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <!------ Include the above in your HEAD tag ---------->

    <script>
        const HOST = "ws://localhost:8080/chat";

        let webSocket;

        function connect() {
            webSocket = new WebSocket(HOST);

            // handshake cookie
            document.cookie = 'AUTH=' + '1234' + ';path=/';

            webSocket.onmessage = function receiveMessage(response) {
                let json = JSON.parse(response['data']);
                // $('#messagesList').first().append("<li class='message'>" + json['from'] + ': ' + json['text'] + "</li>")
                $('#messagesList').first().append('<li style="width:100%">' +
                    '<div class="msj macro">' +
                    '<div class="text text-l">' +
                    '<p>' + json['from'] + '</p>' +
                    '<p>' + json['text'] + '</p>' +
                    '</div>' +
                    '</div>' +
                    '</li>')
            }
        }

        function sendMessage(text, username) {

            let message = {
                "text": text,
                "from": username
            };

            $("#message").val("");

            webSocket.send(JSON.stringify(message));
        }
    </script>

</head>
<body onload="connect()">
<#import "../layout/sprite.ftl" as m>
<@m.sprite/>

<main class="home">
    <div class="home__inner">
        <header class="header">
            <div class="header__inner">
                <a href="/home" class="circle__small-bird">
                    <svg class="header__small-bird">
                        <use xlink:href="#small_bird"></use>
                    </svg>
                </a>
                <nav class="nav">
                    <a href="/home" class="nav__link">
                        <svg class="home__home helper-color">
                            <use xlink:href="#home"></use>
                        </svg>
                        <div class="nav__link-name">
                            <span>Главная</span>
                        </div>
                    </a>
                    <a href="/profile/${userDTO.userId}" class="nav__link">
                        <svg class="profile helper-color">
                            <use xlink:href="#profile"></use>
                        </svg>
                        <div class="nav__link-name">
                            <span>Профиль</span>
                        </div>
                    </a>

                </nav>
                <a href="#" class="nav__link--tweet">Твитнуть</a>
                <a href="/logout" class="nav__link--tweet">Выйти</a>
                <div class="home__small-profile">
                    <div class="home__profile-image">
                        <img src="<@spring.url '/images/default_profile_reasonably_small.png'/>" class="home__img"
                             alt="">
                    </div>
                    <div class="home__small-info">
                        <div class="small__profile-name">${userDTO.userName}
                        </div>
                        <div class="small__profile-alies">${userDTO.userEmail}
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="main__block">

            <div class="col-sm-12 frame">
                <ul id="messagesList" style="margin-top: 30px;">

                </ul>
                <div>
                    <div class="msj-rta macro" style="margin:auto">
                        <div class="text text-r" style="background:whitesmoke !important">
                            <input class="mytext" name="message" id="message" placeholder="Type a message"/>
                            <button onclick="sendMessage($('#message').val(), '${userDTO.userEmail}')">send</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>