/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EL-KH-MAHMOUD Mahmoud 
 */
public class Util implements Serializable{
    private static Map<String, List<String>> allKeyWords= new HashMap<>();
    final private static String filePath = "keywords.txt";
    
    public static Map<String, List<String>> getAllKeyWords(){
        return Util.allKeyWords ;
    }
    public static void setAllKeyWords(Map<String, List<String>> keyWords){
        Util.allKeyWords = keyWords ;
    }
    /**
     * ---------------------- Réalisé par EL KHATTAB Mahmoud ---------------------
     * Cette méthode permet l'ecriture (sérialisation) de l'ensemble 
     * des mots clés dans un fichier
     * ---------------------------------------------------------------------------
     */
    public static void writeKeyWordsToFile(){
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(allKeyWords);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /**
     * ---------------------- Réalisé par EL KHATTAB Mahmoud ---------------------
     * Cette méthode permet lecture (désérialisation) de l'ensemble 
     * des mots clés à partir du fichier de sérialisation
     * ---------------------------------------------------------------------------
     */
    public static Map<String, List<String>> readKeyWordsFromFile(){
        
        String name ; 
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Util.allKeyWords = (HashMap<String, List<String>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null ;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null ;
        }
        return Util.allKeyWords;
    }
    
}
