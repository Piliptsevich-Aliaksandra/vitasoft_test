<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>Login</h1>
        <form action="/login" method="post">
            Username <input type="text" name="username" class="form-control"/><br>
            Password <input type="password" name="password" class="form-control">
            <#if RequestParameters.error??>
                <div class="error">
                    Invalid username or password.
                </div>
            </#if>
            <br>
            <button type="submit" value="Sign In" class="btn btn-success">Enter</button>
        </form>
    </div>
</@c.page>