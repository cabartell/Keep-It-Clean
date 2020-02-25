package io.github.keepitclean;

import io.github.keepitclean.event.Interact;
import io.github.keepitclean.event.Walk;
import io.github.keepitclean.gui.Console;
import io.github.keepitclean.gui.DrawRoom;
import io.github.keepitclean.gui.InfoBox;
import io.github.keepitclean.gui.Inventory;
import io.github.keepitclean.handler.PlayerHandler;
import io.github.keepitclean.model.RoomType;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public final class GameView extends Application {
    private static Stage stage;
    private static StackPane stackPane;
    private static InfoBox stats;
    private static VBox gameBox;

    static void load(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Keep It Clean");
        stage.setWidth(1465);
        stage.setHeight(950);
        stage.getIcons().add(new Image("file:icon.png"));
        GameView.stage = stage;
        getStartScreen();
    }

    /**
     * Display the start screen when the game is started
     */
    private static void getStartScreen() {
        ImageView imageView = new ImageView(new Image("start_screen.png"));
        VBox box = new VBox(imageView);
        final Scene scene = new Scene(box);
        scene.setOnKeyPressed(k -> getGameScene());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Display the game screen
     */
    private static void getGameScene() {
        // Initialize some different elements from the game.
        Inventory.initialize(Game.getInstance().getPlayer());
        PlayerHandler.loadPlayerView(Game.getInstance().getPlayer());
        Console.initialize();

        stats = new InfoBox(Game.getInstance().getPlayer());
        HBox infoBox = new HBox(Console.getScrollPane(), stats.getInfoBox());

        gameBox = new VBox(DrawRoom.drawRoom(Game.getInstance().getPlayer().getCurrentRoom().getRoomType()), infoBox);
        HBox outerBox = new HBox(Inventory.getInventory(), gameBox);
        stackPane = new StackPane(outerBox);

        // Set the events when a key is pressed
        final Scene scene = new Scene(stackPane);
        scene.setOnKeyPressed(k -> {
            onClick(k);
            stats.updateStats();
            DrawRoom.updatePlayerLocation(Game.getInstance().getPlayer());
        });
        DrawRoom.drawRoom(RoomType.BEACH);
        DrawRoom.addPlayer(Game.getInstance().getPlayer());

        //Show the screen
        stage.setScene(scene);
        stage.show();

        Console.println(
            "Welcome to Keep It Clean\n" +
                "\n" +
                "Controls:\n" +
                "Move: WASD\n" +
                "Interact: E\n" +
                "Help menu: H");
    }

    /**
     * Updates the view of the room, and recalculate positions
     *
     * @param roomType - The {@link io.github.keepitclean.model.RoomType} the room have
     */
    public static void updateView(RoomType roomType) {
        gameBox.getChildren().remove(0);
        gameBox.getChildren().add(0, DrawRoom.drawRoom(roomType));
    }

    /**
     * Detect what key is pressed, and use the corresponding events.
     *
     * @param event - The KeyPressed event
     */
    private static void onClick(Event event) {
        if (!(event instanceof KeyEvent)) {
            return;
        }
        // Get the KeyCoe pressed
        KeyCode key = ((KeyEvent) event).getCode();
        if (key != KeyCode.H && stackPane.getChildren().size() != 1) {
            return;
        }
        switch (key) {
            case W:
            case D:
            case S:
            case A:
                new Walk().handle(event);
                break;
            case E:
                new Interact().handle(event);
                break;
            case H:
                toggleHelp();
                break;
        }
    }

    /**
     * Stops the game, and display either a win or lose screen
     *
     * @param won - If the game is won or lost
     */
    public static void stopGame(boolean won) {
        Image img = won ? new Image("win_screen.png") : new Image("lose_screen.png");
        VBox box = new VBox();
        box.setBackground(new Background(new BackgroundImage(img, null, null, null, null)));
        final Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Either show or hide the help screen
     */
    private static void toggleHelp() {
        if (stackPane.getChildren().size() == 1) {
            ImageView img = new ImageView(new Image("help_screen.png"));
            stackPane.getChildren().add(img);
        } else {
            stackPane.getChildren().remove(1);
        }
    }
}
