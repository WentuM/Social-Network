<!DOCTYPE html>
<html lang="en">
<head>
    <title>Twitter</title>
    <link rel="stylesheet" href="/static/css/style.min.css">
    <#import "/templates/layout/head.ftlh" as s>
    <@s.head/>
</head>
<body>
<#import "/templates/layout/sprite.ftl" as m>
<@m.sprite/>

{% block content %}
{% endblock %}
<script src="/static/js/app.js"></script>
</body>
</html>