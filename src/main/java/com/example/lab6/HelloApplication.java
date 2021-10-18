package com.example.lab6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setMaximized(true);

        //Menu
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        MenuItem image1 = new MenuItem("Image 1");
        MenuItem image2 = new MenuItem("Image 2");
        MenuItem image3 = new MenuItem("Image 3");
        MenuItem reset = new MenuItem("Réinitialiser");
        Menu chargerImage = new Menu("Charger une image");
        fichiers.getItems().add(chargerImage);
        chargerImage.getItems().addAll(image1, image2, image3);
        actions.getItems().add(reset);
        MenuItem img1 = new MenuItem("Image 1");
        MenuItem img2 = new MenuItem("Image 2");
        MenuItem img3 = new MenuItem("Image 3");
        Menu imgLoader = new Menu("Charger une image");
        MenuItem reinit = new MenuItem("Réinitialiser");
        imgLoader.getItems().addAll(img1, img2, img3);
        ContextMenu contextMenu = new ContextMenu(imgLoader, reinit);


        //Image et modifs d'image
        ImageView imageView = new ImageView("file:default.jpg");
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        ColorAdjust colorAdjust = new ColorAdjust();
        Slider luminosite = new Slider(-1, 1, 0);
        Slider contraste = new Slider(-1, 1, 0);
        Slider teinte = new Slider(-1, 1, 0);
        Slider saturation = new Slider(-1, 1, 0);
        Label bright = new Label("Luminosité");
        Label cont = new Label("Contraste");
        Label teint = new Label("Teinte");
        Label sat = new Label("Saturation");
        Label info = new Label("Bienvenue!");
        info.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        info.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));
        Tooltip infoBright = new Tooltip("Rend l'image plus claire ou plus sombre");
        luminosite.setTooltip(infoBright);
        Tooltip infoCont = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        contraste.setTooltip(infoCont);
        Tooltip infoTeinte = new Tooltip("Change la teinte (couleur) de l'image");
        teinte.setTooltip(infoTeinte);
        Tooltip infoSat = new Tooltip("Diminue ou augmente l'intensité des couleurs");
        saturation.setTooltip(infoSat);

        //setOnAction
        image1.setOnAction((ae) -> {
            imageView.setImage(new Image("file:suicide.png"));
            info.setText("Chargement de l'image 1 terminé");
        });
        image2.setOnAction((ae) -> {
            imageView.setImage(new Image("file:Bound1.jpg"));
            info.setText("Chargement de l'image 2 terminé");
        });
        image3.setOnAction((ae) -> {
            imageView.setImage(new Image("file:image0.jpg"));
            info.setText("Chargement de l'image 3 terminé");
        });
        imageView.setEffect(colorAdjust);
        reset.setOnAction((ae) -> {
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            info.setText("Réinitialisation des ajustements de couleurs");
        });
        luminosite.valueProperty().addListener((observable, oldValue, newValue) -> colorAdjust.setBrightness((double) newValue));
        contraste.valueProperty().addListener((observableValue, number, t1) -> colorAdjust.setContrast((double) t1));
        teinte.valueProperty().addListener((observableValue, number, t1) -> colorAdjust.setHue((double) t1));
        saturation.valueProperty().addListener((observableValue, number, t1) -> colorAdjust.setSaturation(t1.doubleValue()));
        img1.setOnAction((ae) -> {
            imageView.setImage(new Image("file:suicide.png"));
            info.setText("Chargement de l'image 1 terminé");
        });
        img2.setOnAction((ae) -> {
            imageView.setImage(new Image("file:Bound1.jpg"));
            info.setText("Chargement de l'image 2 terminé");
        });
        img3.setOnAction((ae) -> {
            imageView.setImage(new Image("file:image0.jpg"));
            info.setText("Chargement de l'image 3 terminé");
        });
        reinit.setOnAction((ae) -> {
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
            info.setText("Réinitialisation des ajustements de couleurs");
        });

        MenuBar menuBar = new MenuBar(fichiers, actions);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        VBox vBox = new VBox(bright, luminosite, cont, contraste, teint, teinte, sat, saturation);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(imageView, vBox);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);
        borderPane.setBottom(info);
        hBox.setOnContextMenuRequested((ae) ->
                contextMenu.show(hBox, ae.getScreenX(), ae.getScreenY())
        );

        Scene scene = new Scene(borderPane);

        vBox.setSpacing(5);
        hBox.setSpacing(30);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}