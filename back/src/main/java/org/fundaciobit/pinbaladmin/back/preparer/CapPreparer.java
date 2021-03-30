package org.fundaciobit.pinbaladmin.back.preparer;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.stereotype.Component;


/**
 * @author GenApp
 *
 */
@RunAs("PAD_USER")
@Component
public class CapPreparer extends ViewPreparerSupport {
  
  protected final Logger log = Logger.getLogger(getClass());

	@Override
	public void execute(TilesRequestContext tilesContext, 
	    AttributeContext attributeContext) throws PreparerException {

	 // Map<String, Object> request = tilesContext.getRequestScope();

    // TODO GENAPP  Select  available languages 

	}
}
