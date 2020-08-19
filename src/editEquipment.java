import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
import java.util.List;

public class editEquipment {
    List<File> images;
    String urls;
    Scene scene;

    ObservableList<String> types = FXCollections.observableArrayList("Skillet","Blender","Deep Fryer","Food Processor","Griller","Mixer","Oven","Roast Pan","Sauce Pot","Bakeware");

    public Stage editForm(int index,String brands, String models, String typess, String colors, String lengths, String widths, String heights, String capacitys, String urlss) {

        Stage primaryStage = new Stage();

        StackPane basePane = new StackPane();
        StackPane stackPane = new StackPane();

        BackgroundImage bg = new BackgroundImage(new Image("bggg.jpg", 620, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        basePane.setBackground(new Background(bg));




        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10));

        Label brand = new Label("Brand");
        brand.setTextFill(Color.WHITE);
        brand.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        brand.setFont(new Font("Roboto", 15));
        TextField brand1 = new TextField();
        brand1.setText(brands);


        Label model = new Label("Model");
        TextField model1 = new TextField();
        model.setTextFill(Color.WHITE);
        model.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        model.setFont(new Font("Roboto", 15));
        model1.setText(models);


        Label type = new Label("Type");
        ComboBox type1 = new ComboBox();
        type1.setItems(types);
        type1.setPrefWidth(149);
        type1.setValue("Skillet");
        type.setTextFill(Color.WHITE);
        type.setTextFill(Color.WHITE);
        type.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        type.setFont(new Font("Roboto", 15));
        type1.setValue(typess);


        Label color = new Label("Color");
        TextField color1 = new TextField();
        color.setTextFill(Color.WHITE);
        color.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        color.setFont(new Font("Roboto", 15));
        color1.setText(colors);


        Label length = new Label("Length (mm)");
        TextField length1 = new TextField();
        length.setTextFill(Color.WHITE);
        length.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        length.setFont(new Font("Roboto", 15));
        length1.setText(lengths);


        Label width = new Label("Width (mm)");
        TextField width1 = new TextField();
        width.setTextFill(Color.WHITE);
        width.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        width.setFont(new Font("Roboto", 15));
        width1.setText(widths);


        Label height = new Label("Height (mm)");
        TextField height1 = new TextField();
        height.setTextFill(Color.WHITE);
        height.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        height.setFont(new Font("Roboto", 15));
        height1.setText(heights);


        Label capacity = new Label("Capacity (mm³)");
        TextField capacity1 = new TextField();
        capacity.setTextFill(Color.WHITE);
        capacity.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        capacity.setFont(new Font("Roboto", 15));
        capacity1.setText(capacitys);

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


        gridPane.add(brand, 0, 0);
        gridPane.add(brand1, 1, 0);
        gridPane.add(model, 2, 0);
        gridPane.add(model1, 3, 0);
        gridPane.add(type, 0, 1);
        gridPane.add(type1, 1, 1);
        gridPane.add(color, 2, 1);
        gridPane.add(color1, 3, 1);
        gridPane.add(length, 0, 2);
        gridPane.add(length1, 1, 2);
        gridPane.add(width, 2, 2);
        gridPane.add(width1, 3, 2);
        gridPane.add(height, 0, 3);
        gridPane.add(height1, 1, 3);
        gridPane.add(capacity, 2, 3);
        gridPane.add(capacity1, 3, 3);
        gridPane.add(imageHolder, 0, 4, 4, 1);
        gridPane.add(add, 3, 5);

        gridPane.setHalignment(imageHolder,HPos.CENTER);

        gridPane.setHalignment(add, HPos.RIGHT);
        gridPane.setMargin(add, new Insets(0, 3, 0, 0));



        add.setOnAction(e->{
            Main main = new Main();
            AddingForm addingForm = new AddingForm();
            String urlsss;
            if(!(brand1.getText().isEmpty()||model1.getText().isEmpty()||color1.getText().isEmpty()||width1.getText().isEmpty()||length1.getText().isEmpty()||height1.getText().isEmpty()||capacity1.getText().isEmpty())) {

                if (addingForm.numValidation(length1.getText(), width1.getText(), height1.getText(), capacity1.getText())) {
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

                    String brandsss = brand1.getText();
                    String modelsss = model1.getText();
                    String typesss = type1.getValue().toString();
                    String colorsss = color1.getText();
                    String lengthsss = Integer.toString(Integer.parseInt(length1.getText()));
                    String widthsss = Integer.toString(Integer.parseInt(width1.getText()));
                    String heightsss = Integer.toString(Integer.parseInt(height1.getText()));
                    String capacitysss = Integer.toString(Integer.parseInt(capacity1.getText()));


                    Equipment equipment = new Equipment();
                    equipment.editEquipment(equipment.equipments, index, brandsss, modelsss, typesss, colorsss, lengthsss, widthsss, heightsss, capacitysss, urlsss);
                    main.alertDialog("Equipment Updated Successfully", "Success", basePane, stackPane, "Ok", scene);

                } else {
                    main.alertDialog("Please enter a valid number for length, width, height and capacity(No decimals and negative)", "Input Error", basePane, stackPane, scene);

                }
            } else {
                    main.alertDialog("Please do not leave any blanks !", "Input Error", basePane, stackPane, scene);

                }
//
        });

        basePane.getChildren().add(gridPane);
        scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Equipment");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        return primaryStage;









    }
}
