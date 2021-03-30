<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatSolicitudServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)}">
        <tr id="estatSolicitudServei_estatSolicitudServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID])?'estatSolicitudServei.estatSolicitudServeiID':__theForm.labels[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatSolicitudServei.estatSolicitudServeiID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID)? 'input-large uneditable-input' : 'input-large'}"   path="estatSolicitudServei.estatSolicitudServeiID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.NOM)}">
        <tr id="estatSolicitudServei_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.NOM])?'estatSolicitudServei.nom':__theForm.labels[EstatSolicitudServeiFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudServeiFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatSolicitudServei.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="estatSolicitudServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudServeiFields.DESCRIPCIO)}">
        <tr id="estatSolicitudServei_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudServeiFields.DESCRIPCIO])?'estatSolicitudServei.descripcio':__theForm.labels[EstatSolicitudServeiFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EstatSolicitudServeiFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatSolicitudServei.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="estatSolicitudServei.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitudServei.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
