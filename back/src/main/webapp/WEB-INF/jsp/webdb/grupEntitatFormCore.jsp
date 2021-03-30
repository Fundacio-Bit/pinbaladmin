<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupEntitatFields" className="org.fundaciobit.pinbaladmin.model.fields.GrupEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatFields.NOM)}">
        <tr id="grupEntitat_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatFields.NOM])?'grupEntitat.nom':__theForm.labels[GrupEntitatFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[GrupEntitatFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="grupEntitat.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="grupEntitat.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupEntitatFields.DESCRIPCIO)}">
        <tr id="grupEntitat_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupEntitatFields.DESCRIPCIO])?'grupEntitat.descripcio':__theForm.labels[GrupEntitatFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[GrupEntitatFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[GrupEntitatFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="grupEntitat.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,GrupEntitatFields.DESCRIPCIO)? 'true' : 'false'}" path="grupEntitat.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('grupEntitat.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
