import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class expiryNotice {

    public Stage  expiryNotices(ArrayList<String> expired){
        Stage primaryStage = new Stage();

        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();

        Label header = new Label("The following ingredient(s) has/have expired, please discard as soon as possible.");
        header.setFont(new Font("Agency FB",20));
        header.setWrapText(true);

        JFXButton button = new JFXButton("Close");
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #2196F3;");
        button.setOnAction(e->{
            primaryStage.close();
        });

        for (String name:expired
             ) {
            Label exp = new Label(name);
            exp.setFont(new Font("Agency FB",18));
            vBox.getChildren().add(exp);

        }


        scrollPane.setContent(vBox);
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(header);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(button);
        borderPane.setMargin(button,new Insets(15,0,0,280));

        Scene scene = new Scene(borderPane,350,350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expiry Notice");
        return primaryStage;








    }
}
