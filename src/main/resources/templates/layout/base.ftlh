<!DOCTYPE html>
<html lang="en">
<head>
    <title>Twitter</title>
    <link rel="stylesheet" href="/src/main/resources/static/css/style.min.css">
    <#import "/src/main/resources/templates/layout/head.ftlh" as s>
    <@s.head/>
</head>
<body>
<#import "/src/main/resources/templates/layout/sprite.ftl" as m>
<@m.sprite/>

{% block content %}
{% endblock %}
<script src="/src/main/resources/static/js/app.js"></script>
</body>
</html>