<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        HELLO!
        <form method="get" action="/main" enctype="multipart/form-data">
            <button type="submit">GO-MAIN</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
<div>
    <form method="get" action="/registration">
        <button type="submit">Registration</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</div>
</@c.page>