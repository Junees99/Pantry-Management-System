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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Equipment {
    Main main = new Main();
    ArrayList<EquipmentClass> equipments = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    TilePane tilePane;
    ObservableList<EquipmentClass> olEquipment = FXCollections.observableArrayList();
    TableView<EquipmentClass> tableView = new TableView<EquipmentClass>();
    ScrollPane scrollPane;
    JFXToggleButton table;

    JFXCheckBox skillet = new JFXCheckBox("Skillet");
    JFXCheckBox blender = new JFXCheckBox("Blender");
    JFXCheckBox deepDryer = new JFXCheckBox("Deep Fryer");
    JFXCheckBox foodProcessor = new JFXCheckBox("Food Processor");
    JFXCheckBox griller = new JFXCheckBox("Griller");
    JFXCheckBox mixer = new JFXCheckBox("Mixer");
    JFXCheckBox oven = new JFXCheckBox("Oven");
    JFXCheckBox roastPan = new JFXCheckBox("Roast Pan");
    JFXCheckBox sauceSpot = new JFXCheckBox("Sauce Pot");
    JFXCheckBox bakeware = new JFXCheckBox("Bakeware");

    Scene scene;


    public Stage equipmentPage (){
        Stage primaryStage = new Stage();
        try {
            populateList();
        }catch (NumberFormatException e){
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Data Corrupted");
                alert.setContentText("Equipment Data is tempered. Data has been lost, please restart the program");
                alert.showAndWait();
                PrintWriter pw = new PrintWriter(new Main().userPaths+"/Equipment.txt");
                pw.close();
                System.exit(-1);
            }catch (IOException ie){
                System.exit(-1);
            }

        }


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
        header.setPadding(new Insets(10,0,0,20));
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
        searchh.setPrefSize(21,24.5);
        searchh.setTranslateX(10);
        searchh.setTranslateY(20);
        searchh.setStyle("-fx-background-color:#2196F3;"+"-fx-background-radius:0;");

        Text heading = new Text("Equipment");
        heading.setFont(new Font("agency fb",40));
        heading.setTranslateX(37);
        heading.setTranslateY(8);
        heading.setFill(Color.WHITE);

        header.getChildren().addAll(logo,search,searchh,heading);

        //Navi
        navi.setPadding(new Insets(20,10,2,20));
        navi.setSpacing(10);
        navi.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        navi.setPrefWidth(160);

        Text title = new Text("Filter");
        title.setFont(new Font("agency fb",25));
        title.setFill(Color.BLACK);
        navi.setMargin(title,new Insets(0,0,10,0));



        search.setOnKeyPressed(e->{
            if(table.isSelected()&&e.getCode().equals(KeyCode.ENTER)){
                scrollPane.setContent(tableView);
                if ((search.getText()).isEmpty()) {
                    equipmentTable();
                }else{
                    olEquipment.clear();
                    searchEquipment(search.getText());
                }
            }else {
                if (e.getCode().equals(KeyCode.ENTER)) {
                    tilePane.getChildren().clear();
                    if ((search.getText()).isEmpty()) {
                        showEquipment();
                    } else {
                        search(search.getText());
                    }

                }
            }
        });

        search.setOnMouseClicked(e->{
            search.setText("");
        });
        searchh.setOnAction(e->{
            if(table.isSelected()) {
                scrollPane.setContent(tableView);
                if ((search.getText()).isEmpty()) {
                    equipmentTable();
                }else{
                    olEquipment.clear();
                    searchEquipment(search.getText());
                }
            }else{
                tilePane.getChildren().clear();
                if ((search.getText()).isEmpty()) {
                    showEquipment();
                } else {
                    search(search.getText());
                }
            }


        });
        searchh.setOnMouseEntered(event -> {
            scene.setCursor(Cursor.HAND);
        });
        searchh.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });






        skillet.setFont(new Font("Roboto",14));
        blender.setFont(new Font("Roboto",14));
        deepDryer.setFont(new Font("Roboto",14));
        foodProcessor.setFont(new Font("Roboto",14));
        griller.setFont(new Font("Roboto",14));
        mixer.setFont(new Font("Roboto",14));
        oven.setFont(new Font("Roboto",14));
        roastPan.setFont(new Font("Roboto",14));
        sauceSpot.setFont(new Font("Roboto",14));
        bakeware.setFont(new Font("Roboto",14));


        table = new JFXToggleButton();
        table.setText("Table View");
        navi.setMargin(table,new Insets(0,0,0,-15));
        table.setFont(new Font("Roboto",13));

        table.setOnAction(e->{
            if(table.isSelected()){
                resetCheckboxes();
                equipmentTable();
                if(search.getText()!=null){
                    olEquipment.clear();
                    searchEquipment(search.getText());
                    scrollPane.setContent(tableView);
                }
            }
            else{
                scrollPane.setContent(tilePane);
                if(search.getText()!=null){
                    tilePane.getChildren().clear();
                    search(search.getText());
                }
                else
                    showEquipment();
            }
        });

        JFXButton add = new JFXButton("Add Equipment");
        JFXButton main = new JFXButton("Main Menu");
        add.setPrefSize(140,25);
        add.setStyle("-fx-background-color:#2196F3");
        add.setTextFill(Color.WHITE);
        navi.setMargin(add,new Insets(7,0,0,-10));
        main.setPrefSize(140,25);
        main.setStyle("-fx-background-color:#2196F3");
        main.setTextFill(Color.WHITE);
        navi.setMargin(main,new Insets(0,0,0,-10));
        navi.getChildren().addAll(title,skillet,blender,deepDryer,foodProcessor,griller,mixer,oven,roastPan,sauceSpot,bakeware,table,add,main);
        main.setOnAction(e->{
            Menu menu = new Menu();
            Main main1 = new Main();
            menu.mainMenu().show();
            main1.centerAlign(menu.welcome);
            main1.centerAlign(menu.what);
            main1.centerAlign(menu.tip);
            menu.bulb1.setLayoutX(menu.tip.getLayoutX() - 50);
            primaryStage.close();
        });
        add.setOnAction(e->{
            AddingForm addingForm = new AddingForm();
            addingForm.addForm().showAndWait();
            if(table.isSelected()){
                equipmentTable();
            }
            tilePane.getChildren().clear();
            populateList();
            showEquipment();
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


        skillet.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        blender.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        bakeware.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        oven.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        mixer.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        deepDryer.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        griller.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        foodProcessor.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        roastPan.setOnAction(e->{
            tilePane.getChildren().clear();
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });
        sauceSpot.setOnAction(e->{
            if(table.isSelected()){
                filterEquipmentTable(checkBoxSelected());
            }
            else{
                tilePane.getChildren().clear();
                filterEquipment(checkBoxSelected());
            }
        });

        //Scroll Pane
         scrollPane = new ScrollPane();

        scrollPane.setStyle("-fx-background:transparent;"+"-fx-background-color:transparent");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);

     tilePane = new TilePane();
        tilePane.setPrefWidth(640);
        tilePane.setMinHeight(520);
        tilePane.setPadding(new Insets(8,10,10,10));
        tilePane.setHgap(8);
        tilePane.setVgap(10);




//
//        for(int i =0; i < 20; i++){
//            ImageView delete = new ImageView(new Image("delete.png"));
//            delete.setFitWidth(20);
//            delete.setFitHeight(20);
//            ImageView edit = new ImageView(new Image("edit.png"));
//            edit.setFitWidth(20);
//            edit.setFitHeight(20);
//            ImageView item = new ImageView(new Image("item.png"));
//            item.setFitHeight(100);
//            item.setFitWidth(100);
//
//            Button edit1 = new Button();
//            edit1.setGraphic(edit);
//            edit1.setPrefSize(58.75,20);
//            edit1.setStyle("-fx-background-color:#2196f3");
//
//
//
//            Button delete1 = new Button();
//            delete1.setGraphic(delete);
//            delete1.setPrefSize(58.75,20);
//            delete1.setStyle("-fx-background-color:#2196f3");
//
//            Label name = new Label("Some Toaster");
//            name.setFont(new Font("agency fb",20));
//            name.setTextFill(Color.BLACK);
//
//            String id = "C"+i;
//            VBox equip = new VBox(10);
//            equip.setPadding(new Insets(10,0,0,0));
//            equip.setMaxSize(147.5,160);
//            equip.setStyle("-fx-background-radius:10;"+"-fx-background-color:white;"+"-fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
//            equip.setId(id);
//            delete1.setId(id);
//            edit1.setId(id);
//            HBox hBox = new HBox(10);
//            hBox.setPadding(new Insets(10));
//            hBox.getChildren().addAll(delete1,edit1);
//            equip.getChildren().addAll(item,name,hBox);
//            equip.setAlignment(Pos.CENTER);
//
//            tilePane.getChildren().add(equip);
//        }

            showEquipment();

        scrollPane.setContent(tilePane);




        stackPane.getChildren().add(contentPane);
        contentPane.setTop(header);
        contentPane.setLeft(navi);
        contentPane.setCenter(scrollPane);

        scene = new Scene(stackPane,810,600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Equipment");
        primaryStage.sizeToScene();






        return primaryStage;
    }



    public void populateList()throws NumberFormatException{
        Main main = new Main();
        File file = new File(main.userPaths+"/Equipment.txt");
        String[] data = new String[9];
        int i =0;
        if(!file.exists()){
            try {
                boolean a = file.createNewFile();
            }catch (IOException ie){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);            }
        }else{
            try{
                equipments.clear();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()){
                    data[i] = scanner.nextLine();
                    i++;
                    if(i==9){
                        equipments.add(new EquipmentClass(data[0],data[1],data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7]),data[8]));
                        i = 0;
                    }
                }
                scanner.close();

            }catch (IOException ie){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("System Error");
                alert.setContentText("Error occurred, Aborting !");
                alert.showAndWait();
                System.exit(-1);
            }


        }
    }
    public void showEquipment(){
        String nama ="";
        ImageView item;
        int i = 0;
        if(!equipments.isEmpty()) {
            name.clear();
            for (EquipmentClass equipment : equipments) {
                nama = equipment.getBrandEquipment() + " " + equipment.getModelEquipment();
                name.add(nama);

                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!equipment.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///"+equipment.getImageURL()));
                    item.setFitHeight(100);
                    item.setFitWidth(100);
                }else {
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

                delete1.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this equipment ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get()==ButtonType.OK){
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    deleteEquipment(equipments,Integer.parseInt(ids));
                    tilePane.getChildren().clear();
                    populateList();
                   showEquipment();
                   resetCheckboxes();}else{
                        alert.close();
                    }

                });
                delete1.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                });
                delete1.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                });

                edit1.setOnAction(e->{
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                    editEquipment editEquipment = new editEquipment();
                    editEquipment.editForm(Integer.parseInt(ids),equipmentClass.getBrandEquipment(),equipmentClass.getModelEquipment(),equipmentClass.getTypeEquipment(),equipmentClass.getColorEquipment(),Integer.toString(equipmentClass.getLengthEquipment()),Integer.toString(equipmentClass.getWidthEquipment()),Integer.toString(equipmentClass.getHeightEquipment()),Integer.toString(equipmentClass.getCapacityEquipment()),equipmentClass.getImageURL()).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showEquipment();
                    resetCheckboxes();
                });

                edit1.setOnMouseEntered(event -> {
                    scene.setCursor(Cursor.HAND);
                });
                edit1.setOnMouseExited(event -> {
                    scene.setCursor(Cursor.DEFAULT);
                });

                i = i + 1;

                equip.setOnMouseClicked(e->{
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                    viewEquipment viewEquipment = new viewEquipment();
                    viewEquipment.viewForm(equipmentClass.getBrandEquipment(),equipmentClass.getModelEquipment(),equipmentClass.getTypeEquipment(),equipmentClass.getColorEquipment(),Integer.toString(equipmentClass.getLengthEquipment()),Integer.toString(equipmentClass.getWidthEquipment()),Integer.toString(equipmentClass.getHeightEquipment()),Integer.toString(equipmentClass.getCapacityEquipment()),equipmentClass.getImageURL()).show();

                });



                tilePane.getChildren().add(equip);




            }






        }
    }
    public void deleteEquipment(ArrayList<EquipmentClass> list, int index) {
                  try {
                File inputFile = new File(main.userPaths+"/Equipment.txt");
                File outputFile = new File(main.userPaths+"/tmpEquipment.txt");

                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 9;
            int j = i - 9 + 1;
            int k = 1;
                while ((currentLine = reader.readLine()) != null){
                    if (k == i){
                        File image = new File(currentLine);
                        boolean b = image.delete();
                    }
                    if (k >= j && k <= i){
                        k++;

                    }else {

                        writer.write(currentLine + System.getProperty("line.separator"));
                        k++;

                    }

                }


            writer.close();
            reader.close();
            boolean a = inputFile.delete();
            boolean s = outputFile.renameTo(inputFile);







        }catch (IOException ie){
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText("System Error");
                      alert.setContentText("Error occurred, Aborting !");
                      alert.showAndWait();
                      System.exit(-1);        }






    }
    public void editEquipment(ArrayList<EquipmentClass> list,int index,String brands, String models, String typess, String colors, String lengths, String widths, String heights, String capacitys, String urlss) {
        String[] data = new String[9];
        data[0] = brands;
        data[1] = models;
        data[2] = typess;
        data[3] = colors;
        data[4] = lengths;
        data[5] = widths;
        data[6] = heights;
        data[7] = capacitys;
        data[8] = urlss;

        try {
            File inputFile = new File(main.userPaths+"/Equipment.txt");
            File outputFile = new File(main.userPaths+"/tmpEquipment.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            index = index + 1;
            String currentLine;

            int i = index * 9;
            int j = i - 9 + 1;
            int k = 1;
            int l = 0;

            while ((currentLine = reader.readLine()) != null){

                if (k >= j && k <= i){
                    writer.write(data[l] + System.getProperty("line.separator"));
                    k++;
                    l++;
                }else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                    k++;

                }

            }
            writer.close();
            reader.close();
            boolean a = inputFile.delete();
            boolean s = outputFile.renameTo(inputFile);


        }catch (IOException ie){

        }
    }
    public void filterEquipment(String type) {
        ImageView item;

        if (type.equals("")) {
            showEquipment();
        } else {
            int i = 0;
            for (EquipmentClass equipment : equipments) {
                if (type.contains(equipment.getTypeEquipment())) {
                    ImageView delete = new ImageView(new Image("delete.png"));
                    delete.setFitWidth(20);
                    delete.setFitHeight(20);
                    ImageView edit = new ImageView(new Image("edit.png"));
                    edit.setFitWidth(20);
                    edit.setFitHeight(20);


                    if (!equipment.getImageURL().equals("null")) {
                        item = new ImageView(new Image("file:///" + equipment.getImageURL()));
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
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteEquipment(equipments, Integer.parseInt(ids));
                        tilePane.getChildren().clear();
                        populateList();
                      showEquipment();
                      resetCheckboxes();

                    });

                    edit1.setOnAction(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                        editEquipment editEquipment = new editEquipment();
                        editEquipment.editForm(Integer.parseInt(ids), equipmentClass.getBrandEquipment(), equipmentClass.getModelEquipment(), equipmentClass.getTypeEquipment(), equipmentClass.getColorEquipment(), Integer.toString(equipmentClass.getLengthEquipment()), Integer.toString(equipmentClass.getWidthEquipment()), Integer.toString(equipmentClass.getHeightEquipment()), Integer.toString(equipmentClass.getCapacityEquipment()), equipmentClass.getImageURL()).showAndWait();
                        tilePane.getChildren().clear();
                        populateList();
                        showEquipment();
                        resetCheckboxes();
                    });

                    i = i + 1;

                    equip.setOnMouseClicked(e -> {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                        viewEquipment viewEquipment = new viewEquipment();
                        viewEquipment.viewForm(equipmentClass.getBrandEquipment(), equipmentClass.getModelEquipment(), equipmentClass.getTypeEquipment(), equipmentClass.getColorEquipment(), Integer.toString(equipmentClass.getLengthEquipment()), Integer.toString(equipmentClass.getWidthEquipment()), Integer.toString(equipmentClass.getHeightEquipment()), Integer.toString(equipmentClass.getCapacityEquipment()), equipmentClass.getImageURL()).show();

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
        if (skillet.isSelected()){
            check[0] = "Skillet";
        }
        if (blender.isSelected()){
            check[1] = "Blender";
        }
        if (deepDryer.isSelected()){
            check[2] = "Deep Fryer" ;
        }
        if (foodProcessor.isSelected()){
            check[3] = "Food Processor";
        }
        if (griller.isSelected()){
            check[4] = "Griller";
        }
        if (mixer.isSelected()){
            check[5] = "Mixer";
        }
        if (oven.isSelected()){
            check[6] = "Oven";
        }
        if (roastPan.isSelected()){
            check[7] = "Roast Pan";
        }
        if (sauceSpot.isSelected()){
            check[8] = "Sauce Pot";
        }
        if (bakeware.isSelected()){
            check[9] = "Bakeware";
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
        skillet.setSelected(false);
        blender.setSelected(false);
        deepDryer.setSelected(false);
        bakeware.setSelected(false);
        oven.setSelected(false);
        mixer.setSelected(false);
        griller.setSelected(false);
        sauceSpot.setSelected(false);
        foodProcessor.setSelected(false);
        roastPan.setSelected(false);
    }
    public void search(String keyword){
        int i = 0;
        ImageView item;
        for (EquipmentClass equipment:equipments) {
            String name = equipment.getBrandEquipment()+" "+equipment.getModelEquipment();
            String nama = name.toLowerCase();
            if(nama.contains(keyword.toLowerCase())){
                ImageView delete = new ImageView(new Image("delete.png"));
                delete.setFitWidth(20);
                delete.setFitHeight(20);
                ImageView edit = new ImageView(new Image("edit.png"));
                edit.setFitWidth(20);
                edit.setFitHeight(20);


                if (!equipment.getImageURL().equals("null")) {
                    item = new ImageView(new Image("file:///" + equipment.getImageURL()));
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
                    deleteEquipment(equipments, Integer.parseInt(ids));
                    tilePane.getChildren().clear();
                    populateList();
                    showEquipment();
                    resetCheckboxes();

                });

                edit1.setOnAction(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                    editEquipment editEquipment = new editEquipment();
                    editEquipment.editForm(Integer.parseInt(ids), equipmentClass.getBrandEquipment(), equipmentClass.getModelEquipment(), equipmentClass.getTypeEquipment(), equipmentClass.getColorEquipment(), Integer.toString(equipmentClass.getLengthEquipment()), Integer.toString(equipmentClass.getWidthEquipment()), Integer.toString(equipmentClass.getHeightEquipment()), Integer.toString(equipmentClass.getCapacityEquipment()), equipmentClass.getImageURL()).showAndWait();
                    tilePane.getChildren().clear();
                    populateList();
                    showEquipment();
                    resetCheckboxes();
                });

                i = i + 1;

                equip.setOnMouseClicked(e -> {
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    EquipmentClass equipmentClass = equipments.get(Integer.parseInt(ids));
                    viewEquipment viewEquipment = new viewEquipment();
                    viewEquipment.viewForm(equipmentClass.getBrandEquipment(), equipmentClass.getModelEquipment(), equipmentClass.getTypeEquipment(), equipmentClass.getColorEquipment(), Integer.toString(equipmentClass.getLengthEquipment()), Integer.toString(equipmentClass.getWidthEquipment()), Integer.toString(equipmentClass.getHeightEquipment()), Integer.toString(equipmentClass.getCapacityEquipment()), equipmentClass.getImageURL()).show();

                });

                tilePane.getChildren().add(equip);



            }else {
                i++;
            }

        }
    }

    public void equipmentTable(){
        tableView.getColumns().clear();
        olEquipment.clear();
        populateList();
        TableColumn brandCol = new TableColumn("Brand");
        TableColumn modelCol = new TableColumn("Model");
        TableColumn typeCol  = new TableColumn("Type");
        TableColumn colorCol = new TableColumn("Color");
        TableColumn lengthCol = new TableColumn("Length (mm)");
        TableColumn widthCol = new TableColumn("Width (mm)");
        TableColumn heightCol = new TableColumn("Height (mm)");
        TableColumn capacityCol = new TableColumn("Capacity (mm)");
        TableColumn imgURLCol = new TableColumn("View Image");
        TableColumn editCol = new TableColumn("Edit");
        TableColumn deleteCol = new TableColumn("Delete");

        brandCol.setMinWidth(120);
        brandCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("brandEquipment"));
        brandCol.getStyleClass().add("brand");

        modelCol.setMinWidth(120);
        modelCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("modelEquipment"));
        modelCol.getStyleClass().add("model");

        typeCol.setMinWidth(150);
        typeCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("typeEquipment"));

        colorCol.setMinWidth(120);
        colorCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("colorEquipment"));

        lengthCol.setMinWidth(100);
        lengthCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("lengthEquipment"));

        widthCol.setMinWidth(100);
        widthCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("widthEquipment"));

        heightCol.setMinWidth(100);
        heightCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("heightEquipment"));

        capacityCol.setMinWidth(100);
        capacityCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,String>("capacityEquipment"));

        imgURLCol.setMinWidth(100);
        imgURLCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,JFXButton>("view"));

        editCol.setMinWidth(100);
        editCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,JFXButton>("edit"));

        deleteCol.setMinWidth(100);
        deleteCol.setCellValueFactory(new PropertyValueFactory<EquipmentClass,JFXButton>("delete"));

        if(!equipments.isEmpty()){
            olEquipment.clear();
            int i=0;
            for(EquipmentClass equipment:equipments){
                int id=i;
                olEquipment.add(equipment);
                equipment.getView().setOnAction(e->{
                    viewImageEquipment viewImage = new viewImageEquipment();
                    viewImage.viewImage(equipment.getImageURL()).show();
                });
                equipment.getEdit().setOnAction(e->{
                    editEquipment editEquipment = new editEquipment();
                    editEquipment.editForm(id,equipment.getBrandEquipment(),equipment.getModelEquipment(),equipment.getTypeEquipment(),equipment.getColorEquipment(),Integer.toString(equipment.getLengthEquipment()),Integer.toString(equipment.getWidthEquipment()),Integer.toString(equipment.getHeightEquipment()),Integer.toString(equipment.getCapacityEquipment()),equipment.getImageURL()).showAndWait();
                    olEquipment.clear();
                    populateList();
                    equipmentTable();
                    tilePane.getChildren().clear();
                    showEquipment();
                    resetCheckboxes();
                });
                equipment.getDelete().setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this equipment ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get()==ButtonType.OK){
                        deleteEquipment(equipments,id);
                        olEquipment.clear();
                        populateList();
                        equipmentTable();
                        tilePane.getChildren().clear();
                        showEquipment();
                        resetCheckboxes();}
                    else{
                        alert.close();}
                });
                i++;
            }
            tableView.setItems(olEquipment);
        }
        else{
            Label placeholder = new Label("Equipment is empty");
            tableView.setPlaceholder(placeholder);
        }
        tableView.getColumns().addAll(brandCol,modelCol,typeCol,colorCol,lengthCol,widthCol,heightCol,capacityCol,imgURLCol,editCol,deleteCol);
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
    public void filterEquipmentTable(String type){
        if (type.equals("")) {
            equipmentTable();
        } else {
            olEquipment.clear();
            int i=0;
            for(EquipmentClass equipment:equipments) {
                if (type.contains(equipment.getTypeEquipment())) {
                    olEquipment.add(equipment);
                    int id = i;
                    equipment.getEdit().setId(Integer.toString(id));
                    equipment.getDelete().setId(Integer.toString(id));
                    equipment.getView().setId(Integer.toString(id));
                    equipment.getView().setOnAction(e -> {
                        viewImageEquipment viewImage = new viewImageEquipment();
                        viewImage.viewImage(equipment.getImageURL()).show();
                    });
                    equipment.getEdit().setOnAction(e -> {
                        editEquipment editEquipment = new editEquipment();
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        editEquipment.editForm(Integer.parseInt(ids), equipment.getBrandEquipment(), equipment.getModelEquipment(), equipment.getTypeEquipment(), equipment.getColorEquipment(), Integer.toString(equipment.getLengthEquipment()), Integer.toString(equipment.getWidthEquipment()), Integer.toString(equipment.getHeightEquipment()), Integer.toString(equipment.getCapacityEquipment()), equipment.getImageURL()).showAndWait();
                        olEquipment.clear();
                        populateList();
                        equipmentTable();
                        resetCheckboxes();
                    });
                    equipment.getDelete().setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete this equipment ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            final Node source = (Node) e.getSource();
                            String ids = source.getId();
                            deleteEquipment(equipments, Integer.parseInt(ids));
                            olEquipment.clear();
                            populateList();
                            equipmentTable();
                            resetCheckboxes();
                        } else {
                            alert.close();
                        }
                    });
                    tableView.setItems(olEquipment);
                }
                i++;
            }}
    }
    public void searchEquipment(String keyword){
        int i=0;
        for(EquipmentClass equipment:equipments){
            String name=equipment.getBrandEquipment()+" "+equipment.getModelEquipment();
            String nama= name.toLowerCase();

            if(nama.contains(keyword.toLowerCase())) {
                olEquipment.add(equipment);
                int id = i;
                equipment.getEdit().setId(Integer.toString(id));
                equipment.getDelete().setId(Integer.toString(id));
                equipment.getView().setId(Integer.toString(id));
                equipment.getView().setOnAction(e -> {
                    viewImageEquipment viewImage = new viewImageEquipment();
                    viewImage.viewImage(equipment.getImageURL()).show();
                });
                equipment.getEdit().setOnAction(e -> {
                    editEquipment editEquipment = new editEquipment();
                    final Node source = (Node) e.getSource();
                    String ids = source.getId();
                    editEquipment.editForm(Integer.parseInt(ids), equipment.getBrandEquipment(), equipment.getModelEquipment(), equipment.getTypeEquipment(), equipment.getColorEquipment(), Integer.toString(equipment.getLengthEquipment()), Integer.toString(equipment.getWidthEquipment()), Integer.toString(equipment.getHeightEquipment()), Integer.toString(equipment.getCapacityEquipment()), equipment.getImageURL()).showAndWait();
                    olEquipment.clear();
                    populateList();
                    equipmentTable();
                    tilePane.getChildren().clear();
                    showEquipment();
                    resetCheckboxes();
                });
                equipment.getDelete().setOnAction(e -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete this equipment ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        final Node source = (Node) e.getSource();
                        String ids = source.getId();
                        deleteEquipment(equipments, Integer.parseInt(ids));
                        olEquipment.clear();
                        populateList();
                        equipmentTable();
                        tilePane.getChildren().clear();
                        showEquipment();
                        resetCheckboxes();
                    } else {
                        alert.close();
                    }
                });tableView.setItems(olEquipment);
            }
            i++;} }

}
