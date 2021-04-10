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
            <div class="block__head">
                <h2 class="block__head--title">Главная</h2>
            </div>
            <form action="{% url 'create_twit' %}" method="post" class="block__write-tweet">
                {% csrf_token %}
                <div class="write__tweter-inner">
                    <div class="write__tweet-image">
                        <img src="{% static 'images/default_profile_reasonably_small.png' %}" class="write__tweet-img"
                             alt="">
                    </div>
                    <div class="write__tweet-field">
                    <textarea name="text" class="write__tweet-text" id="" cols="30" rows="10"
                              placeholder="Расскажи что случилось?"></textarea>
                    </div>
                </div>
                <div class="send__tweet">
                    <button class="send__tweet-btn" type="submit">
                        Твитнуть
                    </button>
                </div>
            </form>

            <div class="tweet__items">
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
        <div class="modal fade" id="ex" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Комментарий</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="write__tweter-inner">
                            <div class="tweet__header tweet__header--modal">
                                <div class="tweet__image">
                                    <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                         class="tweet__img">
                                </div>
                            </div>
                            <div class="write__tweet-field">
                            <textarea name="text-tweet" class="write__tweet-text" id="" cols="30" rows="10"
                                      placeholder="Расскажи что случилось?"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="search__block"></div>
    </div>
</main>

</body>
</html>