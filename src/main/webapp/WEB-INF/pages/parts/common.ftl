<#import "../parts/login.ftl" as l >
<#macro page>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GAME_TRADE_PLATFORM</title>
    <a href="/">Main page</a>
    <br>
        <@l.logout/>
    <br>
</head>
<body>
<#nested>
</body>
</html>
</#macro>