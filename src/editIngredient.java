import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class editIngredient {
    List<File> images;
    String urls;
    Scene scene;

    ObservableList<String> types = FXCollections.observableArrayList("Meat","Seafood","Vegetable","Fruit","Beans","Carbohydrate","Dairy","Preserved Food","Condiments","Herbs & Spices","Baking Ingredient");

    public Stage editForm(int index,String typessss, String namessss, String brands , String countryss, String measureTypess, String measuress, String quantitysss, String cur, String expss, String urlss) {

        Stage primaryStage = new Stage();

        StackPane basePane = new StackPane();
        StackPane stackPane = new StackPane();

        BackgroundImage bg = new BackgroundImage(new Image("bggg.jpg", 620, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        basePane.setBackground(new Background(bg));

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



        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        Label brand = new Label("Brand");
        brand.setTextFill(Color.WHITE);
        brand.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        brand.setFont(new Font("Roboto", 15));
        TextField brand1 = new TextField();
        brand1.setMaxWidth(149);
        brand1.setText(brands);

        Label name = new Label("Name");
        TextField name1 = new TextField();
        name.setTextFill(Color.WHITE);
        name.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        name.setFont(new Font("Roboto", 15));
        name1.setText(namessss);

        Label type = new Label("Type");
        ComboBox type1 = new ComboBox();
        type1.setItems(types);
        type1.setPrefWidth(149);
        type.setTextFill(Color.WHITE);
        type.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        type.setFont(new Font("Roboto", 15));
        type1.setValue(typessss);

        Label country = new Label("Country");

        country.setTextFill(Color.WHITE);
        country.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        country.setFont(new Font("Roboto", 15));
        country1.setValue(countryss);

        Label measure = new Label("Measure");
        TextField measure1 = new TextField();
        measure.setTextFill(Color.WHITE);
        measure.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        measure1.setMinWidth(89);
        measure1.setMaxWidth(89);
        measure.setFont(new Font("Roboto", 15));
        measure1.setText(measuress);

        ComboBox measureType = new ComboBox();
        measureType.setItems(FXCollections.observableArrayList("g", "ml"));
        measureType.setMinWidth(60);
        measureType.setMaxWidth(60);
        measureType.setValue(measureTypess);


        Label quantity = new Label("Quantity");
        TextField quantity1 = new TextField();
        quantity.setTextFill(Color.WHITE);
        quantity.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        quantity.setFont(new Font("Roboto", 15));
        quantity1.setText(quantitysss);

        Label exp = new Label("Expiry Date");
        DatePicker exp1 = new DatePicker();
        exp1.setMaxWidth(149);
        exp1.setStyle("-fx-background-color:white");
        exp.setTextFill(Color.WHITE);
        exp.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        exp.setFont(new Font("Roboto", 15));
        exp1.setValue(LocalDate.parse(expss));


        ImageView image;
        if(!urlss.equals("null")) {
            image = new ImageView(new Image("file:///" + urlss));
            image.setPreserveRatio(true);
            image.setFitWidth(250);
        }else {
            image = new ImageView(new Image("NA.jpg"));
            image.setPreserveRatio(true);
            image.setFitWidth(200);
        }

        image.setOnDragOver(e -> {
            if (e.getDragboard().hasFiles()) {
                e.acceptTransferModes(TransferMode.ANY);
            }
        });
        image.setOnDragDropped(e -> {
            images = e.getDragboard().getFiles();
            urls = "file:///"+images.get(0).getPath();
            image.setImage(new Image(urls));
            image.setFitWidth(300);

        });

        image.setStyle( "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");



        JFXButton add = new JFXButton("Save Changes");
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
        imageHolder.setMinSize(400,300);
        imageHolder.setMaxSize(400,300);
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




        add.setOnAction(e->{
            Main main = new Main();
            IngredientAddingForm addingForm = new IngredientAddingForm();
            String urlsss;
            if(!(brand1.getText().isEmpty()||name1.getText().isEmpty()||country1.getValue().isEmpty()||measure1.getText().isEmpty()||quantity1.getText().isEmpty()||exp1.getValue()==null)) {
                if (addingForm.numValidation(measure1.getText(),quantity1.getText())) {
                    if(!(exp1.getValue().equals(LocalDate.now())||exp1.getValue().isBefore(LocalDate.now()))) {
                        if (!(images == null)) {
                            File inputFile = new File(urlss);
                            boolean a = inputFile.delete();


                            urlsss = main.userPaths + "/assets/" + images.get(0).getName();

                            int i = 1;
                            while (true) {
                                try {
                                    Files.copy(images.get(0).toPath(), new File(urlsss).toPath());
                                    break;
                                } catch (IOException ie) {
                                    urlsss = main.userPaths + "/assets/" + i + images.get(0).getName();
                                    i++;
                                }
                            }

                        } else {
                            urlsss = urlss;
                        }

                        String newType = type1.getValue().toString();
                        String newName = name1.getText();
                        String newBrand = brand1.getText();
                        String newCountry = country1.getValue();
                        String newMeasureType = measureType.getValue().toString();
                        String newMeasure = measure1.getText();
                        String newQuantity = quantity1.getText();
                        String newExp = exp1.getValue().toString();
                        String newCur = cur;

                        Ingredient ingredient = new Ingredient();
                        ingredient.editIngredient(index,newType,newName,newBrand,newCountry,newMeasureType,newMeasure,newQuantity,newCur,newExp,urlsss);
                        main.alertDialog("Ingredient Updated Successfully", "Success", basePane, stackPane, "Ok", scene);
                    }else {
                        main.alertDialog("Expiry Date has been reached, please discard this ingredient", "Food Expired!", basePane, stackPane, scene);

                    }
                } else {
                    main.alertDialog("Please enter a valid number for measure and quantity", "Input Error", basePane, stackPane, scene);

                }
            } else {
                main.alertDialog("Please do not leave any blanks !", "Input Error", basePane, stackPane, scene);

            }
//
        });

        basePane.getChildren().add(gridPane);
        scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Ingredient");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        return primaryStage;









    }
}
