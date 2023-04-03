<#import "commons.ftlh" as c>

<@c.page>
    <div class="container mt-5">
        <form method="post" class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${text!}">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">Username</th>
                    <th scope="col">Roles</th>
                    <th scope="col">Change user operator rights</th>
                </tr>
                </thead>
                <tbody>
                <#list users as user>
                    <tr>
                        <td>${user.username}</td>
                        <td>
                            <#list user.roles as role>${role}<#sep>, </#list>
                        </td>
                        <td>
                            <a href = 'user/${user.id}'>Edit</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>