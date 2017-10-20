<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <title>Connector</title>
</head>
<body>
<#import "home.ftl" as home>
<@nav.layout></@nav.layout>
<div class="container">
    <#if pageName == "home">
        <@home.layout></@home.layout>
    </#if>


</div><!-- /.container -->
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/popper-utils.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/login.js"></script>
</body>
</html>