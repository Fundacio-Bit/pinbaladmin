package org.fundaciobit.pinbaladmin.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class Esborrar {

    public static void main(String[] args) {

        try {

            Map<Integer, String> entitat2nom = new HashMap<Integer, String>();
            {
                File file = new File("entitatsconvertidesacaib.txt");

                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;
                while ((line = br.readLine()) != null) {
                    // process the line.

                    String[] parts = line.split("\t");

                    Integer idEnt = Integer.parseInt(parts[0]);
                    String nomEnt = parts[1];

                    entitat2nom.put(idEnt, nomEnt);

                    //System.out.println("]" + idEnt + "[   ]" + nomEnt + " ]");
                }
                br.close();
            }

            Map<Integer, String> depart2nom = new HashMap<Integer, String>();
            Map<Integer, Integer> depart2entitatantiga = new HashMap<Integer, Integer>();
            {

                File file = new File("departaments.txt");

                BufferedReader br = new BufferedReader(new FileReader(file));

                String line;
                while ((line = br.readLine()) != null) {
                    // process the line.

                    String[] parts = line.split("\t");

                    Integer idDep = Integer.parseInt(parts[0]);
                    String nomDep = parts[1];
                    Integer idEntAntiga = Integer.parseInt(parts[2]);

                    depart2nom.put(idDep, nomDep);
                    depart2entitatantiga.put(idDep, idEntAntiga);

                    //System.out.println("]" + idDep + "[ " + nomDep + " ]" + idEntAntiga  + "[");

                    String nomEntitatAntic = entitat2nom.get(idEntAntiga);

                    if (nomEntitatAntic == null) {
                        continue;
                        //throw new Exception("  XXXXXXXXXXXX   " + idEntAntiga + " val null");
                    }

                    String nounom = nomEntitatAntic + " - " + nomDep;

                    System.out.println("UPDATE pad_departament SET nom='" + nounom.replace("'", "''")
                            + "'  WHERE departamentid=" + idDep + ";");

                }

                br.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}
