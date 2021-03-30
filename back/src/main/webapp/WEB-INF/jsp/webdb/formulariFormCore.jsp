<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FormulariFields" className="org.fundaciobit.pinbaladmin.model.fields.FormulariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.NOM)}">
        <tr id="formulari_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.NOM])?'formulari.nom':__theForm.labels[FormulariFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FormulariFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[FormulariFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="formulari.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FormulariFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="formulari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.DESCRIPCIO)}">
        <tr id="formulari_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.DESCRIPCIO])?'formulari.descripcio':__theForm.labels[FormulariFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[FormulariFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[FormulariFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="formulari.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.DESCRIPCIO)? 'true' : 'false'}" path="formulari.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('formulari.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,FormulariFields.FITXERID)}">
        <tr id="formulari_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FormulariFields.FITXERID])?'formulari.fitxerID':__theForm.labels[FormulariFields.FITXERID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FormulariFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[FormulariFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="formulari.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,FormulariFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.formulari.fitxer}">
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pad:fileUrl(__theForm.formulari.fitxer)}"/>">${__theForm.formulari.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
