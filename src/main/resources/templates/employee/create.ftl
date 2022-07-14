<#import "/spring.ftl" as spring/>
<#import "../layout/main.ftl" as main/>
<#import "../partials/form.ftl" as form/>

<#assign arr = {"/employee" : 'employee', "#" : 'action.create'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-12">
            <@form.print "" "action.create">
                <@form.inputText "domain.login" "text" true "" ""/>
                <@form.inputText "domain.password" "password" true ""/>
                <@form.inputText "domain.surname" "text" true ""/>
                <@form.inputText "domain.name" "text" true ""/>
                <@form.inputText "domain.patronymic" "text" true ""/>
                <@form.inputDate "domain.dateOfBirth" true ""/>
                <@form.inputText "domain.rank" "text" true/>
                <@form.select2 "domain.bank" "/reference/bank" domain.bank!"" "" true/>
                <@form.select2 "domain.role" "/reference/security-role" domain.role!"" "" true/>
                <@form.select2Multiple "domain.roles" "/reference/security-role" domain.roles!""/>
            </@form.print>
        </div>
    </div>
</@main.render>
