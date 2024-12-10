package org.fundaciobit.pinbaladmin.ejb.test;

import java.util.List;

import javax.ejb.EJB;

import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitFCteTecLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;

import es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaEJB.C;


public class MappingSolicituds {
 
    public static void main(String[] args) {

    	A a = new A();
    	B b = new B();
    	
    	C c = b;
    	c.myMethod();
    	
    	
    }
    
    public class A extends C {
  		private int Aa;
  		private int Ab;
  		
  		public A() {
  			this.Aa = 1;
  			this.Ab = 2;
  		}
  		
  		@Override
  		public void myMethod() {
  			System.out.println("A");
  		}
  		
  	}
      
  	public class B extends C {
  		private int Ba;
  		private int Bb;

  		public B() {
  			this.Ba = 1;
  			this.Bb = 2;
  		}

  		@Override
  		public void myMethod() {
  			System.out.println("B");
  		}
  	}
  	
  	public abstract class C {
  		private int Ca;
  		private int Cb;

  		public C() {
  			this.Ca = 1;
  			this.Cb = 2;
  		}

  		public abstract void myMethod();
  	}
  	
}
