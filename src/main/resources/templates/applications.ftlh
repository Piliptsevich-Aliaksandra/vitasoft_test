<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <form method="post" class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${text!}">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <@c.sorter url page/>
        <#list page.content as app>
            <@c.operator_app app false/>
        </#list>
        <@c.pager url page/>
    </div>
</@c.page>