package org.fundaciobit.pinbaladmin.back.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.exportdata.ExportData;
import org.fundaciobit.pluginsib.exportdata.ExportFile;
import org.fundaciobit.pluginsib.exportdata.ExportItem;
import org.fundaciobit.pluginsib.exportdata.IExportDataPlugin;
import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.query.BooleanField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.exportdata.DataExported;
import org.fundaciobit.genapp.common.web.exportdata.IDataExporter;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

/**
 *
 * @author anadal
 *
 */
public class  PinbalAdminDataExporter implements IDataExporter {
	private IExportDataPlugin exportDataPlugin;

	protected final Logger log = Logger.getLogger(getClass());

	/**
	 * @param exportDataPlugin
	 */
	public  PinbalAdminDataExporter(IExportDataPlugin exportDataPlugin) {
		super();
		this.exportDataPlugin = exportDataPlugin;
	}

	@Override
	public String getID() {
		return this.exportDataPlugin.getClass().getName().replace(".", "_");
	}

	@Override
	public String getName() {
		return exportDataPlugin.getName();
	}

	@Override
	public DataExported getIcon() {

		ExportFile ef = exportDataPlugin.getIcon();

		if (ef == null) {
			return null;
		} else {
			return new DataExported(ef.getContentType(), ef.getFilename(), ef.getData());
		}

	}

	@Override
	public DataExported exportList(BaseFilterForm filterForm, List<? extends IGenAppEntity> list, Field<?>[] allFields,
			Map<Field<?>, Map<String, String>> mapValuesByField, Field<?>[] primaryKeys) throws Exception {

		// TODO Falten columnes adicionals

		// 1.- Passar llistat a ExportData

		// 1.1 Columnes Visibles
		Field<?>[] visibleFields = getVisibleFields(filterForm, allFields);

		ExportData exportData = getExportData(filterForm, list, visibleFields, mapValuesByField, primaryKeys);

		// 2. Passar exportData al tipus de document

		ExportFile exportFile = exportDataPlugin.getExportFile(exportData);

		if (exportData == null) {
			return null;
		} else {
			return new DataExported(exportFile.getContentType(), exportFile.getFilename(), exportFile.getData());
		}

	}

	public IExportDataPlugin getExportDataPlugin() {
		return exportDataPlugin;
	}

	public void setExportDataPlugin(IExportDataPlugin exportDataPlugin) {
		this.exportDataPlugin = exportDataPlugin;
	}

	private ExportData getExportData(BaseFilterForm filterForm, List<? extends Object> list, Field<?>[] visibleFields,
			Map<Field<?>, Map<String, String>> mapValues, Field<?>... primaryKeys) {

		// 1.1 Titols de les Columnes
		TreeMap<Integer, AdditionalField<?, ?>> additionalFields = filterForm.getAdditionalFields();

		String[] titles = new String[visibleFields.length + additionalFields.size()];
		Map<Field<?>, String> labels = filterForm.getLabels();

		int column = 0;
		for (Integer pos : additionalFields.keySet()) {
			if (pos >= 0) {
				break;
			}
			AdditionalField<?, ?> adField = additionalFields.get(pos);
			String codeName = adField.getCodeName();
			if (codeName.contains("filtreavanzat")) {
				continue;
			}
			titles[column] = I18NUtils.tradueix(codeName);
			column++;
		}

		for (int i = 0; i < visibleFields.length; i++) {
			Field<?> field = visibleFields[i];
			String title = labels.get(field);
			if (title == null) {
				titles[column] = I18NUtils.tradueix(field.fullName);
			} else {
				titles[column] = I18NUtils.tradueix(title);
			}
			column++;
		}

		for (Integer pos : additionalFields.keySet()) {
			if (pos < 0) {
				continue;
			}
			AdditionalField<?, ?> adField = additionalFields.get(pos);
			String codeName = adField.getCodeName();
			
			if (codeName.contains("filtreavanzat")) {
				continue;
			}
			
			if (codeName.startsWith("=")) {
				titles[column] = codeName.substring(1);
			} else {
				titles[column] = I18NUtils.tradueix(codeName);
			}
			log.info("Title " + column + ": " + titles [column]);
			column++;
		}

		// Afegir valors
		int len = list.size();
		ExportItem[][] items = new ExportItem[len][column];

		column = 0;
		
		log.info("VisibleFields: " + visibleFields.length);
		log.info("AdditionalFields: " + additionalFields.size());
		
		for (Integer pos : additionalFields.keySet()) {
			log.info("Pos: " + pos);
			if (pos >= 0) {
				continue;
			}
			AdditionalField<?, ?> adField = additionalFields.get(pos);
			log.info("AdField: " + adField.getCodeName());
			
			if (adField.getCodeName().contains("filtreavanzat")) {
				log.info("No processam el filtre avançat");
				continue;
			}

			// TODO Falta Value byField
			Map<?, String> values = adField.getValueMap();

			for (int j = 0; j < len; j++) {
				// TODO nomes claus primaries uniques
//				log.info("J: " + j);
				Object keyValue = Utils.getValueOfJavaField(list.get(j), primaryKeys[0].javaName);
//				log.info(j + " - KeyValue: " + keyValue);

				String str = values.get(keyValue);
//				log.info(j + " - Str: " + str);

				ExportItem item = new ExportItem(str, str);

				items[j][column] = item;
			}
			column++;
		}

		for (int i = 0; i < visibleFields.length; i++) { // Columnes

			Field<?> field = visibleFields[i];

			Map<String, String> mapping = mapValues.get(field);

			for (int j = 0; j < len; j++) { // El camp "field" de totes les files
				Object obj = Utils.getValueOfJavaField(list.get(j), field.javaName);

				ExportItem item;

				if (mapping != null) {

					String str = "";

					if (obj != null) {
						str = obj.toString();
						String newStr = mapping.get(str);
						if (newStr != null) {
							str = newStr;
						}
					}

					item = new ExportItem(str, obj);

				} else if (field instanceof BooleanField) {
					/*
					 * =No definit genapp.checkbox.true=Si genapp.checkbox.false=No
					 */
					String str = "";
					if (obj != null) {
						str = obj.toString();
					}

					item = new ExportItem(I18NUtils.tradueix("genapp.checkbox." + str), obj);

				} else if (obj == null) {
					item = new ExportItem("", null);

				} else {
					item = new ExportItem(obj.toString(), obj);
				}

				// TODO FALTEN TIPUS TIME, DATE i DATETIME
				// TODO FALTEN TIPUS FLOAT, DOUBLE I BIGDECINAL

				items[j][column] = item;
			}
			column++;

		}

		for (Integer pos : additionalFields.keySet()) {
			if (pos < 0) {
				continue;
			}
			AdditionalField<?, ?> adField = additionalFields.get(pos);

			
			if (adField.getCodeName().contains("filtreavanzat")) {
				log.info("No processam el filtre avançat");
				continue;
			}

			Map<?, String> values = adField.getValueMap();

//			log.info("Columna: " + column + " - CodeName: " + adField.getCodeName());
			
			for (int j = 0; j < len; j++) {
				// TODO nomes claus primaries uniques
				Object keyValue = Utils.getValueOfJavaField(list.get(j), primaryKeys[0].javaName);
				String str = values.get(keyValue);

				if (adField.getCodeName().equals("organ.organ")) {
					str = processOrganGestor(str);
				}

				if (adField.getCodeName().equals("sense.llegir")) {
					str = processSenseLlegir(str);
				}
				ExportItem item = new ExportItem(str, str);
				items[j][column] = item;
			}
			column++;
		}

//		//Comprovació que està tot correcte
//		
//		if (titles.length != items[0].length) {
//            log.error("El nombre de títols no coincideix amb el nombre de columnes");
//		}
//		
//		for (int i = 0; i < titles.length; i++) {
//			log.info("Columna " + i + ": " + titles[i]);
//		}
//		
//		for (int i = 0; i < items.length; i++) {
//			for (int j = 0; j < items[i].length; j++) {
//				log.info("Item[" + i + "][" + j + "]: " + items[i][j].getStringValue());
//			}
//			
//			log.info("\n\n");
//		}
//		
		return new ExportData(titles, items);
	}
//
//	private Object getRecursiveValueOfJavaField(Object target, String path) {
//
//		if (target == null) {
//			return "";
//		}
//
//		log.info("getRecursiveValueOfJavaField:: T:  " + target.getClass() + " ==> F: " + path);
//
//		int pos = path.indexOf('.');
//		if (pos == -1) {
//			return Utils.getValueOfJavaField(target, path);
//		} else {
//			String field = path.substring(0, pos);
//			String path2 = path.substring(pos + 1);
//
//			log.info("GET CAMP ]" + field + "[");
//			Object target2 = Utils.getValueOfJavaField(target, field);
//
//			return getRecursiveValueOfJavaField(target2, path2);
//
//		}
//
//	}

	public String processOrganGestor(String cadena) {
		String result = "";
		int pos = cadena.indexOf(">");
		if (pos != -1) {
			result = cadena.substring(pos+1);
		}
		int pos2 = result.indexOf("<");
		if (pos2 != -1) {
			result = result.substring(0, pos2);
		}

		return result.trim();
	}
	
	public String processSenseLlegir(String cadena) {
		//cadena es <span title="Events no llegits" class="badge badge-warning ">1</span><span title="Events no llegits" class="label label-warning"><b>&#9888;</b></span>
        //volem retornar el número de events no llegits
		
		String result = "";
		int pos = cadena.indexOf(">");
		if (pos != -1) {
			result = cadena.substring(pos + 1);
		}
		int pos2 = result.indexOf("<");
		if (pos2 != -1) {
			result = result.substring(0, pos2);
		}
		return result.trim();

	}
	
	public static Field<?>[] getVisibleFields(BaseFilterForm filterForm, Field<?>[] allFields) {
		Field<?>[] visibleFields;
		{
			Set<Field<?>> hiddenFields = filterForm.getHiddenFields();
			List<Field<?>> visibleFieldsL = new ArrayList<Field<?>>();
			for (Field<?> field : allFields) {
				if (!hiddenFields.contains(field)) {
					visibleFieldsL.add(field);
				}
			}
			visibleFields = visibleFieldsL.toArray(new Field<?>[visibleFieldsL.size()]);
		}
		return visibleFields;
	}

}