import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Recipe {
    Main main = new Main();
    ArrayList<RecipeClass> recipes = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();

    ObservableList<String> recipeTypes = FXCollections.observableArrayList("Western", "Chinese", "Malaysian", "Middle Eastern", "Pastry", "Cake", "Dessert", "Snack", "Japanese", "Korean");

    TilePane tilePane;

    JFXCheckBox western = new JFXCheckBox("Western");
    JFXCheckBox chinese = new JFXCheckBox("Chinese");
    JFXCheckBox malaysian = new JFXCheckBox("Malaysian");
    JFXCheckBox middleEastern = new JFXCheckBox("Middle Eastern");
    JFXCheckBox pastry = new JFXCheckBox("Pastry");
    JFXCheckBox cake = new JFXCheckBox("Cake");
    JFXCheckBox dessert = new JFXCheckBox("Dessert");
    JFXCheckBox snack = new JFXCheckBox("Snack");
    JFXCheckBox japanese = new JFXCheckBox("Japanese");
    JFXCheckBox korean = new JFXCheckBox("Korean");

    Scene scene;


    public Stage recipePage() {
        try {
            populateList();
        }catch (NumberFormatException e){
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Data Corrupted");
                alert.setContentText("Recipe data is tampered. Data has been lost, please restart the program");
                alert.showAndWait();
                PrintWriter pw = new PrintWriter(new Main().userPaths+"/Recipe.txt");
                pw.close();
                System.exit(-1);
            }catch (IOException ie){
                System.exit(-1);
            }

        }
        Stage primaryStage = new Stage();

        StackPane stackPane = new StackPane();
        BorderPane contentPane = new BorderPane();
        HBox header = new HBox(10);
        VBox navi = new VBox(10);

        //Content Pane
        BackgroundImage bg = new BackgroundImage(new Image("bgg.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        contentPane.setBackground(new Background(bg));

        //Header
        header.setPadding(new Insets(10, 0, 0, 20));
        header.setPrefWidth(800);
        header.setPrefHeight(80);
        header.setBackground(new Background(new BackgroundFill(Color.rgb(8, 35, 60), CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView logo = new ImageView(new Image("Logo.png"));
        logo.setFitWidth(110);
        logo.setFitHeight(75);

        TextField search = new TextField();
        search.setStyle("-fx-background-radius:0");
        search.setPrefWidth(400);
        search.setPrefHeight(19);
        search.setTranslateX(20);
        search.setTranslateY(20);
        search.setFocusTraversable(false);

        Button searchh = new Button();
        ImageView mag = new ImageView(new Image("search.png"));
        mag.setFitHeight(15);
        mag.setFitWidth(15);
        searchh.setGraphic(mag);
        searchh.setPrefSize(21, 24.5);
        searchh.setTranslateX(10);
        searchh.setTranslateY(20);
        searchh.setStyle("-fx-background-color:#2196F3;" + "-fx-background-radius:0;");

        Text heading = new Text("   Recipe");
        heading.setFont(new Font("agency fb", 40));
        heading.setTranslateX(37);
        heading.setTranslateY(8);
        heading.setFill(Color.WHITE);

        header.getChildren().addAll(logo, search, searchh, heading);

        //Navi
        navi.setPadding(new Insets(20, 10, 2, 20));
        navi.setSpacing(10);
        navi.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        navi.setPrefWidth(160);

        Text title = new Text("Filter");
        title.setFont(new Font("agency fb", 25));
        title.setFill(Color.BLACK);
        navi.setMargin(title, new Insets(0, 0, 10, 0));

        search.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                tilePane.getChildren().clear();
                if ((search.getText()).isEmpty()) {
                        showRecipe();
                } else {
                    search(search.getText());
                }

            }
        });

        searchh.setOnAction(e -> {
            tilePane.getChildren().clear();
            if ((search.getText()).isEmpty()) {
                showRecipe();
            } else {
                search(search.getText());
            }

            searchh.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
            });
            searchh.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
            });

        });


        western.setFont(new Font("Roboto", 14));
        chinese.setFont(new Font("Roboto", 14));
        malaysian.setFont(new Font("Roboto", 14));
        middleEastern.setFont(new Font("Roboto", 14));
        pastry.setFont(new Font("Roboto", 14));
        cake.setFont(new Font("Roboto", 14));
        snack.setFont(new Font("Roboto", 14));
        dessert.setFont(new Font("Roboto", 14));
        japanese.setFont(new Font("Roboto", 14));
        korean.setFont(new Font("Roboto", 14));


        JFXButton add = new JFXButton("Add Recipe");
        JFXButton main = new JFXButton("Main Menu");
        add.setPrefSize(140, 25);
        add.setStyle("-fx-background-color:#2196F3");
        add.setTextFill(Color.WHITE);
        navi.setMargin(add, new Insets(77, 0, 0, -10));
        main.setPrefSize(140, 25);
        main.setStyle("-fx-background-color:#2196F3");
        main.setTextFill(Color.WHITE);
        navi.setMargin(main, new Insets(0, 0, 0, -10));
        navi.getChildren().addAll(title, western, chinese, malaysian, middleEastern, pastry, cake, dessert, snack, japanese, korean, add, main);
        main.setOnAction(e -> {
            Menu menu = new Menu();
            Main main1 = new Main();
            menu.mainMenu().show();
            main1.centerAlign(menu.welcome);
            main1.centerAlign(menu.what);
            main1.centerAlign(menu.tip);
            menu.bulb1.setLayoutX(menu.tip.getLayoutX() - 50);
            primaryStage.close();
        });
        add.setOnAction(e -> {
            RecipeAddingForm addingForm = new RecipeAddingForm();
            addingForm.addForm().showAndWait();
            tilePane.getChildren().clear();
            populateList();
            showRecipe();
        });

        add.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        add.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);

        });

        main.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);

        });
        main.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);

        });


        //Scroll Pane
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setStyle("-fx-background:transparent;" + "-fx-background-color:transparent");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);

        tilePane = new TilePane();
        tilePane.setPrefWidth(640);
        tilePane.setMinHeight(520);
        tilePane.setPadding(new Insets(8, 10, 10, 10));
        tilePane.setHgap(8);
        tilePane.setVgap(10);


//
//
//

        showRecipe();
        scrollPane.setContent(tilePane);


        stackPane.getChildren().add(contentPane);
        contentPane.setTop(header);
        contentPane.setLeft(navi);
        contentPane.setCenter(scrollPane);

        scene = new Scene(stackPane, 810, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Recipe");
        primaryStage.sizeToScene();

        western.setOnAction(e->{
            tilePane.getChildren().clear();
            filterRecipe(checkBoxSelected());
        });
        chinese.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        malaysian.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        middleEastern.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        japanese.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        korean.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        pastry.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        cake.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        snack.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });
        dessert.setOnAction(e->{
            tilePane.getChildren().clear();

            filterRecipe(checkBoxSelected());
        });



        return primaryStage;
    }


    public void populateList() {
        Main main = new Main();
        File file = new File(main.userPaths + "/Recipe.txt");
        String[] data = new String[9];
        int i = 0;
        if (!file.exists()) {
            try {
                boolean a = file.createNewFile();
            } catch (IOException ie) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }
        } else {
            try {
                recipes.clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    data[i] = scanner.nextLine();

                    i++;
                    if (i == 6) {
                        String[] ingredientList = data[2].split("#");
                        String[] equipmentList = data[3].split("#");
                        String[] recipeInstruction = data[4].split("#");
                        recipes.add(new RecipeClass(data[0], data[1], ingredientList, equipmentList, recipeInstruction, data[5]));
                        i = 0;
                    }
                }
                scanner.close();
            } catch (IOException ie) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }


        }
    }


    public void showRecipe() {
        String nama = "";
        ImageView item;
        int i = 0;
        if (!recipes.isEmpty()) {
            name.clear();
            for (RecipeClass recipeClass : recipes) {
                nama = recipeClass.getRecipeName();
                name.add(nama);

                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!recipeClass.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///" + recipeClass.getImageURL()));
                    item.setFitHeight(100);
                    item.setFitWidth(100);
                } else {
                    item = new ImageView(new Image("NA.jpg"));
                    item.setFitHeight(100);
                    item.setFitWidth(100);
                }


                Button edit1 = new Button();
                edit1.setGraphic(edit);
                edit1.setPrefSize(58.75, 20);
                edit1.setStyle("-fx-background-color:#2196f3");


                Button delete1 = new Button();
                delete1.setGraphic(delete);
                delete1.setPrefSize(58.75, 20);
                delete1.setStyle("-fx-background-color:#2196f3");

                Text names = new Text(nama);
                names.setFont(new Font("agency fb", 20));
                names.setFill(Color.BLACK);
                names.setWrappingWidth(145);
                names.setTextAlignment(TextAlignment.CENTER);

                String id = Integer.toString(i);

                VBox equip = new VBox(10);
                equip.setPadding(new Insets(10, 0, 0, 0));
                equip.setMaxSize(147.5, 160);
                equip.setStyle("-fx-background-radius:10;" + "-fx-background-color:white;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                equip.setId(id);
                delete1.setId(id);
                edit1.setId(id);
                HBox hBox = new HBox(10);
                hBox.setPadding(new Insets(10));
                hBox.getChildren().addAll(delete1, edit1);
                equip.getChildren().addAll(item, names, hBox);
                equip.setAlignment(Pos.CENTER);

                equip.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                    equip.setTranslateY(-5);
                });
                equip.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                    equip.setTranslateY(0);
                });

                delete1.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this recipe ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteRecipe(Integer.parseInt(ids));
                        tilePane.getChildren().clear();
                        populateList();
                        showRecipe();
                         resetCheckboxes();
                    } else {
                        alert.close();
                    }

                });
                delete1.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                });
                delete1.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                });

                edit1.setOnAction(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                    editRecipe editRecipe = new editRecipe();
                    editRecipe.editForm(Integer.parseInt(ids),recipeClass1.getRecipeName(),recipeClass1.getRecipeType(),recipeClass1.getRecipeIngredients(),recipeClass1.getRecipeEquipment(),recipeClass1.getRecipeInstructions(),recipeClass1.getImageURL() ).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showRecipe();
                  //  resetCheckboxes();
                });

                edit1.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                });
                edit1.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                });

                i = i + 1;

                equip.setOnMouseClicked(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                    viewRecipe viewRecipe = new viewRecipe();
                    viewRecipe.viewForm(recipeClass1.getRecipeName(), recipeClass1.getRecipeType(), recipeClass1.getRecipeIngredients(), recipeClass1.getRecipeEquipment(), recipeClass1.getRecipeInstructions(), recipeClass1.getImageURL()).show();

                });


                tilePane.getChildren().add(equip);


            }


        }
    }


    public void deleteRecipe(int index) {
        try {
            File inputFile = new File(main.userPaths + "/Recipe.txt");
            File outputFile = new File(main.userPaths + "/tmpRecipe.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 6;
            int j = i - 6 + 1;
            int k = 1;
            while ((currentLine = reader.readLine()) != null) {
                if (k == i) {
                    File image = new File(currentLine);
                    boolean b = image.delete();
                }
                if (k >= j && k <= i) {
                    k++;

                } else {

                    writer.write(currentLine + System.getProperty("line.separator"));
                    k++;

                }

            }


            writer.close();
            reader.close();
            boolean a = inputFile.delete();
            boolean s = outputFile.renameTo(inputFile);


        } catch (IOException ie) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("System Error");
            alert.setContentText("Error occurred, Aborting !");
            alert.showAndWait();
            System.exit(-1);
        }


    }



    public void editRecipe(int index,String recipeNamess, String recipeTypess, String ingredientListss, String equipmentListss,String recipeInstructions,String urlss) {
        String[] data = new String[6];
        data[0] = recipeNamess;
        data[1] = recipeTypess;
        data[2] = ingredientListss;
        data[3] = equipmentListss;
        data[4] = recipeInstructions;
        data[5] = urlss;


        try {
            File inputFile = new File(main.userPaths + "/Recipe.txt");
            File outputFile = new File(main.userPaths + "/tmpRecipe.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 6;
            int j = i - 6 + 1;
            int k = 1;
            int l = 0;

            while ((currentLine = reader.readLine()) != null) {

                if (k >= j && k <= i) {
                    writer.write(data[l] + System.getProperty("line.separator"));
                    k++;
                    l++;
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                    k++;

                }

            }
            writer.close();
            reader.close();
            boolean a = inputFile.delete();
            boolean s = outputFile.renameTo(inputFile);


        } catch (IOException ie) {

        }
    }

    public void filterRecipe(String type) {
        ImageView item;

        if (type.equals("")) {
            showRecipe();
        } else {
            int i = 0;
            for (RecipeClass recipeClass : recipes) {
                if (type.contains(recipeClass.getRecipeType())) {
                    ImageView delete = new ImageView(new Image("delete.png"));
                    delete.setFitWidth(20);
                    delete.setFitHeight(20);
                    ImageView edit = new ImageView(new Image("edit.png"));
                    edit.setFitWidth(20);
                    edit.setFitHeight(20);


                    if (!recipeClass.getImageURL().equals("null")) {
                        item = new ImageView(new Image("file:///" + recipeClass.getImageURL()));
                        item.setFitHeight(100);
                        item.setFitWidth(100);
                    } else {
                        item = new ImageView(new Image("NA.jpg"));
                        item.setFitHeight(100);
                        item.setFitWidth(100);
                    }


                    Button edit1 = new Button();
                    edit1.setGraphic(edit);
                    edit1.setPrefSize(58.75, 20);
                    edit1.setStyle("-fx-background-color:#2196f3");


                    Button delete1 = new Button();
                    delete1.setGraphic(delete);
                    delete1.setPrefSize(58.75, 20);
                    delete1.setStyle("-fx-background-color:#2196f3");

                    Label names = new Label(name.get(i));

                    names.setFont(new Font("agency fb", 20));
                    names.setTextFill(Color.BLACK);

                    String id = Integer.toString(i);

                    VBox equip = new VBox(10);
                    equip.setPadding(new Insets(10, 0, 0, 0));
                    equip.setMaxSize(147.5, 160);
                    equip.setStyle("-fx-background-radius:10;" + "-fx-background-color:white;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                    equip.setId(id);
                    delete1.setId(id);
                    edit1.setId(id);
                    HBox hBox = new HBox(10);
                    hBox.setPadding(new Insets(10));
                    hBox.getChildren().addAll(delete1, edit1);
                    equip.getChildren().addAll(item, names, hBox);
                    equip.setAlignment(Pos.CENTER);

                    delete1.setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete this recipe ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            final Node source = (Node) e.getSource();
                            String ids = source.getId();
                            deleteRecipe(Integer.parseInt(ids));
                            tilePane.getChildren().clear();
                            populateList();
                            showRecipe();
                             resetCheckboxes();
                        } else {
                            alert.close();
                        }

                    });

                    edit1.setOnAction(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                        editRecipe editRecipe = new editRecipe();
                        editRecipe.editForm(Integer.parseInt(ids),recipeClass1.getRecipeName(),recipeClass1.getRecipeType(),recipeClass1.getRecipeIngredients(),recipeClass1.getRecipeEquipment(),recipeClass1.getRecipeInstructions(),recipeClass1.getImageURL() ).showAndWait();
                        tilePane.getChildren().clear();
                        populateList();
                        showRecipe();
                          resetCheckboxes();
                    });

                    i = i + 1;

                    equip.setOnMouseClicked(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                        viewRecipe viewRecipe = new viewRecipe();
                        viewRecipe.viewForm(recipeClass1.getRecipeName(), recipeClass1.getRecipeType(), recipeClass1.getRecipeIngredients(), recipeClass1.getRecipeEquipment(), recipeClass1.getRecipeInstructions(), recipeClass1.getImageURL()).show();

                    });

                    equip.setOnMouseEntered(event -> {
                        scene.setCursor(Cursor.HAND);
                        equip.setTranslateY(-5);
                    });
                    equip.setOnMouseExited(event -> {
                        scene.setCursor(Cursor.DEFAULT);
                        equip.setTranslateY(0);
                    });


                    tilePane.getChildren().add(equip);


                } else {
                    i++;
                }

            }


        }
    }

    public String checkBoxSelected (){
        String[] check = new String[10];

        Arrays.fill(check," ");
        if (western.isSelected()){
            check[0] = "Western";
        }
        if (malaysian.isSelected()){
            check[1] = "Malaysian";
        }
        if (chinese.isSelected()){
            check[2] = "Chinese" ;
        }
        if (middleEastern.isSelected()){
            check[3] = "Middle Eastern";
        }
        if (pastry.isSelected()){
            check[4] = "Pastry";
        }
        if (cake.isSelected()){
            check[5] = "Cake";
        }
        if (dessert.isSelected()){
            check[6] = "Dessert";
        }
        if (snack.isSelected()){
            check[7] = "Snack";
        }
        if (japanese.isSelected()){
            check[8] = "Japanese";
        }
        if (korean.isSelected()){
            check[9] = "Korean";
        }

        String filter = "";
        for(int i = 0; i < check.length;i++){
            if(!check[i].equals(" ")){
                filter = filter + check[i] + " ";
            }
        }
        return filter;
    }
    public void resetCheckboxes(){
        western.setSelected(false);
        malaysian.setSelected(false);
        middleEastern.setSelected(false);
        chinese.setSelected(false);
        dessert.setSelected(false);
        pastry.setSelected(false);
        korean.setSelected(false);
        japanese.setSelected(false);
        cake.setSelected(false);
        snack.setSelected(false);
    }

    public void search(String keyword){
        int i = 0;
        ImageView item;
        for (RecipeClass recipeClass:recipes) {
            String name = recipeClass.getRecipeName();
            String nama = name.toLowerCase();
            if(nama.contains(keyword.toLowerCase())){
                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!recipeClass.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///" + recipeClass.getImageURL()));
                    item.setFitHeight(100);
                    item.setFitWidth(100);
                } else {
                    item = new ImageView(new Image("NA.jpg"));
                    item.setFitHeight(100);
                    item.setFitWidth(100);
                }


                Button edit1 = new Button();
                edit1.setGraphic(edit);
                edit1.setPrefSize(58.75, 20);
                edit1.setStyle("-fx-background-color:#2196f3");


                Button delete1 = new Button();
                delete1.setGraphic(delete);
                delete1.setPrefSize(58.75, 20);
                delete1.setStyle("-fx-background-color:#2196f3");

                Label names = new Label(name);

                names.setFont(new Font("agency fb", 20));
                names.setTextFill(Color.BLACK);

                String id = Integer.toString(i);

                VBox equip = new VBox(10);
                equip.setPadding(new Insets(10, 0, 0, 0));
                equip.setMaxSize(147.5, 160);
                equip.setStyle("-fx-background-radius:10;" + "-fx-background-color:white;" + "-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                equip.setId(id);
                delete1.setId(id);
                edit1.setId(id);
                HBox hBox = new HBox(10);
                hBox.setPadding(new Insets(10));
                hBox.getChildren().addAll(delete1, edit1);
                equip.getChildren().addAll(item, names, hBox);
                equip.setAlignment(Pos.CENTER);

                delete1.setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this recipe ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteRecipe(Integer.parseInt(ids));
                        tilePane.getChildren().clear();
                        populateList();
                        showRecipe();
                        resetCheckboxes();
                    } else {
                        alert.close();
                    }

                });

                edit1.setOnAction(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                    editRecipe editRecipe = new editRecipe();
                    editRecipe.editForm(Integer.parseInt(ids),recipeClass1.getRecipeName(),recipeClass1.getRecipeType(),recipeClass1.getRecipeIngredients(),recipeClass1.getRecipeEquipment(),recipeClass1.getRecipeInstructions(),recipeClass1.getImageURL() ).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showRecipe();
                    resetCheckboxes();
                });

                i = i + 1;

                equip.setOnMouseClicked(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    RecipeClass recipeClass1 = recipes.get(Integer.parseInt(ids));
                    viewRecipe viewRecipe = new viewRecipe();
                    viewRecipe.viewForm(recipeClass1.getRecipeName(), recipeClass1.getRecipeType(), recipeClass1.getRecipeIngredients(), recipeClass1.getRecipeEquipment(), recipeClass1.getRecipeInstructions(), recipeClass1.getImageURL()).show();

                });

                equip.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                    equip.setTranslateY(-5);
                });
                equip.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                    equip.setTranslateY(0);
                });
                tilePane.getChildren().add(equip);



            }else {
                i++;
            }

        }
    }

}
