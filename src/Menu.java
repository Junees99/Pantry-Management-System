import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

public class Menu {
        Scene scene;
        Label welcome;
        Label tip;
        Label what;
        ImageView bulb1;
        Label time;
        StackPane stackPane = new StackPane();
        RotateTransition rt;


        String[] tipss = {"Save your tears by cutting onion under water","You can peel ginger with a spoon without losing all that extra ginger.","Citrus fruits, tomato, cheese and chocolate all taste best at room temperature.","Use unscented floss to cut through soft things like cheeses or cakes.","Separate your bananas to prevent them from going off quicker.","Sun Jun is the most handsome guy alive"};

        public Stage mainMenu(){
            Main main = new Main();
            Stage stage = new Stage();
            AnchorPane anchorPane = new AnchorPane();
            VBox equipment = new VBox(10);
            VBox utensils = new VBox(10);
            VBox ingredients = new VBox(10);
            VBox recipe = new VBox(10);
            anchorPane.setPadding(new Insets(0));

            String musicFile = "menu.mp3";     // For example

            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();



            BackgroundImage bg = new BackgroundImage(new Image("fruits.jpg",960,540,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(960,540,false,false,true,true));
            anchorPane.setBackground(new Background(bg));

            BackgroundImage bg1 = new BackgroundImage(new Image("kitchen.jpg",960,540,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(960,540,false,false,true,true));

            BackgroundImage bg2 = new BackgroundImage(new Image("spoon.jpg",960,540,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(960,540,false,false,true,true));

            BackgroundImage bg3 = new BackgroundImage(new Image("ingredient.jpg",960,540,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(960,540,false,false,true,true));

           BackgroundImage bg4 = new BackgroundImage(new Image("pizza.jpg",960,540,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(960,540,false,false,true,true));

            //Equipment
            equipment.setPadding(new Insets(10));
            equipment.setPrefSize(200,240);
            equipment.setMaxSize(200,240);
            equipment.setStyle("-fx-background-color:#235687;"+"-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"+"-fx-background-radius:20;");
            equipment.setLayoutX(34);
            equipment.setLayoutY(235);
            Image equip = new Image("boil.png");
            ImageView equipp = new ImageView(equip);
            equipp.setFitWidth(150);
            equipp.setFitHeight(150);
            Label equippp = new Label("Equipment");
            equippp.setFont(new Font("agency fb",25));
            equippp.setTextFill(Color.WHITE);
            equipment.getChildren().addAll(equipp,equippp);
            equipment.setAlignment(Pos.CENTER);
            equipment.setOnMouseEntered(e->{
                equipment.setLayoutY(215);
                scene.setCursor(Cursor.HAND);
                anchorPane.setBackground(new Background(bg1));

            });
            equipment.setOnMouseExited(e->{
                equipment.setLayoutY(235);
                scene.setCursor(Cursor.DEFAULT);
                anchorPane.setBackground(new Background(bg));

            });
            equipment.setOnMouseClicked(e->{
                Equipment equipment1 = new Equipment();
                equipment1.equipmentPage().show();
                mediaPlayer.stop();
                stage.close();
            });


            //Utensils
            utensils.setPadding(new Insets(10));
            utensils.setPrefSize(200,240);
            utensils.setStyle("-fx-background-color:#923480;"+"-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"+"-fx-background-radius:20;");
            utensils.setLayoutX(268);
            utensils.setLayoutY(235);
            Image utensil = new Image("baby.png");
            ImageView utensilss = new ImageView(utensil);
            utensilss.setFitWidth(150);
            utensilss.setFitHeight(150);
            Label utensilllll = new Label("Utensil");
            utensilllll.setFont(new Font("agency fb",25));
            utensilllll.setTextFill(Color.WHITE);
            utensils.getChildren().addAll(utensilss,utensilllll);
            utensils.setAlignment(Pos.CENTER);
            utensils.setOnMouseEntered(e->{
                utensils.setLayoutY(215);
                scene.setCursor(Cursor.HAND);
                anchorPane.setBackground(new Background(bg2));


            });
            utensils.setOnMouseExited(e->{
                utensils.setLayoutY(235);
                scene.setCursor(Cursor.DEFAULT);
                anchorPane.setBackground(new Background(bg));


            });
            utensils.setOnMouseClicked(e->{
                Utensil utensil1 = new Utensil();
                utensil1.utensilPage().show();
                mediaPlayer.stop();

                stage.close();
            });

    //

            //Ingredients
            ingredients.setPadding(new Insets(10));
            ingredients.setPrefSize(200,240);
            ingredients.setStyle("-fx-background-color:#563784;"+"-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"+"-fx-background-radius:20;");
            ingredients.setLayoutX(502);
            ingredients.setLayoutY(235);
            Image ing = new Image("ketchup.png");
            ImageView ingre = new ImageView(ing);
            ingre.setFitWidth(150);
            ingre.setFitHeight(150);
            Label ingred = new Label("Ingredient");
            ingred.setFont(new Font("agency fb",25));
            ingred.setTextFill(Color.WHITE);
            ingredients.getChildren().addAll(ingre,ingred);
            ingredients.setAlignment(Pos.CENTER);
            ingredients.setOnMouseEntered(e->{
                ingredients.setLayoutY(215);
                scene.setCursor(Cursor.HAND);
                anchorPane.setBackground(new Background(bg3));


            });
            ingredients.setOnMouseExited(e->{
                ingredients.setLayoutY(235);
                scene.setCursor(Cursor.DEFAULT);
                anchorPane.setBackground(new Background(bg));


            });
            ingredients.setOnMouseClicked(e->{
                Ingredient ingredient = new Ingredient();
                ingredient.ingredientPage().show();
                mediaPlayer.stop();

                stage.close();
            });


            //Recipe
            recipe.setPadding(new Insets(10));
            recipe.setPrefSize(200,240);
            recipe.setStyle("-fx-background-color:#438297;"+"-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"+"-fx-background-radius:20;");
            recipe.setLayoutX(736);
            recipe.setLayoutY(235);
            Image reci = new Image("ramen.png");
            ImageView recip = new ImageView(reci);
            recip.setFitWidth(150);
            recip.setFitHeight(150);
            Label recipee = new Label("Recipe");
            recipee.setFont(new Font("agency fb",25));
            recipee.setTextFill(Color.WHITE);
            recipe.getChildren().addAll(recip,recipee);
            recipe.setAlignment(Pos.CENTER);
            recipe.setOnMouseEntered(e->{
                recipe.setLayoutY(215);
                scene.setCursor(Cursor.HAND);
                anchorPane.setBackground(new Background(bg4));


            });
            recipe.setOnMouseExited(e->{
                recipe.setLayoutY(235);
                scene.setCursor(Cursor.DEFAULT);
                anchorPane.setBackground(new Background(bg));


            });
            recipe.setOnMouseClicked(e->{
                Recipe recipe1 = new Recipe();
                recipe1.recipePage().show();
                mediaPlayer.stop();

                stage.close();
            });


            //Time
            time = new Label("he");
            time.setFont(new Font("Arial Black",60));
            time.setLayoutX(394.5);
            time.setLayoutY(0);
            time.setTextFill(Color.WHITE);
            time.setStyle("-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0.5, 0, 0);");
            time.setTextAlignment(TextAlignment.CENTER);
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
              int  second = LocalDateTime.now().getSecond();
              String  minute =Integer.toString(LocalDateTime.now().getMinute());
              if(minute.length()==1){
                  minute = "0" + minute;
              }
              String hour = Integer.toString(LocalDateTime.now().getHour());
                if(hour.length()==1){
                    hour = "0" + hour;
                }
              String dots = " ";
              if(second%2==0){
                  dots = ":";

              }else {
                  dots =" ";
              }

                time.setText(hour + dots + (minute));

            }),
                    new KeyFrame(Duration.seconds(0.5))
            );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();



            //Welcome

            welcome = new Label("Welcome, "+main.username);
            welcome.setFont(new Font("agency fb Rounded MT Bold",35));
            welcome.setTextFill(Color.WHITE);
            welcome.setStyle("-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0.5, 0, 0);"+"-fx-font-weight: bold;");
            welcome.setLayoutY(80);

            //Sub Title
            what = new Label("What would you like to do today ?");
            what.setFont(new Font("Roboto",26));
            what.setTextFill(Color.WHITE);
            what.setLayoutY(155);
            what.setStyle(" -fx-stroke: #000000;" + "-fx-stroke-width: 5px;");




            //Line
            Line line = new Line();
            line.setStartX(251);
            line.setEndX(719);
            line.setStartY(195);
            line.setEndY(195);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(2);

            //Tips
            tip = new Label(setTip());
            tip.setFont(new Font("agency fb",22));
            tip.setTextFill(Color.WHITE);
            tip.setLayoutY(500);
            tip.setStyle("-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0.5, 0, 0)");
            Image bulb = new Image("bulb.png");
             bulb1 = new ImageView(bulb);
            bulb1.setFitHeight(50);
            bulb1.setFitWidth(50);
            bulb1.setLayoutY(480);
            bulb1.setStyle("-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0.5, 0, 0)");

            //Log Out
            JFXButton logout = new JFXButton("Log Out");
            logout.setLayoutX(870);
            logout.setLayoutY(10);
            logout.setPrefWidth(80);
            logout.setTextFill(Color.WHITE);
            logout.setTextAlignment(TextAlignment.CENTER);
            logout.setStyle("-fx-background-color:#2196F3");









            logout.setOnMouseEntered(e->{

                scene.setCursor(Cursor.HAND);

            });
            logout.setOnMouseExited(e->{

                scene.setCursor(Cursor.DEFAULT);

            });

    logout.setOnAction(e-> {
        BoxBlur blur = new BoxBlur(3,3,3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,dialogLayout,JFXDialog.DialogTransition.TOP);
        JFXButton yes = new JFXButton("Yes");
        JFXButton cancel = new JFXButton("Cancel");
        yes.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        yes.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
        cancel.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        cancel.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
        dialogLayout.setBody(new Label("Are you sure you want to logout ?"));
        dialogLayout.setActions(yes,cancel);
        anchorPane.setEffect(blur);
        dialog.show();
        scene.setOnMouseClicked(event->{
            anchorPane.setEffect(new BoxBlur(0,0,0));
        });


        yes.setOnAction(event->{
            dialog.close();
            stage.close();
            mediaPlayer.stop();

            Stage stage1 = new Stage();
            Main main1 = new Main();
            main1.start(stage1);


        });

        cancel.setOnAction(event -> {
            dialog.close();
            anchorPane.setEffect(new BoxBlur(0,0,0));
        });

    });

            anchorPane.getChildren().addAll(logout,equipment,utensils,ingredients,recipe,welcome,line,time,what,tip,bulb1);
            stackPane.getChildren().add(anchorPane);
            scene = new Scene(stackPane,960,540);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Taylor's Pantry Management - "+main.username);
            stage.sizeToScene();


            return stage;

        }


public String setTip(){
    Random random = new Random();
    int index = random.nextInt(tipss.length);
    return tipss[index];

}


    }
