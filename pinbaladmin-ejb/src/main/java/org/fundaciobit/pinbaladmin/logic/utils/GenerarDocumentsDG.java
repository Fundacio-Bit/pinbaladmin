package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.ContacteLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;

@Stateless(name = "GenerarDocumentsDGLogicaEJB")
public class GenerarDocumentsDG implements GenerarDocumentsDGLogicaService {
	
	private static final Logger log = Logger.getLogger(GenerarDocumentsDG.class);
	
	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;
	
	@EJB(mappedName = ContacteLogicaService.JNDI_NAME)
	protected ContacteLogicaService contacteLogicaEjb;
	
	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicaEjb;
	
	@EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
	protected TramitCDadesCesiLogicaService tramitCDadesCesiLogicaEjb;
	
	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
	
    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicEjb;
    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;

	public GenerarDocumentsDG() {
		// Constructor vacío
	}
	
	@Override
	public void generarFormulariDirectorGeneralPDFODT(Long solicitudID) throws Exception {
		
		//Preparar datos.
		Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
		
		if (soli == null) {
			return;
		}
		
	    Map<String, Object> data = new HashMap<String, Object>();
		
		Long auditoriaID = soli.getContacteAuditoriaID(); //E
		Long gestionID = soli.getContacteGestAutID(); //D
		Long titularID = soli.getContacteTitularID(); //G
		
		Contacte auditor = null, gestor = null, titular = null;
		
		if (auditoriaID != null) {
            auditor = contacteLogicaEjb.findByPrimaryKey(auditoriaID);
        }
		
		if (gestionID != null) {
			gestor = contacteLogicaEjb.findByPrimaryKey(gestionID);
		}
		
		if (titularID != null) {
            titular = contacteLogicaEjb.findByPrimaryKey(titularID);
        }
		
		List<Contacte> contactos = new java.util.ArrayList<>();
		contactos.add(auditor);
		contactos.add(gestor);
		contactos.add(titular);
		
		//Si un campo es null, poner '---'
		for (Contacte contacte : contactos) {
			contacte.setTelefon(contacte.getTelefon() != null ? contacte.getTelefon() : "---");
			contacte.setMail(contacte.getMail() != null ? contacte.getMail() : "---");
			contacte.setCarrec(contacte.getCarrec() != null ? contacte.getCarrec() : "---");
			contacte.setLlinatge2(contacte.getLlinatge2() != null ? contacte.getLlinatge2() : "---");
			contacte.setLlinatge1(contacte.getLlinatge1() != null ? contacte.getLlinatge1() : "---");
			contacte.setNom(contacte.getNom() != null ? contacte.getNom() : "---");
			contacte.setNif(contacte.getNif() != null ? contacte.getNif() : "---");
		}
		
		
		Long organID = soli.getOrganid();
		
		data.put("auditor", auditor);
		data.put("gestor", gestor);
		data.put("titular", titular);
		
		setOrganGestorProperties(organID, data);

		File outputPDF = File.createTempFile("pinbaladmin_formulari", ".pdf");
		File outputODT = File.createTempFile("pinbaladmin_formulari", ".odt");

		crearDocuments(data, outputPDF, outputODT);
		
		{
			String fileName = "Formulario_Director_General.pdf";
			File output = outputPDF;
			String mimeType = "application/pdf";
			Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF;
			String nomDocument = "Formulario_Director_General (PDF)";
			
			afegirFitxerADocumentsSolicitud(fileName, output, mimeType, nomDocument, tipus, solicitudID);
		}
		{
			String fileName = "Formulario_Director_General.odt";
			File output = outputODT;
			String mimeType = "application/vnd.oasis.opendocument.text";
			Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT;
			String nomDocument = "Formulario_Director_General (ODT)";

			afegirFitxerADocumentsSolicitud(fileName, output, mimeType, nomDocument, tipus, solicitudID);
		}
	}
	
	private void afegirFitxerADocumentsSolicitud(String fileName, File output, String mimeType, String nomDocument,
			Long tipus, Long solicitudID) throws Exception {
		
		FitxerJPA fitxer = new FitxerJPA(fileName, output.length(), mimeType, "");

		fitxer = (FitxerJPA) fitxerPublicLogicaEjb.create(fitxer);

		FileSystemManager.sobreescriureFitxer(output, fitxer.getFitxerID());

        Document doc = documentLogicaEjb.create(nomDocument, fitxer.getFitxerID(), null, null, tipus);

        DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

        documentSolicitudLogicEjb.create(ds);
        log.info("Afegit document: " + nomDocument + " a la solicitud: " + solicitudID );
	}

	
	private void crearDocuments(Map<String, Object> data, File outputPDF, File outputODT) throws Exception {

		File plantilla = new File(Configuracio.getTemplateFormulari());
		
	    data.put("data", new Date());
		
	    FileOutputStream fosPDF = new FileOutputStream(outputPDF);
	    FileOutputStream fosODT = new FileOutputStream(outputODT);
	    //FileInputStream fis = new FileInputStream(plantilla);
	    
	    byte[] template = FileUtils.readFromFile(plantilla);
	    
	    try {
	    	ParserFormulariXML.createPdf(new ByteArrayInputStream(template), fosPDF, data);
	    	ParserFormulariXML.createOdt(new ByteArrayInputStream(template), fosODT, data);

	    } finally {
	      try {
	       
	        fosODT.flush();
	        fosODT.close();
	        
	        fosPDF.flush();
	        fosPDF.close();
	      } catch (Exception e) {
	          System.err.println("Error creant Documents de Solicitud" + e.getMessage());
	      }

	    }
	}
	
	public void setOrganGestorProperties(Long organID,  Map<String, Object> data ) throws Exception {
		String denomincaion;
		String cif;
		String UR;
		String dir3UR;
		String dir3Raiz;

		/*
		 * Denominació: Organ Gestor
		 * CIF: Primer CIF que trobi cercant als pares.
		 * Unitat Responsable: Si el CIF es el de Govern, posar DGTIC, sino, la del CIF trobat.
		 * DIR3 RESPONSABLE: DIR3 UR
		 * DIR3 RAIZ: Dir3 pare mes alt.
		 */
		
		Organ organGestor = organLogicaEjb.findByPrimaryKey(organID);
		Organ unitatResponsable = null; 
		Organ arrel = null; 

		Organ organTest = organGestor;
		boolean end = false;
		while (!end) {
			if (unitatResponsable == null && organTest.getCif() != null) {
				unitatResponsable = organTest;
			}
			if (arrel == null && organTest.getDir3pare() == null) {
				arrel = organTest;
			}
			
			if (organTest.getDir3pare() != null) {
				List<Organ> pares = organLogicaEjb.select(OrganFields.DIR3.equal(organTest.getDir3pare()));
				organTest = pares.get(0);
			}else {
				end = true;
			}
		}
		
		
		denomincaion = organGestor.getNom();
		cif = unitatResponsable.getCif();
		
		if (arrel.getCif().equals("S0711001H")) {
			String dir3Dgtic = "A04027005";
			List<Organ> organs = organLogicaEjb.select(OrganFields.DIR3.equal(dir3Dgtic));
			if (organs.size() == 1) {
				Organ dgtic = organs.get(0);
				unitatResponsable = dgtic;
			}
		}
		
		UR = unitatResponsable.getNom();
		dir3UR = unitatResponsable.getDir3();
		
		dir3Raiz = arrel.getDir3();

//		log.info("denomincaion: " + denomincaion);
//		log.info("cif: " + cif);
//		log.info("UR: " + UR);
//		log.info("dir3UR: " + dir3UR);
//		log.info("dir3Raiz: " + dir3Raiz);

		data.put("DENOMINACION", denomincaion);
		data.put("CIF", cif);
		data.put("UNIDAD_RESPONSABLE", UR);
		data.put("DIR3_UR", dir3UR);
		data.put("DIR3_RAIZ", dir3Raiz);
		
		
		//Ara hem de cercar l'unitat responsable a tramitC i trobar les dades d'ubicacio. Si no les trobam, posem unes dades per defecte.
		List<TramitCDadesCesi> urs = tramitCDadesCesiLogicaEjb
				.select(TramitCDadesCesiFields.ORGANID.equal(unitatResponsable.getOrganid()));		
		
		if (urs.size() > 0) {
			TramitCDadesCesi ur = urs.get(0);
			data.put("DIRECCION", ur.getDireccio());
			data.put("MUNICIPIO", tramitCDadesCesiLogicaEjb.getMunicipiValue(ur.getMunicipi()));
			data.put("CP", ur.getCodipostal());
//		} else if (urs.size() > 1) {
//			//Puede ser que haya varios tramites hechos por el mismo organo gestor. Y puede que todos tengan los mismos datos, o puede que los tengan diferentes.
//			// Por eso nos quedamos con el más repetido.
//			
//			Map<String, Integer> countMap = new HashMap<>();
//			for (TramitCDadesCesi ur : urs) {
//				String key = ur.getDireccio() + "|" + ur.getMunicipi() + "|" + ur.getCodipostal();
//				countMap.put(key, countMap.getOrDefault(key, 0) + 1);
//			}
//			
//			String mostFrequentKey = Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
//			String[] parts = mostFrequentKey.split("\\|");
//			prop.setProperty("DIRECCION", parts[0]);
//			prop.setProperty("MUNICIPIO", parts[1]);
//			prop.setProperty("CP", parts[2]);
//			
		} else {
//			log.warn("No s'ha trobat informació d'ubicació per a l'unitat responsable. Posant dades per defecte.");
			data.put("DIRECCION", "Carrer Sant Pere n.7");
			data.put("MUNICIPIO", "Palma");
			data.put("CP", "07012");
		}
		
		//Dades per defecte:
//		direccio: Carrer Sant Pere n.7
//		municipi: Palma
//		CP: 07012
		
	}
}
