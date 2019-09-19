<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        It's main page
        <form method="post" action="/" enctype="multipart/form-data">
            <table>
                <#list users as user>
                    <h1>${user.id}</h1>
                    <tr>${user.first_name}</tr>
                </#list>
            </table>
            <button type="submit">ADD</button>
        <#--input type="hidden" name="_csrf" value="${_csrf.token}" /-->
        </form>
    </div>

</@c.page>