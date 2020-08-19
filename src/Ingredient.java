import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class Ingredient {


    Main main = new Main();
    ArrayList<IngredientClass> ingredients = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    TilePane tilePane;
    ObservableList<IngredientClass> olIngredient = FXCollections.observableArrayList();
    TableView<IngredientClass> tableView = new TableView<IngredientClass>();
    ScrollPane scrollPane = new ScrollPane();

    JFXCheckBox meat = new JFXCheckBox("Meat");
    JFXCheckBox seafood = new JFXCheckBox("Seafood");
    JFXCheckBox vegetable = new JFXCheckBox("Vegetable");
    JFXCheckBox fruit = new JFXCheckBox("Fruit");
    JFXCheckBox beans = new JFXCheckBox("Beans");
    JFXCheckBox carbohydrate = new JFXCheckBox("Carbohydrate");
    JFXCheckBox dairy = new JFXCheckBox("Dairy");
    JFXCheckBox preserved_food = new JFXCheckBox("Preserved Food");
    JFXCheckBox condiments = new JFXCheckBox("Condiments");
    JFXCheckBox herbs_and_spices = new JFXCheckBox("Herbs & Spices");
    JFXCheckBox baking_ingredients = new JFXCheckBox("Baking Ingredient");

    Scene scene;
    ObservableList<String> cities = FXCollections.observableArrayList();
    ComboBox<String> country1 = new ComboBox<String>(cities);


    public Stage ingredientPage() {
        Stage primaryStage = new Stage();
        try {
            populateList();
        }catch (NumberFormatException e){
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Data Corrupted");
                alert.setContentText("Ingredient data is tampered. Data has been lost, please restart the program");
                alert.showAndWait();
                PrintWriter pw = new PrintWriter(new Main().userPaths+"/Ingredient.txt");
                pw.close();
                System.exit(-1);
            }catch (IOException ie){
                System.exit(-1);
            }

        }

        ArrayList<String> expired = new ArrayList<>();

        for(IngredientClass ingredientClass : ingredients){
            if(LocalDate.parse(ingredientClass.getExpDate()).equals(LocalDate.now())||LocalDate.parse(ingredientClass.getExpDate()).isBefore(LocalDate.now())){
                expired.add(ingredientClass.getIngredientBrand()+" "+ingredientClass.getIngredientName());
            }
        }
        if(!expired.isEmpty()){
            new expiryNotice().expiryNotices(expired).showAndWait();
        }


        cities.add("None");
        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = {obj.getDisplayCountry()};
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        country1.setValue("None");
        country1.setItems(cities);
        country1.setMaxWidth(140);


        StackPane stackPane = new StackPane();
        BorderPane contentPane = new BorderPane();
        HBox header = new HBox(10);
        VBox navi = new VBox(5);
        VBox naviBase = new VBox(5);
        ScrollPane naviScroll = new ScrollPane();

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




        Text heading = new Text("Ingredient");
        heading.setFont(new Font("agency fb", 40));
        heading.setTranslateX(37);
        heading.setTranslateY(8);
        heading.setFill(Color.WHITE);

        header.getChildren().addAll(logo, search, searchh, heading);

        //Navi
        navi.setPadding(new Insets(20, 0, 2, 5));
        navi.setSpacing(10);
        navi.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        navi.setPrefWidth(160);

        naviScroll.setContent(navi);
        naviScroll.setMaxWidth(160);
        naviScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Text title = new Text("Filter");
        title.setFont(new Font("agency fb", 25));
        title.setFill(Color.BLACK);
        navi.setMargin(title, new Insets(-8, 0, 0, 0));


        meat.setFont(new Font("Roboto", 14));
        seafood.setFont(new Font("Roboto", 14));
        fruit.setFont(new Font("Roboto", 14));
        vegetable.setFont(new Font("Roboto", 14));
        herbs_and_spices.setFont(new Font("Roboto", 14));
        dairy.setFont(new Font("Roboto", 14));
        condiments.setFont(new Font("Roboto", 14));
        beans.setFont(new Font("Roboto", 14));
        carbohydrate.setFont(new Font("Roboto", 14));
        preserved_food.setFont(new Font("Roboto", 14));
        baking_ingredients.setFont(new Font("Roboto", 14));
        navi.setMargin(country1, new Insets(0, 0, 0, 5));

        JFXToggleButton table = new JFXToggleButton();
        table.setText("Table View");
        naviBase.setMargin(table, new Insets(-13, 0, 0, 0));
        table.setFont(new Font("Roboto", 13));

        JFXButton add = new JFXButton("Add Ingredient");
        JFXButton main = new JFXButton("Main Menu");
        add.setPrefSize(140, 25);
        add.setStyle("-fx-background-color:#2196F3");
        add.setTextFill(Color.WHITE);
        naviBase.setMargin(add, new Insets(-15, 0, 0, 10));
        main.setPrefSize(140, 25);
        main.setStyle("-fx-background-color:#2196F3");
        main.setTextFill(Color.WHITE);
        naviBase.setMargin(main, new Insets(0, 0, 0, 10));
        navi.getChildren().addAll(title, meat, seafood, vegetable, fruit, beans, carbohydrate, dairy, preserved_food, herbs_and_spices, condiments, baking_ingredients, country1);
        naviBase.getChildren().addAll(naviScroll, table, add, main);
        naviBase.setStyle("-fx-background-color:white");
        naviScroll.setStyle("-fx-background-color:transparent");
        naviBase.setMaxWidth(160);
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
            IngredientAddingForm addingForm = new IngredientAddingForm();
            addingForm.addingForm().showAndWait();
            if(table.isSelected()) {
                ingredientTable();
            }
            tilePane.getChildren().clear();
            populateList();
            showIngredient();
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



        searchh.setOnAction(e->{
            if(table.isSelected()) {
                scrollPane.setContent(tableView);
                if ((search.getText()).isEmpty()) {
                    ingredientTable();
                }else{
                    olIngredient.clear();
                    searchIngredient(search.getText());
                }
            }else{
                tilePane.getChildren().clear();
                if ((search.getText()).isEmpty()) {
                    showIngredient();
                } else {
                    search(search.getText());
                }
            }

            searchh.setOnMouseEntered(event -> {
                scene.setCursor(Cursor.HAND);
            });
            searchh.setOnMouseExited(event -> {
                scene.setCursor(Cursor.DEFAULT);
            });

        });







        //Scroll Pane
        scrollPane = new ScrollPane();

        scrollPane.setStyle("-fx-background:transparent;" + "-fx-background-color:transparent");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(false);

        tilePane = new TilePane();
        tilePane.setPrefWidth(640);
        tilePane.setMinHeight(520);
        tilePane.setPadding(new Insets(8, 10, 10, 10));
        tilePane.setHgap(8);
        tilePane.setVgap(10);

        showIngredient();
        scrollPane.setContent(tilePane);

        meat.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        seafood.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        vegetable.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        fruit.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        beans.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        carbohydrate.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        dairy.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        preserved_food.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        herbs_and_spices.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        condiments.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        baking_ingredients.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });
        country1.setOnAction(e -> {
            if (table.isSelected()){
                filterIngredientTable(checkBoxSelected(),country1.getValue());
            }else {
                tilePane.getChildren().clear();
                filterIngredient(checkBoxSelected(), country1.getValue());
            }
        });

        table.setOnAction(e->{
            if(table.isSelected()){
                resetCheckboxes();
                ingredientTable();
                if(search.getText()!=null){
                    olIngredient.clear();
                    searchIngredient(search.getText());
                    scrollPane.setContent(tableView);
                }
            }
            else{
                scrollPane.setContent(tilePane);
                if(search.getText()!=null){
                    tilePane.getChildren().clear();
                    search(search.getText());
                }else {
                    showIngredient();
                }

            }
        });

        search.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                if (table.isSelected()) {
                    scrollPane.setContent(tableView);
                    if ((search.getText()).isEmpty()) {
                        ingredientTable();
                    } else {
                        olIngredient.clear();
                        searchIngredient(search.getText());
                    }
                } else {
                    tilePane.getChildren().clear();
                    if ((search.getText()).isEmpty()) {
                        showIngredient();
                    } else {
                        search(search.getText());
                    }

                }
            }
            });


            stackPane.getChildren().add(contentPane);
        contentPane.setTop(header);
        contentPane.setLeft(naviBase);
        contentPane.setCenter(scrollPane);

        scene = new Scene(stackPane, 810, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ingredient");
        primaryStage.sizeToScene();


        return primaryStage;


        //checkBoxSelected();
    }


    public void populateList() {
        Main main = new Main();
        File file = new File(main.userPaths + "/Ingredient.txt");
        String[] data = new String[10];
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
                ingredients.clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    data[i] = scanner.nextLine();
                    i++;
                    if (i == 10) {
                        ingredients.add(new IngredientClass(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), Integer.parseInt(data[6]), data[7], data[8], data[9]));
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

    public void showIngredient() {
        String nama = "";
        ImageView item;
        int i = 0;
        if (!ingredients.isEmpty()) {
            name.clear();
            for (IngredientClass ingredientClass : ingredients) {
                nama = ingredientClass.getIngredientBrand() + " " + ingredientClass.getIngredientName();
                name.add(nama);

                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!ingredientClass.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///" + ingredientClass.getImageURL()));
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

                Label names = new Label(nama);
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
                    alert.setContentText("Are you sure you want to delete this ingredient ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteIngredient(ingredients, Integer.parseInt(ids));
                        tilePane.getChildren().clear();
                        populateList();
                        showIngredient();
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
                    IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                    editIngredient editIngredient = new editIngredient();
                    editIngredient.editForm(Integer.parseInt(ids), ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL()).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showIngredient();
                    resetCheckboxes();
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
                    IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                    viewIngredient viewIngredient = new viewIngredient();
                    viewIngredient.viewForm(ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL(), ingredientClass1.getIngredientKeepPlace()).show();
                });


                tilePane.getChildren().add(equip);


            }


        }
    }

    public void deleteIngredient(ArrayList<IngredientClass> list, int index) {
        try {
            File inputFile = new File(main.userPaths + "/Ingredient.txt");
            File outputFile = new File(main.userPaths + "/tmpIngredient.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 10;
            int j = i - 10 + 1;
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

    public void editIngredient(int index, String type, String name, String brands, String country, String measureType, String measure, String quantity, String curDate, String exp, String urlsss) {
        String[] data = new String[10];
        data[0] = type;
        data[1] = name;
        data[2] = brands;
        data[3] = country;
        data[4] = measureType;
        data[5] = measure;
        data[6] = quantity;
        data[7] = curDate;
        data[8] = exp;
        data[9] = urlsss;

        try {
            File inputFile = new File(main.userPaths + "/Ingredient.txt");
            File outputFile = new File(main.userPaths + "/tmpIngredient.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 10;
            int j = i - 10 + 1;
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

    public void filterIngredient(String type, String country) {
        ImageView item;

        if (type.equals("") && country.equals("None")) {
            showIngredient();
        } else {
            if (country.equals("None")) {
                country = "";
            }
            int i = 0;
            for (IngredientClass ingredientClass : ingredients) {
                if ((type.contains(ingredientClass.getIngredientType()) || ingredientClass.getIngredientType().contains(type)) && ingredientClass.getIngredientCountry().contains(country)) {
                    ImageView delete = new ImageView(new Image("delete.png"));
                    delete.setFitWidth(20);
                    delete.setFitHeight(20);
                    ImageView edit = new ImageView(new Image("edit.png"));
                    edit.setFitWidth(20);
                    edit.setFitHeight(20);


                    if (!ingredientClass.getImageURL().equals("null")) {
                        item = new ImageView(new Image("file:///" + ingredientClass.getImageURL()));
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
                        alert.setContentText("Are you sure you want to delete this ingredient ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            final Node source = (Node) e.getSource();
                            String ids = source.getId();
                            deleteIngredient(ingredients, Integer.parseInt(ids));
                            tilePane.getChildren().clear();
                            populateList();
                            showIngredient();
                            resetCheckboxes();
                            alert.close();
                        }

                    });

                    edit1.setOnAction(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();

                        IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                        editIngredient editIngredient1 = new editIngredient();
                        editIngredient1.editForm(Integer.parseInt(ids), ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL()).showAndWait();
                        tilePane.getChildren().clear();
                        populateList();
                        showIngredient();
                        resetCheckboxes();
                    });

                    i = i + 1;

                    equip.setOnMouseClicked(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                        viewIngredient viewIngredient = new viewIngredient();
                        viewIngredient.viewForm(ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL(), ingredientClass1.getIngredientKeepPlace()).show();

                    });

                    tilePane.getChildren().add(equip);


                } else {
                    i++;
                }

            }


        }
    }

    public String checkBoxSelected() {
        String[] check = new String[11];

        Arrays.fill(check, " ");
        if (meat.isSelected()) {
            check[0] = "Meat";
        }
        if (seafood.isSelected()) {
            check[1] = "Seafood";
        }
        if (vegetable.isSelected()) {
            check[2] = "Vegetable";
        }
        if (fruit.isSelected()) {
            check[3] = "Fruit";
        }
        if (beans.isSelected()) {
            check[4] = "Beans";
        }
        if (carbohydrate.isSelected()) {
            check[5] = "Carbohydrate";
        }
        if (dairy.isSelected()) {
            check[6] = "Dairy";
        }
        if (preserved_food.isSelected()) {
            check[7] = "Preserved Food";
        }
        if (herbs_and_spices.isSelected()) {
            check[8] = "Herbs & Spices";
        }
        if (condiments.isSelected()) {
            check[9] = "Condiments";
        }
        if (baking_ingredients.isSelected()) {
            check[0] = "Baking Ingredient";
        }


        String filter = "";
        for (int i = 0; i < check.length; i++) {
            if (!check[i].equals(" ")) {
                filter = filter + check[i] + " ";
            }
        }
        return filter;
    }

    public void resetCheckboxes() {
        meat.setSelected(false);
        seafood.setSelected(false);
        vegetable.setSelected(false);
        fruit.setSelected(false);
        beans.setSelected(false);
        carbohydrate.setSelected(false);
        dairy.setSelected(false);
        preserved_food.setSelected(false);
        herbs_and_spices.setSelected(false);
        condiments.setSelected(false);
        baking_ingredients.setSelected(false);
        country1.setValue("None");
    }

    public void search(String keyword) {
        int i = 0;
        ImageView item;
        for (IngredientClass ingredientClass : ingredients) {
            String name = ingredientClass.getIngredientBrand() + " " + ingredientClass.getIngredientName();
            String nama = name.toLowerCase();
            if (nama.contains(keyword.toLowerCase())) {
                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!ingredientClass.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///" + ingredientClass.getImageURL()));
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
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    deleteIngredient(ingredients, Integer.parseInt(ids));
                    tilePane.getChildren().clear();
                    populateList();
                    showIngredient();
                    resetCheckboxes();


                });

                edit1.setOnAction(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                    editIngredient editIngredient = new editIngredient();
                    editIngredient.editForm(Integer.parseInt(ids), ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL()).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showIngredient();
                    resetCheckboxes();
                });

                i = i + 1;

                equip.setOnMouseClicked(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    IngredientClass ingredientClass1 = ingredients.get(Integer.parseInt(ids));
                    viewIngredient viewIngredient = new viewIngredient();
                    viewIngredient.viewForm(ingredientClass1.getIngredientType(), ingredientClass1.getIngredientName(), ingredientClass1.getIngredientBrand(), ingredientClass1.getIngredientCountry(), ingredientClass1.getIngredientMeasureType(), Double.toString(ingredientClass1.getIngredientMeasure()), Integer.toString(ingredientClass1.getIngredientQuantity()), ingredientClass1.getCurDate(), ingredientClass1.getExpDate(), ingredientClass1.getImageURL(), ingredientClass1.getIngredientKeepPlace()).show();

                });

                tilePane.getChildren().add(equip);


            } else {
                i++;
            }

        }
    }

    public void ingredientTable(){
        tableView.getColumns().clear();
        olIngredient.clear();
        populateList();
        TableColumn brandCol = new TableColumn("Brand");
        TableColumn modelCol = new TableColumn("Name");
        TableColumn typeCol  = new TableColumn("Type");
        TableColumn countryCol = new TableColumn("Country");
        TableColumn measurementCol = new TableColumn("Measurement");
        TableColumn unitCol = new TableColumn("Unit");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn dateCol = new TableColumn("Date Added");
        TableColumn expiryCol = new TableColumn("Expiry Date");
        TableColumn imgURLCol = new TableColumn("View Image");
        TableColumn editCol = new TableColumn("Edit");
        TableColumn deleteCol = new TableColumn("Delete");

        brandCol.setMinWidth(120);
        brandCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientBrand"));
        brandCol.getStyleClass().add("brand");

        modelCol.setMinWidth(120);
        modelCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientName"));
        modelCol.getStyleClass().add("model");

        typeCol.setMinWidth(150);
        typeCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientType"));

        countryCol.setMinWidth(120);
        countryCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientCountry"));

        measurementCol.setMinWidth(100);
        measurementCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientMeasure"));

        unitCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("ingredientMeasureType"));

        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,Integer>("ingredientQuantity"));

        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("curDate"));

        expiryCol.setMinWidth(100);
        expiryCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,String>("expDate"));

        imgURLCol.setMinWidth(100);
        imgURLCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,JFXButton>("view"));

        editCol.setMinWidth(100);
        editCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,JFXButton>("edit"));

        deleteCol.setMinWidth(100);
        deleteCol.setCellValueFactory(new PropertyValueFactory<IngredientClass,JFXButton>("delete"));

        if(!ingredients.isEmpty()){
            olIngredient.clear();
            int i=0;
            for(IngredientClass ingredientClass:ingredients){
                int id=i;
                olIngredient.add(ingredientClass);
                ingredientClass.getView().setOnAction(e->{
                    viewImageEquipment viewImage = new viewImageEquipment();
                    viewImage.viewImage(ingredientClass.getImageURL()).show();
                });
                ingredientClass.getEdit().setOnAction(e->{
                    editIngredient editIngredient = new editIngredient();
                    editIngredient.editForm(id, ingredientClass.getIngredientType(), ingredientClass.getIngredientName(), ingredientClass.getIngredientBrand(), ingredientClass.getIngredientCountry(), ingredientClass.getIngredientMeasureType(), Double.toString(ingredientClass.getIngredientMeasure()), Integer.toString(ingredientClass.getIngredientQuantity()), ingredientClass.getCurDate(), ingredientClass.getExpDate(), ingredientClass.getImageURL()).showAndWait();
                    olIngredient.clear();
                    populateList();
                    ingredientTable();
                    tilePane.getChildren().clear();
                    showIngredient();
                    resetCheckboxes();
                });
                ingredientClass.getDelete().setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this ingredient ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get()==ButtonType.OK){
                        deleteIngredient(ingredients,id);
                        olIngredient.clear();
                        populateList();
                        ingredientTable();
                        tilePane.getChildren().clear();
                        showIngredient();
                        resetCheckboxes();}
                    else{
                        alert.close();}
                });
                i++;
            }
            tableView.setItems(olIngredient);
        }
        else{
            Label placeholder = new Label("Ingredient is empty");
            tableView.setPlaceholder(placeholder);
        }
        tableView.getColumns().addAll(brandCol,modelCol,typeCol,countryCol,measurementCol,unitCol,quantityCol,dateCol,expiryCol,imgURLCol,editCol,deleteCol);
        tableView.setPrefWidth(640);
        tableView.setMinHeight(520);
        tableView.setPadding(new Insets(20,20,20,20));
        tableView.setOnMouseClicked(e->{
            if(e.getClickCount()==2){
                tableView.getSelectionModel().clearSelection();
            }
        });
        URL url = this.getClass().getResource("tableviewCSS.css");
        if (url == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("System Error");
            alert.setContentText("Error occurred, Aborting !");
            alert.showAndWait();
            System.exit(-1);
        }
        else{
            String css = url.toExternalForm();
            scene.getStylesheets().add(css);
        }

        scrollPane.setContent(tableView);
    }

    public void filterIngredientTable(String type, String country){
        if (type.equals("")&&country.equals("None")) {
            ingredientTable();
        } else {
            if (country.equals("None")) {
                country = "";
            }
            olIngredient.clear();
            int i=0;
            for(IngredientClass ingredientClass:ingredients) {
                if ((type.contains(ingredientClass.getIngredientType()) || ingredientClass.getIngredientType().contains(type)) && ingredientClass.getIngredientCountry().contains(country)) {
                    olIngredient.add(ingredientClass);
                    int id = i;

                    ingredientClass.getEdit().setId(Integer.toString(id));
                    ingredientClass.getDelete().setId(Integer.toString(id));
                    ingredientClass.getView().setId(Integer.toString(id));
                    ingredientClass.getView().setOnAction(e -> {
                        viewImageEquipment viewImage = new viewImageEquipment();
                        viewImage.viewImage(ingredientClass.getImageURL()).show();
                    });
                    ingredientClass.getEdit().setOnAction(e -> {
                        editEquipment editEquipment = new editEquipment();
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        editIngredient editIngredient = new editIngredient();
                        editIngredient.editForm(id, ingredientClass.getIngredientType(), ingredientClass.getIngredientName(), ingredientClass.getIngredientBrand(), ingredientClass.getIngredientCountry(), ingredientClass.getIngredientMeasureType(), Double.toString(ingredientClass.getIngredientMeasure()), Integer.toString(ingredientClass.getIngredientQuantity()), ingredientClass.getCurDate(), ingredientClass.getExpDate(), ingredientClass.getImageURL()).showAndWait();
                        olIngredient.clear();
                        populateList();
                        ingredientTable();
                        tilePane.getChildren().clear();
                        showIngredient();
                        resetCheckboxes();
                    });
                    ingredientClass.getDelete().setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete this ingredient ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            final Node source = (Node) e.getSource();
                            String ids = source.getId();
                            deleteIngredient(ingredients, Integer.parseInt(ids));
                            olIngredient.clear();
                            populateList();
                            ingredientTable();
                            tilePane.getChildren().clear();
                            showIngredient();
                            resetCheckboxes();
                        } else {
                            alert.close();
                        }
                    });
                    tableView.setItems(olIngredient);
                }
                i++;}}
    }

    public void searchIngredient(String keyword){
        int i=0;
        for(IngredientClass ingredientClass:ingredients) {
            String name=ingredientClass.getIngredientBrand()+" "+ingredientClass.getIngredientName();
            String nama= name.toLowerCase();

            if(nama.contains(keyword.toLowerCase())) {
               olIngredient.add(ingredientClass);
                int id = i;
                ingredientClass.getEdit().setId(Integer.toString(id));
                ingredientClass.getDelete().setId(Integer.toString(id));
                ingredientClass.getView().setId(Integer.toString(id));
                ingredientClass.getView().setOnAction(e -> {
                    viewImageEquipment viewImage = new viewImageEquipment();
                    viewImage.viewImage(ingredientClass.getImageURL()).show();
                });
                ingredientClass.getEdit().setOnAction(e -> {
                    editEquipment editEquipment = new editEquipment();
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    editIngredient editIngredient = new editIngredient();
                    editIngredient.editForm(id, ingredientClass.getIngredientType(), ingredientClass.getIngredientName(), ingredientClass.getIngredientBrand(), ingredientClass.getIngredientCountry(), ingredientClass.getIngredientMeasureType(), Double.toString(ingredientClass.getIngredientMeasure()), Integer.toString(ingredientClass.getIngredientQuantity()), ingredientClass.getCurDate(), ingredientClass.getExpDate(), ingredientClass.getImageURL()).showAndWait();
                    olIngredient.clear();
                    populateList();
                    ingredientTable();
                    tilePane.getChildren().clear();
                    showIngredient();
                    resetCheckboxes();
                });
                ingredientClass.getDelete().setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this ingredient ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteIngredient(ingredients, Integer.parseInt(ids));
                        olIngredient.clear();
                        populateList();
                        ingredientTable();
                        tilePane.getChildren().clear();
                        showIngredient();
                        resetCheckboxes();
                    } else {
                        alert.close();
                    }
                });
                tableView.setItems(olIngredient);
            }
            i++;}}
    }


