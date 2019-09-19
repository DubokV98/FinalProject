<#macro login path>
        <form action="${path}" method="post">
            <#if message??>
                ${message}
            </#if>
            <div><label> Email : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Enter"/></div>
            <#--input type="hidden" name="_csrf" value="${_csrf.token}"/-->
        </form>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
        <#--input type="hidden" name="_csrf" value="${_csrf.token}"/-->
            <input type="submit" value="Exit"/>
        </form>
    </div>
</#macro>

