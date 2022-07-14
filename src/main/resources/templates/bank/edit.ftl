<#import "/spring.ftl" as spring/>
<#import "../layout/main.ftl" as main/>
<#import "../partials/form.ftl" as form/>

<#assign arr = {"/bank" : 'bank', "#" : 'action.edit'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-12">
            <@form.print "" "action.edit">
                <@form.inputText "domain.name" "text" true "" ""/>
                <@form.inputText "domain.address" "text" true ""/>
                <@form.inputText "domain.moneyCount" "number" true ""/>
                <@spring.formHiddenInput "domain.id" />
            </@form.print>
        </div>
    </div>
</@main.render>