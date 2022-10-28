<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EmailFields" className="org.fundaciobit.pinbaladmin.model.fields.EmailFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.DATAENVIAMENT)}">
        <tr id="email_dataEnviament_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.DATAENVIAMENT])?'email.dataEnviament':__theForm.labels[EmailFields.DATAENVIAMENT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EmailFields.DATAENVIAMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[EmailFields.DATAENVIAMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="email.dataEnviament" cssClass="errorField alert alert-error" />
              <div id="dataEnviament" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.DATAENVIAMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EmailFields.DATAENVIAMENT)? 'input-medium uneditable-input' : 'input-medium'}"  path="email.dataEnviament" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EmailFields.DATAENVIAMENT)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataEnviament').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.ENVIADOR)}">
        <tr id="email_enviador_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.ENVIADOR])?'email.enviador':__theForm.labels[EmailFields.ENVIADOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EmailFields.ENVIADOR]}">
              <i class="icon-info-sign" title="${__theForm.help[EmailFields.ENVIADOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="email.enviador" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.ENVIADOR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EmailFields.ENVIADOR)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="email.enviador"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.DESTINATARIS)}">
        <tr id="email_destinataris_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.DESTINATARIS])?'email.destinataris':__theForm.labels[EmailFields.DESTINATARIS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EmailFields.DESTINATARIS]}">
              <i class="icon-info-sign" title="${__theForm.help[EmailFields.DESTINATARIS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="email.destinataris" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.DESTINATARIS)? 'true' : 'false'}" path="email.destinataris"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('email.destinataris'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.SUBJECT)}">
        <tr id="email_subject_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.SUBJECT])?'email.subject':__theForm.labels[EmailFields.SUBJECT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EmailFields.SUBJECT]}">
              <i class="icon-info-sign" title="${__theForm.help[EmailFields.SUBJECT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="email.subject" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EmailFields.SUBJECT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EmailFields.SUBJECT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="email.subject"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EmailFields.MESSAGE)}">
        <tr id="email_message_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EmailFields.MESSAGE])?'email.message':__theForm.labels[EmailFields.MESSAGE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EmailFields.MESSAGE]}">
              <i class="icon-info-sign" title="${__theForm.help[EmailFields.MESSAGE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="email.message" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,EmailFields.MESSAGE)? 'mceEditorReadOnly':'mceEditor'}" path="email.message"  />
           </td>
        </tr>
        </c:if>
        
