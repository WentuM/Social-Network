<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Twitter</title>
    <link rel="stylesheet" href="<@spring.url '/css/style.min.css'/>"/>
    <#import "../layout/head.ftlh" as s>
    <@s.head/>
</head>
<body>
<#import "../layout/sprite.ftl" as m>
<@m.sprite/>

<section class="login">
    <div class="login__container">
        <div class="login__inner">
            <svg class="login__twitter">
                <use xlink:href="#small_bird"></use>
            </svg>
            <h1 class="login__title login__title--registration">Зарегистрироваться <br> в Твиттере</h1>
            <form method="post" class="login__groups">
<#--                {% csrf_token %}-->
<#--                {% if error_password_repeat %}-->
<#--                <div>{{ error_password_repeat }}</div>-->
<#--                {% elif error_email_repeat %}-->
<#--                <div>{{ error_email_repeat }}</div>-->
<#--                {% endif %}-->
                <input type="hidden" name="_csrf" value="{{_csrf.token}}">
                <div class="input__group">
                    <label for="username" class="input__label">Имя</label>
                    <input type="text" id="nick" name="username" class="input__field" required>
                </div>
                <div class="input__group">
                    <label for="email" class="input__label">Адрес электронной почты</label>
                    <input type="email" id="email" name="email" class="input__field" required>
                </div>
                <div class="input__group">
                    <label for="password" class="input__label">Пароль</label>
                    <input type="password" id="password" name="password" class="input__field" required>
                </div>
                <div class="input__group">
                    <label for="repeat_password" class="input__label">Повторить пароль</label>
                    <input type="password" id="repeat_password" name="repeat_password" class="input__field"
                           required>
                </div>
                <div class="login-button">
                    <button class="login__btn" type="submit">Зарегистрироваться</button>
                </div>
                <a href="/signIn" class="login__link">- Войти в Твиттер -</a>
            </form>
        </div>
    </div>
</section>

<script src="/static/js/app.js"></script>
</body>
</html>