<#import "/spring.ftl" as spring/>

<#macro page>
    <html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
    </head>
    <body>
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                <use xlink:href="#bootstrap"></use>
            </svg>
        </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="/" class="nav-link px-2 link-dark">Home</a></li>
            <#if admin??>
                <li><a href="/users" class="nav-link px-2 link-dark">Users</a></li>
            </#if>
            <#if operator??>
                <li><a href="/applications" class="nav-link px-2 link-dark">Applications</a></li>
            </#if>
            <#if client??>
                <li><a href="/myApplications" class="nav-link px-2 link-dark">My applications</a></li>
                <li><a href="/createApplication" class="nav-link px-2 link-dark">Add application</a></li>
            </#if>
        </ul>

        <div class="col-md-3 text-end">
            <#if user??>
                <form action="/logout" method="post" class="logout">
                    <button type="button" class="btn btn-outline-primary me-2">${user.username}</button>
                    <button type="submit" class="btn btn-primary">Logout</button>
                </form>
            <#else>
                <button type="button" class="btn btn-outline-primary me-2" onclick="location.href='/login'">Login
                </button>
            </#if>
        </div>
    </header>
    <#nested/>
    <div fragment="footer">
        <footer class="text-muted py-5">
            <div class="container">
                <p class="float-end mb-1">
                    <a href="#">Back to top</a>
                </p>
                <p class="mb-1">Album example is © Bootstrap, but please download and customize it for yourself!</p>
                <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
                            href="/docs/5.3/getting-started/introduction/">getting started guide</a>.</p>
            </div>
        </footer>
    </div>
    </body>
    </html>
</#macro>



<#macro client_app app is_details>
    <div class="alert alert-info mt-2">
        <div class="mb-1 text-muted">${app.getFormattedCreationTime()} by ${app.user.username}</div>
        <p>${app.text}</p>
        <ul class="nav nav-pills">
            <li class="nav-item btn-text">Status: ${app.status}</li>
            <#if is_details>
            <#if app.status == 'DRAFT'>
            <li class="nav-item"><a href="/myApplication/${app.id}/send"
                                    class="btn btn-warning m-l first-btn last-btn">Send</a>
            <li class="nav-item"><a href="/myApplication/${app.id}/edit"
                                    class="btn btn-warning m-l last-btn">Edit</a>
                </#if>
                <#else>
                    <a href="/myApplication/${app.id}" class="btn btn-warning last-btn">Details...</a>
                </#if>
        </ul>
    </div>
</#macro>


<#macro operator_app app is_details>
    <div class="alert alert-info mt-2">
        <div class="mb-1 text-muted">${app.getFormattedCreationTime()} by ${app.user.username}</div>
        <p>${app.getFormattedText()}</p>
        <ul class="nav nav-pills">
            <#if is_details>
                <li class="nav-item"><a href="/application/${app.id}/accept"
                                        class="btn btn-warning m-l first-btn">Accept</a></li>
                <li class="nav-item"><a href="/application/${app.id}/reject"
                                        class="btn btn-warning m-l last-btn">Reject</a>
                </li>
            <#else>
                <a href="/application/${app.id}" class="btn btn-warning last-btn">Details...</a>
            </#if>
        </ul>
    </div>
</#macro>




<#macro error field>
    <@spring.bind field/>
    <#if spring.status.errorMessage??>
        <div class="error">${spring.status.errorMessage}</div>
    </#if>
</#macro>




<#macro sorter url page>
    <p>
        <#if page.getSort()?keep_after(" ") == "DESC">
            <a href="${url}&page=${page.getNumber()}&sort=creationTime,DESC" class="btn btn-secondary my-2 disabled">Sort
                by time
                descending</a>
            <a href="${url}&page=${page.getNumber()}&sort=creationTime,ASC" class="btn btn-primary my-2">Sort by time
                ascending</a>
        <#else>
            <a href="${url}&page=${page.getNumber()}&sort=creationTime,DESC" class="btn btn-primary my-2">Sort by time
                descending</a>
            <a href="${url}&page=${page.getNumber()}&sort=creationTime,ASC" class="btn btn-secondary my-2 disabled">Sort
                by time
                ascending</a>
        </#if>
    </p>
</#macro>




<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1

        head = (pageNumber > 4)?then([1, -1], [1, 2, 3])
        tail = (pageNumber < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
        bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1], [])
        bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1, pageNumber + 2], [])

        body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber], []) + bodyAfter + tail
        >
    <#else>
        <#assign
        body = (page.getTotalPages() == 0)?then([1], 1..page.getTotalPages())
        >
    </#if>
    <div class="mt-3">
        <ul class="pagination">

            <#if page.hasPrevious()>
                <li class="page-item">
                    <a class="page-link"
                       href="${url}&page=${page.getNumber() - 1}&sort=creationTime,${page.getSort()?keep_after(" ")}">Previous</a>
                </li>
            <#else>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
            </#if>

            <#list body as index>
                <#if (index - 1) == page.getNumber()>
                    <li class="page-item active"><a class="page-link" href="#">${index}</a></li>
                <#elseif index == -1>
                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">...</a></li>
                <#else>
                    <#if page.getSort()?ends_with("DESC")>
                        <#assign
                        sort="&sort=CreationTime,desc"
                        >
                    <#else>
                        <#assign
                        sort="&sort=CreationTime,asc"
                        >
                    </#if>
                    <li class="page-item"><a class="page-link"
                                             href="${url}&page=${index - 1}&sort=creationTime,${page.getSort()?keep_after(" ")}">${index}</a>
                    </li>
                </#if>
            </#list>

            <#if page.hasNext()>
                <li class="page-item">
                    <a class="page-link"
                       href="${url}&page=${page.getNumber() + 1}&sort=creationTime,${page.getSort()?keep_after(" ")}">Next</a>
                </li>
            <#else>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Next</a>
                </li>
            </#if>
        </ul>
    </div>
</#macro>