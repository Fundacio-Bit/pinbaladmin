<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.ESTATSERVEIID)}">
        <tr id="estatServei_estatServeiID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.ESTATSERVEIID])?'estatServei.estatServeiID':__theForm.labels[EstatServeiFields.ESTATSERVEIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatServeiFields.ESTATSERVEIID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatServeiFields.ESTATSERVEIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatServei.estatServeiID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.ESTATSERVEIID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatServeiFields.ESTATSERVEIID)? 'input-large uneditable-input' : 'input-large'}"   path="estatServei.estatServeiID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.NOM)}">
        <tr id="estatServei_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.NOM])?'estatServei.nom':__theForm.labels[EstatServeiFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatServeiFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatServeiFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatServei.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatServeiFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="estatServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatServeiFields.DESCRIPCIO)}">
        <tr id="estatServei_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatServeiFields.DESCRIPCIO])?'estatServei.descripcio':__theForm.labels[EstatServeiFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EstatServeiFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatServei.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="estatServei.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatServei.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
