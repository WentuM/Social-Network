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

<div class="start__page">
    <div class="start__main">
        <div class="start__left">
            <svg class="start__big-bird">
                <use xlink:href="#big_bird"></use>
            </svg>
            <div class="start__info">
                <ul class="start__info-items">
                    <li class="start__info-item">
                        <svg class=start__search>
                            <use xlink:href="#search"></use>
                        </svg>
                        <div class="start__info-item--text">
                                <span>
                                    Читайте о том, что вам интересно.
                                </span>
                        </div>
                    </li>
                    <li class="start__info-item">
                        <svg class=start__group>
                            <use xlink:href="#group"></use>
                        </svg>
                        <div class="start__info-item--text">
                                <span>
                                    Узнайте, о чем говорят в мире.
                                </span>
                        </div>
                    </li>
                    <li class="start__info-item">
                        <svg class=start__comment>
                            <use xlink:href="#comment"></use>
                        </svg>
                        <div class="start__info-item--text">
                                <span>
                                    Присоединяйтесь к общению.
                                </span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <section class="start__right">
            <div class="start__right-inner">
                <svg class="small__bird small__bird-animation">
                    <use xlink:href="#small_bird"></use>
                </svg>
                <h2 class="start__right-title">Узнайте, что происходит <br> в мире прямо сейчас</h2>
                <p class="start__right-subtitle">Присоединяйтесь к Твиттеру прямо сейчас!</p>
                <div class="start__buttons">
                    <a href="/signUp" class="start__registration-btn">
                        Зарегистрироваться
                    </a>
                    <a href="/signIn" class="start__input-btn">Войти</a>
                </div>
            </div>
        </section>
    </div>
</div>

<script src="/static/js/app.js"></script>
</body>
</html>