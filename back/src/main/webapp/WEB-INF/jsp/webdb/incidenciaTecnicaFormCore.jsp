<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IncidenciaTecnicaFields" className="org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.TITOL)}">
        <tr id="incidenciaTecnica_titol_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.TITOL])?'incidenciaTecnica.titol':__theForm.labels[IncidenciaTecnicaFields.TITOL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.TITOL]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.TITOL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="incidenciaTecnica.titol" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TITOL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.TITOL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="incidenciaTecnica.titol"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.DESCRIPCIO)}">
        <tr id="incidenciaTecnica_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.DESCRIPCIO])?'incidenciaTecnica.descripcio':__theForm.labels[IncidenciaTecnicaFields.DESCRIPCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="incidenciaTecnica.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DESCRIPCIO)? 'true' : 'false'}" path="incidenciaTecnica.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('incidenciaTecnica.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('incidenciaTecnica.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('incidenciaTecnica.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.DATAINICI)}">
        <tr id="incidenciaTecnica_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.DATAINICI])?'incidenciaTecnica.dataInici':__theForm.labels[IncidenciaTecnicaFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.DATAINICI]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="incidenciaTecnica.dataInici" cssClass="errorField alert alert-error" />
              <div id="dataInici" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAINICI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAINICI)? 'input-medium uneditable-input' : 'input-medium'}"  path="incidenciaTecnica.dataInici" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.DATAINICI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.ESTAT)}">
        <tr id="incidenciaTecnica_estat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.ESTAT])?'incidenciaTecnica.estat':__theForm.labels[IncidenciaTecnicaFields.ESTAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.ESTAT]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.ESTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="incidenciaTecnica.estat" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.ESTAT)}" >
          <form:hidden path="incidenciaTecnica.estat"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.incidenciaTecnica.estat,__theForm.listOfValuesForEstat)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.ESTAT)}" >
          <form:select id="incidenciaTecnica_estat"  onchange="if(typeof onChangeEstat == 'function') {  onChangeEstat(this); };"  cssClass="input-xxlarge" path="incidenciaTecnica.estat">
            <c:forEach items="${__theForm.listOfValuesForEstat}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.NOMENTITAT)}">
        <tr id="incidenciaTecnica_nomEntitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.NOMENTITAT])?'incidenciaTecnica.nomEntitat':__theForm.labels[IncidenciaTecnicaFields.NOMENTITAT]}" />
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.NOMENTITAT]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.NOMENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="incidenciaTecnica.nomEntitat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.NOMENTITAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.NOMENTITAT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="incidenciaTecnica.nomEntitat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTENOM)}">
        <tr id="incidenciaTecnica_contacteNom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTENOM])?'incidenciaTecnica.contacteNom':__theForm.labels[IncidenciaTecnicaFields.CONTACTENOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTENOM]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTENOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="incidenciaTecnica.contacteNom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTENOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTENOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="incidenciaTecnica.contacteNom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTEEMAIL)}">
        <tr id="incidenciaTecnica_contacteEmail_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTEEMAIL])?'incidenciaTecnica.contacteEmail':__theForm.labels[IncidenciaTecnicaFields.CONTACTEEMAIL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTEEMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTEEMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="incidenciaTecnica.contacteEmail" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTEEMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTEEMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="incidenciaTecnica.contacteEmail"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IncidenciaTecnicaFields.CONTACTETELEFON)}">
        <tr id="incidenciaTecnica_contacteTelefon_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[IncidenciaTecnicaFields.CONTACTETELEFON])?'incidenciaTecnica.contacteTelefon':__theForm.labels[IncidenciaTecnicaFields.CONTACTETELEFON]}" />
              <c:if test="${not empty __theForm.help[IncidenciaTecnicaFields.CONTACTETELEFON]}">
              <i class="icon-info-sign" title="${__theForm.help[IncidenciaTecnicaFields.CONTACTETELEFON]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="incidenciaTecnica.contacteTelefon" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTETELEFON)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,IncidenciaTecnicaFields.CONTACTETELEFON)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="incidenciaTecnica.contacteTelefon"   />

           </td>
        </tr>
        </c:if>
        
