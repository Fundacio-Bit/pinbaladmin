<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EmailFields" className="org.fundaciobit.pinbaladmin.model.fields.EmailFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.DATAENVIAMENT)}">
        <tr id="email_dataEnviament_rowid">
          <td id="email_dataEnviament_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.DATAENVIAMENT])?'email.dataEnviament':__theForm.labels[EmailFields.DATAENVIAMENT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EmailFields.DATAENVIAMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EmailFields.DATAENVIAMENT]}" ></i>
              </c:if>
            </td>
          <td id="email_dataEnviament_columnvalueid">
    <form:errors path="email.dataEnviament" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="email_dataEnviament" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.DATAENVIAMENT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#email_dataEnviament" path="email.dataEnviament" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EmailFields.DATAENVIAMENT)}" >
                    <div class="input-group-append"  data-target="#email_dataEnviament"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#email_dataEnviament').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.ENVIADOR)}">
        <tr id="email_enviador_rowid">
          <td id="email_enviador_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.ENVIADOR])?'email.enviador':__theForm.labels[EmailFields.ENVIADOR]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EmailFields.ENVIADOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EmailFields.ENVIADOR]}" ></i>
              </c:if>
            </td>
          <td id="email_enviador_columnvalueid">
            <form:errors path="email.enviador" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.ENVIADOR)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EmailFields.ENVIADOR)? ' uneditable-input' : ''}"  style="" maxlength="255" path="email.enviador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.DESTINATARIS)}">
        <tr id="email_destinataris_rowid">
          <td id="email_destinataris_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.DESTINATARIS])?'email.destinataris':__theForm.labels[EmailFields.DESTINATARIS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EmailFields.DESTINATARIS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EmailFields.DESTINATARIS]}" ></i>
              </c:if>
            </td>
          <td id="email_destinataris_columnvalueid">
              <form:errors path="email.destinataris" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.DESTINATARIS)? 'true' : 'false'}" path="email.destinataris"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_destinataris" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_destinataris" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_destinataris').on('click', function(){
					var valor = ($('#dropdownMenuContainer_destinataris').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_destinataris').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.SUBJECT)}">
        <tr id="email_subject_rowid">
          <td id="email_subject_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.SUBJECT])?'email.subject':__theForm.labels[EmailFields.SUBJECT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EmailFields.SUBJECT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EmailFields.SUBJECT]}" ></i>
              </c:if>
            </td>
          <td id="email_subject_columnvalueid">
            <form:errors path="email.subject" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.SUBJECT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EmailFields.SUBJECT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="email.subject"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.MESSAGE)}">
        <tr id="email_message_rowid">
          <td id="email_message_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.MESSAGE])?'email.message':__theForm.labels[EmailFields.MESSAGE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EmailFields.MESSAGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EmailFields.MESSAGE]}" ></i>
              </c:if>
            </td>
          <td id="email_message_columnvalueid">
              <form:errors path="email.message" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.MESSAGE)? 'true' : 'false'}" path="email.message"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_message" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_message" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('email.message'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('email.message'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('email.message'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_message').on('click', function(){
					var valor = ($('#dropdownMenuContainer_message').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_message').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
