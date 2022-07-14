<#macro head>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>
            <@spring.message "application.name"/>
        </title>

        <!-- Global stylesheets -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" type="text/css">
        <link href="/assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
        <link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="/assets/css/components.min.css" rel="stylesheet" type="text/css">
        <link href="/assets/css/core.css" rel="stylesheet" type="text/css">
        <link href="/assets/css/colors.css" rel="stylesheet" type="text/css">
        <link href="/assets/css/app.css" rel="stylesheet" type="text/css">
        <!-- /global stylesheets -->

        <!-- Core JS files -->
        <script type="text/javascript" src="/assets/js/plugins/loaders/pace.min.js"></script>
        <script type="text/javascript" src="/assets/js/core/libraries/jquery.min.js"></script>
        <script type="text/javascript" src="/assets/js/core/libraries/bootstrap.min.js"></script>
        <!-- /core JS files -->
    </head>
</#macro>

<#macro userbar>
    <div class="navbar navbar-inverse">
        <div class="navbar-collapse collapse" id="navbar-mobile">
            <p class="navbar-text">
                <span class="label bg-success-400">
                    <@spring.message profile/>
                </span>
            </p>
        </div>
    </div>
</#macro>

<#macro content>
    <div class="page-container">
        <div class="page-content">
            <div class="content-wrapper">
                <#nested />
            </div>
        </div>
    </div>
</#macro>

<#macro render>
    <!DOCTYPE html>
    <html lang="en">
    <@head/>

    <body class="login-container login-cover">
    <@userbar/>
    <@content>
        <#nested/>
    </@content>
    </body>
    </html>
</#macro>