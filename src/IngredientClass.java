import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IngredientClass {



    private String ingredientType;
    private String ingredientName;
    private String ingredientBrand;
    private String ingredientCountry;
    private String ingredientKeepPlace;
    private double ingredientMeasure;
    private String ingredientMeasureType;
    private int ingredientQuantity;
    private String curDate;
    private String expDate;
    private String imageURL;
    private JFXButton view;
    private JFXButton edit;
    private JFXButton delete;

    public IngredientClass() {

        ingredientType = "";
        ingredientName = "";
        ingredientBrand = "";
        ingredientCountry = "";
        ingredientKeepPlace = "";
        ingredientMeasure = 0.00;
        ingredientQuantity=0;
        ingredientMeasureType="";
        imageURL = "";
        curDate = "";
        expDate = "";

    }
    public IngredientClass(String ingredientType,String ingredientName, String ingredientBrand, String ingredientCountry, String ingredientMeasureType,  double ingredientMeasure,int ingredientQuantity,String curDate, String expDate,String imageURL){


        this.curDate = curDate;
        this.expDate = expDate;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.ingredientBrand =ingredientBrand;
        this.ingredientCountry = ingredientCountry;
        ingredientKeepPlace = putIngredientKeepPlace(ingredientType);
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientMeasureType = ingredientMeasureType;
        this.ingredientQuantity=ingredientQuantity;
        this.imageURL = imageURL;

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


    //getter and setter


public String getIngredientMeasureType(){
        return ingredientMeasureType;
}

    public void setCurDate(String curDate){
        this.curDate=curDate; }

    public String getCurDate(){
        return curDate; }

    public void setExpDate(String expDate){
        this.expDate=expDate; }

    public String getExpDate(){
        return expDate; }

    public void setIngredientName(String ingredientName){
        this.ingredientName=ingredientName;}

    public String getIngredientName(){
        return ingredientName; }

    public void setIngredientType(String ingredientType){
        this.ingredientType=ingredientType; }

    public String getIngredientType(){
        return ingredientType; }

    public void setIngredientBrand(String ingredientBrand){
        this.ingredientBrand=ingredientBrand; }

    public String getIngredientBrand(){
        return ingredientBrand; }

    public void setIngredientCountry(String ingredientCountry){
        this.ingredientCountry=ingredientCountry; }

    public String getIngredientCountry(){
        return ingredientCountry; }

    public void setIngredientKeepPlace(String ingredientKeepPlace){
        this.ingredientKeepPlace=ingredientKeepPlace;}

    public String getIngredientKeepPlace(){
        return ingredientKeepPlace;}

    public void setIngredientMeasurement(double ingredientMeasure)  {
        this.ingredientMeasure=ingredientMeasure; }

    public double getIngredientMeasure(){
        return ingredientMeasure; }

    public void setIngredientQuantity(int ingredientQuantity){
        this.ingredientQuantity=ingredientQuantity; }

    public int getIngredientQuantity(){
        return ingredientQuantity; }

        public String getImageURL(){
        return imageURL;
        }

    public String putIngredientKeepPlace(String ingredientType){
        String ingredientKeepPlace = "";
        if (ingredientType.equals("Meat")){
            ingredientKeepPlace = "Frozen with -18°c";
        }else if(ingredientType.equals("Seafood")){
            ingredientKeepPlace = "Frozen with -18°c";
        }else if(ingredientType.equals("Vegetable")){
            ingredientKeepPlace = "Refridge with 15°c";
        }else if(ingredientType.equals("Fruit")){
            ingredientKeepPlace = "Refridge with 15°c";
        }else if(ingredientType.equals("Beans")){
            ingredientKeepPlace = "Cool temp:22°c";
        }else if(ingredientType.equals("Carbohydrate")){
            ingredientKeepPlace = "Store in dry and cool place";
        }else if(ingredientType.equals("Preserved Food")){
            ingredientKeepPlace = "Refridge with 15°c";
        }else if(ingredientType.equals("Condiments")){
            ingredientKeepPlace = "Store in dry and cool place";
        }else if(ingredientType.equals("Herbs and spices")){
            ingredientKeepPlace = "Store in dry and cool place";
        }else if(ingredientType.equals("Baking Ingredient")){
            ingredientKeepPlace = "Store in dry and cool place";
        }else if(ingredientType.equals("Dairy")){
            ingredientKeepPlace = "Refridge with 15°c";
        }
        return ingredientKeepPlace;

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

    public String toString(){
        return "Ingredient Type :"+ingredientType+"\nIngredient Name :"+ingredientName+"\nIngredient Brand :"+ingredientBrand+"\nCountry :"+ingredientCountry+"\nKeep Place :"+ingredientKeepPlace+"Store Date :"+curDate+"* Best use before Date *:"+expDate+"\nIngredient Measurement :"+ingredientMeasure+"\nIngredient Quantity :"+ingredientQuantity;
    }
}



