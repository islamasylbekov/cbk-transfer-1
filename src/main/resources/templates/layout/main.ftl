<#import "/spring.ftl" as spring/>

<#macro head title>
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
<link href="/assets/css/icons/fontawesome/styles.min.css" rel="stylesheet" type="text/css">
<link href="/assets/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="/assets/css/core.css" rel="stylesheet" type="text/css">
<link href="/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="/assets/css/colors.css" rel="stylesheet" type="text/css">
<link href="/assets/css/app.css" rel="stylesheet" type="text/css">
<link href="/assets/css/body.css" rel="stylesheet" type="text/css">
<link href="/assets/css/corner-indicator.css" rel="stylesheet" type="text/css">
<link href="/assets/css/main.css" rel="stylesheet" type="text/css">
<!-- /global stylesheets -->

<!-- Core JS files -->
<script type="text/javascript" src="/assets/js/core/libraries/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/core/libraries/bootstrap.min.js"></script>
<script type="text/javascript" src="/assets/js/core/libraries/jquery.mask.min.js"></script>
<script type="text/javascript" src="/assets/js/core/libraries/jquery_ui/core.min.js"></script>
<script type="text/javascript" src="/assets/js/core/libraries/jquery_ui/effects.min.js"></script>
<script type="text/javascript" src="/assets/js/core/libraries/jquery_ui/interactions.min.js"></script>
<!-- /core JS files -->

<!-- plugin JS files -->
<script type="text/javascript" src="/assets/js/plugins/loaders/blockui.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/loaders/pace.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/ui/nicescroll.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/ui/drilldown.js"></script>
<script type="text/javascript" src="/assets/js/plugins/notifications/sweet_alert.min.js"></script>
<!-- plugin JS files -->

<#--Datatable-->
<script type="text/javascript" src="/assets/js/plugins/tables/datatables/datatables.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/tables/datatables/extensions/select.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/tables/datatables/extensions/buttons.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/tables/datatables/extensions/col_reorder.min.js"></script>
<#--Datatable-->

<#--Time and pickers-->
<script type="text/javascript" src="/assets/js/plugins/pickers/anytime.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/ui/moment/moment.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/pickers/daterangepicker.js"></script>
<#--Time and pickers-->

<#--Forms-->
<script type="text/javascript" src="/assets/js/plugins/forms/selects/select2.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/forms/styling/uniform.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/forms/styling/switch.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/forms/validation/validate.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/notifications/pnotify.min.js"></script>
<script type="text/javascript" src="/assets/js/plugins/forms/wizards/stepy.min.js"></script>
<#--Forms-->

<script type="text/javascript" src="/assets/js/core/app.js"></script>

<#--Form validate override-->
<script>
    (function ($) {
        jQuery.extend(jQuery.validator.messages, {
            required: "<@spring.message 'validation.form.required-field' />",
            remote: "Please fix this field.",
            email: "Please enter a valid email address.",
            url: "Please enter a valid URL.",
            date: "Please enter a valid date.",
            dateISO: "Please enter a valid date (ISO).",
            number: "Please enter a valid number.",
            digits: "Please enter only digits.",
            creditcard: "Please enter a valid credit card number.",
            equalTo: "Please enter the same value again.",
            accept: "Please enter a value with a valid extension.",
            maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
            minlength: jQuery.validator.format("Please enter at least {0} characters."),
            rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
            range: jQuery.validator.format("Please enter a value between {0} and {1}."),
            max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
            min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
        });
        $.fn.lasronValidate = function () {
            let rules = {};
            let messages = {};

            $(this).find("input").each(function () {
                rules[$(this).attr("name")] = {
                    required: $(this).prop('required'),
                    email: (String($(this).attr("type")).indexOf('email') > -1)
                };
                messages[$(this).attr("name")] = (($(this).prop('required'))
                        ? "<@spring.message 'validation.form.required-field' />"
                        : "")
                        + ((String($(this).attr("name")).indexOf('email') === -1)
                                ? ""
                                : " <@spring.message 'validation.form.input-valid-email' />")
            });

            $(this).find("textarea").each(function () {
                rules[$(this).attr("name")] = {
                    required: $(this).prop('required'),
                    minlength: 2,
                    maxlength: 60000
                };
                messages[$(this).attr("name")] = (($(this).prop('required'))
                        ? "<@spring.message 'validation.form.required-field' />"
                        : "");
            });

            $(this).find("select").each(function () {
                rules[$(this).attr("name")] = {
                    required: $(this).prop('required')
                };
                messages[$(this).attr("name")] = (($(this).prop('required'))
                        ? "<@spring.message 'validation.form.select' />"
                        : "");
            });

            $(this).find("file").each(function () {
                rules[$(this).attr("name")] = {
                    required: $(this).prop('required')
                };
                messages[$(this).attr("name")] = (($(this).prop('required'))
                        ? "<@spring.message 'validation.form.required-field' />"
                        : "");
            });

            validateOptions = {
                ignore: 'input[type=hidden], .select2-search__field',
                rules: rules,
                messages: messages,
                errorPlacement: function (error, element) {

                    // Styled checkboxes, radios, bootstrap switch
                    if (element.parents('div').hasClass("checker") || element.parents('div').hasClass("choice") || element.parent().hasClass('bootstrap-switch-container')) {
                        if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
                            error.appendTo(element.parent().parent().parent().parent());
                        }
                        else {
                            error.appendTo(element.parent().parent().parent().parent().parent());
                        }
                    }

                    // Unstyled checkboxes, radios
                    else if (element.parents('div').hasClass('checkbox') || element.parents('div').hasClass('radio')) {
                        error.appendTo(element.parent().parent().parent());
                    }

                    // Input with icons and Select2
                    else if (element.parents('div').hasClass('has-feedback') || element.hasClass('select2-hidden-accessible')) {
                        error.appendTo(element.parent());
                    }

                    // Inline checkboxes, radios
                    else if (element.parents('label').hasClass('checkbox-inline') || element.parents('label').hasClass('radio-inline')) {
                        error.appendTo(element.parent().parent());
                    }

                    // Input group, styled file input
                    else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
                        error.appendTo(element.parent().parent());
                    }

                    // Input group, styled file input
                    else if (element.prop("tagName") === "TEXTAREA") {
                        error.appendTo(element.parent());
                    }

                    else {
                        error.insertAfter(element);
                    }

                },
                submitHandler: function (form) {
                    form.submit();
                }
            }

            this.validate(validateOptions);
        }
    }(jQuery))
</script>
<#--Form validate override-->

<#setting datetime_format = "iso">

</head>
</#macro>

<#macro userbar>
<div class="navbar navbar-inverse">

    <div class="navbar-header">
        <#if user.hasRole("ROLE_ADMIN")>
            <a class="navbar-brand" href="/employee">Коммерческий банк КЫРГЫЗСТАН</a>
        </#if>
    </div>

    <div class="navbar-header">
        <#if user.hasRole("CASH_DESK_EMPLOYEE")>
            <a class="navbar-brand" href="/transfer">Коммерческий банк КЫРГЫЗСТАН</a>
        </#if>
    </div>
    <p class="navbar-text"><span class="label bg-success-400"><@spring.message profile/></span></p>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown dropdown-user">
                        <a class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/assets/images/placeholder.jpg" alt="">
                            <span>${user.fullName}</span>
                            <i class="caret"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="/auth/update-password?username=${user.username}"><i class="icon-user-lock">
                                </i>Сменить пароль</a></li>
                            <li class="divider"></li>
                            <li><a href="/auth/logout"><i class="icon-switch2"></i>Выход</a></li>
                        </ul>
                    </li>
                </ul>
</div>
</#macro>

<#macro navbar>
<div class="navbar navbar-default" id="navbar-second">
    <div class="navbar-collapse collapse" id="navbar-second-toggle">

         <div class="admin-container">
             <ul class="nav navbar-nav navbar-left">
                <#if user.hasRole("ROLE_ADMIN")>
                    <li>
                        <a href="/employee">
                            <i class="icon-users position-left"></i>
                            Сотрудники
                        </a>
                    </li>

                    <li>
                        <a href="/bank">
                            <i class="icon-list2 position-left"></i>
                            Филиалы
                        </a>
                    </li>
                </#if>
                <li>
                    <a href="/transfer">
                        <i class="icon-list2 position-left"></i>
                        Переводы
                    </a>
                </li>
             </ul>
         </div>
    </div>
</div>
</#macro>

<#macro pageHeader arr>
<div class="page-header">
    <div class="page-header-content">
        <div class="page-title">
            <#list arr as k,v>
                <#assign pageTitle = v/>
            </#list>
            <h4 style="padding-left: 25px">
                <i class="icon-arrow-left52 position-left" style="cursor: pointer" onclick="history.back()"></i>
                <@spring.message pageTitle/>
            </h4>

            <ul class="breadcrumb breadcrumb-caret position-right">
                <#list arr as k,v>
                    <li>
                        <a href="${k}">
                            <@spring.message v/>
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</#macro>

<#macro content>
    <#if error?? && error?has_content>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="alert bg-danger text-center">
                    <button type="button" class="close" data-dismiss="alert"><span>×</span><span
                            class="sr-only">Close</span>
                    </button>
                    <span class="text-semibold">Внимание!</span>
                    ${error}
                </div>
            </div>
        </div>
    </div>
    </#if>
    <#if createReasons?has_content>
    <div class="navbar navbar-default navbar-component">
        <div class="navbar-collapse collapse" id="navbar-content">
            <ul class="nav navbar-nav">
                <li>
                    <div class="dropdown" style="padding-top: 5px">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            <i class="icon-user-plus position-left"></i>
                            <@spring.message "action.create"/>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <#list createReasons as reason>
                                <li><a href="${reason.action}">${reason.title}</a></li>
                            </#list>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    </#if>
    <div class="page-container">
        <div class="page-content">
            <div class="content-wrapper">
                <#nested />
            </div>
        </div>
    </div>
</#macro>

<#macro render breadcrumb={} title={}>
    <!DOCTYPE html>
    <html lang="en">
    <@head title/>
    <body>
        <@userbar/>
        <@navbar/>
        <@pageHeader breadcrumb/>
        <@content>
            <#nested/>
        </@content>
    </body>
    </html>
</#macro>