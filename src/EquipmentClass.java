//
//public class EquipmentClass {
//
//    // declare instance variable of Equipment
//    private String brandEquipment;
//    private String modelEquipment;
//    private String typeEquipment;
//    private String colorEquipment;
//    private int lengthEquipment;
//    private int widthEquipment;
//    private int heightEquipment;
//    private int capacityEquipment;
//    private String user;
//    private String imageURL;
//
//    // default constructor
//    public EquipmentClass ()
//    {
//        brandEquipment = null;
//        modelEquipment = null;
//        typeEquipment = null;
//        colorEquipment = null;
//        lengthEquipment = 0;
//        widthEquipment = 0;
//        heightEquipment = 0;
//        capacityEquipment = 0;
//        user = null;
//        imageURL = null;
//
//    }
//
//    // constructor typeEquipment
//    public EquipmentClass(String typeEquipment) {
//        this.brandEquipment = null;
//        this.modelEquipment = null;
//        this.typeEquipment = typeEquipment;
//        this.colorEquipment = null;
//        this.lengthEquipment = 0;
//        this.widthEquipment = 0;
//        this.heightEquipment = 0;
//        this.capacityEquipment = 0;
//        this.imageURL = null;
//    }
//
//    // constructor brandEquipment, modelEquipment, typeEquipment, colorEquipment, lengthEquipment, widthEquipment, HeightEquipment, capacityEquipment, user
//    public EquipmentClass(String brandEquipment, String modelEquipment,String typeEquipment, String colorEquipment, int lengthEquipment, int widthEquipment, int heightEquipment, int capacityEquipment,String imageURL) {
//        this.brandEquipment = brandEquipment;
//        this.modelEquipment = modelEquipment;
//        this.typeEquipment = typeEquipment;
//        this.colorEquipment = colorEquipment;
//        this.lengthEquipment = lengthEquipment;
//        this.widthEquipment = widthEquipment;
//        this.heightEquipment = heightEquipment;
//        this.capacityEquipment = capacityEquipment;
//
//        this.imageURL = imageURL;
//    }
//
//
//    // getter & setter methods
//    public String getBrandEquipment() {
//        return brandEquipment;
//    }
//
//    public void setBrandEquipment(String brandEquipment) {
//        this.brandEquipment = brandEquipment;
//    }
//
//    public String getModelEquipment() {
//        return modelEquipment;
//    }
//
//    public void setModelEquipment(String modelEquipment) {
//        this.modelEquipment = modelEquipment;
//    }
//
//    public String getTypeEquipment() {
//        return typeEquipment;
//    }
//
//    public void setTypeEquipment(String typeEquipment) {
//        this.typeEquipment = typeEquipment;
//    }
//
//    public String getColorEquipment(){
//        return colorEquipment;
//    }
//
//    public void setColorEquipment (String colorEquipment) {
//        this.colorEquipment = colorEquipment;
//    }
//
//    public int getLengthEquipment() {
//        return lengthEquipment;
//    }
//
//    public void setLengthEquipment (int lengthEquipment) {
//        this.lengthEquipment = lengthEquipment;
//    }
//
//    public int getWidthEquipment() {
//        return widthEquipment;
//    }
//
//    public void setWidthEquipment (int widthEquipment) {
//        this.widthEquipment = widthEquipment;
//    }
//
//    public int getHeightEquipment() {
//        return heightEquipment;
//    }
//
//    public void setHeightEquipment (int heightEquipment){
//        this.heightEquipment = heightEquipment;
//    }
//
//    public int getCapacityEquipment() {
//        return capacityEquipment;
//    }
//
//    public void setCapacityEquipment (int capacityEquipment) {
//        this.capacityEquipment = capacityEquipment;
//    }
//
//    public void setImageURL (String imageURL){
//        this.imageURL = imageURL;
//    }
//
//    public String getImageURL(){
//        return imageURL;
//    }
//
//
//    public String toString() {
//        return this.getClass().getSimpleName();
//    }
//}

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EquipmentClass {

    // declare instance variable of Equipment
    private String brandEquipment;
    private String modelEquipment;
    private String typeEquipment;
    private String colorEquipment;
    private int lengthEquipment;
    private int widthEquipment;
    private int heightEquipment;
    private int capacityEquipment;
    private String user;
    private String imageURL;
    private JFXButton view;
    private JFXButton edit;
    private JFXButton delete;


    // default constructor
    public EquipmentClass ()
    {
        brandEquipment = null;
        modelEquipment = null;
        typeEquipment = null;
        colorEquipment = null;
        lengthEquipment = 0;
        widthEquipment = 0;
        heightEquipment = 0;
        capacityEquipment = 0;
        user = null;
        imageURL = null;
        view=null;
        edit=null;
        delete=null;
    }

    // constructor typeEquipment
    public EquipmentClass(String typeEquipment) {
        this.brandEquipment = null;
        this.modelEquipment = null;
        this.typeEquipment = typeEquipment;
        this.colorEquipment = null;
        this.lengthEquipment = 0;
        this.widthEquipment = 0;
        this.heightEquipment = 0;
        this.capacityEquipment = 0;
        this.imageURL = null;
    }

    // constructor brandEquipment, modelEquipment, typeEquipment, colorEquipment, lengthEquipment, widthEquipment, HeightEquipment, capacityEquipment, user
    public EquipmentClass(String brandEquipment, String modelEquipment,String typeEquipment, String colorEquipment, int lengthEquipment, int widthEquipment, int heightEquipment, int capacityEquipment,String imageURL) {
        this.brandEquipment = brandEquipment;
        this.modelEquipment = modelEquipment;
        this.typeEquipment = typeEquipment;
        this.colorEquipment = colorEquipment;
        this.lengthEquipment = lengthEquipment;
        this.widthEquipment = widthEquipment;
        this.heightEquipment = heightEquipment;
        this.capacityEquipment = capacityEquipment;

        this.imageURL = imageURL;
        this.view = new JFXButton();
        ImageView gallery = new ImageView(new Image("gallery.png"));
        view.setGraphic(gallery);
        gallery.setFitHeight(20);
        gallery.setFitWidth(20);
        view.setPrefSize(28.75,20);
        view.setStyle("-fx-background-color:#2196f3");

        this.edit = new JFXButton();
        ImageView edit1 = new ImageView(new Image("edit.png"));
        edit.setGraphic(edit1);
        edit1.setFitHeight(20);
        edit1.setFitWidth(20);
        edit.setPrefSize(28.75,20);
        edit.setStyle("-fx-background-color:#2196f3");

        this.delete = new JFXButton();
        ImageView delete1 = new ImageView(new Image("delete.png"));
        delete.setGraphic(delete1);
        delete1.setFitHeight(20);
        delete1.setFitWidth(20);
        delete.setPrefSize(28.75,20);
        delete.setStyle("-fx-background-color:#2196f3");

    }


    // getter & setter methods
    public String getBrandEquipment() {
        return brandEquipment;
    }

    public void setBrandEquipment(String brandEquipment) {
        this.brandEquipment = brandEquipment;
    }

    public String getModelEquipment() {
        return modelEquipment;
    }

    public void setModelEquipment(String modelEquipment) {
        this.modelEquipment = modelEquipment;
    }

    public String getTypeEquipment() {
        return typeEquipment;
    }

    public void setTypeEquipment(String typeEquipment) {
        this.typeEquipment = typeEquipment;
    }

    public String getColorEquipment(){
        return colorEquipment;
    }

    public void setColorEquipment (String colorEquipment) {
        this.colorEquipment = colorEquipment;
    }

    public int getLengthEquipment() {
        return lengthEquipment;
    }

    public void setLengthEquipment (int lengthEquipment) {
        this.lengthEquipment = lengthEquipment;
    }

    public int getWidthEquipment() {
        return widthEquipment;
    }

    public void setWidthEquipment (int widthEquipment) {
        this.widthEquipment = widthEquipment;
    }

    public int getHeightEquipment() {
        return heightEquipment;
    }

    public void setHeightEquipment (int heightEquipment){
        this.heightEquipment = heightEquipment;
    }

    public int getCapacityEquipment() {
        return capacityEquipment;
    }

    public void setCapacityEquipment (int capacityEquipment) {
        this.capacityEquipment = capacityEquipment;
    }

    public void setImageURL (String imageURL){
        this.imageURL = imageURL;
    }

    public String getImageURL(){
        return imageURL;
    }

    public void setView (JFXButton view){
        this.view = view;
    }

    public JFXButton getView(){
        return view;
    }

    public void setEdit (JFXButton edit){
        this.edit = edit;
    }

    public JFXButton getEdit(){
        return edit;
    }

    public void setDelete (JFXButton delete){
        this.delete = delete;
    }

    public JFXButton getDelete(){
        return delete;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}

