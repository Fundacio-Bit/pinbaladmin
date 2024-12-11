package org.fundaciobit.pinbaladmin.logic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataEJB;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PinfoDataLogicaEJB")
public class PinfoDataLogicaEJB extends PinfoDataEJB implements PinfoDataLogicaService {

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;
	
	@Override
	@PermitAll
	public PinfoData create(PinfoData instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public PinfoDataJPA findByPrimaryKey(Long _ID_) {
		return (PinfoDataJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public PinfoData update(PinfoData instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	@PermitAll
	public void delete(Long id) {
		super.delete(id);
	}
	
	@Override
	@PermitAll
	public void delete(PinfoData instance) {
		super.delete(instance);
	}

	

	@Override
	public PinfoDataFull getEstructuraUsuarisProcedimentServeis(Long pinfoID) throws I18NException {
		
		log.info("getEstructuraUsuarisProcedimentServeis per PINFO" + pinfoID);
		
		OrderBy orderByServ = new OrderBy(PinfoDataFields.SERVEIID, OrderType.ASC);
		OrderBy orderByProc = new OrderBy(PinfoDataFields.PROCEDIMENTID, OrderType.ASC);
		OrderBy orderByUser = new OrderBy(PinfoDataFields.USUARIID, OrderType.ASC);

		OrderBy[] orderBy = { orderByUser, orderByProc, orderByServ };
		List<PinfoData> llista = this.select(PinfoDataFields.PINFOID.equal(pinfoID), orderBy);

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

//			log.info("UsuariID: " + usuariID + " lastUsuariID: " + lastUsuariID);
//			log.info("ProcedimentID: " + procedimentID + " lastProcedimentID: " + lastProcedimentID);
			boolean nouUsuari = !usuariID.equals(lastUsuariID);
			boolean nouProcediment = !procedimentID.equals(lastProcedimentID);
			
			// Si es distinto, uno nuevo, sino, cojemos el anterior.
			if (nouUsuari) {
//				log.info("Creant nou usuari amb nova llista de procediments");
				UsuariData usuariData = new UsuariData(usuariID, new ArrayList<ProcedimentData>());
				procedimentsList = usuariData.getProcediments();
				lastUsuariData = usuariData;
				lastUsuariID = usuariID;
				usuarisList.add(usuariData);
			} else {
//				log.info("Usuari " + usuariID + " ja existent, afegirem al seu procediment.");
				procedimentsList = lastUsuariData.getProcediments();
			}

			// Puede ser que el codigo de procedimiento sea igual, pero haya cambiado el
			// usuario. En ese caso, se crea un nuevo procedimiento.
			if (nouProcediment || nouUsuari) {
//				log.info("Creant nou procediment");
				SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(procedimentID);
				ProcedimentData procedimentData = new ProcedimentData(procedimentID,
						solicitud.getProcedimentNom(),solicitud.getProcedimentCodi(), 
						new ArrayList<ServeiData>());
				serveisList = procedimentData.getServeis();
				lastProcedimentData = procedimentData;
				lastProcedimentID = procedimentID;
				procedimentsList.add(procedimentData);
			} else {
//				log.info("Procediment " + procedimentID + " ja existent, afegirem al seu serveiList.");
				serveisList = lastProcedimentData.getServeis();
			}

			ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(serveiID);
			ServeiData serveiData = new ServeiData(serveiID, servei.getCodi(), pinfoData.getPinfodataID(), pinfoData.getAlta());
			serveisList.add(serveiData);
		}

		PinfoDataFull pinfoDataFull = new PinfoDataFull(pinfoID, usuarisList);
		printPinfoDataFull(pinfoDataFull);

		log.info(llista.size() + " registres de PinfoData per PINFO" + pinfoID);
		return pinfoDataFull;

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
		private Long pinfoDataID;
		private Long alta;
		

		public ServeiData(Long serveiID, String servei, Long pinfoDataID, Long alta) {
			this.serveiID = serveiID;
			this.servei = servei;
			this.pinfoDataID = pinfoDataID;
			this.alta = alta;
		}

		public Long getServeiID() {
			return this.serveiID;
		}

		public String getServei() {
			return this.servei;
		}
		
		public Long getPinfoDataID() {
			return this.pinfoDataID;
		}
		
		public Long getAlta() {
			return this.alta;
		}

		public void setServeiID(Long serveiID) {
			this.serveiID = serveiID;
		}

		public void setServei(String servei) {
			this.servei = servei;
		}
		
		public void setPinfoDataID(Long pinfoDataID) {
			this.pinfoDataID = pinfoDataID;
		}
		
		public void setAlta(Long alta) {
			this.alta = alta;
		}
	}

	public class ProcedimentData {
		private Long procedimentID;
		private String procediment;
		private String codi;
		private List<ServeiData> serveis = new ArrayList<ServeiData>();

		public ProcedimentData(Long procedimentID, String procediment, String codi,  List<ServeiData> serveis) {
			this.procedimentID = procedimentID;
			this.procediment = procediment;
			this.codi = codi;
			this.serveis = serveis;
		}

		public Long getProcedimentID() {
			return this.procedimentID;
		}

		public String getProcediment() {
			return this.procediment;
		}

		public String getCodi() {
			return this.codi;
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

		public void setCodi(String codi) {
            this.codi = codi;
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