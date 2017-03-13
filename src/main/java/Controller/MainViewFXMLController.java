package Controller;

import Model.Xkcd;
import Utils.Services;
import Utils.XkcdServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Milos on 3/10/2017.
 * IT255-DZ03
 * Tiny client for accessing xkcd.com.
 */
public class MainViewFXMLController implements Initializable {
    private Services services;

    private Xkcd xkcd;
    private Xkcd xkcdNew;
    private Xkcd xkcdTemp;

    @FXML
    private Tooltip tooltip;

    @FXML
    private ImageView imageView;

    @FXML
    private Button buttonPrevious;

    @FXML
    private Button buttonRandom;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonNewest;

    @FXML
    private Label labelName;

    @FXML
    private void showNextImage() {
        xkcd = (Xkcd) services.methodGetSpecific(xkcdTemp.getNum() + 1);
        if (xkcdTemp.getNum() == 0) {
            xkcd = (Xkcd) services.methodGetSpecific(xkcdTemp.getNum() + 2);
        }
        showImageTemplate(xkcd);
    }

    @FXML
    private void showPreviousImage() {
        xkcd = (Xkcd) services.methodGetSpecific(xkcdTemp.getNum() - 1);
        if (xkcdTemp.getNum() == 0) {
            xkcd = (Xkcd) services.methodGetSpecific(xkcdTemp.getNum() - 2);
        }
        showImageTemplate(xkcd);
    }

    @FXML
    private void showRandomImage() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(xkcdNew.getNum()) + 1;
        xkcd = (Xkcd) services.methodGetSpecific(randomNumber);
        if (xkcd.getNum() == 0) {
            randomNumber = randomGenerator.nextInt(xkcdNew.getNum()) + 1;
            xkcd = (Xkcd) services.methodGetSpecific(randomNumber);
        }
        showImageTemplate(xkcd);
    }

    private void showImageTemplate(Xkcd xkcd) {
        try {
            Platform.runLater(() -> {
                setButton();
                imageView.setImage(new Image(xkcd.getImg()));
                labelName.setText(xkcd.getTitle());
                tooltip.setText(xkcd.getAlt());
            });
            xkcdTemp = this.xkcd;
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showNewestImage() {
        try {
            Platform.runLater(() -> {
                setButton();
                imageView.setImage(new Image(xkcdNew.getImg()));
                labelName.setText(xkcdNew.getTitle());
                tooltip.setText(xkcdNew.getAlt());
            });
            xkcdTemp = xkcdNew;
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setButton() {
        if (xkcdTemp.getNum() == xkcdNew.getNum()) {
            buttonNext.setDisable(true);
        } else if (xkcdTemp.getNum() == 1) {
            buttonPrevious.setDisable(true);
        } else {
            buttonNext.setDisable(false);
            buttonPrevious.setDisable(false);
        }
    }

    private void startTaskNext() {
        Runnable runnable = this::showNextImage;

        Thread backgroundThread = new Thread(runnable);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    private void startTaskPrevious() {
        Runnable runnable = this::showPreviousImage;

        Thread backgroundThread = new Thread(runnable);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    private void startTaskNewest() {
        Runnable runnable = this::showNewestImage;

        Thread backgroundThread = new Thread(runnable);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    private void startTaskRandom() {
        Runnable runnable = this::showRandomImage;

        Thread backgroundThread = new Thread(runnable);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        services = new XkcdServices();
        xkcdNew = (Xkcd) services.methodGetNewest();
        startTaskNewest();

        buttonNext.setOnAction(event -> startTaskNext());

        buttonPrevious.setOnAction(event -> startTaskPrevious());

        buttonRandom.setOnAction(event -> startTaskRandom());

        buttonNewest.setOnAction(event -> startTaskNewest());
    }
}
