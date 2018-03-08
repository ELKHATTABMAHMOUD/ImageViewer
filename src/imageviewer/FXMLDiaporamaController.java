/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 *
 * @author uapv1400733
 */
public class FXMLDiaporamaController implements Initializable {
    
    
    /*---------------------- button assocés à une image affichée ----------------------*/
    @FXML
    private ImageView playDiapoButton ;
    
    @FXML
    private TableView<Picture> images ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        images = new TableView<Picture>();
        // TODO
        //Image img = new Image("C:/Users/EL-KH-MAHMOUD/Desktop/bac.jpg");
    } 
   
}
