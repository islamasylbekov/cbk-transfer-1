<#import "/spring.ftl" as spring/>
<#import "../partials/table/horizontal_table.ftl" as tableMacro/>
<#import "../layout/main.ftl" as main/>

<#assign arr = {"/transfer" : 'transfer'} />

<@main.render arr arr>
    <div class="row">
        <div class="col-md-2">
            <div class="sidebar sidebar-main sidebar-default">
                <div class="sidebar-content">
                    <div class="sidebar-category sidebar-category-visible">
                        <div class="category-title h6">
                            <span>Навигация</span>
                        </div>
                        <div class="category-content no-padding" >
                            <ul class="navigation navigation-main navigation-accordion" >
                                <#list statuses as item>
                                    <li class="custom-li">
                                        <a class="classA" href="/transfer/${item.getCode()!""}">
                                            <i class="icon-coin-dollar"></i> ${item.getTitle()!""}
                                        </a>
                                    </li>
                                </#list>
                                <li class="custom-li">
                                    <a class="classA" href="/transfer">
                                        <i class="icon-coin-dollar"></i> Все
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-7">
            <@tableMacro.render table/>
        </div>
        <div class="col-md-3" data-info>

        </div>
    </div>
    <script>
        let userId = ${user.id}
        var ${table.tableCamelCaseId()}dataTableSelectedRow = function (data) {
            let code = ``
            let btnGetMoney = ``
            if (userId == data.employee.id)
                code = `<p><span class="btn btn-sm btn-danger">КОД:</span>` + data.code + `</p>`;
            if(data.status == "ОТПРАВЛЕНО")
                btnGetMoney = `<a class="btn btn-primary" href="/transfer/getMoney?id=` + data.id + `">Получить</a>`;
            $("[data-info]").append(`
                <div class="panel panel-danger">
                    <div class="panel-heading danger">
                    </div>
                    <div class="panel-body">
                    `+ code +`
                        <p><span class="btn btn-sm btn-danger">ОТПРАВИТЕЛЬ:</span> ` + data.senderFullName + `</p>
                        <p><span class="btn btn-sm btn-danger">БАНК ОТПРАВИТЕЛЬ:</span> ` + data.senderBank + `</p>
                        <p><span class="btn btn-sm btn-danger">ПОЛУЧАТЕЛЬ:</span> ` + data.recipientFullName + `</p>
                        <p><span class="btn btn-sm btn-danger">СУММА:</span> ` + data.moneyCount + `</p>
                        <p><span class="btn btn-sm btn-danger">СТАТУС:</span> ` + data.status + `</p>
                        <center> ` + btnGetMoney + ` </center>
                    </div>
                </div>
                <button class="btn btn-sm">ascasc</button>
            `)
        };
        var ${table.tableCamelCaseId()}dataTableDeselectedRow = function (data) {
            $("[data-info]").empty()
        };
    </script>
</@main.render>