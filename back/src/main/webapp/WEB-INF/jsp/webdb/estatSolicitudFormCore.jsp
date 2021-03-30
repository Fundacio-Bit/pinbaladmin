<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatSolicitudFields" className="org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.ESTATSOLICITUDID)}">
        <tr id="estatSolicitud_estatSolicitudID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.ESTATSOLICITUDID])?'estatSolicitud.estatSolicitudID':__theForm.labels[EstatSolicitudFields.ESTATSOLICITUDID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.ESTATSOLICITUDID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudFields.ESTATSOLICITUDID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatSolicitud.estatSolicitudID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.ESTATSOLICITUDID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.ESTATSOLICITUDID)? 'input-large uneditable-input' : 'input-large'}"   path="estatSolicitud.estatSolicitudID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.NOM)}">
        <tr id="estatSolicitud_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.NOM])?'estatSolicitud.nom':__theForm.labels[EstatSolicitudFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estatSolicitud.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="estatSolicitud.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatSolicitudFields.DESCRIPCIO)}">
        <tr id="estatSolicitud_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatSolicitudFields.DESCRIPCIO])?'estatSolicitud.descripcio':__theForm.labels[EstatSolicitudFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EstatSolicitudFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatSolicitudFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatSolicitud.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatSolicitudFields.DESCRIPCIO)? 'true' : 'false'}" path="estatSolicitud.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatSolicitud.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
