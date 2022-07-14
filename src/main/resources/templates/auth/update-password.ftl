<#import "../layout/auth.ftl" as main/>
<#import "/spring.ftl" as spring>
<#import "../partials/vertForm.ftl" as form/>

<@main.render>
    <form action="" method="post">
        <div class="panel-auth text-center">
            <div class="panel-login-form">
                <div class="text-center">
                    <h5 class="content-group">
                        <@spring.message 'auth.update-password'/>
                    </h5>
                </div>

                <@form.inputText "auth.oldPassword" "password" true />
                <@form.inputText "auth.newPassword" "password" true />
                <@form.inputText "auth.confirmPassword" "password" true />

                <div class="form-group">
                    <button type="submit" class="btn btn-auth btn-block" style="color: black;">
                        <@spring.message 'auth.submit'/>
                        <i class="icon-circle-right2 position-right"></i>
                    </button>
                </div>
            </div>
        </div>
    </form>

    <script type="text/javascript">
        $("#username").focus();
    </script>
</@main.render>