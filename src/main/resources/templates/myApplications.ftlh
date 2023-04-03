<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <h1>My Applications</h1>
        <@c.sorter url page/>
        <#list page.content as app>
            <@c.client_app app false/>
        </#list>
        <@c.pager url page/>
    </div>
</@c.page>