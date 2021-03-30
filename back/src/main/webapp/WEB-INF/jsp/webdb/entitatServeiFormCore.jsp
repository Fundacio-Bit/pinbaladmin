<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatServeiFields" className="org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.NOM)}">
        <tr id="entitatServei_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.NOM])?'entitatServei.nom':__theForm.labels[EntitatServeiFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EntitatServeiFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatServeiFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="entitatServei.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="entitatServei.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.DESCRIPCIO)}">
        <tr id="entitatServei_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.DESCRIPCIO])?'entitatServei.descripcio':__theForm.labels[EntitatServeiFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EntitatServeiFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatServeiFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="entitatServei.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.DESCRIPCIO)? 'true' : 'false'}" path="entitatServei.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('entitatServei.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntitatServeiFields.BALEARS)}">
        <tr id="entitatServei_balears_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EntitatServeiFields.BALEARS])?'entitatServei.balears':__theForm.labels[EntitatServeiFields.BALEARS]}" />
              <c:if test="${not empty __theForm.help[EntitatServeiFields.BALEARS]}">
              <i class="icon-info-sign" title="${__theForm.help[EntitatServeiFields.BALEARS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)}" >
              <form:errors path="entitatServei.balears" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)? 'false' : 'true'}" path="entitatServei.balears" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntitatServeiFields.BALEARS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.entitatServei.balears}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
