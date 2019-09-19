<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l >
<@c.page>
Registration
    <#if message??>
        ${message}
    </#if>
        <form action="/registration" method="post">
            <div><label> Email: <input type="text" name="email"/ > </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><label> First-name: <input type="text" name="first_name"/> </label></div>
            <div><label> last-name: <input type="text" name="last_name"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
        <#--input type="hidden" name="_csrf" value="${_csrf.token}" /-->
        </form>
</@c.page>