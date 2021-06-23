
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EventQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EventQueryPath() {
  }

  protected EventQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField EVENTID() {
    return new LongField(getQueryPath(), EventFields.EVENTID);
  }

  public LongField SOLICITUDID() {
    return new LongField(getQueryPath(), EventFields.SOLICITUDID);
  }

  public LongField INCIDENCIATECNICAID() {
    return new LongField(getQueryPath(), EventFields.INCIDENCIATECNICAID);
  }

  public TimestampField DATAEVENT() {
    return new TimestampField(getQueryPath(), EventFields.DATAEVENT);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), EventFields.TIPUS);
  }

  public StringField PERSONA() {
    return new StringField(getQueryPath(), EventFields.PERSONA);
  }

  public StringField COMENTARI() {
    return new StringField(getQueryPath(), EventFields.COMENTARI);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), EventFields.FITXERID);
  }

  public BooleanField NOLLEGIT() {
    return new BooleanField(getQueryPath(), EventFields.NOLLEGIT);
  }

  public StringField CAIDIDENTIFICADORCONSULTA() {
    return new StringField(getQueryPath(), EventFields.CAIDIDENTIFICADORCONSULTA);
  }

  public StringField CAIDNUMEROSEGUIMENT() {
    return new StringField(getQueryPath(), EventFields.CAIDNUMEROSEGUIMENT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EventFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public SolicitudQueryPath SOLICITUD() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EventQueryPath.this.getQueryPath() + "solicitud" + ".";
      }
    });
  }

  public IncidenciaTecnicaQueryPath INCIDENCIATECNICA() {
    return new IncidenciaTecnicaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EventQueryPath.this.getQueryPath() + "incidenciaTecnica" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EventQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

}
