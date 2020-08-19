import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class IngredientAddingForm  {
    String url,urls;
    List<File> images = null;

    ObservableList<String> types = FXCollections.observableArrayList("Meat","Seafood","Vegetable","Fruit","Beans","Carbohydrate","Dairy","Preserved Food","Condiments","Herbs & Spices","Baking Ingredient");
    StackPane basePane,stackPane;
    Scene scene;


    public Stage addingForm() {
        Stage primaryStage = new Stage();


        basePane = new StackPane();
        stackPane = new StackPane();

        BackgroundImage bg = new BackgroundImage(new Image("bggg.jpg", 620, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        basePane.setBackground(new Background(bg));

        stackPane.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        ObservableList<String> cities = FXCollections.observableArrayList();
        ComboBox<String> country1 = new ComboBox<String>(cities);

        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = { obj.getDisplayCountry() };
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        country1.setItems(cities);
        country1.setMaxWidth(149);




        Label brand = new Label("Brand");
        brand.setTextFill(Color.WHITE);
        brand.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        brand.setFont(new Font("Roboto", 15));
        TextField brand1 = new TextField();
        brand1.setMaxWidth(149);

        Label name = new Label("Name");
        TextField name1 = new TextField();
        name.setTextFill(Color.WHITE);
        name.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        name.setFont(new Font("Roboto", 15));

        Label type = new Label("Type");
        ComboBox type1 = new ComboBox();
        type1.setItems(types);
        type1.setPrefWidth(149);
        type1.setValue("Meat");
        type.setTextFill(Color.WHITE);
        type.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        type.setFont(new Font("Roboto", 15));


        Label country = new Label("Country");
        country.setTextFill(Color.WHITE);
        country.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        country.setFont(new Font("Roboto", 15));

        Label measure = new Label("Measure");
        TextField measure1 = new TextField();
        measure.setTextFill(Color.WHITE);
        measure.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        measure1.setMinWidth(89);
        measure1.setMaxWidth(89);
        measure.setFont(new Font("Roboto", 15));

        ComboBox measureType = new ComboBox();
        measureType.setItems(FXCollections.observableArrayList("g", "ml"));
        measureType.setMinWidth(60);
        measureType.setMaxWidth(60);
        measureType.setValue("g");


        Label quantity = new Label("Quantity");
        TextField quantity1 = new TextField();
        quantity.setTextFill(Color.WHITE);
        quantity.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        quantity.setFont(new Font("Roboto", 15));

        Label exp = new Label("Expiry Date");
        DatePicker exp1 = new DatePicker();
        exp1.setMaxWidth(149);
        exp1.setStyle("-fx-background-color:white");
        exp.setTextFill(Color.WHITE);
        exp.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        exp.setFont(new Font("Roboto", 15));


        ImageView image = new ImageView(new Image("image.png"));
        image.setPreserveRatio(true);
        image.setFitWidth(300);


        JFXButton add = new JFXButton("Add");
        add.setButtonType(JFXButton.ButtonType.RAISED);
        add.setPrefSize(100, 30);
        add.setTextFill(Color.WHITE);
        add.setStyle("-fx-background-color: #2196F3\n");
        add.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        add.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);

        });

        StackPane imageHolder = new StackPane();

        imageHolder.setPadding(new Insets(0));
        imageHolder.setMinSize(400, 300);
        imageHolder.setMaxSize(400, 300);
        imageHolder.getChildren().add(image);

        HBox hBox = new HBox(0);
        hBox.getChildren().addAll(measure1, measureType);
        hBox.setAlignment(Pos.CENTER_LEFT);

        gridPane.add(brand, 0, 0);
        gridPane.add(brand1, 1, 0);
        gridPane.add(name, 2, 0);
        gridPane.add(name1, 3, 0);
        gridPane.add(type, 0, 1);
        gridPane.add(type1, 1, 1);
        gridPane.add(country, 2, 1);
        gridPane.add(country1, 3, 1);
        gridPane.add(measure, 0, 2);
        gridPane.add(hBox, 1, 2);
        gridPane.add(quantity, 2, 2);
        gridPane.add(quantity1, 3, 2);
        gridPane.add(exp, 0, 3);
        gridPane.add(exp1, 1, 3);
        gridPane.add(imageHolder, 0, 4, 4, 1);
        gridPane.add(add, 3, 5);

        gridPane.setHalignment(imageHolder, HPos.CENTER);

        gridPane.setHalignment(add, HPos.RIGHT);
        gridPane.setMargin(add, new Insets(0, 3, 0, 0));


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
        add.setOnAction(e->{


            Main main = new Main();
            if(!(brand1.getText().isEmpty()||name1.getText().isEmpty()||country1.getValue().isEmpty()||measure1.getText().isEmpty()||quantity1.getText().isEmpty()||exp1.getValue()==null)) {
                if (numValidation(measure1.getText(),quantity1.getText())) {
                    if(!(exp1.getValue().equals(LocalDate.now())||exp1.getValue().isBefore(LocalDate.now()))) {
                        if (images == null) {
                            url = null;
                        } else {

                            url = main.userPaths + "/assets/" + images.get(0).getName();
                            int i = 1;
                            while (true) {
                                try {
                                    Files.copy(images.get(0).toPath(), new File(url).toPath());
                                    break;
                                } catch (IOException ie) {
                                    url = main.userPaths + "/" + "/assets/" + i + images.get(0).getName();
                                    i++;
                                }
                            }
                        }

                        File ingredient = new File(main.userPaths + "/Ingredient.txt");
                        try {


                            LocalDate ld = LocalDate.now();
                            FileWriter fileWriter = new FileWriter(ingredient, true);
                            fileWriter.write(type1.getValue().toString() + "\n");
                            fileWriter.write(name1.getText() + "\n");
                            fileWriter.write(brand1.getText() + "\n");
                            fileWriter.write(country1.getValue() + "\n");
                            fileWriter.write(measureType.getValue().toString() + "\n");
                            fileWriter.write(measure1.getText() + "\n");
                            fileWriter.write(quantity1.getText() + "\n");
                            fileWriter.write(ld + "\n");
                            fileWriter.write(exp1.getValue().toString() + "\n");
                            fileWriter.write(url + "\n");

                            fileWriter.close();
                            main.alertDialog("Ingredient Added Successfully", "Success", basePane, stackPane, "Ok", scene);
                            brand1.setText("");
                            name1.setText("");
                            quantity1.setText("");
                            country1.setValue("");
                            measure1.setText("");
                            image.setImage(new Image("image.png"));

                        } catch (IOException ie) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("System Error");
                            alert.setContentText("Error occurred, Aborting !");
                            alert.showAndWait();
                            System.exit(-1);                        }
                    }else{
                        main.alertDialog("Expiry Date has been reached, please discard this ingredient", "Food Expired!", basePane, stackPane, scene);

                    }
                } else {

                    main.alertDialog("Please enter a valid number for measurement and quantity!", "Input Error", basePane, stackPane, scene);
                }
            }else{
                main.alertDialog("Please do not leave any blanks !", "Input Error", basePane, stackPane, scene);

            }

        });

        basePane.getChildren().add(gridPane);
        scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Ingredient");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

       return primaryStage;
    }











    public boolean numValidation(String a, String b){
        boolean status = true;
        try{
            int q =Integer.parseInt(b);
            double r = Double.parseDouble(a);
            if(q<0||r<0){
                status = false;
            }
        }catch (NumberFormatException ie){

            status = false;
        }
        return status;
    }
}

