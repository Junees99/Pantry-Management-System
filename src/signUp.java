import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class signUp {

    JFXTextField username;
    JFXPasswordField password, password1;
    JFXCheckBox agreement;
    StackPane stackPane;
    StackPane stackPane1;
    Stage stage = new Stage();
    Scene scene;
    JFXButton tryAgain;

    public Stage signups() {
        Stage stages ;

        stackPane = new StackPane();
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color:white;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);" + "-fx-background-radius:10;");
        gridPane.setMaxSize(280, 350);
        gridPane.setPadding(new Insets(10));


        gridPane.setHgap(10);
        gridPane.setVgap(10);
        stackPane1 = new StackPane();
        BackgroundImage bg = new BackgroundImage(new Image("bread.jpg", 960, 540, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        stackPane1.setBackground(new Background(bg));

        double x = 20;

        username = new JFXTextField();
        username.setPromptText("Username");
        password = new JFXPasswordField();
        password.setPromptText("Password");
        password1 = new JFXPasswordField();
        password1.setPromptText("Re-type Password");
        username.setMinWidth(220);
        password.setMinWidth(220);
        password1.setMinWidth(220);


        JFXButton sign_up = new JFXButton("Sign Up");
        sign_up.setTextFill(Color.WHITE);
        sign_up.setStyle("-fx-background-color:#2196F3");
        sign_up.setMinSize(220, 30);
        sign_up.setOnMouseEntered(e->{
            scene.setCursor(Cursor.HAND);
        });
        sign_up.setOnMouseExited(e->{
            scene.setCursor(Cursor.DEFAULT);
        });
        sign_up.setOnMouseClicked(e -> {
            signingUp(stackPane,stackPane1);
        });

        agreement = new JFXCheckBox("I accept the Terms & Conditions");

        ImageView logo = new ImageView(new Image("Logo.png"));
        logo.setFitWidth(200);
        logo.setFitHeight(130);

        gridPane.add(logo, 1, 0);

        gridPane.add(username, 0, 1, 3, 1);
        gridPane.add(password, 0, 2, 3, 1);
        gridPane.add(password1, 0, 3, 3, 1);
        gridPane.add(agreement, 0, 4, 3, 1);
        gridPane.add(sign_up, 0, 5, 3, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHalignment(sign_up, HPos.LEFT);
        gridPane.setHalignment(username, HPos.CENTER);
        gridPane.setHalignment(password, HPos.CENTER);
        gridPane.setHalignment(password1, HPos.CENTER);
        gridPane.setHalignment(logo, HPos.CENTER);
        gridPane.setValignment(sign_up, VPos.BOTTOM);


        stackPane.setAlignment(Pos.CENTER);


        stackPane1.getChildren().add(gridPane);
        stackPane.getChildren().add(stackPane1);
        scene = new Scene(stackPane, 380, 400);
        stage.setScene(scene);
        stage.setTitle("Sign Up");
        stage.initModality(Modality.APPLICATION_MODAL);
        stages = stage;
        stage.setResizable(false);
        stage.sizeToScene();



        return stages;


    }

    private void signingUp(StackPane basePane, StackPane contentPane) {
        Main main1 = new Main();
        boolean status = false;

        try{

            String tmp = username.getText();
            String[] tmp1;

            Scanner scanner = new Scanner(new File(main1.path+"/Login.txt"));

            while(scanner.hasNextLine()){
                tmp1 = scanner.nextLine().split(" ");
                if(tmp.equals(tmp1[0])){
                    status = true;
                    break;
                }

            }
        }catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("System Error");
            alert.setContentText("Error occurred, Aborting !");
            alert.showAndWait();
            System.exit(-1);        }
        if (username.getText().contains(" ")) {
            main1.alertDialog("Please don't include space in the username", "Warning", basePane, contentPane,scene);
        }else if(status){
            main1.alertDialog("Username is already taken", "Warning", basePane, contentPane,scene);
        }
        else if (username.getText().isEmpty() || password.getText().isEmpty()) {
            main1.alertDialog("Please don't leave the username or password field empty!", "Warning", basePane, contentPane,scene);
        } else if (!password.getText().equals(password1.getText())) {
            main1.alertDialog("Password entered does not match", "Warning", basePane, contentPane,scene);
        } else if (!agreement.isSelected()) {
            main1.alertDialog("Please read and accept the terms and conditions", "Warning", basePane, contentPane,scene);
        } else {
            try {
                FileWriter rememberWriter = new FileWriter(main1.path+"/Login.txt", true);
                rememberWriter.write("\n" + username.getText() + " " + password.getText());
                rememberWriter.close();
                main1.alertDialog("Sign Up is successful, please login again", "Sign Up Successful", basePane, contentPane, "OK",scene, stage);
                main1.userDirGenerator(username.getText());

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }


        }
        scene.setOnMouseClicked(event->{
            stackPane1.setEffect(new BoxBlur(0,0,0));
        });
    }
}




