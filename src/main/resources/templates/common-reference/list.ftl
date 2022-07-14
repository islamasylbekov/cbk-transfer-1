<#import "/spring.ftl" as spring/>
<#import "../partials/table/horizontal_table.ftl" as tableMacro/>
<#import "../layout/main.ftl" as main/>

<#assign arr = {"/common-reference" : 'commonReference'} />

<@main.render arr arr>
    <@tableMacro.render table/>
</@main.render>