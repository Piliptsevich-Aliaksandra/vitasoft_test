<#import "commons.ftlh" as c>

<@c.page>
<h1>Hello!</h1>
<form action="/logout" method="post">
    <input type="submit" value="Sign Out"/>
</form>
</@c.page>