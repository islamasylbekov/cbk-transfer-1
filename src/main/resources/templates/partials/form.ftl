<#import "/spring.ftl" as spring>

<#macro label path required=false>
<label class="control-label col-sm-4">
    <@spring.message path/>:
<#--<i class="icon-info3" data-popup="tooltip" title="<@spring.message "help.${path}"/>"></i>-->
    <#if required><span style="color:red">*</span></#if></label>
</#macro>

<#macro inputText path fieldType="text" required=false class="" attributes="" allowed=true >
<div class="form-group">
    <@spring.bind path />
    <#assign attributesOverride = attributes + required?string(" required","")/>
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formInput path 'class="form-control'+(class??)?then(" "+class,"")+'" '+attributesOverride fieldType/>
                <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
</#macro>

<#macro inputAddress path required=false>
    <@inputText "${path}.address" "text" required/>
    <@inputText "${path}.addressMark" "text"/>
</#macro>

<#macro inputTextAtlernative path fieldType="text" placeholder="" required=false class="" attributes="" allowed=true >
<div class="form-group">
    <@spring.bind path />
    <#assign attributesOverride = attributes + required?string("required","") + " placeholder=${placeholder}"/>
    <@label path required/>
    <@spring.formInput path 'class="form-control'+(class??)?then(" "+class,"")+'" '+attributesOverride fieldType/>
            <@spring.showErrors "<br>" "text-danger" />
</div>
</#macro>

<#macro month filter defaultDate>
    <#assign replacedPath = filter?replace(".", "-") />
<div class="form-group">
    <label><@spring.message '${filter}'/>:
    </label>
    <div class="input-group">
        <span class="input-group-addon"><i class="icon-calendar22"></i></span>
        <input type="text" name="${replacedPath}" id="${replacedPath}-month-date" class="form-control month-picker"
               value="${.now?datetime?string('MM.yyyy')}"/>
    </div>
</div>
<script>
    $(function () {
        $('.month-picker').datepicker(
                {
                    autoclose: true,
                    format: "mm.yyyy",
                    startView: "year",
                    minViewMode: "months"
                });
        <#if defaultDate?? && defaultDate?has_content>
            $('.month-picker').datepicker("setDate", new Date("${defaultDate}".split(".")[1], "${defaultDate}".split(".")[0] - 1, 01));
        </#if>
    });
</script>
</#macro>

<#macro inputDateTime path required=false offset=0>
<div class="form-group">
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <#assign dateId = "anytime-month-numeric-${replacedPath}" />
    <@label path required />
    <div class="col-sm-8">
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-calendar3"></i></span>
            <input type="text" name="${spring.status.expression}"
                   class="form-control anytime-both" ${required?string("required","")}
                   id="${dateId}"
                   <#if spring.stringStatusValue?? && spring.stringStatusValue?has_content>value="${spring.stringStatusValue?string}"</#if>
                   placeholder="${springMacroRequestContext.getMessage(path)}">
        </div>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
<script>
    $("#${dateId}").AnyTime_picker({
        format: "%d.%m.%Z %H:%i",
        firstDOW: 1,
        beforeShow: function () {
            setTimeout(function () {
                $('.ui-datepicker').css('z-index', 99999999999999);
            }, 0);
        }
    });

    if (0 != ${offset}) {
        var d8 = new Date();
        d8.setHours(d8.getHours() + ${offset});
        $('#${dateId}').val(new AnyTime.Converter({format: "%d.%m.%Z %H:%i"}).format(d8));
    }
</script>
</#macro>

<#macro inputDateTimeNoBind path required=false id="action-select-datetime">
<div class="form-group" id="selected-rows-datepicker-holder">
    <#assign replacedPath = path?replace(".", "-") />
    <#assign dateId = "anytime-month-numeric-${id}" />
    <@label path required />
    <div class="col-sm-8">
        <div class="input-group">
            <span class="input-group-addon"><i class="icon-calendar3"></i></span>
            <input type="text"
                   class="form-control anytime-both" ${required?string("required","")}
                   id="${dateId}">
        </div>
    </div>
</div>
<script>
    $("#${dateId}").AnyTime_picker({
        format: "%d.%m.%Z %H:%i",
        firstDOW: 1,
        beforeShow: function () {
            setTimeout(function () {
            }, 0);
        }
    });
    $('.AnyTime-win').css('z-index', 2000);
</script>
</#macro>

<#macro inputDate path required=false attributes="">
<div class="form-group">
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <#assign dateId = "anytime-month-numeric-${replacedPath}" />
    <@label path required/>
    <div class="col-sm-8">
        <div class="input-group">
            <span id="${dateId}-button"
                  class="input-group-addon">
                <i class="icon-calendar3"></i>
            </span>
            <input type="text" name="${spring.status.expression}"
                ${required?string("required","")}
                   id="${dateId}"
                   class="form-control anytime-both"
                   <#if spring.stringStatusValue?? && spring.stringStatusValue?has_content>value="${spring.stringStatusValue?string}"</#if>
                   placeholder="${springMacroRequestContext.getMessage(path)}" ${attributes}>
            <@spring.showErrors "<br>" "text-danger" />
            <span id="${dateId}-clear"
                  class="input-group-addon">
                <i class="icon-close2"></i>
            </span>
        </div>
    </div>
</div>
    <script>
        $("#${dateId}-button").click(function () {
            $("#${dateId}")
                .AnyTime_noPicker()
                .AnyTime_picker({
                    labelTitle: "Выберите дату",
                    labelYear: "Год",
                    labelMonth: "Месяц",
                    labelDayOfMonth: "День",
                    dayNames: ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"],
                    dayAbbreviations: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
                    monthAbbreviations: ["Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек"],
                    monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                    format: "%d.%m.%Z",
                    firstDOW: 1,
                    beforeShow: function () {
                        setTimeout(function () {
                            $('.ui-datepicker').css('z-index', 99999999999999);
                        }, 0);
                    }
                })
                .focus();
        });

        $("#${dateId}-clear").click(function () {
            $("#${dateId}").AnyTime_noPicker();
            $("#${dateId}").val("").change();
        });
    </script>
</#macro>

<#macro dateRange path value="">
<div class="form-group">
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <@label path/>
    <div class="col-sm-8">
        <input name="${spring.status.expression}" value="${value}" type="text"
               class="form-control daterange-filter-${replacedPath}">
    </div>
</div>
<script>
    $(function () {
        var selector = $('.daterange-filter-${replacedPath}');
        selector.daterangepicker({
            applyClass: 'bg-slate-600',
            cancelClass: 'btn-default',
            locale: {
                cancelLabel: 'Очистить',
                format: 'DD.MM.YYYY',
                firstDay: 1
            }
        });

        selector.on('apply.daterangepicker', function (ev, picker) {
            var startDate = picker.startDate.format('DD.MM.YYYY HH:MM');
            var endDate = picker.endDate.format('DD.MM.YYYY HH:MM');

            $("#${path}EndDate").val(endDate);
            $("#${path}StartDate").val(startDate);
        });
        selector.on('cancel.daterangepicker', function (ev, picker) {
            selector.val('');
        });
    });
</script>
</#macro>

<#macro simpleSelect path options required=false>
<div class="form-group">
    <@spring.bind path />
    <@label path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
    <div class="col-sm-8">
        <select id="${selectId}" name="position" class="form-control">
            <@enumSelect "positionsSelectName" positions/>
        </select>
    </div>
</div>

<script type="application/javascript">
    $("#${selectId}").select2({
        width: "100%",
        minimumResultsForSearch: Infinity
    });
</script>
</#macro>

<#macro fileInput path required=false fileMask="">
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <input name="${path}" type="file" class="file-styled" accept="${fileMask}">
    </div>
</div>
</#macro>

<#macro fileInputBind path required=false fileMask="">
    <@spring.bind path/>
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <input name="${spring.status.expression}" type="file" class="file-styled" accept="${fileMask}">
    </div>
</div>
</#macro>

<#macro knowledgeAttachments path attachments required=false limit=6>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "knowledge-attachments-${replacedPath}" />
    <#assign counter = 0 />
<div class="panel panel-flat">
    <div class="panel-heading">
        <h6 class="panel-title"><@spring.message 'article.attachments'/></h6>
        <div class="heading-elements">
             <span class="btn btn-primary" id="${selectId}-add-attachment">
                <span><i class="icon-plus2 position-left"></i>
                    <@spring.message 'article.attachments.add'/>
                </span>
             </span>
        </div>
    </div>
    <div id="${selectId}-attachments-list" class="panel-body">
        <#if attachments?? && attachments?has_content>
            <#list attachments as attachment>
                <div class="panel panel-flat border-top-warning col-sm-12">
                    <span class='pull-right ${selectId}-attachment-delete'
                          style="padding: 5px;"
                          data-id="#{attachment.id}">
                        <i class='icon-close2 position-left'></i>
                    </span>
                    <input name="attachments[${counter}].id" type="hidden" value="#{attachment.id}">
                    <br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.title'/>
                        *</label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].title" type="text" value="${attachment.title}"
                               class="form-control" required>
                    </div>
                    <br><br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.description'/></label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].description" value="${attachment.description}"
                               type="text" class="form-control">
                    </div>
                    <br><br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.file-name'/>
                        *</label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].originalName"
                               value="${attachment.originalName}"
                               type="text" disabled class="form-control">
                    </div>
                    <br> <br> <br>
                </div>
                <#assign counter++/>
            </#list>
        </#if>
    </div>

    <div id="${selectId}-modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-info">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="select-modal-title">
                        <@spring.message 'article.attachments.add'/>
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.title'/>: *
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="${selectId}-attachment-title">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.description'/>:
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="${selectId}-attachment-desc">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.file-name'/>: *
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="file" class="file-styled" id="${selectId}-attachment-file">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                <span id="${selectId}-accept-modal" class="btn btn-success">
                    <@spring.message 'modal.accept'/>
                </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var counter = ${counter};
    var modalAttachmentTitle = $('#${selectId}-attachment-title');
    var modalAttachmentDesc = $('#${selectId}-attachment-desc');
    var modalAttachmentFile = $('#${selectId}-attachment-file')[0];

    $("#${selectId}-add-attachment").on("click", function () {
        $("#${selectId}-modal").modal("show");
    });

    $("#${selectId}-accept-modal").on('click', function () {
        if (modalAttachmentFile.files.length == 0 || modalAttachmentTitle.val() == "") {
            swal({
                title: "<@spring.message 'attention'/>",
                text: "<@spring.message 'knowledge.attachment.input-require-fieds'/>",
                type: "warning"
            });
            return;
        }
        var data = new FormData();
        data.append("title", modalAttachmentTitle.val());
        data.append("description", modalAttachmentDesc.val());
        data.append("file", modalAttachmentFile.files[0]);

        $.ajax({
            url: '/api//knowledge-article/attachment',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            success: function (data) {
                $("#${selectId}-attachments-list").append($("<div>").addClass("panel panel-flat border-top-warning col-sm-12")
                        .append($("<span class='pull-right ${selectId}-attachment-delete' style='padding:5px;' data-id=" + data.id + "><i class='icon-close2 position-left'></i></span>"))
                        .append($("<input name='${spring.status.expression}[" + counter + "].id' type='hidden' class='form-control' value='" + data.id + "'>"))
                        .append($("<br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.title'/> *")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].title' value='" + data.title + "' type='text' class='form-control'>")
                        )
                        .append($("<br><br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.description'/>")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].description' value='" + data.description + "' type='text' class='form-control'>")
                        )
                        .append($("<br><br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.choose-file'/> *")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].originalName' value='" + data.originalName + "' disabled type='text' class='form-control'>")
                        )
                        .append($("<br><br><br>"))
                );
                counter++;
                $("#${selectId}-modal").modal("hide");
                modalAttachmentTitle.val("");
                modalAttachmentDesc.val("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                sweetAlert({
                    title: "<@spring.message 'error'/>",
                    text: "<@spring.message 'failed-file-upload'/>",
                    type: "error"
                });
            }
        });
    });

    $("#${selectId}-attachments-list").on('click', ".${selectId}-attachment-delete", function () {
        var holder = $(this);
        $.ajax({
            url: '/api/knowledge-article/attachment/' + holder.attr("data-id"),
            type: 'DELETE',
            success: function (data) {
                holder.parent().remove();
                counter--;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                sweetAlert({
                    title: "Oops!",
                    text: "Не удалось загрузить файл",
                    type: "error"
                });
            }
        });
    });
</script>
</#macro>

<#macro instructionAttachments path attachments required=false limit=6>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "knowledge-attachments-${replacedPath}" />
    <#assign counter = 0 />
<div class="panel panel-flat">
    <div class="panel-heading">
        <h6 class="panel-title"><@spring.message 'article.attachments'/></h6>
        <div class="heading-elements">
             <span class="btn btn-primary" id="${selectId}-add-attachment">
                <span><i class="icon-plus2 position-left"></i>
                    <@spring.message 'article.attachments.add'/>
                </span>
             </span>
        </div>
    </div>
    <div id="${selectId}-attachments-list" class="panel-body">
        <#if attachments?? && attachments?has_content>
            <#list attachments as attachment>
                <div class="panel panel-flat border-top-warning col-sm-12">
                    <span class='pull-right ${selectId}-attachment-delete'
                          style="padding: 5px;"
                          data-id="#{attachment.id}">
                        <i class='icon-close2 position-left'></i>
                    </span>
                    <input name="attachments[${counter}].id" type="hidden" value="#{attachment.id}">
                    <br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.title'/>
                        *</label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].title" type="text" value="${attachment.title}"
                               class="form-control" required>
                    </div>
                    <br><br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.description'/></label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].description" value="${attachment.description}"
                               type="text" class="form-control">
                    </div>
                    <br><br>
                    <label class="control-label col-sm-6"><@spring.message 'knowledge.attachment.file-name'/>
                        *</label>
                    <div class="col-sm-6">
                        <input name="attachments[${counter}].originalName"
                               value="${attachment.originalName}"
                               type="text" disabled class="form-control">
                    </div>
                    <br> <br> <br>
                </div>
                <#assign counter++/>
            </#list>
        </#if>
    </div>

    <div id="${selectId}-modal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-info">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="select-modal-title">
                        <@spring.message 'article.attachments.add'/>
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.title'/>: *
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="${selectId}-attachment-title">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.description'/>:
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="${selectId}-attachment-desc">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>
                                <@spring.message 'knowledge.attachment.file-name'/>: *
                            </label>
                        </div>
                        <div class="col-sm-8">
                            <input type="file" class="file-styled" id="${selectId}-attachment-file">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                <span id="${selectId}-accept-modal" class="btn btn-success">
                    <@spring.message 'modal.accept'/>
                </span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var counter = ${counter};
    var modalAttachmentTitle = $('#${selectId}-attachment-title');
    var modalAttachmentDesc = $('#${selectId}-attachment-desc');
    var modalAttachmentFile = $('#${selectId}-attachment-file')[0];

    $("#${selectId}-add-attachment").on("click", function () {
        $("#${selectId}-modal").modal("show");
    });

    $("#${selectId}-accept-modal").on('click', function () {
        if (modalAttachmentFile.files.length == 0 || modalAttachmentTitle.val() == "") {
            swal({
                title: "<@spring.message 'attention'/>",
                text: "<@spring.message 'knowledge.attachment.input-require-fieds'/>",
                type: "warning"
            });
            return;
        }
        var data = new FormData();
        data.append("title", modalAttachmentTitle.val());
        data.append("description", modalAttachmentDesc.val());
        data.append("file", modalAttachmentFile.files[0]);

        $.ajax({
            url: '/api/instruction-article/attachment',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            success: function (data) {
                $("#${selectId}-attachments-list").append($("<div>").addClass("panel panel-flat border-top-warning col-sm-12")
                        .append($("<span class='pull-right ${selectId}-attachment-delete' style='padding:5px;' data-id=" + data.id + "><i class='icon-close2 position-left'></i></span>"))
                        .append($("<input name='${spring.status.expression}[" + counter + "].id' type='hidden' class='form-control' value='" + data.id + "'>"))
                        .append($("<br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.title'/> *")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].title' value='" + data.title + "' type='text' class='form-control'>")
                        )
                        .append($("<br><br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.description'/>")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].description' value='" + data.description + "' type='text' class='form-control'>")
                        )
                        .append($("<br><br>"))
                        .append(
                                $("<label>").addClass("control-label col-sm-6").text("<@spring.message 'knowledge.attachment.choose-file'/> *")
                        )
                        .append(
                                $("<div>").addClass("col-sm-6")
                                        .append("<input name='${spring.status.expression}[" + counter + "].originalName' value='" + data.originalName + "' disabled type='text' class='form-control'>")
                        )
                        .append($("<br><br><br>"))
                );
                counter++;
                $("#${selectId}-modal").modal("hide");
                modalAttachmentTitle.val("");
                modalAttachmentDesc.val("");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                sweetAlert({
                    title: "<@spring.message 'error'/>",
                    text: "<@spring.message 'failed-file-upload'/>",
                    type: "error"
                });
            }
        });
    });

    $("#${selectId}-attachments-list").on('click', ".${selectId}-attachment-delete", function () {
        var holder = $(this);
        $.ajax({
            url: '/api/instruction-article/attachment/' + holder.attr("data-id"),
            type: 'DELETE',
            success: function (data) {
                holder.parent().remove();
                counter--;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                sweetAlert({
                    title: "Oops!",
                    text: "Не удалось загрузить файл",
                    type: "error"
                });
            }
        });
    });
</script>
</#macro>

<#macro select2 path url selected attributes="" required=false placeholder="" multiple=false allowClear=true>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
    <#assign varId = path?replace(".", "") />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <select id="${selectId}" ${multiple?then("multiple","")}
                name="${spring.status.expression}" ${required?then("required","")} ${attributes}>
            <#if selected?? && selected?has_content>
                <option value="${selected.getSelectorId()}"
                        selected="selected">${selected.getSelectorTitle()}</option>
            </#if>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>

<script type="application/javascript">
    var select2${varId} = "${url}";
    $(function () {
        $("#${selectId}").select2({
            width: "100%",
            ajax: {
                url: function () {
                    console.log(select2${varId});
                    return select2${varId};
                },
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
            placeholder: "<@spring.message placeholder />",
            allowClear: ${allowClear?c},
            templateSelection: function (data) {

                return data.selectorTitle || data.title || data.text;
            },
            templateResult: function (data, container) {
                if (data.className != null)
                    $(container).addClass(data.className);
                return data.selectorTitle || data.title;
            }
        });
    });
</script>
</#macro>

<#macro select2list path options selected required=false allowClear=true multiple=false firstEmpty=false>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <select id="${selectId}" ${multiple?then("multiple","")} ${required?then("required","")}
                name="${spring.status.expression}"
                class="form-control">
            <#if firstEmpty>
                <option value=""></option>
            </#if>
            <#list options as option>
                <#assign selectedValue = false>
                <#if selected?? && selected?has_content>
                    <#if selected?is_enumerable>
                        <#list selected as s>
                            <#assign selectedValue = s?? && s?has_content && s.getSelectorId() == option.getSelectorId()>
                            <#if selectedValue>
                                <#break/>
                            </#if>
                        </#list>
                    <#else>
                        <#assign selectedValue = selected?? && selected?has_content && selected.getSelectorId() == option.getSelectorId()>
                    </#if>
                </#if>
                <option value="${option.getSelectorId()}" ${(selectedValue??&&selectedValue)?then('selected','')}>
                    ${option.getSelectorTitle()}
                </option>
            </#list>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
<script type="application/javascript">
    $("#${selectId}").select2({
        width: "100%",
        allowClear: ${allowClear?c},
        placeholder: "",
        templateResult: function (data, container) {
            return data.text;
        }
    });
</script>
</#macro>

<#macro select2listNotSelectable path options selected required=false allowClear=true multiple=false firstEmpty=false>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <select id="${selectId}" ${multiple?then("multiple","")} ${required?then("required","")}
                name="${spring.status.expression}"
                class="form-control">
            <#if firstEmpty>
                <option value=""></option>
            </#if>
            <#list options as option>
                <#assign selectedValue = false>
                <#if selected?? && selected?has_content>
                    <#if selected?is_enumerable>
                        <#list selected as s>
                            <#assign selectedValue = s?? && s?has_content && s == option.getSelectorId()>
                            <#if selectedValue>
                                <#break/>
                            </#if>
                        </#list>
                    <#else>
                        <#assign selectedValue = selected?? && selected?has_content && selected == option.getSelectorId()>
                    </#if>
                </#if>
                <option value="${option.getSelectorId()}" ${(selectedValue??&&selectedValue)?then('selected','')}>
                    ${option.getSelectorTitle()}
                </option>
            </#list>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
<script type="application/javascript">
    $("#${selectId}").select2({
        width: "100%",
        allowClear: ${allowClear?c},
        placeholder: "",
        templateResult: function (data, container) {
            if (data.id == "success")
                $(container).addClass("success");

            if (data.id == "danger")
                $(container).addClass("danger");

            if (data.id == "warning")
                $(container).addClass("warning");

            if (data.id == "primary")
                $(container).addClass("primary");

            return data.text;
        }
    })
    ;
</script>
</#macro>

<#macro select2listButtons path options selected required=false>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <select id="${selectId}" class="form-control" name="${spring.status.expression}">
            <#list options as option>
                <#assign selectedValue = selected?? && selected?has_content && selected.getSelectorId() == option.getSelectorId()>
                <option value="${option.getSelectorId()}" ${selectedValue?then('selected','')}>
                    ${option.getSelectorTitle()}
                </option>
            </#list>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
<script type="application/javascript">
    $("#${selectId}").select2({
        width: "100%",
        templateResult: function (data, container) {
            if (data.id == "success")
                return $("<button class='btn btn-sm btn-success'><@spring.message 'send.mail'/></button>");
            if (data.id == "danger")
                return $("<button class='btn btn-sm btn-danger'><@spring.message 'send.mail'/></button>");
            if (data.id == "warning")
                return $("<button class='btn btn-sm btn-warning'><@spring.message 'send.mail'/></button>");
            if (data.id == "primary")
                return $("<button class='btn btn-sm btn-primary'><@spring.message 'send.mail'/></button>");
            return $("<button class='btn btn-sm btn-default'><@spring.message 'send.mail'/></button>");
        },
        templateSelection: function (data) {
            if (data.id == "success")
                return $("<button class='btn btn-sm btn-success'><@spring.message 'send.mail'/></button>");
            if (data.id == "danger")
                return $("<button class='btn btn-sm btn-danger'><@spring.message 'send.mail'/></button>");
            if (data.id == "warning")
                return $("<button class='btn btn-sm btn-warning'><@spring.message 'send.mail'/></button>");
            if (data.id == "primary")
                return $("<button class='btn btn-sm btn-primary'><@spring.message 'send.mail'/></button>");
            return $("<button class='btn btn-sm btn-default'><@spring.message 'send.mail'/></button>");
        }
    });
</script>
</#macro>

<#macro select2enum path options value="" required=false allowClear=true>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <select id="${selectId}" class="form-control"
                name="${spring.status.expression}" ${required?then("required","")}>
            <#list options as option>
                <#assign selected = value?has_content && value.getCode() == option.getCode()>
                <option value="${option.getCode()}"${selected?then(' selected="selected','')}><@spring.message option.name() /></option>
            </#list>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>

<script type="application/javascript">
    $("#${selectId}").select2({
        width: "100%",
        placeholder: "",
        allowClear: ${allowClear?c},
        minimumResultsForSearch: Infinity
    });
</script>
</#macro>

<#--Для модального окна-->
<#macro select2NoBind path>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class='form-group' id="selected-rows-select-holder">
    <@label path required/>
    <div class='col-sm-8'>
        <select id="${selectId}">
        </select>
    </div>
</div>
<script type='application/javascript'>
    $(function () {
        $('#${selectId}').select2({});
    });
</script>
</#macro>

<#macro select2Multiple path url selected required=false showLabel=true>
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
    <#assign varId = path?replace(".", "") />

<div class="form-group">
    <#if showLabel><@label path required/></#if>
    <div class="col-sm-8">
        <select id="${selectId}" multiple="multiple"
                name="${spring.status.expression}" ${required?then("required","")}>
            <#if selected?? && selected?has_content>
                <#if selected?is_enumerable>
                    <#list selected as selectedItem>
                        <option value="${selectedItem.getSelectorId()}"
                                selected="selected">${selectedItem.getSelectorTitle()}</option>
                    </#list>
                <#else>
                    <option value="${selected.getSelectorId()}"
                            selected="selected">${selected.getSelectorTitle()}</option>
                </#if>
            </#if>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>

<script type="application/javascript">
    var select2${varId} = "${url}";
    $(function () {
        $("#${selectId}").select2({
            width: "100%",
            ajax: {
                url: function(){
                    console.log(select2${varId});
                    return select2${varId};
                },
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    return {
                        q: params.term,
                        page: params.page == null ? 0 : params.page - 1
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
                console.log(data);
                return data.selectorTitle || data.title || data.text;
            },
            templateResult: function (data) {
                console.log(data);
                return data.selectorTitle || data.title;
            }
        });
    });
</script>
</#macro>

<#macro printUpdateControl path title="" lastUpdatedAt="">
    <@print path title "icon-pencil" true lastUpdatedAt>
        <#nested />
    </@print>
</#macro>

<#macro print path title="" icon="icon-pencil" showSubmit=true lastUpdatedAt="">
    <@noSubmitPrintFile path>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h6 class="panel-title">
                <i class="${icon}"></i>
                <@spring.message title />
            </h6>
        </div>
        <div class="panel-body">

            <#nested />

            <#if showSubmit>
                <div class="form-actions text-right">
                    <input type="hidden" name="lastUpdatedAt" value="${lastUpdatedAt}" />
                    <@inputSubmit />
                </div>
            </#if>

        </div>
    </div>
    </@noSubmitPrintFile>
</#macro>

<#macro printWithSidebar path sidebar edit=true title=true showSubmit=true customTitle="" name="">
    <@noSubmitPrintFile path name>
    <div class="row">
        <div class="col-md-9">
            <div class="panel-default">
                <#if title>
                    <div class="panel-heading">
                        <h6 class="panel-title">
                            <i class="icon-pencil"></i>
                            <#if customTitle?has_content>
                                ${customTitle}
                            <#else>
                                <@spring.message edit?then("edit","new") />
                            </#if>
                        </h6>
                    </div>
                </#if>
                <div class="panel-body">
                    <#nested />
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <#if sidebar?is_macro>
                <@sidebar/>
            </#if>
        </div>
    </div>
    <div class="panel panel-flat">
        <div class="panel-heading">
            <div class="form-actions text-right">
                <@inputSubmit />
            </div>
        </div>
    </div>
    </@noSubmitPrintFile>
</#macro>

<#macro sendEmailYourSelf>
<div class="form-actions text-right">
    <button id="send-yourself" class="btn btn-warning"><@spring.message 'send.yourself' /></button>
</div>
<br>

<script>
    $("#send-yourself").click(function () {
        var thisHolder = $(this);
        thisHolder.prop('disabled', true);
        /**
         * TextArea остается пустым до момента сабмита
         * Эта строка копирует содержимое визивига в textarea
         */
        $("#mailBody").val($("#mailBody").summernote('code'));

        $.ajax({
            url: "/api/email-templates/send-yourself",
            type: 'POST',
            data: $(this).closest("form").serialize(),
            success: function (data) {
                thisHolder.prop('disabled', false);
                sweetAlert({
                    title: data.data.title,
                    text: data.data.message,
                    type: data.data.type
                });
            },
            error: function (e1, e2, e3) {
                thisHolder.prop('disabled', false);
                sweetAlert({
                    title: "<@spring.message 'swal.error'/>",
                    text: "<@spring.message 'send.mail.input-required-fieds'/>",
                    type: "error"
                });
            }

        });
    });
</script>
</#macro>

<#macro passwordForm path>
    <@noSubmitPrint path>
    <div class="panel panel-default">
        <div class="panel-body">

            <#nested />

            <div class="form-actions text-right">
                <@inputSubmit />
            </div>

        </div>
    </div>
    </@noSubmitPrint>
</#macro>

<#macro printSimple>
<span class="form-horizontal form">
    <div class="panel-body">

        <#nested />

        <div class="form-actions text-right">

        </div>
    </div>
    </span>
</#macro>

<#macro printFile path edit=true>
    <@noSubmitPrintFile path>
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i
                class="icon-menu"></i> <@spring.message edit?then("edit","new") /></h6></div>
        <div class="panel-body">

            <#nested />

            <div class="form-actions text-right">
                <@inputSubmit />
            </div>

        </div>
    </div>
    </@noSubmitPrintFile>
</#macro>

<#macro printDistribute path label="" edit=true>
    <@noSubmitPrintFile path>
    <div class="panel panel-default">
        <div class="panel-heading"><h6 class="panel-title"><i
                class="icon-menu"></i>${label}</h6></div>
        <div class="panel-body">

            <#nested />

            <div class="form-actions text-right">
                <@inputSubmit />
            </div>

        </div>
    </div>
    </@noSubmitPrintFile>
</#macro>

<#macro flat path>
    <@noSubmitPrint path>
        <#nested />
    <div class="form-actions text-right">
        <@inputSubmit />
    </div>
    </@noSubmitPrint>
</#macro>

<#macro noSubmitPrint path>
<form class="form-horizontal form" action="${path}" role="form" method="POST">
    <#nested />
</form>
</#macro>

<#macro noSubmitPrintFile path>
<form class="form-horizontal form "
      action="${path}"
      method="POST"
      role="form"
      enctype="multipart/form-data">
    <#nested />
</form>
</#macro>

<#macro inputSubmit text="action.accept" class="primary" id="">
<button <#if (id?has_content)>id="${id}"</#if> type="submit" class="btn btn-${class}"><@spring.message text /></button>
</#macro>

<#macro ajaxButton text function class="warning">
<div class="form-actions text-right">
    <button type="button" onclick="${function}" class="btn btn-${class}"><@spring.message text /></button>
</div>
</#macro>

<#macro textArea path required=false attributes="">
    <@spring.bind path />
    <#assign attr = attributes?string + " rows='5' cols='5' class='elastic form-control' style='overflow: hidden; word-wrap: break-word; resize: vertical; height: 160px;'"/>
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formTextarea path attr />
    </div>
</div>
</#macro>

<#macro textAreaSimple path>
    <@spring.bind path />
<div class="col-sm-12">
    <@spring.formTextarea path 'rows="5" cols="5" class="elastic form-control" style="overflow: scroll; word-wrap: break-word; resize: horizontal; height: 80px;"' />
</div>
</#macro>

<#macro radioButton path values>
    <@spring.bind path />
    <@spring.showErrors "<br>" "text-danger" />
<div class="form-group">
    <@spring.formRadioButtons path values "&nbsp;" 'class="styled"' />
</div>
</#macro>

<#macro checkBox path required=false checked=false>
<div class="form-group<#if required> required</#if>">
    <@spring.bind path />
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formCheckbox path 'class="styled" ${checked?then("checked","")}'/>
    </div>
    <@spring.showErrors "<br>" "text-danger" />
</div>
</#macro>

<#macro multiSelect path options required=false >
<div class="form-group<#if required> required</#if>">
<#--<@spring.bind path />-->
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formMultiSelect path options '' />
    </div>
    <@spring.showErrors "<br>" "text-danger" />
</div>
</#macro>

<#macro avatarForm user userType actionUrl avatarEditPermission=false>
<div style="text-align: center">
    <div class="thumb thumb-rounded thumb-slide" style="width: 150px; height: 150px">
        <img src="${user.getThumbnail('320')!"/static/images/no_avatar.svg"}"
             id="avatar-holder"
             alt="Avatar">
        <div class="caption">
            <span>
                <#if avatarEditPermission>
                    <#if user.photoUrl??>
                        <a href="/${userType}/#{user.getId()}/edit"
                           class="btn bg-success-400 btn-icon btn-xs"
                           data-popup="lightbox">
                    <i class="icon-database-edit2"></i>
                    </a>
                    <a href="#"
                       id="remove-avatar"
                       class="btn bg-danger-400 btn-icon btn-xs">
                        <i class="icon-folder-remove"></i>
                    </a>
                    <#else >
                        <a href="#"
                           class="btn bg-success-400 btn-icon btn-xs"
                           id="avatar-id-action"
                           data-popup="lightbox">
                    <i class="icon-add"></i>
                    </a>
                    </#if>
                </#if>
            </span>
            <form enctype="multipart/form-data" id="add-form">
                <input type="file" style="position: absolute; visibility: hidden;" id="avatar-add-file-holder"
                       accept="image/jpeg, image/jpg">
            </form>
        </div>
    </div>
<#--<#if user.isOnline()>-->
<#--<span id="employee-profile-online-stat" class="label bg-success-400" style="margin: 10px 0;">-->
<#--Online-->
<#--</span>-->
<#--<#else>-->
<#--<span id="employee-profile-online-stat" class="label bg-danger-400" style="margin: 10px 0;">-->
<#--Offline (${user.getLastOnlineAtAsString()})-->
<#--</span>-->
<#--</#if>-->
</div>
<script type="text/javascript">
    $("#remove-avatar").click(function () {
        swal({
                    title: "Подтверждение",
                    text: 'Вы действительно хотите удалить аватар?',
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#EF5350",
                    confirmButtonText: 'Да, удалить',
                    cancelButtonText: "Отмена",
                    closeOnConfirm: true,
                    closeOnCancel: true
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            url: '${actionUrl}/#{user.getId()}/remove-avatar',
                            type: 'DELETE',
                            data: {},
                            success: function (data) {
                                location.reload();
                            }
                        });
                    }
                });
    });

    $("#avatar-id-action").click(function () {
        $("#avatar-add-file-holder").click();
    });

    $("#avatar-add-file-holder").change(function () {
        if (this.files.length == 0)
            return;

        var data = new FormData();
        data.append('avatar', $('#avatar-add-file-holder')[0].files[0]);
        $.ajax({
            url: '${actionUrl}/#{user.getId()}/add-avatar',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            success: function (data) {
                $("#avatar-holder").prop('src', "/uploads/users/" + data.uid + "/avatar/thumbnail.320." + data.photoUrl);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                location.reload();
            }
        });
    });

    <#--$(window).ready(function () {-->
    <#--setTimeout(function () {-->
    <#--var statBar = $("#employee-profile-online-stat");-->
    <#--window.stompClient.subscribe("${wsHelper.getDestinationTopic()}/user.#{user.getId()}.online-state", function (data) {-->
    <#--var body = JSON.parse(data.body);-->

    <#--console.log(body);-->

    <#--if (body.data.state) {-->
    <#--statBar.html("Online");-->
    <#--statBar.addClass("bg-success-400");-->
    <#--statBar.removeClass("bg-danger-400");-->
    <#--}-->
    <#--else {-->
    <#--statBar.html("Offline (%s)".replace("%s", body.data.lastOnlineAt));-->
    <#--statBar.removeClass("bg-success-400");-->
    <#--statBar.addClass("bg-danger-400");-->
    <#--}-->
    <#--});-->
    <#--}, 1000);-->
    <#--});-->
</script>
</#macro>

<#macro wysiwyg path required=false>
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formTextarea path 'wysiwyg="wysiwyg" ${required?string("required","")}'/>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>

<script type="text/javascript">
    $("#${spring.status.expression}").summernote();
</script>
</#macro>

<#macro fileAttachments path ajaxUrl attachments limit=5>
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <input type="file" class="file-styled" id="file-attachment-holder">
    </div>
</div>
<div id="attachments-list">
    <#if attachments?? && attachments?has_content>
        <#list attachments as attachment>
            <div class='alert alert-success'>
                <input name='${spring.status.expression}' type='hidden' value='${attachment.getAttachmentId()}'/>
                <strong>${attachment.getAttachmentName()}</strong> <@spring.message 'emailAttachment.success-uploaded'/>
                <a class='remove-attachment pull-right'
                   data-id='${attachment.getAttachmentId()}'><@spring.message 'emailAttachment.delete'/></a>
            </div>
        </#list>
    </#if>
</div>
<script>
    var limitCounter = 0;

    $("#file-attachment-holder").change(function () {
        if (limitCounter >= ${limit}) {
            sweetAlert({
                title: "<@spring.message 'swal.failed' />",
                text: "Вы достигли максимального количества файлов: " + limitCounter,
                type: "warning"
            });
            return;
        }

        if (this.files.length == 0)
            return;

        var data = new FormData();
        data.append('file', $('#file-attachment-holder')[0].files[0]);
        $.ajax({
            url: '${ajaxUrl}',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            success: function (data) {
                $("#attachments-list")
                        .append(
                                "<div class='alert alert-success'>" +
                                "<input name='${spring.status.expression}' type='hidden' value='" + data.id + "'/>" +
                                "<strong>" + data.originalName + "</strong> <@spring.message 'emailAttachment.success-uploaded'/> " +
                                "<a class='remove-attachment pull-right' data-id='" + data.id + "'><@spring.message 'emailAttachment.delete'/> </a>" +
                                "</div>");
                limitCounter++;
            },
            error: function (jqXHR, textStatus, errorThrown) {
                sweetAlert({
                    title: "Oops!",
                    text: "Не удалось загрузить файл",
                    type: "error"
                });
            }
        });
    });

    $("#attachments-list").on("click", ".remove-attachment", function () {
        var thisObject = this;
        $.ajax({
            url: '${ajaxUrl}?id=' + $(thisObject).attr("data-id"),
            type: 'DELETE',
            processData: false,
            contentType: false,
            success: function (data) {
                $(thisObject).parent().remove();
                limitCounter--;
            }
        });
    });
</script>
</#macro>

<#macro fileAttachmentsStatic path attachments limit=5>
    <@spring.bind path />
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-id-${replacedPath}" />
<div class="form-group">
    <@label path required/>
    <div class="col-sm-8">
        <span class="btn btn-warning pull-right" id="add-attachment">Добавить вложение</span>
    </div>
</div>
<div class="form-group" id="attachment-list">
</div>
<script>
    var limitCounter = 0;
    $("#add-attachment").click(function () {
        if (limitCounter >= ${limit}) {
            sweetAlert({
                title: "<@spring.message 'swal.failed' />",
                text: "Вы достигли максимального количества файлов: " + limitCounter,
                type: "warning"
            });
            return;
        }

        $("#attachment-list").append($("<div>" +
                "<div class='col-sm-11'>" +
                "<input name='${spring.status.expression}' type='file' class='file-styled'></div><div class='col-sm-1 text-right'>" +
                "<span class='btn btn-danger remove-static-file'>Удалить</span></div><br><br></div>"));
        $(".file-styled").uniform({
            fileButtonClass: 'action btn bg-primary'
        });
        limitCounter++;
    });
    $("#attachment-list").on("click", ".remove-static-file", function () {
        $(this).parent().parent().remove();
        limitCounter--;
    });
</script>
</#macro>

<#macro select2listExpanded path options selected required=false attribute="">
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-expanded-id-${replacedPath}" />
<div class="form-group">

    <div class="col-sm-12">
        <@label path required/>
        <br>
        <select id="${selectId}"  ${required?then("required","")}
                name="${spring.status.expression}"
            ${attribute}
                class="form-control">
            <#if options?? && options?has_content>
                <#list options as option>
                    <option value="${option.getSelectorId()}">
                        ${option.getSelectorTitle()}
                    </option>
                </#list>
            </#if>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
</#macro>

<#macro select2listExpanded path options selected required=false attribute="">
    <@spring.bind path/>
    <#assign replacedPath = path?replace(".", "-") />
    <#assign selectId = "select-expanded-id-${replacedPath}" />
<div class="form-group">
    <div class="col-sm-12">
        <@label path required/>
        <br>
        <select id="${selectId}"
            ${required?then("required","")}
                name="${spring.status.expression}"
            ${attribute}
                class="form-control">
            <#if options?? && options?has_content>
                <#list options as option>
                    <option value="${option.getSelectorId()}"
                            <#if selected?? && selected?has_content && selected.getSelectorId() ==option.getSelectorId()>selected</#if>>
                        ${option.getSelectorTitle()}
                    </option>
                </#list>
            </#if>
        </select>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
</#macro>

<#macro inputColor path required=false>
<div class="form-group">
    <@spring.bind path />
    <@label path required/>
    <div class="col-sm-8">
        <@spring.formInput path ' class="form-control color-picker" data-preferred-format="hex" ' "text"/>
        <@spring.showErrors "<br>" "text-danger" />
    </div>
</div>
<script type="text/javascript">
    $(".color-picker").spectrum({
        showInput: true
    });
</script>
</#macro>