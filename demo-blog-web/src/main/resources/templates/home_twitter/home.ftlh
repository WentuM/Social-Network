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
</head>
<body>
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
                    <a href="/profile" class="nav__link">
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
                        <div class="small__profile-name">${username}
                        </div>
                        <div class="small__profile-alies">${email}
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <div class="main__block">
            <div class="block__head">
                <h2 class="block__head--title">Главная</h2>
            </div>
            <form action="/savePost" method="post" class="block__write-tweet" enctype="multipart/form-data">
                <#--                {% csrf_token %}-->
                <div class="write__tweter-inner">
                    <div class="write__tweet-image">
                        <img src="<@spring.url 'images/default_profile_reasonably_small.png'/>" class="write__tweet-img"
                             alt="">
                    </div>
                    <div class="write__tweet-field">
                    <textarea name="body" class="write__tweet-text" id="" cols="30" rows="10"
                              placeholder="Расскажи что случилось?"></textarea>
                    </div>
                </div>
                <div class="send__tweet">
                    <input class="send__tweet-btn" type="file" name="file">
                </div>
                <div class="send__tweet">
                    <button class="send__tweet-btn" type="submit">
                        Твитнуть
                    </button>
                </div>
            </form>

            <div class="tweet__items">
                <#list posts as post>
                    <a class="tweet__item">
                        <div class="tweet__header">
                            <div class="tweet__image">
                                <img src="<@spring.url 'images/default_profile_reasonably_small.png'/>" alt=""
                                     class="tweet__img">
                            </div>
                            <div class="tweet__name-author">${post.accountDto.userName}</div>
                            <div class="tweet__aliase-author">${post.accountDto.userEmail}</div>
                            <div class="tweet__date-author">{{ twit.date | date:"M d,Y" }}</div>
                        </div>
                        <div class="tweet__text">
                            ${post.body}
                        </div>
                        <div>
                            <#if post.filename??>
                                <img src="<@spring.url 'images/${post.filename}'/>"
                                     class="tweet__image__post" style="width: 100%">
                            </#if>
                        </div>
                        <div style="margin-top: 10px">
                            <form id="form" class="card-link" action="/likePost/${post.id}" method=post>
                                <#--                                    {% csrf_token %}-->
                                <#if post.likePosts??>
                                    <p>Оценили: <span>${post.likePosts?size}</span></p>
                                </#if>
                                <#--                                <input type="hidden" name="post_id" value="{{ post.id }}">-->
                                <#--                                    {% if user not in post.likes.all %}-->
                                <#--                                <a href="#" id="no-like" onclick="document.getElementById('form${post.id}').submit();-->
                                <#--                                        return false;"><img src="<@spring.url 'images/unlike.png'/>"></a>-->
                                <#if post.likePosts?seqContains(userDto)>
                                    <button class="send__tweet-btn" type="submit">
                                        Твитнуть
                                    </button>
                                <#else>
                                    <a href="#" id="no-like" onclick="document.getElementById('form${post.id}').submit();
                                               return false;"><img src="<@spring.url 'images/unlike.png'/>"></a>
                                </#if>
                                <#--                                    {% else %}-->
                                <#--                                <a href="#" id="like" onclick="document.getElementById('form${post.id}').submit();-->
                                <#--                                    return false;"><img src="<@spring.url 'images/like.png'/>"></a>-->
                                <#--                                    {% endif %}-->
                            </form>
                        </div>

                        <form action="/saveComment/${post.id}" method="post">
                            <div class="write__tweet-field">
                            <textarea name="text" class="write__tweet-text" id="" cols="30" rows="10"
                                      placeholder="Написать комментарий"></textarea>
                            </div>
                            <button type="submit" class="tweet__comment">
                                <svg class="tweet__comment-icon">
                                    <use xlink:href="#comment"></use>
                                </svg>
                                <div class="tweet__reply">ответить</div>
                            </button>
                        </form>
                        <#list comments as comment>
                        <div>
                            <div class="media-block">
                                <a class="media-left" href="#"><img class="img-circle img-sm" alt="Профиль пользователя" src="https://bootstraptema.ru/snippets/icons/2016/mia/2.png"></a>
                                <div class="media-body">
                                    <div class="mar-btm">
                                        <a href="#" class="btn-link text-semibold media-heading box-inline">Вася</a>
                                        <p class="text-muted text-sm"><i class="fa fa-mobile fa-lg"></i> - 19-06-2016</p>
                                    </div>
                                    <p>Дада. С формой добавления нового комментария!</p>
                                </div>
                            </div>
                        </div>
                    </a>
                <#else>
                    No posts
                </#list>
            </div>
        </div>
        <div class="modal fade" id="ex" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
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
                                    <img src="<@spring.url 'images/default_profile_reasonably_small.png'/>" alt=""
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