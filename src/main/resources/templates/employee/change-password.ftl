<#import "/spring.ftl" as spring/>
<#import "../layout/main.ftl" as main/>
<#import "../partials/form.ftl" as form/>

<#assign arr = {"/employee" : 'employee', "#" : 'employee.changePassword'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-12">
            <@form.print "" "employee.changePassword">
                <@form.inputText "domain.newPassword" "password" true "" ""/>
                <@form.inputText "domain.confirmPassword" "password" true "" ""/>
            </@form.print>
        </div>
    </div>
</@main.render>