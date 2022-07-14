<#import "/spring.ftl" as spring/>
<#import "../layout/main.ftl" as main/>
<#import "../partials/form.ftl" as form/>

<#assign arr = {"/transfer" : 'transfer', "#" : 'action.edit'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-12">
            <@form.print "" "action.edit">
                <@form.inputText "domain.code" "text" true "" ""/>
                <@spring.formHiddenInput "domain.id" />
            </@form.print>
        </div>
    </div>
</@main.render>