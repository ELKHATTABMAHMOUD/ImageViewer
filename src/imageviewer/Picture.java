/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

/**
 *
 * @author uapv1800074
 */
public class Picture {
    private String name ; 
    private String extension ; 
    private String path ;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public Picture(String name, String extension, String path){
        this.name = name ; 
        this.extension = extension; 
        this.path = path ; 
    }
    
    
    
}
