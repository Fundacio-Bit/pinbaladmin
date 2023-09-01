<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OrganFields" className="org.fundaciobit.pinbaladmin.model.fields.OrganFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganFields.NOM)}">
        <tr id="organ_nom_rowid">
          <td id="organ_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganFields.NOM])?'organ.nom':__theForm.labels[OrganFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[OrganFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="organ_nom_columnvalueid">
              <form:errors path="organ.nom" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,OrganFields.NOM)? 'true' : 'false'}" path="organ.nom"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_nom" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_nom" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.nom'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('organ.nom'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.nom'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_nom').on('click', function(){
					var valor = ($('#dropdownMenuContainer_nom').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_nom').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganFields.DIR3)}">
        <tr id="organ_dir3_rowid">
          <td id="organ_dir3_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganFields.DIR3])?'organ.dir3':__theForm.labels[OrganFields.DIR3]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[OrganFields.DIR3]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganFields.DIR3]}" ></i>
              </c:if>
            </td>
          <td id="organ_dir3_columnvalueid">
              <form:errors path="organ.dir3" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,OrganFields.DIR3)? 'true' : 'false'}" path="organ.dir3"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_dir3" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_dir3" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.dir3'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('organ.dir3'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.dir3'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_dir3').on('click', function(){
					var valor = ($('#dropdownMenuContainer_dir3').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_dir3').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganFields.DIR3PARE)}">
        <tr id="organ_dir3pare_rowid">
          <td id="organ_dir3pare_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganFields.DIR3PARE])?'organ.dir3pare':__theForm.labels[OrganFields.DIR3PARE]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganFields.DIR3PARE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganFields.DIR3PARE]}" ></i>
              </c:if>
            </td>
          <td id="organ_dir3pare_columnvalueid">
              <form:errors path="organ.dir3pare" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,OrganFields.DIR3PARE)? 'true' : 'false'}" path="organ.dir3pare"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_dir3pare" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_dir3pare" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.dir3pare'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('organ.dir3pare'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('organ.dir3pare'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_dir3pare').on('click', function(){
					var valor = ($('#dropdownMenuContainer_dir3pare').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_dir3pare').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganFields.ENTITATID)}">
        <tr id="organ_entitatid_rowid">
          <td id="organ_entitatid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganFields.ENTITATID])?'organ.entitatid':__theForm.labels[OrganFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="organ_entitatid_columnvalueid">
          <form:errors path="organ.entitatid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,OrganFields.ENTITATID)}" >
          <form:hidden path="organ.entitatid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.organ.entitatid,__theForm.listOfEntitatForEntitatid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,OrganFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="organ_entitatid"  onchange="if(typeof onChangeEntitatid == 'function') {  onChangeEntitatid(this); };"  cssClass="form-control col-md-9-optional" path="organ.entitatid">
            <c:forEach items="${__theForm.listOfEntitatForEntitatid}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.organ.entitatid }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.organ.entitatid }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,OrganFields.CIF)}">
        <tr id="organ_cif_rowid">
          <td id="organ_cif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[OrganFields.CIF])?'organ.cif':__theForm.labels[OrganFields.CIF]}" />
             </label>
              <c:if test="${not empty __theForm.help[OrganFields.CIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[OrganFields.CIF]}" ></i>
              </c:if>
            </td>
          <td id="organ_cif_columnvalueid">
            <form:errors path="organ.cif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,OrganFields.CIF)? 'true' : 'false'}" cssClass="w-50 form-control  ${gen:contains(__theForm.readOnlyFields ,OrganFields.CIF)? ' uneditable-input' : ''}"  style="" maxlength="30" path="organ.cif"   />

           </td>
        </tr>
        </c:if>
        
