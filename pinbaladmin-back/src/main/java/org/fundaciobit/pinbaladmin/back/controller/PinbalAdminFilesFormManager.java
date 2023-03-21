package org.fundaciobit.pinbaladmin.back.controller;

import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;

/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class PinbalAdminFilesFormManager extends FilesFormManager<Fitxer> {

    public PinbalAdminFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
        super(fitxerEjb);
    }

    @Override
    public FitxerJPA createEmptyFile() {
        return new FitxerJPA();
    }

}
