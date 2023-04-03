<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <h3>${userEdit.username}</h3>
        <hr>
        <#list userEdit.roles as role>${role}<#sep>, </#list>
        <br><br>
        <form method="post">
            <#if operatorEdit>
                <button type="submit" class="btn btn-success">Take away the rights of the operator</button>
            <#else>
                <button type="submit" class="btn btn-success">Assign operator rights</button>
            </#if>
        </form>
        <br><br>
    </div>
</@c.page>