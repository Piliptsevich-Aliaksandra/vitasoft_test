<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5 mb-5">
        <h1>Edit Application</h1>
        <form method="post">
            <textarea name="text" placeholder="Write post text" class="form-control">${appForm.text!}</textarea><br>
            <@c.error "postForm.text"/><br>
            <button type="submit" class="btn btn-success">Update</button>
        </form>
    </div>
</@c.page>