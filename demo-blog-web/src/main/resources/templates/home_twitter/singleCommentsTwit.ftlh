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
            <div class="block__head">
                <h2 class="block__head--title">Твит</h2>
            </div>
            <div class="tweet__items">
                <a href="{% url 'single_twit' twit_info.user_creator.user_id twit_info.pk %}" class="comment__item--over">
                    <div class="tweet__header">
                        <div class="tweet__image tweet__image--over">
                            <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                 class="tweet__img tweet__img--over">
                        </div>
                        <div class="tweet__info--over">
                            <div class="tweet__short-info--over">
                                <div class="tweet__name-author">{{ twit_info.user_creator.username }}</div>
                                <div class="tweet__aliase-author">@{{ twit_info.user_creator.user_id }}</div>
                                <div class="tweet__date-author">{{ twit_info.date | date:"M d,Y H:i" }}</div>
                            </div>
                            <div class="tweet__text comment__text tweet__text--over">
                                {{ twit_info.text }}
                            </div>
                        </div>
                    </div>
                </a>

                {% if over_comments %}
                {% for over_comment in over_comments %}
                <a href="{% url 'single_comment' twit_info.pk over_comment.pk %}"
                   class="comment__item--over">
                    <div class="tweet__header">
                        <div class="tweet__image tweet__image--over">
                            <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                 class="tweet__img tweet__img--over">
                        </div>
                        <div class="tweet__info--over">
                            <div class="tweet__short-info--over">
                                <div class="tweet__name-author">{{ over_comment.user_commentor.username }}</div>
                                <div class="tweet__aliase-author">@{{ over_comment.user_commentor.user_id }}</div>
                                <div class="tweet__date-author">{{ over_comment.date | date:"M d,Y H:i" }}</div>
                            </div>
                            <div class="tweet__text comment__text tweet__text--over">
                                {{ over_comment.comment_text }}
                            </div>
                        </div>
                    </div>
                </a>
                {% endfor %}
                {% endif %}

                {% if single_comment %}
                <div class="tweet__item tweet__item--singletweet">
                    <div class="tweet__header singletweet__header">
                        <div class="singletweet__info">
                            <div class="tweet__image singletweet__image">
                                <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                     class="tweet__img singlet__img">
                            </div>
                            <div class="singlettweet__author">
                                <div class="tweet__name-author singlet__name">{{ single_comment.user_commentor.username }}</div>
                                <div class="tweet__aliase-author singlet__aliase">
                                    @{{ single_comment.user_commentor.user_id }}</div>
                            </div>
                        </div>
                    </div>
                    <div class="tweet__text singlettweet__text">
                        {{ single_comment.comment_text }}
                    </div>
                    <div class="tweet__date-author singlettweet__date">{{ single_comment.date | date:"M d,Y H:i" }}</div>

                    <button type="button" class="tweet__comment singlet__comment-btn" data-toggle="modal"
                            data-target="#{{ single_comment.user_commentor.user_id }}">
                        <div class="singleet__help-flex">
                            <svg class="tweet__comment-icon">
                                <use xlink:href="#comment"></use>
                            </svg>
                            <div class="tweet__reply">ответить</div>
                        </div>
                    </button>

                </div>
                {% endif %}
            </div>
            <div class="singletweet__comments">
                {% if subcomments %}
                {% for subcomment in subcomments %}
                <a href="{% url 'single_comment' twit_info.pk subcomment.pk %}" class="comment__item">
                    <div class="tweet__header">
                        <div class="tweet__image">
                            <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                 class="tweet__img">
                        </div>
                        <div class="tweet__name-author">{{ subcomment.user_commentor.username }}</div>
                        <div class="tweet__aliase-author">@{{ subcomment.user_commentor.user_id }}</div>
                        <div class="tweet__date-author">{{ subcomment.date | date:"M d,Y H:i" }}</div>
                    </div>
                    <div class="tweet__text comment__text">
                        {{ subcomment.comment_text }}
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



        <div class="modal fade" id="{{ single_comment.user_commentor.user_id }}" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <form class="modal-content" action="{% url 'create_subcomment' twit_info.pk single_comment.pk %}"
                      method="post">
                    {% csrf_token %}
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Комментарий</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="comment__item">
                            <div class="tweet__header">
                                <div class="tweet__image">
                                    <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                         class="tweet__img">
                                </div>
                                <div class="tweet__name-author">{{ single_comment.user_commentor }}</div>
                                <div class="tweet__aliase-author">@{{ single_comment.user_commentor.user_id }}</div>
                                <div class="tweet__date-author">{{ single_comment.date | date:"M d,Y H:i" }}</div>
                            </div>
                            <div class="tweet__text comment__text">
                                {{ single_comment.comment_text }}
                            </div>
                        </div>
                        <div class="write__tweter-inner">
                            <div class="tweet__header tweet__header--modal">
                                <div class="tweet__image">
                                    <img src="{% static 'images/default_profile_reasonably_small.png' %}" alt=""
                                         class="tweet__img">
                                </div>
                            </div>
                            <div class="write__tweet-field">
                            <textarea name="comment_text" class="write__tweet-text" id="" cols="30" rows="10"
                                      placeholder="Расскажи что случилось?"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="search__block"></div>
    </div>
</main>

</body>
</html>