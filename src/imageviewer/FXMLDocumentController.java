/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer;

import java.io.File ;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author uapv1400733
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField directoryPath;
            
    @FXML
    private Button browseButton ;
    
    @FXML
    private Label picture ;
    
    @FXML
    private Label welcomeText ;
    
    @FXML
    private Label welcomeText2 ;
        
    @FXML
    private TableView<Picture> images ;
    
    
    private TableView<Picture> originalImageList = new TableView<>();
        
    
    @FXML 
    private TableColumn<Picture, String> name ; 
    
    @FXML 
    private TableColumn<Picture, String> extension ; 
    
    @FXML
    private TableColumn<Picture, String> path ; 
    
    @FXML
    private ImageView currentImage;
    
    @FXML
    private ImageView prevImageButton;
    
    @FXML
    private ImageView playDiapoButton;
    
    @FXML
    private ImageView nextImageButton;
    
    @FXML
    private ImageView cropImageButton;
    
    @FXML
    private ImageView switchImageButton;
    
    @FXML
    private ImageView addKeyWord;
    
    @FXML
    private TextField pictureName;
    
    @FXML
    private TextArea keyWords;    
    private Map<String,List<String>> keyWordsMap = new HashMap();
    
    @FXML
    private TextField searchArea ;
    @FXML
    private Label labelRecherche ;
    @FXML
    private Label renameLabel ;
    @FXML
    private Label keywordsLabel ;
    @FXML
    private Tab fileTab ;
    @FXML
    private Tab editTab;
    
    @FXML
    private Menu langMenu;
    @FXML
    private MenuItem englishItem;
    @FXML
    private MenuItem arabicItem;
    @FXML
    private MenuItem frenchItem;
    
    
    /* gg
     * ------------------ Réalisé par EL KHATTAB Mahmoud -------------------------
     * fonction pour intercepter l'action de parcour d'un dossier 
     * ---------------------------------------------------------------------------
     */
    @FXML
    private void browseDirectory(ActionEvent event) throws Exception {

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        extension.setCellValueFactory(new PropertyValueFactory<>("extension"));
        path.setCellValueFactory(new PropertyValueFactory<>("path"));

        DirectoryChooser dialog = new DirectoryChooser();
        File selectedDirectory = dialog.showDialog(browseButton.getScene().getWindow());
        
        if (selectedDirectory != null) {
            images.getItems().removeAll(images.getItems()); 
            directoryPath.setText(selectedDirectory.getAbsolutePath());
            String [] listefichiers;
            listefichiers=selectedDirectory.list();
            for(int i=0;i<listefichiers.length; i++){
                if(listefichiers[i].endsWith(".png")){
                    String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                    images.getItems().add(new Picture(picName,".png",selectedDirectory.getAbsolutePath()));
                }
                if(listefichiers[i].endsWith(".bmp")){
                    String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                    images.getItems().add(new Picture(picName,".bmp",selectedDirectory.getAbsolutePath()));
                }
                if(listefichiers[i].endsWith(".jpg")){
                    String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                    images.getItems().add(new Picture(picName,".jpg",selectedDirectory.getAbsolutePath()));
                }
                if(listefichiers[i].endsWith(".gif")){
                    String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                    images.getItems().add(new Picture(picName,".gif",selectedDirectory.getAbsolutePath()));
                }
                if(listefichiers[i].endsWith("jpeg")){
                    String picName = listefichiers[i].substring(0,listefichiers[i].length()-5) ;
                    images.getItems().add(new Picture(picName,".jpeg",selectedDirectory.getAbsolutePath()));
                }
            }
            originalImageList.getItems().addAll(images.getItems()) ; 
        }
    }
    
    private void initializeTable() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        extension.setCellValueFactory(new PropertyValueFactory<>("extension"));
        path.setCellValueFactory(new PropertyValueFactory<>("path"));
        
        File defaultDirectory = new File(System.getProperty("user.dir"));
        images.getItems().removeAll(images.getItems());
        Path path = FileSystems.getDefault().getPath(".");
        String [] listefichiers;
        
        listefichiers=defaultDirectory.list();
        for(int i=0;i<listefichiers.length; i++){
            if(listefichiers[i].endsWith(".png")){
                String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                images.getItems().add(new Picture(picName,".png",defaultDirectory.getAbsolutePath()));
            }
            if(listefichiers[i].endsWith(".bmp")){
                String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                images.getItems().add(new Picture(picName,".bmp",defaultDirectory.getAbsolutePath()));
            }
            if(listefichiers[i].endsWith(".jpg")){
                String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                images.getItems().add(new Picture(picName,".jpg",defaultDirectory.getAbsolutePath()));
            }
            if(listefichiers[i].endsWith(".gif")){
                String picName = listefichiers[i].substring(0,listefichiers[i].length()-4) ;
                images.getItems().add(new Picture(picName,".gif",defaultDirectory.getAbsolutePath()));
            }
            if(listefichiers[i].endsWith(".jpeg")){
                String picName = listefichiers[i].substring(0,listefichiers[i].length()-5) ;
                images.getItems().add(new Picture(picName,".jpeg",defaultDirectory.getAbsolutePath()));
            }
        }

        originalImageList.getItems().addAll(images.getItems()) ;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        updateAAlKeyWorList(keyWordsMap);
        findImagesKeyWords(); 
        initializeTable();
        displayPicture();
        openWindow();
        rechercherParMotCle();
    } 
    
    /** ---------------------- Réalisé par EL KHATTAB Mahmoud -----------------------------
     * Suite au clic d'une image ou d'un click sur les touches up, down 
     * du clavier dans la liste du répertoire, les vues sont mises à jour
     * ------------------------------------------------------------------------------------
     */
    private void displayPicture() {
        images.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeImageDisplayed();
            }
            
        });
        images.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                changeImageDisplayed();
            }
            
        });
        images.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                changeImageDisplayed();
            }
            
        });
    }
    /** ---------------------- Réalisé par EL KHATTAB Mahmoud -----------------------------
     * cette méthode est appelé à chaque clic sur la souris ou les touches haut et bas du
     * clavier et permet l'a&ffichagge de l'image sur laquelle à été effectué l'action
     * ------------------------------------------------------------------------------------
     */    
    private void changeImageDisplayed(){
        /*  MAJ de la vue de visualisation de l'image */
                prevImageButton.setVisible(true);
                playDiapoButton.setVisible(true);
                nextImageButton.setVisible(true);
                cropImageButton.setVisible(true);
                switchImageButton.setVisible(true);
                welcomeText.setVisible(false);
                welcomeText2.setVisible(false);
                currentImage.setVisible(true);
                editTab.setDisable(false);
                
                String path = images.getSelectionModel().getSelectedItem().getPath();
                String name = images.getSelectionModel().getSelectedItem().getName();
                String extension = images.getSelectionModel().getSelectedItem().getExtension();
                File sourceimage = new File(path+"/"+name+extension); 
                Image image = new Image(sourceimage.toURI().toString());
                currentImage.setImage(image);
                
                /* MAJ de la vue d'édition */
                pictureName.setText(name);
                
                /* MAJ de la zone de texte contenant les mots-clés associés à une image */
                keyWords.setText("");
                
                /* on récupère dans une liste les mots-clés associés au nom de l'image*/
                List list = keyWordsMap.get(pictureName.getText());
                /* si la liste est vide alors pas besoin de récupérer les mots-clés */
                /* sinon, on parcourt la liste et on remplit la zone de texte à chaque itération */
               
                if (list != null) {
                    for(int i = 0; i < list.size(); i++) {
                        if (keyWords.getText().equals("")) {
                            keyWords.setText(list.get(i).toString());
                        }
                        else {
                            keyWords.setText(keyWords.getText()+"\n"+list.get(i));
                        }
                    }
                }
    }
    /** ---------------------- Réalisé par HARKIOLAKIS Laurent -------------------
     * Ouverture d'une fenêtre pour la saisie de mot-clés
     * ---------------------------------------------------------------------------
     */
    private void openWindow() {
        addKeyWord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showDialog();
            }
 
        });
    }
    /** ---------------------- Réalisé par HARKIOLAKIS Laurent -------------------
     * Conception de la fenêtre pour la saisie de mot-clés 
     * ---------------------------------------------------------------------------
     */
    private void showDialog() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ajouter un mot-clé");
        stage.setResizable(false);
        TextField newKeyWord = new TextField("");
        Button addButton = new Button("Ajouter");
        Button cancelButton = new Button("Annuler");
        /* gestion de l'ajout d'un mot-clé */
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                /* si l'utilisateur ne remplit pas le champ on ne rajoute rien */
                /* il faudra gérer les espaces */
                if (newKeyWord.getText().equals("")) {
                    stage.close();
                }
                else {
                    if (keyWords.getText().equals(""))
                        keyWords.setText(newKeyWord.getText());
                    else
                        keyWords.setText(keyWords.getText()+"\n"+newKeyWord.getText());
                    /* si dans la map il n'existe pas encore de liste pour l'image alors on la créé */
                    if (keyWordsMap.get(pictureName.getText()) == null) {
                        List list = new ArrayList();
                        list.add(newKeyWord.getText());
                        keyWordsMap.put(pictureName.getText(),list);
                    }
                    else {
                        keyWordsMap.get(pictureName.getText()).add(newKeyWord.getText());
                    }
                    updateAAlKeyWorList(keyWordsMap);
                    stage.close();
                }
            }
        });
        cancelButton.setOnAction(e -> stage.close());
        VBox root = new VBox();
        HBox buttons = new HBox();
        buttons.getChildren().addAll(addButton, cancelButton);
        root.getChildren().addAll(newKeyWord, buttons);
        Scene scene = new Scene(root,200,50);
        stage.setScene(scene);
        stage.show();
        
    }
    
    /* --------------------- Réaliser par EL KHATTAB Mahmoud ---------------------------
     * cette fonction permet de rechercher les images dont le ou les mots clés commencer 
     * par la lettre ou le mot saisi dans le champs de texte dédié à la recherche 
     * ---------------------------------------------------------------------------------
     */
    private void rechercherParMotCle(){
        searchArea.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                String motCle = searchArea.getText();
                if(motCle.equals("")){
                    images.getItems().removeAll(images.getItems());
                    images.getItems().addAll(originalImageList.getItems());
                     
                }
                if(!motCle.equals("")){
                    if(keyWordsMap.isEmpty()){
                            images.getItems().removeAll(images.getItems());
                    }
                    else{
                        List<String> picturesWithKeyWords = new ArrayList<String>() ;
                        for(Map.Entry<String, List<String>> element : keyWordsMap.entrySet()){
                            String pictureName = element.getKey();
                            List<String> keyWords = element.getValue() ; 
                            if(contains(keyWords,motCle)){
                                picturesWithKeyWords.add(pictureName);
                            }
                        }
                        List<Picture> pictures = new ArrayList<Picture>();
                        pictures.addAll(originalImageList.getItems()) ;
                        images.getItems().removeAll(images.getItems()) ;                       
                        for(int j=0; j <picturesWithKeyWords.size(); j++ ){
                            for(int i=0 ; i < pictures.size()  ; i++){
                                if(pictures.get(i).getName().equals(picturesWithKeyWords.get(j))){
                                    images.getItems().add(pictures.get(i));
                                }    
                            }
                        }
                    }
                }
            }
        });
    }
    boolean contains(List<String> keyWords, String motCle){
            for(int i = 0 ; i< keyWords.size(); i++ ){
                if(keyWords.get(i).startsWith(motCle))
                    return true ; 
            }
            return false ; 
    }
    @FXML
    private void setLanguageToEnglish(){
        switchInterfaceLanguage("anglais");
    }
    @FXML
    private void setLanguageToArabic(){
        
        switchInterfaceLanguage("arabe");
    }
    @FXML
    private void setLanguageToFrench(){
        switchInterfaceLanguage("francais");
    }
    /**
     * ---------------------- Réalisé par EL KHATTAB Mahmoud ------------------------------
     * Cette fonction permet le changement de la langue de l'interface graphique 
     * selon le choix de l'utilisateur à travers un menu de langues. Un affichage par defaut 
     * des éléments de l'interface est adopté au lancement de l'application.
     * ------------------------------------------------------------------------------------
     */
    private void switchInterfaceLanguage(String language){
        ResourceBundle rb ; 
        /* modification du contenu des différents widgets de l'interface */
        switch(language){
            case "anglais":{
                Locale locale = new Locale("en","US");
                rb = ResourceBundle.getBundle("imageviewer.properties.langue",locale);
                break;
            }
            case "arabe":{
                Locale locale = new Locale("ar","MA");
                rb = ResourceBundle.getBundle("imageviewer.properties.langue",locale);
                break;
            }
            case "francais":{
                Locale locale = new Locale("fr","FR");
                rb = ResourceBundle.getBundle("imageviewer.properties.langue",locale);
                break;
            }
            default:{
                rb = ResourceBundle.getBundle("imageviewer.properties.langue");
                break ;
            }
        }
        langMenu.setText(rb.getString("langue"));
        fileTab.setText(rb.getString("fichier"));
        editTab.setText(rb.getString("edition"));
        labelRecherche.setText(rb.getString("recherche"));
        browseButton.setText(rb.getString("parcourir"));
        browseButton.setText(rb.getString("nom"));
        browseButton.setText(rb.getString("extension"));
        browseButton.setText(rb.getString("chemin"));
        renameLabel.setText(rb.getString("renommer"));
        keywordsLabel.setText(rb.getString("motscles"));
        frenchItem.setText(rb.getString("francais"));
        arabicItem.setText(rb.getString("arabe"));
        englishItem.setText(rb.getString("anglais"));
    }
    private void findImagesKeyWords(){ 
        keyWordsMap = Util.readKeyWordsFromFile();
    }
    private void updateAAlKeyWorList(Map<String, List<String>> keyWords){
        Util.setAllKeyWords(keyWordsMap);
    }
}
