package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.ejb.PinfoEJB;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PinfoLogicaEJB")
public class PinfoLogicaEJB extends PinfoEJB implements PinfoLogicaService {

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicaEjb;

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

	@Override
	@PermitAll
	public Pinfo create(Pinfo instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public PinfoJPA findByPrimaryKey(Long _ID_) {
		return (PinfoJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public Pinfo update(Pinfo instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	public Long generarPinfoPDF(Long pinfoID) throws Exception, I18NException {

		log.info("Generant PDF per PINFO: " + pinfoID);
		PinfoJPA pinfo = findByPrimaryKey(pinfoID);

		OrderBy orderByServ = new OrderBy(PinfoDataFields.SERVEIID, OrderType.ASC);
		OrderBy orderByProc = new OrderBy(PinfoDataFields.PROCEDIMENTID, OrderType.ASC);
		OrderBy orderByUser = new OrderBy(PinfoDataFields.USUARIID, OrderType.ASC);

		OrderBy[] orderBy = { orderByUser, orderByProc, orderByServ };
		List<PinfoData> llista = pinfoDataLogicaEjb.select(PinfoDataFields.PINFOID.equal(pinfoID), orderBy);

		String lastUsuariID = null;
		Long lastProcedimentID = null;

		List<UsuariData> usuarisList = new ArrayList<UsuariData>();

		List<ProcedimentData> procedimentsList;
		List<ServeiData> serveisList;

		UsuariData lastUsuariData = null;
		ProcedimentData lastProcedimentData = null;

		for (PinfoData pinfoData : llista) {
			String usuariID = pinfoData.getUsuariid();
			Long procedimentID = pinfoData.getProcedimentID();
			Long serveiID = pinfoData.getServeiID();

			// Si es distinto, uno nuevo, sino, cojemos el anterior.
			if (!usuariID.equals(lastUsuariID)) {
				UsuariData usuariData = new UsuariData(usuariID, new ArrayList<ProcedimentData>());
				procedimentsList = usuariData.getProcediments();
				lastUsuariData = usuariData;
				lastUsuariID = usuariID;
				usuarisList.add(usuariData);
			} else {
				procedimentsList = lastUsuariData.getProcediments();
			}

			// Puede ser que el codigo de procedimiento sea igual, pero haya cambiado el
			// usuario. En ese caso, se crea un nuevo procedimiento.
			if (!procedimentID.equals(lastProcedimentID) || !usuariID.equals(lastUsuariID)) {
				SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(procedimentID);
				ProcedimentData procedimentData = new ProcedimentData(procedimentID,
						solicitud.getProcedimentCodi() + " - " + solicitud.getProcedimentNom(),
						new ArrayList<ServeiData>());
				serveisList = procedimentData.getServeis();
				lastProcedimentData = procedimentData;
				lastProcedimentID = procedimentID;
				procedimentsList.add(procedimentData);
			} else {
				serveisList = lastProcedimentData.getServeis();
			}

			ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(serveiID);
			ServeiData serveiData = new ServeiData(serveiID, servei.getCodi());
			serveisList.add(serveiData);
		}

		PinfoDataFull pinfoDataFull = new PinfoDataFull(pinfoID, usuarisList);
		printPinfoDataFull(pinfoDataFull);

		log.info(llista.size() + " registres de PinfoData per PINFO" + pinfoID);

		String fileName = "PINFO_" + pinfoID + ".pdf";
		File outputPDF = File.createTempFile("pinbaladmin_formulari_pinfo", ".pdf");
		FileOutputStream fosPDF = new FileOutputStream(outputPDF);

		File plantilla = new File(Configuracio.getTemplatePinfo());
		byte[] template = FileUtils.readFileToByteArray(plantilla);

		log.info(template.length + " bytes llegits de la plantilla: " + plantilla.getAbsolutePath());

		Map<String, Object> data = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataStr = sdf.format(new Date());
		data.put("fecha", dataStr);

		data.put("pinfo", pinfo);
		data.put("pinfoDataFull", pinfoDataFull);

		try {
			ParserFormulariXML.createPdf(new ByteArrayInputStream(template), fosPDF, data);

			long size = outputPDF.length();
			String mime = "application/pdf";
			String desc = "";

			FitxerJPA fitxer = new FitxerJPA(fileName, size, mime, desc);
			fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

			FileSystemManager.crearFitxer(outputPDF, fitxer.getFitxerID());

			pinfo.setFitxerID(fitxer.getFitxerID());
			update(pinfo);

			return fitxer.getFitxerID();

//			return null;

		} finally {
			try {
				fosPDF.flush();
				fosPDF.close();
			} catch (Exception e) {
				System.err.println("Error creant Documents de Solicitud" + e.getMessage());
			}
		}
	}

	private void printPinfoDataFull(PinfoDataFull pinfoDataFull) {
		log.info("PinfoDataFull: " + pinfoDataFull.getPinfoID());
		for (UsuariData usuariData : pinfoDataFull.getUsuaris()) {
			log.info("Usuari: " + usuariData.getUsuariID());
			for (ProcedimentData procedimentData : usuariData.getProcediments()) {
				log.info("\tProcediment: " + procedimentData.getProcedimentID());
				for (ServeiData serveiData : procedimentData.getServeis()) {
					log.info("\t\tServei: " + serveiData.getServeiID() + " - " + serveiData.getServei());
				}
			}
		}

	}

	public class ServeiData {
		private Long serveiID;
		private String servei;

		public ServeiData(Long serveiID, String servei) {
			this.serveiID = serveiID;
			this.servei = servei;
		}

		public Long getServeiID() {
			return this.serveiID;
		}

		public String getServei() {
			return this.servei;
		}

		public void setServeiID(Long serveiID) {
			this.serveiID = serveiID;
		}

		public void setServei(String servei) {
			this.servei = servei;
		}
	}

	public class ProcedimentData {
		private Long procedimentID;
		private String procediment;
		private List<ServeiData> serveis = new ArrayList<ServeiData>();

		public ProcedimentData(Long procedimentID, String procediment, List<ServeiData> serveis) {
			this.procedimentID = procedimentID;
			this.procediment = procediment;
			this.serveis = serveis;
		}

		public Long getProcedimentID() {
			return this.procedimentID;
		}

		public String getProcediment() {
			return this.procediment;
		}

		public List<ServeiData> getServeis() {
			return this.serveis;
		}

		public void setProcedimentID(Long procedimentID) {
			this.procedimentID = procedimentID;
		}

		public void setProcediment(String procediment) {
			this.procediment = procediment;
		}

		public void setServeis(List<ServeiData> serveis) {
			this.serveis = serveis;
		}
	}

	public class UsuariData {
		private String usuariID;
		private List<ProcedimentData> procediments = new ArrayList<ProcedimentData>();

		public UsuariData(String usuariID, List<ProcedimentData> procediments) {
			this.usuariID = usuariID;
			this.procediments = procediments;
		}

		public String getUsuariID() {
			return this.usuariID;
		}

		public List<ProcedimentData> getProcediments() {
			return this.procediments;
		}

		public void setUsuariID(String usuariID) {
			this.usuariID = usuariID;
		}

		public void setProcediments(List<ProcedimentData> procediments) {
			this.procediments = procediments;
		}
	}

	public class PinfoDataFull {
		private Long pinfoID;
		private List<UsuariData> usuaris = new ArrayList<UsuariData>();

		public PinfoDataFull(Long pinfoID, List<UsuariData> usuaris) {
			this.pinfoID = pinfoID;
			this.usuaris = usuaris;
		}

		public Long getPinfoID() {
			return this.pinfoID;
		}

		public List<UsuariData> getUsuaris() {
			return this.usuaris;
		}

		public void setPinfoID(Long pinfoID) {
			this.pinfoID = pinfoID;
		}

		public void setUsuaris(List<UsuariData> usuaris) {
			this.usuaris = usuaris;
		}
	}

}