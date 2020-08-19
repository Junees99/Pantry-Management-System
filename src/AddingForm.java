import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import java.nio.file.Path;
import java.util.List;

public class AddingForm {
    String url,urls;
    List<File> images = null;
    Path root;
    ObservableList<String> types = FXCollections.observableArrayList("Skillet","Blender","Deep Fryer","Food Processor","Griller","Mixer","Oven","Roast Pan","Sauce Pot","Bakeware");
    StackPane basePane,stackPane;
    Scene scene;

    public Stage addForm() {

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



        Label brand = new Label("Brand");
        brand.setTextFill(Color.WHITE);
        brand.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        brand.setFont(new Font("Roboto", 15));
        TextField brand1 = new TextField();

        Label model = new Label("Model");
        TextField model1 = new TextField();
        model.setTextFill(Color.WHITE);
        model.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        model.setFont(new Font("Roboto", 15));

        Label type = new Label("Type");
        ComboBox type1 = new ComboBox();
        type1.setItems(types);
        type1.setPrefWidth(149);
        type1.setValue("Skillet");
        type.setTextFill(Color.WHITE);
        type.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        type.setFont(new Font("Roboto", 15));


        Label color = new Label("Color");
        TextField color1 = new TextField();
        color.setTextFill(Color.WHITE);
        color.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        color.setFont(new Font("Roboto", 15));

        Label length = new Label("Length (mm)");
        TextField length1 = new TextField();
        length.setTextFill(Color.WHITE);
        length.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        length.setFont(new Font("Roboto", 15));

        Label width = new Label("Width (mm)");
        TextField width1 = new TextField();
        width.setTextFill(Color.WHITE);
        width.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        width.setFont(new Font("Roboto", 15));

        Label height = new Label("Height (mm)");
        TextField height1 = new TextField();
        height.setTextFill(Color.WHITE);
        height.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        height.setFont(new Font("Roboto", 15));

        Label capacity = new Label("Capacity (mm³)");
        TextField capacity1 = new TextField();
        capacity.setTextFill(Color.WHITE);
        capacity.setStyle("-fx-font-weight: bold;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        capacity.setFont(new Font("Roboto", 15));

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
        add.setOnAction(e->{
            Main main = new Main();
            if(!(brand1.getText().isEmpty()||model1.getText().isEmpty()||color1.getText().isEmpty()||width1.getText().isEmpty()||length1.getText().isEmpty()||height1.getText().isEmpty()||capacity1.getText().isEmpty())) {
                if (numValidation(length1.getText(), width1.getText(), height1.getText(), capacity1.getText())) {
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

                    File equipment = new File(main.userPaths + "/Equipment.txt");
                    try {
                        FileWriter fileWriter = new FileWriter(equipment, true);
                        fileWriter.write(brand1.getText() + "\n");
                        fileWriter.write(model1.getText() + "\n");
                        fileWriter.write(type1.getValue().toString() + "\n");
                        fileWriter.write(color1.getText() + "\n");
                        fileWriter.write(length1.getText() + "\n");
                        fileWriter.write(width1.getText() + "\n");
                        fileWriter.write(height1.getText() + "\n");
                        fileWriter.write(capacity1.getText() + "\n");
                        fileWriter.write(url + "\n");

                        fileWriter.close();

                        main.alertDialog("Equipment Added Successfully", "Success", basePane, stackPane, "Ok", scene);
                        brand1.setText("");
                        model1.setText("");
                        color1.setText("");
                        width1.setText("");
                        length1.setText("");
                        height1.setText("");
                        capacity1.setText("");
                        image.setImage(new Image("image.png"));

                    } catch (IOException ie) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("System Error");
                        alert.setContentText("Error occurred, Aborting !");
                        alert.showAndWait();
                        System.exit(-1);
                    }
                } else {

                    main.alertDialog("Please enter a valid number for length, width, height and capacity(No decimals and negative)", "Input Error", basePane, stackPane, scene);
                }
            }else{
                main.alertDialog("Please do not leave any blanks !", "Input Error", basePane, stackPane, scene);

            }

        });

        basePane.getChildren().add(gridPane);
        scene = new Scene(basePane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Equipment");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        return primaryStage;









    }

    public boolean numValidation(String a, String b, String c, String d){
       boolean status = true;
        try{
            int q =Integer.parseInt(a);
            int r = Integer.parseInt(b);
            int s =Integer.parseInt(c);
            int t = Integer.parseInt(d);
            if(q<0||r<0||s<0||t<0){
                status = false;
            }
        }catch (NumberFormatException ie){

            status = false;
        }
        return status;
    }
}
