<#import "../layout/auth.ftl" as main/>
<#import "/spring.ftl" as spring>

<@main.render>
    <form action="" method="post">
        <div class="row col-md-12">
            <div class="text-center">
                <div class="row text-center" style="color: white; text-align: center">
                    <div class="panel-login-form">
                        <div class="text-center" style="padding-bottom: 20px; color: white">
                            <div class="text-center">
                                <span class="glyphicon glyphicon-user panel-login-form-border"
                                      style="font-size: 50px"></span>
                            </div>
                            <small class="panel-small-fonts-min" style="font-size: 16px;">
                                <@spring.message 'auth.input-login-pass'/>
                            </small>
                        </div>
                        <div class="form-group has-feedback has-feedback-left">
                            <input id="username" type="text" name="username" class="form-control"
                                   placeholder="<@spring.message 'auth.login'/>">
                            <div class="form-control-feedback">
                                <i class="icon-user text-muted"></i>
                            </div>
                        </div>

                        <div class="form-group has-feedback has-feedback-left">
                            <input type="password" name="password" class="form-control"
                                   placeholder="<@spring.message 'auth.pass'/>">
                            <div class="form-control-feedback">
                                <i class="icon-lock2 text-muted"></i>
                            </div>
                        </div>

                        <#if error?? && error>
                            <div class="form-group text-danger">
                                <@spring.message "error.badCredentials"/>
                            </div>
                        </#if>

                        <div class="form-group">
                            <button type="submit" class="btn btn-auth btn-block" style="color: black;">
                                <@spring.message 'auth.login-submit'/>
                                <i class="icon-circle-right2 position-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</@main.render>