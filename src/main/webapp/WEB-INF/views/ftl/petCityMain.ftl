<#-- @ftlvariable name="returnClass" type="chubiang.model.ReturnClass" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <#--Cascading style sheet-->
    <link rel="stylesheet" href="resources/css/bootstrap.css">
    <link rel="stylesheet" href="resources/css/bootstrap-grid.css">
    <link rel="stylesheet" href="resources/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="resources/css/simple-sidebar.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <title>PetCity</title>
</head>
<body>
<#--Spring form binding with freemarker-->
<#import "/spring.ftl" as spring/>
<#--Layout import ftl-->
<#import "navbar.ftl" as nav>
<#import "sidebarR.ftl" as sidebarR>
<#import "home.ftl" as home>
<#import "login.ftl" as login>
<#--Error Page -->
<#import "error403.ftl" as error403>
<#--Navbar layout-->
<@nav.layout></@nav.layout>

<#--Container layout-->
<div class="container-fluid">
   <#if returnClass.viewName == "home">
        <@home.main></@home.main>
    </#if>
   <#if returnClass.viewName == "login">
        <@login.panel></@login.panel>
   </#if>
    <#if returnClass.viewName == "error403">
        <@error403.error403></@error403.error403>
    </#if>
</div>
<#-- /.container -->

<#--Sidebar menu-->
<@sidebarR.layout></@sidebarR.layout>

<#--Javascript-->
<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="resources/js/popper.min.js"></script>
<script type="text/javascript" src="resources/js/popper-utils.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.bundle.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
</body>
</html>