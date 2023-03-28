
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface IncidenciaTecnicaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_incidenciatecnica";


  public static final String _TABLE_MODEL = "incidenciaTecnica";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INCIDENCIATECNICAID = new LongField(_TABLE_MODEL, "incidenciaTecnicaID", "incidenciatecnicaid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final IntegerField ESTAT = new IntegerField(_TABLE_MODEL, "estat", "estat");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField NOMENTITAT = new StringField(_TABLE_MODEL, "nomEntitat", "nomentitat");
	 public static final StringField CONTACTENOM = new StringField(_TABLE_MODEL, "contacteNom", "contactenom");
	 public static final StringField CONTACTEEMAIL = new StringField(_TABLE_MODEL, "contacteEmail", "contacteemail");
	 public static final StringField CONTACTETELEFON = new StringField(_TABLE_MODEL, "contacteTelefon", "contactetelefon");
	 public static final StringField CAIDIDENTIFICADORCONSULTA = new StringField(_TABLE_MODEL, "caidIdentificadorConsulta", "caididentificadorconsulta");
	 public static final StringField CAIDNUMEROSEGUIMENT = new StringField(_TABLE_MODEL, "caidNumeroSeguiment", "caidnumeroseguiment");
	 public static final StringField CREADOR = new StringField(_TABLE_MODEL, "creador", "creador");
	 public static final StringField OPERADOR = new StringField(_TABLE_MODEL, "operador", "operador");


  public static final Field<?>[] ALL_INCIDENCIATECNICA_FIELDS = {
    INCIDENCIATECNICAID,
    TITOL,
    DESCRIPCIO,
    DATAINICI,
    DATAFI,
    ESTAT,
    TIPUS,
    NOMENTITAT,
    CONTACTENOM,
    CONTACTEEMAIL,
    CONTACTETELEFON,
    CAIDIDENTIFICADORCONSULTA,
    CAIDNUMEROSEGUIMENT,
    CREADOR,
    OPERADOR
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INCIDENCIATECNICAID
  };
}
