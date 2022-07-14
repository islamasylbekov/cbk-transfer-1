<#import "../layout/error.ftl" as main />
<#import "/spring.ftl" as spring />

<#assign arr = {} />

<@main.render arr arr>
    <div class="text-center content-group">
        <h1 class="error-title">500</h1>
        <h5>${message!""}</h5>
    </div>
    <div class="row">
        <div class="col-lg-4 col-lg-offset-4 col-sm-6 col-sm-offset-3">
            <div class="row">
                <div class="col-sm-12">
                    <a onclick="history.back()" class="btn btn-primary btn-block content-group">
                        <i class="icon-circle-left2 position-left"></i>
                        <@spring.message 'action.back'/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</@main.render>