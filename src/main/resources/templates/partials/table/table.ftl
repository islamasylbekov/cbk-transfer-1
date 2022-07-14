<#import "/spring.ftl" as spring>
<#import "../form.ftl" as form>

<#macro render table addUrl="" collapse=false>
    <#if table.getDropDownButtons()?? && table.getDropDownButtons()?has_content>
    <style>
        .dataTable tbody > tr.selected, .dataTable tbody > tr {
            cursor: pointer;
        }

        .dataTable tbody > tr.selected, .dataTable tbody > tr:hover {
            background-color: #d2d2d2;
        }

        .table-hover > tbody > tr.selected > td {
            background-color: #bfbfbf !important;
        }

        .dt-button-collection > .disabled {
            cursor: default;
        }
    </style>
    </#if>
<div class="row">
    <div class="col-sm-${table.hasFilters()?string("9", "12")}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h5 class="panel-title">${table.title()}</h5>
                <div class="heading-elements">
                    <ul class="icons-list">
                        <li><a data-action="reload"></a></li>
                    </ul>
                </div>
            </div>

        <#--<div class="panel-body">-->
        <#--</div>-->

            <div class="table-responsive table-scrolling">
                <table class="table table-responsive table-hover table-striped table-xxs" id="${table.tableId()}">
                    <thead>
                    <tr>
                        <#list table.getColumns() as column >
                            <th>${column.getTitle()}</th>
                        </#list>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    <#if table.hasFilters() >
        <div class="col-sm-3">
            <div id="${table.tableId()}-filter" class="panel panel-default">
                <div class="panel-heading">
                    <h5 class="panel-title">${springMacroRequestContext.getMessage("table.filters")}</h5>
                    <div class="heading-elements">
                        <ul class="icons-list">
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <form id="${table.tableId()}-filter">
                        <#list table.getFilters() as filter >
                            <#if filter??>
                                <@.vars[filter.node()] filter />
                            </#if>
                        </#list>

                        <div class="text-right">
                            <button type="reset" class="btn btn-default" id="reset"
                                    onclick="resetTable();">${springMacroRequestContext.getMessage("action.reset")} <i
                                    class="icon-reload-alt position-right"></i></button>
                            <a onclick="reloadTable();"
                               class="btn btn-primary">${springMacroRequestContext.getMessage("action.accept")}</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
</div>

<script>

    var dataTable = $('#${table.tableId()}').DataTable({
        autoWidth: ${table.getAutoWidth()?c},
        dom: '<"datatable-header"fBl><"datatable-scroll"t><"datatable-footer"ip>',
        processing: true,
        serverSide: true,
        stateSave: true,
        bFilter: false,
        scrollX: true,
        order: ${table.order()},
        pageLength: ${table.getRowsPerPage()},
        <#if collapse>
            scrollY: '50vh',
            scrollCollapse: true,
        </#if>
        ajax: {
            url: "${table.url()}${addUrl}",
            type: 'POST',
            "contentType": "application/json",
            dataFilter: function (data) {
                return data;
            },
            "data": function (d) {
                return JSON.stringify(d);
            }
        },
        columns: ${table.getJsonColumn()},
        buttons: [
            <#list table.getButtons() as button >
                {
                    text: '${button.text()}',
                    className: 'btn btn-${button.getColor()}',
                    action: function () {
                        console.log('Activated!');
                        window.open('${button.getActionUrl()}', '${button.getActionTarget()}');
                    }
                },
            </#list>
            {
                extend: 'colvis',
                text: '<i class="icon-gear"></i> <span class="caret"></span>',
                className: 'btn btn-default btn-icon'
            }
        ],
        language: {
            lengthMenu: "<@spring.message 'table.showRowsStart'/> _MENU_ <@spring.message 'table.showRowsEnd'/>",
            zeroRecords: "<@spring.message 'table.nothingToShow'/>",
            info: "<@spring.message 'table.showingPageBegin'/> _START_  <@spring.message 'table.showingPageMiddle'/> _END_ <@spring.message 'table.showingPageEnd'/> _TOTAL_",
            paginate: {
                first:      "<@spring.message 'table.firstPage'/>",
                previous:   "<@spring.message 'table.previousPage'/>",
                next:       "<@spring.message 'table.nextPage'/>",
                last:       "<@spring.message 'table.lastPage'/>"
            }
        },
        rowCallback: function (row, data, dataIndex) {

        }
    });

    function reloadTable() {
        var queryData = $('form#${table.tableId()}-filter').serialize();
        dataTable.ajax.url("${table.url()}?" + queryData);
        dataTable.ajax.reload();
    }

    $().ready(function () {
        // Enable Select2 select for the length option
        $('.dataTables_length select').select2({
            minimumResultsForSearch: Infinity,
            width: 'auto'
        });
    });
</script>
</#macro>

<#macro text filter >
<div class="form-group">
    <label class="control-label is-visible">${filter.title()}</label>
    <input type="text" name="${filter.name()}" class="form-control" value="" placeholder="${filter.title()}">
</div>
</#macro>

<#macro selectStaticMultiple filter allowClear=false>
    <#assign replacedPath = filter.name()?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />

<div class="form-group">
    <label class="control-label">${filter.title()}: </label>
    <select id="${selectId}" name="${filter.name()}" multiple>
        <#list filter.getOptions() as option>
            <option value="${option.getSelectorId()}">${option.getSelectorTitle()}</option>
        </#list>
    </select>
</div>

<script type="application/javascript">
    $(function () {
        $("#${selectId}").select2({
            width: "100%",
            allowClear: ${allowClear?c},
            placeholder: "",
            minimumResultsForSearch: Infinity
        });
    });
</script>
</#macro>

<#macro selectStatic filter>
    <#assign replacedPath = filter.name()?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />

<div class="form-group">
    <label class="control-label">${filter.title()}: </label>
    <select id="${selectId}" name="${filter.name()}">
        <option selected></option>
        <#list filter.getOptions() as option>
            <option value="${option.getSelectorId()}">${option.getSelectorTitle()}</option>
        </#list>
    </select>
</div>

<script type="application/javascript">
    $(function () {
        $("#${selectId}").select2({
            width: "100%",
            minimumResultsForSearch: Infinity
        });
    });
</script>
</#macro>

<#macro checkbox filter>
    <#assign replacedPath = filter.name()?replace(".", "-") />

<div class="form-group">
    <div class="checkbox">
        <label>
            <input type="checkbox" name="${replacedPath}" class="styled">
        ${filter.title()}
        </label>
    </div>
</div>
</#macro>

<#macro month filter>
    <#assign replacedPath = filter.name()?replace(".", "-") />

<div class="form-group">
    <label class="control-label is-visible">${filter.title()}:</label><br/>
    <input name="${replacedPath}" id="monthDate" class="month-picker" title=""/>
</div>

<script>
    $(function () {
        $('.month-picker').datepicker(
                {
                    autoclose: true,
                    format: "mm.yyyy",
                    startView: "year",
                    minViewMode: "months"
                })
    });
</script>
</#macro>

<#macro dateRange filter >
<div class="form-group">
    <label>${filter.title()}: </label>
    <div class="input-group">
        <span class="input-group-addon"><i class="icon-calendar22"></i></span>
        <input name="${filter.name()}Range" type="text" class="form-control daterange-filter-${filter.name()}">
        <input name="${filter.name()}Start" type="hidden" id="${filter.name()}StartDate">
        <input name="${filter.name()}End" type="hidden" id="${filter.name()}EndDate">
    </div>
</div>
<script>
    $(function () {
        var selector = $('.daterange-filter-${filter.name()}');
        selector.daterangepicker({
            applyClass: 'bg-slate-600',
            cancelClass: 'btn-default',
            locale: {
                format: 'DD.MM.YYYY'
            }
        });

        selector.on('apply.daterangepicker', function (ev, picker) {
            var startDate = picker.startDate.format('DD.MM.YYYY HH:MM');
            var endDate = picker.endDate.format('DD.MM.YYYY HH:MM');

            $("#${filter.name()}EndDate").val(endDate);
            $("#${filter.name()}StartDate").val(startDate);
        });
    });
</script>
</#macro>

<#macro select filter>
    <#assign replacedPath = filter.name()?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />

<div class="form-group">
    <label class="control-label">${filter.title()}: </label>
    <select id="${selectId}" name="${filter.name()}">
        <option selected></option>
    </select>
</div>

<script type="application/javascript">
    $(function () {
        $("#${selectId}").select2({
            width: "100%",
            ajax: {
                url: "${filter.url()}",
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term, // search term
                        page: params.page
                    };
                },
                processResults: function (data, params) {
                    return {
                        results: data._embedded ? data._embedded.list : [],
                        pagination: {
                            more: ((data.page.number + 1) * data.page.size) < data.page.totalElements
                        }
                    };
                },
                cache: true
            },
            templateSelection: function (data) {
                return data.title || data.text;
            },
            templateResult: function (data) {
                return data.title;
            }
        });
    });
</script>
</#macro>