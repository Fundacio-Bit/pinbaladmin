
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class DocumentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public DocumentQueryPath() {
  }

  protected DocumentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DOCUMENTID() {
    return new LongField(getQueryPath(), DocumentFields.DOCUMENTID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), DocumentFields.NOM);
  }

  public LongField FITXERORIGINALID() {
    return new LongField(getQueryPath(), DocumentFields.FITXERORIGINALID);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), DocumentFields.FITXERFIRMATID);
  }

  public StringField NOTES() {
    return new StringField(getQueryPath(), DocumentFields.NOTES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (DocumentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public DocumentSolicitudQueryPath DOCUMENTSOLICITUDS() {
    return new DocumentSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentQueryPath.this.getQueryPath() + "documentSolicituds" + ".";
      }
    });
  }
*/

  public FitxerQueryPath FITXERORIGINAL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentQueryPath.this.getQueryPath() + "fitxerOriginal" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERFIRMAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentQueryPath.this.getQueryPath() + "fitxerFirmat" + ".";
      }
    });
  }

}
