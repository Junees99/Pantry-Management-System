import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class forgetPassword {
    Scene scene1,scene2;
    JFXTextField username;
    String pass;
    VBox stackPane1,hbox1;
    Label message = new Label();
    Label password = new Label();
    HBox hbox2;
    JFXButton returns;


    public Stage forget(){
        //Scene 1
        Stage primaryStage = new Stage();
        StackPane stackPane = new StackPane();
        VBox vbox1 = new VBox(10);
        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.BOTTOM_RIGHT);
        vbox1.setPadding(new Insets(20));

        Label l1 = new Label("Please enter your username: " );
        l1.setFont(new Font("Roboto",13));
        l1.setStyle("-fx-font-weight:bold");
        username = new JFXTextField();
        JFXButton cont = new JFXButton("Continue");
        cont.setTextFill(Color.WHITE);
        cont.setStyle("-fx-background-color:#2196f3");
        cont.setOnMouseEntered(e->{
            scene1.setCursor(Cursor.HAND);
        });
        cont.setOnMouseExited(e->{
            scene1.setCursor(Cursor.DEFAULT);
        });
        cont.setOnAction(e->{
            if (passLookup()) {
                String dots ="";
                message .setText("Your pass word is (Click and Hold to view) ");
                for (int i =0; i < pass.length();i++){
                    dots += "-";
                }
                password.setText(dots);
                String constant = dots;
                scene2.setOnMousePressed(event->{
                    password.setText(pass);
                });
                scene2.setOnMouseReleased(event->{

                    password.setText(constant);
                });


            }else {
                password.setText("");
                message.setText("Username does not exists");

            }

            message.setFont(new Font("Roboto",13));
            message.setStyle("-fx-font-weight:bold");


            WritableImage wi = new WritableImage(300, 130);
            Image img1 = stackPane.snapshot(new SnapshotParameters(), wi);
            ImageView imgView1 = new ImageView(img1);
            wi = new WritableImage(300, 130);
            Image img2 = stackPane1.snapshot(new SnapshotParameters(), wi);
            ImageView imgView2 = new ImageView(img2);
            imgView1.setTranslateX(0);
            imgView2.setTranslateX(300);
            StackPane pane = new StackPane(imgView1, imgView2);
            pane.setPrefSize(300, 130);
            stackPane.getChildren().setAll(pane);
            // create transtition
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(imgView2.translateXProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                // remove pane and restore scene 1
                stackPane.getChildren().setAll(vbox1);
                // set scene 2
                primaryStage.setScene(scene2);
            });
            timeline.play();


        });

        returns = new JFXButton("Return");
        returns.setTextFill(Color.WHITE);
        returns.setStyle("-fx-background-color:#2196f3");
        returns.setOnMouseEntered(e->{
            scene2.setCursor(Cursor.HAND);
        });
        returns.setOnMouseExited(e->{
            scene2.setCursor(Cursor.DEFAULT);
        });

        returns.setOnAction(e->{
            l1.setFont(new Font("Roboto",13));
            l1.setStyle("-fx-font-weight:bold");
            WritableImage wi = new WritableImage(300, 130);
            Image img1 = stackPane.snapshot(new SnapshotParameters(),wi);
            ImageView imgView1= new ImageView(img1);
            wi = new WritableImage(300, 130);
            Image img2 = stackPane1.snapshot(new SnapshotParameters(),wi);
            ImageView imgView2= new ImageView(img2);
            // Create new pane with both images
            imgView2.setTranslateX(0);
            imgView1.setTranslateX(300);

            StackPane pane= new StackPane(imgView2,imgView1);
            pane.setMinSize(300,130);
            // Replace root2 with new pane
            stackPane1.getChildren().setAll(pane);
            // create transtition
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(imgView1.translateXProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t->{
                // remove pane and restore scene 2
                stackPane1.getChildren().setAll(hbox1,hbox2);
                // set scene 1
                primaryStage.setScene(scene1);
            });
            timeline.play();
        });


        username.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)) {

                if (passLookup()) {
                    String dots ="";
                    message .setText("Your pass word is (Click and Hold to view) ");
                    for (int i =0; i < pass.length();i++){
                        dots += "-";
                    }
                    password.setText(dots);
                    String constant = dots;
                    scene2.setOnMousePressed(event->{
                        password.setText(pass);
                    });
                    scene2.setOnMouseReleased(event->{

                        password.setText(constant);
                    });


                }else {
                    password.setText("");
                    message.setText("Username does not exists");

            }

                message.setFont(new Font("Roboto",13));
                message.setStyle("-fx-font-weight:bold");


                WritableImage wi = new WritableImage(300, 130);
                Image img1 = stackPane.snapshot(new SnapshotParameters(), wi);
                ImageView imgView1 = new ImageView(img1);
                wi = new WritableImage(300, 130);
                Image img2 = stackPane1.snapshot(new SnapshotParameters(), wi);
                ImageView imgView2 = new ImageView(img2);
                imgView1.setTranslateX(0);
                imgView2.setTranslateX(300);
                StackPane pane = new StackPane(imgView1, imgView2);
                pane.setPrefSize(300, 130);
                stackPane.getChildren().setAll(pane);
                // create transtition
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(imgView2.translateXProperty(), 0, Interpolator.EASE_BOTH);
                KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
                timeline.getKeyFrames().add(kf);
                timeline.setOnFinished(t -> {
                    // remove pane and restore scene 1
                    stackPane.getChildren().setAll(vbox1);
                    // set scene 2
                    primaryStage.setScene(scene2);
                });
                timeline.play();

            }
        });

        hBox1.getChildren().add(cont);
        vbox1.getChildren().addAll(l1,username,hBox1);
        vbox1.setMargin(hBox1,new Insets(10,0,0,0));
        stackPane.getChildren().add(vbox1);

        scene1 = new Scene(stackPane,300,130);

        //Scene 2
        stackPane1 = new VBox(20);
        stackPane1.setPadding(new Insets(10));
         hbox1 = new VBox(10);
         hbox2 = new HBox(10);
         hbox2.getChildren().add(returns);
         hbox2.setAlignment(Pos.BOTTOM_RIGHT);
         hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(message,password);
         stackPane1.getChildren().addAll(hbox1,hbox2);
         stackPane1.setMargin(hbox1,new Insets(15,0,0,0));
        scene2 = new Scene(stackPane1,300,130);





        primaryStage.setScene(scene1);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Forgot Password");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();


        return primaryStage;

    }

    public boolean passLookup() {
        String[] login;
        boolean status = false;
        Main main = new Main();
        try {
            Scanner lookup = new Scanner(new File(main.path+"/Login.txt"));
            while (lookup.hasNextLine()){
                login = lookup.nextLine().split(" ");
                if(login[0].equals(username.getText())){
                    pass = login[1];
                    status = true;
                    break;
                }
            }
        }catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("System Error");
            alert.setContentText("Error occurred, Aborting !");
            alert.showAndWait();
            System.exit(-1);        }
        return status;
        }
    }

