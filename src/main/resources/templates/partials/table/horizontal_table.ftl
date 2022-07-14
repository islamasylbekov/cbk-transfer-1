<#import "/spring.ftl" as spring>
<#import "../form.ftl" as form>

<#assign keyNumber = 1>
<#macro render table addUrl="" collapse=false rowNo=false>

<div class="row">
    <#if table.hasFilters() >
        <div class="col-sm-12">
            <div id="${table.tableId()}-filter" class="panel panel-default <#if collapse>collapse</#if>">
                <div class="panel-body">
                    <form id="${table.tableId()}-filter">
                        <div class="row">
                            <#assign filters = table.getFilters()>
                            <#list 0..filters?size-1 as i>
                                <#if (i <= 5)>
                                    <div class="col-sm-2">
                                        <@.vars[filters[i].node()] filters[i] />
                                    </div>
                                </#if>
                            </#list>
                        </div>

                        <div class="row">
                            <#list 0..filters?size-1 as i>
                                <#if (i > 5)>
                                    <div class="col-sm-2">
                                        <@.vars[filters[i].node()] filters[i] />
                                    </div>
                                </#if>
                            </#list>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="text-right">
                                    <a id="${table.tableId()}-search-action"
                                       class="btn btn-primary">${springMacroRequestContext.getMessage("action.accept")}</a>
                                    <button type="reset" class="btn btn-default"
                                            id="${table.tableId()}-filter-clear">

                                        ${springMacroRequestContext.getMessage("action.reset")}
                                        <i class="icon-reload-alt position-right"></i></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </#if>
    <div class="col-sm-12">
        <div id="panel" class="panel panel-default">
            <div class="panel-heading">
                <h5 class="panel-title" id="${table.tableCamelCaseId()}-title">${table.title()}</h5>
            </div>
            <div class="table-responsive table-scrolling">
                <table class="table table-responsive table-hover table-striped table-xxs" id="${table.tableId()}">
                    <thead>
                    <tr>
                        <#if rowNo>
                            <th class="sorting_disabled">№</th>
                        </#if>
                        <#list table.getColumns() as column >
                            <th>${column.getTitle()}</th>
                        </#list>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    var columns = ${table.getJsonColumn()};

    <#if rowNo>
        columns.unshift({
            data: "id",
            name: "id",
            orderable: false,
            searchable: false
        });
        function setRowNo() {
            var start = ${table.tableCamelCaseId()}dataTable.page.info().start;
                ${table.tableCamelCaseId()}dataTable.column(0).nodes().each( function (cell, i) {
                cell.innerHTML = start + i + 1;
            });
        }
    </#if>

    var ${table.tableCamelCaseId()}dataTable = $('#${table.tableId()}').DataTable({
        autoWidth: ${table.getAutoWidth()?c},
        dom: '<"datatable-header"fBl><"datatable-scroll"t><"datatable-footer"ip>',
        processing: true,
        serverSide: true,
        // stateSave: true,
        bFilter: false,
        scrollX: true,
        colReorder: true,
        select: {
            style: 'single'
        },
        pageLength: ${table.getRowsPerPage()},
        order:  ${table.order()},
        ajax: {
            url: "${table.url()}",
            type: 'POST',
            contentType: "application/json",
            dataFilter: function (data) {
                return data;
            },
            data: function (d) {
                return JSON.stringify(d);
            }
        },
        <#if rowNo>
        drawCallback: setRowNo,
        </#if>
        columns: columns,
        buttons: [
            <#list table.getButtons() as button >
                {
                    text: '${button.text()}',
                    className: 'btn btn-${button.getColor()}',
                    action: function () {
                        window.open('${button.getActionUrl()}', '${button.getActionTarget()!"_self"}');
                    }
                },
            </#list>
            <#if table.hasDropDownButtons()>
                <#list table.getDropDownButtons() as dropDownButton >
                    <#if dropDownButton?has_content && dropDownButton.getButtons()?has_content>
                    {
                        extend: 'collection',
                        text: '${dropDownButton.text()}',
                        autoClose: true,
                        className: 'btn btn-${dropDownButton.getColor()} btn-icon',
                        fade: true,
                        buttons: [
                            <#list dropDownButton.getButtons() as button >
                                {
                                    text: '${button.text()}',
                                    className: 'selected-row-button',
                                    action: function () {
                                        if (${table.tableCamelCaseId()}dataTable.rows('.selected').data().length <= 0 && true == ${button.needSelected?c}) {
                                            swal({
                                                title: "Внимание!",
                                                text: "Для работы выделите строку",
                                                type: "warning"
                                            });
                                            return;
                                        }
                                        var id = null;
                                        if (${table.tableCamelCaseId()}dataTable.rows('.selected').data().toArray().length > 0)
                                            id = ${table.tableCamelCaseId()}dataTable.rows('.selected').data().toArray()[0].uuid;

                                        <#if button.getButtonType().toString() == "function">
                                        swal({
                                                    title: "<@spring.message '${button.getTitle()}'/>?",
                                                    text: "<@spring.message 'action.dialog.accept-question'/>",
                                                    type: "warning",
                                                    showCancelButton: true,
                                                    confirmButtonColor: "#EF5350",
                                                    confirmButtonText: '<@spring.message 'action.dialog.accept'/>',
                                                    cancelButtonText: "<@spring.message 'action.dialog.cancel'/>",
                                                    closeOnConfirm: true,
                                                    closeOnCancel: true
                                                },
                                                function (isConfirm) {
                                                    if (!isConfirm)
                                                        return;
                                                    $.ajax({
                                                        url: "${button.getActionUrl()}" + ((id != null) ? "?id=" + id : ""),
                                                        type: "${button.getMethod()!"get"}",
                                                        success: function (data) {
                                                                ${table.tableCamelCaseId()}dataTable.rows('.selected').deselect();
                                                                ${table.tableCamelCaseId()}dataTable.ajax.reload();
                                                        },
                                                        error: function (e1, e2, e3) {
                                                            setTimeout(function () {
                                                                swal({
                                                                    title: "Внимание!",
                                                                    text: e1.responseJSON.message,
                                                                    type: "warning"
                                                                });
                                                            }, 1000)
                                                        }
                                                    })
                                                }
                                        );
                                        <#elseif button.getButtonType().toString() == "action">
                                            <#if button.isDialog()>
                                            swal({
                                                        title: "<@spring.message '${button.getTitle()}'/>?",
                                                        text: "<@spring.message 'action.dialog.accept-question'/>",
                                                        type: "warning",
                                                        showCancelButton: true,
                                                        confirmButtonColor: "#EF5350",
                                                        confirmButtonText: '<@spring.message 'action.dialog.accept'/>',
                                                        cancelButtonText: "<@spring.message 'action.dialog.cancel'/>",
                                                        closeOnConfirm: true,
                                                        closeOnCancel: true
                                                    },
                                                    function (isConfirm) {
                                                        if (!isConfirm)
                                                            return;
                                                        window.open("${button.getActionUrl()}" + (id != null ? "?id=" + id : ""), "_self");
                                                    }
                                            );
                                            <#else>
                                            window.open("${button.getActionUrl()}" + (id != null ? "?id=" + id : ""), "_self");
                                            </#if>
                                        </#if>
                                    }
                                },
                            </#list>
                        ]
                    },
                    </#if>
                </#list>
            </#if>
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

        ${table.tableCamelCaseId()}dataTable
            .on('select', function (e, dt, type, indexes) {
                var rowData = ${table.tableCamelCaseId()}dataTable.rows(indexes).data().toArray()[0];
                    ${table.tableCamelCaseId()}dataTableSelectedRow(rowData);
            })
            .on('deselect', function (e, dt, type, indexes) {
                var rowData = ${table.tableCamelCaseId()}dataTable.rows(indexes).data().toArray()[0];
                    ${table.tableCamelCaseId()}dataTableDeselectedRow(rowData);
            });

    /*
    * #SelectRowCallback
    */
    var ${table.tableCamelCaseId()}dataTableSelectedRow = function (data) {

    };
    /*
    * #SelectRowCallback
    */
    var ${table.tableCamelCaseId()}dataTableDeselectedRow = function (data) {

    };


    $("#${table.tableId()}-search-action").click(function () {
        var queryData = $('form#${table.tableId()}-filter').serialize();
            ${table.tableCamelCaseId()}dataTable.ajax.url("${table.url()}?" + queryData);
            ${table.tableCamelCaseId()}dataTable.ajax.reload();
    });

    /**
     * Pre ajax
     */
        ${table.tableCamelCaseId()}dataTable.on('preXhr.dt', function (e, settings, processing) {
        $(this).block({
            message: '<i class="icon-spinner2 spinner"></i>',
            overlayCSS: {
                backgroundColor: '#fff',
                opacity: 0.8,
                cursor: 'wait',
                'box-shadow': '0 0 0 1px #ddd'
            },
            css: {
                border: 0,
                padding: 0,
                backgroundColor: 'none'
            }
        });
    });

    /**
     * Post ajax
     */
        ${table.tableCamelCaseId()}dataTable.on('xhr.dt', function (e, settings, processing) {
        $(this).unblock();
    });

    $().ready(function () {
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

<#macro selectStatic filter allowClear=true>
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
            allowClear: ${allowClear?c},
            placeholder: "",
            minimumResultsForSearch: Infinity
        });
    });
</script>
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

<#macro checkbox filter>
    <#assign replacedPath = filter.name()?replace(".", "-") />

<div class="form-group">
    <label class="control-label">&nbsp;</label>
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
    <label>${filter.title()}: </label>
    <div class="input-group">
        <span class="input-group-addon"><i class="icon-calendar22"></i></span>
        <input type="text" name="${replacedPath}" id="monthDate" class="form-control month-picker"
               value="${.now?datetime?string('MM.yyyy')}"/>
    </div>
</div>

<script>
    $(function () {
        $('.month-picker').datepicker(
                {
                    autoclose: true,
                    format: "DD.MM.YY",
                    startView: "year",
                    minViewMode: "months"
                });
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
                format: 'DD.MM.YYYY',
                firstDay: 1
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

<#macro select filter allowClear=true>
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
            allowClear: ${allowClear?c},
            placeholder: "",
            ajax: {
                url: "${filter.url()}",
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term, // search term
                        page: isNaN(params.page) ? 0 : params.page - 1
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