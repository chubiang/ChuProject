<#-- @ftlvariable name="returnClass" type="chubiang.model.ReturnClass" -->
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
    <link rel="stylesheet" href="resources/css/simple-sidebar.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <title>Connector</title>
</head>
<body>
<#import "navbar.ftl" as nav>
<#import "sidebarR.ftl" as sidebarR>
<#import "home.ftl" as home>
<#import "login.ftl" as login>
<@nav.layout></@nav.layout>
<div class="container-fluid">
   <#if returnClass.viewName == "home">
        <@home.main></@home.main>
    </#if>
   <#if returnClass.viewName == "login">
        <@login.panel></@login.panel>
   </#if>
</div><!-- /.container -->
<@sidebarR.layout></@sidebarR.layout>
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/popper-utils.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/bootstrap.bundle.js"></script>
<script src="resources/js/login.js"></script>
</body>
</html>