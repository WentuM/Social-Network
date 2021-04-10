<!DOCTYPE html>
<html lang="en">
<head>
    <title>Twitter</title>
    <link rel="stylesheet" href="{% static 'css/style.min.css' %}">
    <#import "/templates/layout/head.ftlh" as s>
    <@s.head/>
</head>
<body>
<#import "/templates/layout/sprite.ftl" as m>
<@m.sprite/>

<main class="home">
    <div class="home__inner">
        <header class="header">
            <div class="header__inner">
                <a href="{% url 'home' %}" class="circle__small-bird">
                    <svg class="header__small-bird">
                        <use xlink:href="#small_bird"></use>
                    </svg>
                </a>
                <nav class="nav">
                    <a href="{% url 'home' %}" class="nav__link">
                        <svg class="home__home helper-color">
                            <use xlink:href="#home"></use>
                        </svg>
                        <div class="nav__link-name">
                            <span>Главная</span>
                        </div>
                    </a>
                    <a href="{% url 'profile' twitter_user.username id %}" class="nav__link">
                        <svg class="profile helper-color">
                            <use xlink:href="#profile"></use>
                        </svg>
                        <div class="nav__link-name">
                            <span>Профиль</span>
                        </div>
                    </a>

                </nav>
                {#                <a href="#" class="nav__link--tweet">Твитнуть</a>#}
                <a href="{% url 'logout' %}" class="nav__link--tweet">Выйти</a>
                <div class="home__small-profile">
                    <div class="home__profile-image">
                        <img src="{% static 'images/default_profile_reasonably_small.png' %}" class="home__img" alt="">
                    </div>
                    <div class="home__small-info">
                        <div class="small__profile-name">{{ twitter_user.username }}
                        </div>
                        <div class="small__profile-alies">@{{ twitter_user.user_id }}
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="main__block">
            <div class="block__head block__head-profile">
                <a href="{% url 'home' %}" class="arrow__back">
                    <svg class="arrow">
                        <use xlink:href="#arrow"></use>
                    </svg>
                </a>
                <h2 class="block__head--title">{{ twitter_user.username }}</h2>
            </div>
            <div class="profile__photo">
                <div class="profile__circle--photo">
                    <img src="{% static 'images/default_profile_400x400.png' %}" alt="" class="profile__img">
                </div>
            </div>
            <div class="profile__info">
                <a href="{% url 'logout' %}" class="profile__edit">
                    <span class="edit__text">Выйти из профиля</span></a>
                <h1 class="profile__name">{{ twitter_user.username }}</h1>
                <div class="profile__alies">@{{ twitter_user.user_id }}</div>
                <div class="profile__alies">Joined: {{ twitter_user.create_time | date:"Y.m.d" }}</div>
                <div class="profile__count-tweets">
                    <b class="profile__count">{{ count }}</b>
                    Твиты
                </div>
            </div>
            <div class="profile__tweets">
                <div class="profile__tweets-header">
                    Tweets
                </div>
                {% if twits %}
                {% for twit in twits %}
                <a href="{% url 'single_twit' twit.user_creator.user_id twit.pk%}" class="tweet__item">
                    <div class="tweet__header">
                        <div class="tweet__image">
                            <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                 class="tweet__img">
                        </div>
                        <div class="tweet__name-author">{{ twit.user_creator.username }}</div>
                        <div class="tweet__aliase-author">@{{ twit.user_creator.user_id }}</div>
                        <div class="tweet__date-author">{{ twit.date | date:"M d,Y" }}</div>
                    </div>
                    <div class="tweet__text">
                        {{ twit.text }}
                    </div>

                    <button type="button" class="tweet__comment">
                        <svg class="tweet__comment-icon">
                            <use xlink:href="#comment"></use>
                        </svg>
                        <div class="tweet__reply">ответить</div>
                    </button>

                </a>
                {% endfor %}
                {% endif %}
            </div>
        </div>
        <div class="search__block"></div>
    </div>
</main>

</body>
</html>