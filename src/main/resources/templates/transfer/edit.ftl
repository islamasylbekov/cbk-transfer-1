<#import "/spring.ftl" as spring/>
<#import "../layout/main.ftl" as main/>
<#import "../partials/form.ftl" as form/>

<#assign arr = {"/transfer" : 'transfer', "#" : 'action.edit'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-12">
            <@form.print "" "action.edit">
                <@form.inputText "domain.senderName" "text" true "" ""/>
                <@form.inputText "domain.senderSurname" "text" true ""/>
                <@form.inputText "domain.senderPatronymic" "text" true ""/>
                <@form.inputText "domain.recipientName" "text" true ""/>
                <@form.inputText "domain.recipientSurname" "text" true ""/>
                <@form.inputText "domain.recipientPatronymic" "text" true ""/>
                <@form.inputText "domain.moneyCount" "number" true ""/>
                <@spring.formHiddenInput "domain.id" />
            </@form.print>
        </div>
    </div>
</@main.render>