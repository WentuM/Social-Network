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

<script src="/static/js/vue.js"></script>
<section class="login">
    <div class="login__container">
        <div class="login__inner">
            <svg class="login__twitter">
                <use xlink:href="#small_bird"></use>
            </svg>
            <h1 class="login__title">Войти в Твиттер</h1>
            <form method="post" class="login__groups">
<#--                {% csrf_token %}-->
<#--                {% if success_registration %}-->
<#--                <div>{{ success_registration }}</div>-->
<#--                {% elif error_auth %}-->
<#--                <div>{{ error_auth }}</div>-->
<#--                {% endif %}-->
                <input type="hidden" name="_csrf" value="{{_csrf.token}}">
                <div class="input__group">
                    <label for="email" class="input__label">Адрес электронной почты</label>
                    <input type="email" id="email" name="email" class="input__field" required>
                </div>
                <div class="input__group">
                    <label for="password" class="input__label">Пароль</label>
                    <input type="password" id="password" name="password" class="input__field" required>
                </div>
                <label>
                    <input type="checkbox" name="remember-me">
                    Запомни меня
                </label>
                <div class="login-button">
                    <button class="login__btn" type="submit">Войти</button>
                </div>
            </form>
            <a href="/signUp" class="login__link">- Зарегистрироваться в Твиттере -</a>
        </div>
    </div>
</section>
<script>
    let app = Vue.createApp({

    });

    app.mount('')
</script>
<script src="/static/js/app.js"></script>
</body>
</html>