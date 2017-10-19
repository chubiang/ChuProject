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
    <link rel="stylesheet" href="resources/css/connectorMain.css">
    <title>Connector</title>
</head>
<body>
<#macro page pageName>
    <#if pageName == "login">
    <#elseif pageName == "home">
    <#elseif pageName == "community">
    </#if>
</#macro>
<@nav.layout></@nav.layout>
<div class="container">
    <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
    </div>

</div><!-- /.container -->
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/popper.min.js"></script>
<script src="resources/js/popper-utils.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>