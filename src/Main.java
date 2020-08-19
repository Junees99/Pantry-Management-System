import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Main extends Application {
    Scene scene;
    Stage stage;
    Menu menu = new Menu();
    Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
    static String path;
    static String username;
    static String userPaths;


    File loginFile, rememberFile, file;
    JFXTextField t1;
    JFXPasswordField t2;
    StackPane stackPane;
    StackPane stackPane1;


    @Override

    public void start(Stage primaryStage) {
        loginFileGenerator();
        userDirGenerator("admin");


        stage = primaryStage;
        stackPane = new StackPane();
        stackPane1 = new StackPane();
        GridPane gridPane = new GridPane();
        HBox remnfor = new HBox(10);
        HBox lpane = new HBox(10);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setMaxHeight(350);
        gridPane.setMaxWidth(280);


        gridPane.setPadding(new Insets(10));
        gridPane.setStyle("-fx-background-color:transparent");
        Rectangle rectangle = new Rectangle(280, 320);
        rectangle.setStyle("-fx-effect: dropshadow(three-pass-box, black, 20, 0, 0, 0)");
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);
        rectangle.setFill(Color.WHITE);
        Rectangle rectangle1 = new Rectangle(960, 540);
        rectangle1.setFill(new Color(0, 0, 0, 0.3));


        BackgroundImage bg = new BackgroundImage(new Image("background.jpg", 960, 540, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        stackPane.setBackground(new Background(bg));


        Image logo = new Image("Logo.png");
        ImageView logoo = new ImageView(logo);
        logoo.setFitWidth(200);
        logoo.setFitHeight(130);

        t1 = new JFXTextField();
        t1.setMaxWidth(220);
        t1.setPromptText("Username");
        t2 = new JFXPasswordField();
        t2.setPromptText("Password");
        t2.setMaxWidth(220);
        JFXButton login = new JFXButton("Login");
        login.setButtonType(JFXButton.ButtonType.RAISED);

        CheckBox remember = new CheckBox("Remember me");
        remember.setFont(new Font("Roboto", 12));
        remember.setSelected(true);
        Label forgot = new Label("Forgot Password?");
        forgot.setFont(new Font("Roboto", 11));
        forgot.setUnderline(true);
        forgot.setStyle("-fx-text-fill:#2196F3");


        Label l1 = new Label("Don't have an account yet ?");
        Label l2 = new Label("Sign Up");
        lpane.getChildren().addAll(l1, l2);
        l1.setFont(Font.font("Roboto", 10));
        l2.setFont(Font.font("Roboto", 12));
        l2.setTextFill(Color.valueOf("#2196F3"));

        l2.setOnMouseEntered(e -> {
            l2.setTextFill(Color.GRAY);
            scene.setCursor(Cursor.HAND);

        });
        l2.setOnMouseExited(e -> {
            l2.setTextFill(Color.valueOf("#2196F3"));
            scene.setCursor(Cursor.DEFAULT);
        });
        forgot.setOnMouseEntered(e -> {
            forgot.setTextFill(Color.GRAY);
            scene.setCursor(Cursor.HAND);

        });
        forgot.setOnMouseExited(e -> {
            forgot.setTextFill(Color.valueOf("#2196F3"));
            scene.setCursor(Cursor.DEFAULT);
        });
        forgot.setOnMouseClicked(e -> {
            forgetPassword forge = new forgetPassword();
            stackPane.setEffect(new BoxBlur(3, 3, 3));
            forge.forget().showAndWait();
            stackPane.setEffect(new BoxBlur(0, 0, 0));
        });
        login.setOnMouseEntered(e -> {

            scene.setCursor(Cursor.HAND);

        });
        login.setOnMouseExited(e -> {

            scene.setCursor(Cursor.DEFAULT);
        });


        lpane.setAlignment(Pos.CENTER);

        login.setRipplerFill(Color.WHITE);
        login.setPrefSize(220, 30);
        login.setTextFill(Color.WHITE);
        login.setStyle("-fx-background-color: #2196F3\n");

        remnfor.getChildren().addAll(remember, forgot);
        remnfor.setAlignment(Pos.CENTER);
        remnfor.setMargin(remember, new Insets(0, 23, 0, 0));


        gridPane.add(logoo, 1, 0);
        gridPane.add(t1, 0, 1, 3, 1);
        gridPane.add(t2, 0, 2, 3, 1);
        gridPane.add(remnfor, 0, 3, 3, 1);
        gridPane.add(login, 1, 4);
        gridPane.add(lpane, 0, 5, 3, 1);
        gridPane.setHalignment(login, HPos.CENTER);
        gridPane.setHalignment(t1, HPos.CENTER);
        gridPane.setHalignment(t2, HPos.CENTER);
        gridPane.setHalignment(logoo, HPos.CENTER);
        gridPane.setValignment(login, VPos.BOTTOM);
        gridPane.setAlignment(Pos.CENTER);


        login.setOnAction(e -> {
            if (validateLogin()) {
                username = t1.getText();
                userPaths = path + "/" + username;
                if (remember.isSelected()) {
                    rememberMe();
                } else {

                    try {
                        FileWriter rmb = new FileWriter(path+"/Remember.txt", false);
                        rmb.write("");
                        rmb.close();
                    } catch (IOException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Exception error has occured, please restart the program");
                        alert.show();
                    }
//
                }


                stage.close();
                menu.mainMenu().show();
                centerAlign(menu.welcome);
                centerAlign(menu.what);
                centerAlign(menu.tip);
                menu.bulb1.setLayoutX(menu.tip.getLayoutX() - 50);

            } else {
                alertDialog("Invalid username or password, please try again", "Invalid Login", stackPane, stackPane1, scene);
                scene.setOnMouseClicked(event->{
                    stackPane1.setEffect(new BoxBlur(0,0,0));
                });
            }

        });
        l2.setOnMouseClicked(e -> {
            signUp sign = new signUp();
            stackPane.setEffect(new BoxBlur(3, 3, 3));
            sign.signups().showAndWait();
            stackPane.setEffect(new BoxBlur(0, 0, 0));

        });
        autoFill();
        stackPane1.getChildren().addAll(rectangle, gridPane);
        stackPane.getChildren().add(stackPane1);
        stackPane.setAlignment(Pos.CENTER);
        scene = new Scene(stackPane, 960, 540);
        stage.setTitle("Taylor's Pantry Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stackPane1.setPadding(new Insets(0,0,0,0));
        stackPane.setPadding(new Insets(0,0,0,0));
        stage.sizeToScene();
        stage.show();



    }


    public void centerAlign(Label text) {
        text.setLayoutX((960 - text.getWidth()) / 2);

    }

    public void loginFileGenerator() {
        try {
            File tmpDir1 = new File(root.toString() + "/Program Data");
            if (!tmpDir1.exists()) {
                boolean a = tmpDir1.mkdirs();



            }
            path = tmpDir1.getPath();


            File tmpDir = new File(path + "/Login.txt");

            if (!tmpDir.exists()) {
                FileWriter demo_login = new FileWriter(tmpDir);
                demo_login.write("admin 123456");
                demo_login.close();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.toString());
            alert.show();
        }
    }

    public boolean validateLogin() {
        boolean status = false;
        String username = t1.getText();
        String password = t2.getText();
        String tmp;
        String[] login = new String[2];
        try {
            Scanner loginLookup = new Scanner(new File(path + "/Login.txt"));
            while (loginLookup.hasNextLine()) {
                tmp = loginLookup.nextLine();
                login = tmp.split(" ");
                if (login[0].equals(username) && login[1].equals(password)) {
                    status = true;
                    break;

                }
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exception error has occured, please restart the program");
            alert.show();
        }
        return status;
    }

    public void alertDialog(String message, String title, StackPane basePane, StackPane contentPane, Scene scene) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(basePane, dialogLayout, JFXDialog.DialogTransition.TOP);
        JFXButton tryAgain = new JFXButton("Try Again");
        tryAgain.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        tryAgain.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
        tryAgain.setOnMouseClicked(e -> {
            dialog.close();
            contentPane.setEffect(new BoxBlur(0, 0, 0));
        });
        Label label = new Label(message);
        label.setWrapText(true);
        dialogLayout.setBody(label);
        dialogLayout.setActions(tryAgain);
        dialogLayout.setHeading(new Label(title));
        contentPane.setEffect(blur);
        dialog.show();
    }

    public void alertDialog(String message, String title, StackPane basePane, StackPane contentPane, String button, Scene scene, Stage stage) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(basePane, dialogLayout, JFXDialog.DialogTransition.TOP);
        JFXButton tryAgain = new JFXButton(button);
        tryAgain.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        tryAgain.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
        tryAgain.setOnMouseClicked(e -> {
            dialog.close();
            stage.close();
        });

        dialogLayout.setBody(new Label(message));
        dialogLayout.setActions(tryAgain);
        dialogLayout.setHeading(new Label(title));
        contentPane.setEffect(blur);
        dialog.show();
    }
    public void alertDialog(String message, String title, StackPane basePane, StackPane contentPane, String button, Scene scene) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(basePane, dialogLayout, JFXDialog.DialogTransition.TOP);
        JFXButton tryAgain = new JFXButton(button);
        tryAgain.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        tryAgain.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });
        tryAgain.setOnMouseClicked(e -> {
            dialog.close();
            BoxBlur blur1 = new BoxBlur(0, 0, 0);
            contentPane.setEffect(blur1);



        });

        dialogLayout.setBody(new Label(message));
        dialogLayout.setActions(tryAgain);
        dialogLayout.setHeading(new Label(title));
        contentPane.setEffect(blur);
        dialog.show();
    }

    public void rememberMe() {
        File tmpDir = new File(path + "/Remember.txt");


            try {
                FileWriter rememberWriter = new FileWriter(tmpDir,false);
                rememberWriter.write(t1.getText() + " " + t2.getText());
                rememberWriter.close();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Exception error has occured, please restart the program");
                alert.show();
            }



    }
        public void autoFill () {
            try {
                File file = new File(path + "/Remember.txt");
                if (file.exists()) {
                    Scanner rememberLookup = new Scanner(file);
                    while (rememberLookup.hasNextLine()) {
                        String tmp = rememberLookup.nextLine();
                        String[] tmp1 = tmp.split(" ");
                        t1.setText(tmp1[0]);
                        t2.setText(tmp1[1]);
                    }
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Exception error has occured, please restart the program ");
                alert.show();
            }
        }

        public void userDirGenerator(String username){
                File file = new File(path+"/"+username);
                if (!file.exists()) {
                    boolean a = file.mkdirs();
                    String userPath = file.getPath();
                    File assets = new File(userPath + "/assets");
                    File equipmentFile = new File(userPath + "/Equipment.txt");
                    File utensilFile = new File(userPath + "/Utensil.txt");
                    File ingredientFile = new File(userPath + "/Ingredient.txt");
                    File recipeFile = new File(userPath + "/Recipe.txt");
                    try {
                        boolean b;
                        b = equipmentFile.createNewFile();
                        b = utensilFile.createNewFile();
                        b = ingredientFile.createNewFile();
                        b = recipeFile.createNewFile();
                        b = assets.mkdirs();
                    } catch (IOException ie) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("System Error");
                        alert.setContentText("Error occurred, Aborting !");
                        alert.showAndWait();
                        System.exit(-1);
                    }
                }

        }
    }




