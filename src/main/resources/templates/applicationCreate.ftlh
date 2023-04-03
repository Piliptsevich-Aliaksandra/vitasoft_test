<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>Create Application</h1>
        <form method="post">
            <textarea name="text" placeholder="Write post text" class="form-control">${appForm.text!}</textarea><br>
            <@c.error "appForm.text"/><br>
            <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
</@c.page>