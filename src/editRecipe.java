import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class editRecipe {
    String url,urls;
    List<File> images = null;



    ObservableList<String> equipments = FXCollections.observableArrayList("Skillet","Blender","Deep Fryer","Food Processor","Griller","Mixer","Oven","Roast Pan","Sauce Pot","Bakeware");
    ArrayList<String> ingredients = new ArrayList<>();
    ObservableList<String> recipeTypes = FXCollections.observableArrayList("Western","Chinese","Malaysian","Middle Eastern","Pastry","Cake","Dessert","Snack","Healthy");
    String[] recipeStep;
    String[] ingredientLists;
    String[] equipmentLists;
    static int i = 1;
    static int j = 1;
    static int k = 1;


    StackPane basePane,basePane2,stackPane,basePane1,stackPane1;
    Scene scene,scene2;
    public Stage editForm(int index,String recipeNamess, String recipeTypess, String[] ingredientListss, String[] equipmentListss,String[] recipeInstructions,String urlss){
        Stage primaryStage = new Stage();
        getIngredients();
        basePane = new StackPane();
        stackPane = new StackPane();

        BackgroundImage bg = new BackgroundImage(new Image("bggg.jpg", 650, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        basePane.setBackground(new Background(bg));



        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        Label recipeName = new Label("Recipe Name");
        TextField recipeName1 = new TextField();
        recipeName1.setMaxWidth(149);
        recipeName1.setStyle("-fx-background-color:white");
        recipeName1.setText(recipeNamess);
        recipeName.setTextFill(Color.WHITE);
        recipeName.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        recipeName.setFont(new Font("Roboto", 15));

        Label recipeType = new Label("Recipe Type      ");
        ComboBox recipeType1 = new ComboBox();
        recipeType1.setItems(FXCollections.observableArrayList(recipeTypes));
        recipeType1.setPrefWidth(149);
        recipeType1.setValue(recipeTypess);
        recipeType.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        recipeType.setTextFill(Color.WHITE);
        recipeType.setFont(new Font("Roboto", 15));

        ComboBox ingredients1 = new ComboBox();
        ingredients1.setItems(FXCollections.observableArrayList(ingredients));
        ingredients1.setPrefWidth(149);
        ingredients1.setValue("Select Ingredients");
        ingredients1.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        Label measure = new Label("Measure");
        TextField measure1 = new TextField();
        measure.setTextFill(Color.WHITE);
        measure.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        measure1.setMinWidth(89);
        measure1.setMaxWidth(89);
        measure.setFont(new Font("Roboto", 15));

        ComboBox measureType = new ComboBox();
        measureType.setItems(FXCollections.observableArrayList("g", "ml","qty"));
        measureType.setMinWidth(60);
        measureType.setMaxWidth(60);
        measureType.setValue("g");
        HBox hBox = new HBox(0);
        hBox.getChildren().addAll(measure1, measureType);
        hBox.setAlignment(Pos.CENTER_LEFT);

        TextArea ingredientList = new TextArea();
        ingredientList.setPrefSize(30,100);
        for(int a = 0; a < ingredientListss.length;a++){
            ingredientList.appendText(ingredientListss[a]+"\n");
            j++;
        }

        ComboBox equipment1 = new ComboBox();
        equipment1.setItems(FXCollections.observableArrayList(equipments));
        equipment1.setPrefWidth(149);
        equipment1.setValue("Select Equipment");
        equipment1.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        TextArea equipmentList = new TextArea();
        equipmentList.setPrefSize(30,50);
        for(int a = 0; a < equipmentListss.length;a++){
            equipmentList.appendText(equipmentListss[a]+"\n");
            i++;
        }


        JFXButton addIngredient = new JFXButton("Add Ingredient");
        addIngredient.setButtonType(JFXButton.ButtonType.RAISED);
        addIngredient.setPrefSize(100, 30);
        addIngredient.setTextFill(Color.WHITE);
        addIngredient.setStyle("-fx-background-color: #2196F3\n");
        addIngredient.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        addIngredient.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);

        });

        JFXButton addEquipment = new JFXButton("Add Equipment");
        addEquipment.setButtonType(JFXButton.ButtonType.RAISED);
        addEquipment.setPrefSize(100, 30);
        addEquipment.setTextFill(Color.WHITE);
        addEquipment.setStyle("-fx-background-color: #2196F3\n");
        addEquipment.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        addEquipment.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });


        JFXButton deleteEquipment = new JFXButton("Delete Equipment");
        deleteEquipment.setButtonType(JFXButton.ButtonType.RAISED);
        deleteEquipment.setPrefSize(100, 30);
        deleteEquipment.setTextFill(Color.WHITE);
        deleteEquipment.setStyle("-fx-background-color: #2196F3\n");
        deleteEquipment.setOnAction(e->{
            equipmentLists = equipmentList.getText().split("\n");
            equipmentList.clear();
            for(int g = 0; g < equipmentLists.length-1;g++){
                equipmentList.appendText(equipmentLists[g]+"\n");
            }
            if(i != 1) {
                i--;
            }
        });
        deleteEquipment.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        deleteEquipment.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });

        JFXButton deleteIngredient = new JFXButton("Delete Ingredient");
        deleteIngredient.setButtonType(JFXButton.ButtonType.RAISED);
        deleteIngredient.setPrefSize(100, 30);
        deleteIngredient.setTextFill(Color.WHITE);
        deleteIngredient.setStyle("-fx-background-color: #2196F3\n");
        deleteIngredient.setOnAction(e->{
            ingredientLists = ingredientList.getText().split("\n");
            ingredientList.clear();
            for(int g = 0; g <ingredientLists.length-1;g++){
                ingredientList.appendText(ingredientLists[g]+"\n");
            }
            if(j != 1) {
                j--;
            }
        });
        deleteIngredient.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        deleteIngredient.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });




        JFXButton next = new JFXButton("Next");
        next.setButtonType(JFXButton.ButtonType.RAISED);
        next.setPrefSize(100, 10);
        next.setTextFill(Color.WHITE);
        next.setStyle("-fx-background-color: #2196F3\n");
        next.setOnAction(e->{
            if (ingredientList.getText().isEmpty() || equipmentList.getText().isEmpty() || recipeName1.getText().isEmpty()){
                Main main = new Main();
                main.alertDialog("Please do not leave any blanks !","Error",basePane2,basePane,scene);
            }else {
                primaryStage.setScene(scene2);

            }
        });
        next.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });


        gridPane.add(recipeName,0,0);
        gridPane.add(recipeName1,1,0);
        gridPane.add(recipeType,2,0);
        gridPane.add(recipeType1,3,0);
        gridPane.add(ingredients1,0,1);
        gridPane.add(hBox,1,1);
        gridPane.add(equipment1,2,1);
        gridPane.add(ingredientList,0,2,2,1);
        gridPane.add(equipmentList,2,2,2,1);
        gridPane.add(addIngredient,1,3);
        gridPane.setHalignment(addIngredient, HPos.RIGHT);
        gridPane.add(addEquipment,3,3);
        gridPane.add(deleteIngredient,0,3);
        gridPane.setHalignment(deleteIngredient,HPos.LEFT);
        gridPane.setHalignment(addEquipment, HPos.RIGHT);
        gridPane.add(deleteEquipment,2,3);
        gridPane.setHalignment(deleteEquipment,HPos.LEFT);
        gridPane.add(next,3 ,4);
        gridPane.setHalignment(next,HPos.RIGHT);



        addEquipment.setOnAction(e->{
            if(!equipment1.getValue().equals("Select Equipment")) {
                if (!equipmentList.getText().contains(equipment1.getValue().toString())) {
                    equipmentList.appendText(i + ") " + equipment1.getValue().toString() + "\n");
                    i++;
                }
            }
        });
        equipmentList.setEditable(false);
        AddingForm addingForm = new AddingForm();
        addIngredient.setOnAction(e->{
            if(!ingredients1.getValue().equals("Select Ingredients")) {
                if(addingForm.numValidation(measure1.getText(),"2","33","4")) {
                    if (measureType.getValue().equals("qty")) {
                        ingredientList.appendText(j + ") " + measure1.getText() + " " + ingredients1.getValue().toString() + "\n");
                    } else {
                        ingredientList.appendText(j + ") " + measure1.getText() + measureType.getValue().toString() + " of " + ingredients1.getValue().toString() + "\n");
                    }
                    j++;
                }else {
                    Main main = new Main();
                    main.alertDialog("Please enter a valid number for measurement!","Error",basePane2,basePane,scene);
                }
            }
        });
        ingredientList.setEditable(false);













        basePane.getChildren().add(gridPane);
         basePane2 = new StackPane();
        basePane2.getChildren().add(basePane);


        basePane1 = new StackPane();
        stackPane1 = new StackPane();
        basePane1.setBackground(new Background(bg));


        GridPane gridPane1 = new GridPane();
        gridPane1.setVgap(10);
        gridPane1.setHgap(10);
        gridPane1.setPadding(new Insets(10));

        Label recipe = new Label("Recipe Instruction:");
        recipe.setTextFill(Color.WHITE);
        recipe.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        recipe.setFont(new Font("Roboto", 15));
        TextField recipeNext = new TextField();
        recipeNext.setPromptText("Type your step here one by one");
        recipeNext.setFocusTraversable(false);
        TextArea recipeNext1 = new TextArea();
        recipeNext1.setPrefSize(260,150);
        recipeNext1.setWrapText(true);
        for(int a = 0; a < recipeInstructions.length;a++){
            recipeNext1.appendText(recipeInstructions[a]+"\n\n");
            k++;
        }

        ImageView image;
        if(!urlss.equals("null")) {
            image = new ImageView(new Image("file:///" + urlss));
        }else {
            image = new ImageView(new Image("NA.jpg"));
        }
        image.setPreserveRatio(true);
        image.setFitWidth(300);
        StackPane imageHolder = new StackPane();
        imageHolder.setPadding(new Insets(0));
        imageHolder.setMinSize(350, 250);
        imageHolder.setMaxSize(350, 250);
        imageHolder.getChildren().add(image);


        image.setOnDragOver(e -> {
            if (e.getDragboard().hasFiles()) {
                e.acceptTransferModes(TransferMode.ANY);
            }
        });
        image.setOnDragDropped(e -> {
            images = e.getDragboard().getFiles();
            urls = "file:///" + images.get(0).getPath();
            image.setImage(new Image(urls));
            image.setFitWidth(300);


        });

        JFXButton next1 = new JFXButton("Save Recipe");
        next1.setButtonType(JFXButton.ButtonType.RAISED);
        next1.setPrefSize(100, 10);
        next1.setTextFill(Color.WHITE);
        next1.setStyle("-fx-background-color: #2196F3\n");
        next1.setOnAction(e->{
            if (recipeNext1.getText().isEmpty()){
                Main main = new Main();
                main.alertDialog("Please do not leave the recipe blank !","Error",basePane1,stackPane1,scene2);
            }else {
                recipeStep = recipeNext1.getText().split("\n\n");
                ingredientLists = ingredientList.getText().split("\n");
                equipmentLists = equipmentList.getText().split("\n");
                Main main = new Main();
                if (!(images == null)) {
                    File inputFile = new File(urlss);
                    boolean a = inputFile.delete();


                    url = main.userPaths + "/assets/" + images.get(0).getName();

                    int i = 1;
                    while (true) {
                        try {
                            Files.copy(images.get(0).toPath(), new File(url).toPath());
                            break;
                        } catch (IOException ie) {
                            url = main.userPaths + "/assets/" + i + images.get(0).getName();
                            i++;
                        }
                    }

                } else {
                    url = urlss;
                }


                String ingre, equi, step, newRecipeName, newRecipeType;
                ingre = "";
                equi = "";
                step = "";

                for (int a = 0; a < ingredientLists.length; a++) {
                    if (a != ingredientLists.length - 1) {
                        ingre += ingredientLists[a] + "#";
                    } else {
                        ingre += ingredientLists[a];
                    }
                }

                for (int a = 0; a < equipmentLists.length; a++) {
                    if (a != equipmentLists.length - 1) {
                        equi += equipmentLists[a] + "#";
                    } else {
                        equi += equipmentLists[a];
                    }

                }

                for (int a = 0; a < recipeStep.length; a++) {
                    if (a != recipeStep.length - 1) {
                        step += recipeStep[a] + "#";
                    } else {
                        step += recipeStep[a];
                    }

                }

                newRecipeName = recipeName1.getText();
                newRecipeType = recipeType1.getValue().toString();

                Recipe recipe1 = new Recipe();
                recipe1.editRecipe(index, newRecipeName, newRecipeType, ingre, equi, step, url);

                main.alertDialog("Recipe Updated Succesfully !","Success",basePane1,stackPane1,"OK",scene2);


            }





        });
        next1.setOnMouseEntered(event -> {
            scene2.setCursor(Cursor.HAND);

        });
        next1.setOnMouseExited(event -> {
            scene2.setCursor(Cursor.DEFAULT);
        });

        JFXButton back = new JFXButton("Return");
        back.setButtonType(JFXButton.ButtonType.RAISED);
        back.setPrefSize(100, 10);
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-background-color: #2196F3\n");
        back.setTranslateX(23);
        back.setOnAction(e->{
            primaryStage.setScene(scene);
        });
        back.setOnMouseEntered(event -> {
            scene2.setCursor(Cursor.HAND);

        });
        back.setOnMouseExited(event -> {
            scene2.setCursor(Cursor.DEFAULT);
        });

        recipeNext.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                if(!recipeNext.getText().isEmpty()){
                    recipeNext1.appendText(k + ") "+recipeNext.getText()+"\n\n");
                    k++;
                }
                recipeNext.clear();
            }
        });

        JFXButton addRecipe = new JFXButton("Add Step");
        addRecipe.setButtonType(JFXButton.ButtonType.RAISED);
        addRecipe.setPrefSize(100, 30);
        addRecipe.setTextFill(Color.WHITE);
        addRecipe.setStyle("-fx-background-color: #2196F3\n");
        addRecipe.setTranslateX(23);
        addRecipe.setOnAction(e->{
            if(!recipeNext.getText().isEmpty()){
                recipeNext1.appendText(k + ") "+recipeNext.getText()+"\n\n");
                k++;
            }
            recipeNext.clear();
        });
        addRecipe.setOnMouseEntered(event -> {
            scene2.setCursor(Cursor.HAND);

        });
        addRecipe.setOnMouseExited(event -> {
            scene2.setCursor(Cursor.DEFAULT);
        });

        JFXButton deleteRecipe = new JFXButton("Delete Step");
        deleteRecipe.setButtonType(JFXButton.ButtonType.RAISED);
        deleteRecipe.setPrefSize(100, 30);
        deleteRecipe.setTextFill(Color.WHITE);
        deleteRecipe.setStyle("-fx-background-color: #2196F3\n");
        deleteRecipe.setTranslateX(23);
        deleteRecipe.setOnAction(e->{
            recipeStep = recipeNext1.getText().split("\n");
            recipeNext1.clear();
            for(int g = 0; g <recipeStep.length-1;g++){
                recipeNext1.appendText(recipeStep[g]+"\n");
            }
            if(k != 1) {
                k--;
            }

        });
        deleteRecipe.setOnMouseEntered(event -> {
            scene2.setCursor(Cursor.HAND);

        });
        addRecipe.setOnMouseExited(event -> {
            scene2.setCursor(Cursor.DEFAULT);
        });

        HBox hBox1 = new HBox(60);
        hBox1.getChildren().addAll(deleteRecipe,addRecipe);

        hBox1.setMargin(deleteRecipe,new Insets(0,0,0,-23));


        gridPane1.add(recipe,1,0);
        gridPane1.add(recipeNext,1,1);
        gridPane1.add(recipeNext1,1,2);
        gridPane1.add(imageHolder,0,1,1,2);
        gridPane1.add(hBox1,1,3);
        gridPane1.add(next1,1,4);
        gridPane1.add(back,0,4);
        gridPane1.setHalignment(back,HPos.LEFT);
        gridPane1.setHalignment(next1,HPos.RIGHT);
        gridPane1.setValignment(recipeNext1, VPos.BOTTOM);
        gridPane1.setMargin(imageHolder,new Insets(40,0,0,0));
        gridPane1.setHalignment(hBox1,HPos.LEFT);
        gridPane1.setMargin(next1,new Insets(0,0,15,0));
        gridPane1.setMargin(back,new Insets(0,0,15,0));
        gridPane1.setMargin(recipe,new Insets(10,0,0,0));

//

        stackPane1.getChildren().add(gridPane1);
        basePane1.getChildren().add(stackPane1);


        scene2 = new Scene(basePane1,646,400);



        scene = new Scene(basePane2);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Recipe");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();


        return primaryStage;
    }

    public void getIngredients(){
        Main main = new Main();
        File file = new File(main.userPaths+"/Ingredient.txt");
        String[] data = new String[10];
        int i =0;
        if(!file.exists()){
            try {
                boolean a = file.createNewFile();
            }catch (IOException ie){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }
        }else{
            try{
                ingredients.clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    data[i] = scanner.nextLine();
                    i++;
                    if(i==10){
                        ingredients.add(data[0]+" "+data[1]);
                        i = 0;
                    }

                }
                scanner.close();

            }catch (IOException ie){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }


        }
    }

}

