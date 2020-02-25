package io.github.keepitclean.gui;

import io.github.keepitclean.Game;
import io.github.keepitclean.model.Player;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;

@Getter
public class InfoBox {
    private final Label moneyAmount = new Label();
    private final ProgressBar energyBar = new ProgressBar();
    private final ProgressBar pollutionBar = new ProgressBar();
    private final VBox infoBox;

    public InfoBox(Player player) {
        this.infoBox = getStats(player);
    }

    /**
     * Create a stats display for the player
     *
     * @param player - The player to create a stat window for
     * @return - The VBox node with all stat information
     */
    private VBox getStats(Player player) {
        Font font = new Font(20);

        // Create the money field
        Label moneyLabel = new Label("Money");
        moneyAmount.setText("$" + player.getMoney());
        moneyLabel.setFont(font);
        moneyAmount.setFont(font);
        VBox moneyBox = new VBox(moneyLabel, moneyAmount);
        moneyBox.setId("moneyBox");

        // Create the energy field
        Label energyLabel = new Label("Energy");
        energyLabel.setFont(font);
        energyBar.setProgress((double) player.getEnergy() / 100);
        energyBar.setPrefSize(150, 35);
        VBox energyBox = new VBox(energyLabel, energyBar);
        energyBox.setId("energyBox");

        // Create the pollution field
        Label pollutionLabel = new Label("Pollution");
        pollutionLabel.setFont(font);
        pollutionBar.setProgress((double) Game.getInstance().getPollutionBar() / 100);
        pollutionBar.setPrefSize(150, 35);
        VBox environmentBox = new VBox(pollutionLabel, pollutionBar);
        environmentBox.setId("pollutionBox");

        VBox infoBox = new VBox(moneyBox, energyBox, environmentBox);
        infoBox.setSpacing(15);
        return infoBox;
    }

    /**
     * Update the stat window with the current information
     */
    public void updateStats() {
        for (Node c : infoBox.getChildren()) {
            switch (c.getId()) {
                case "moneyBox":
                    ((Label) ((VBox) c).getChildren().get(1)).setText("$" + Game.getInstance().getPlayer().getMoney());
                    break;
                case "energyBox":
                    ((ProgressBar) ((VBox) c).getChildren().get(1)).setProgress((double) Game.getInstance().getPlayer().getEnergy() / 100);
                    break;
                case "pollutionBox":
                    ((ProgressBar) ((VBox) c).getChildren().get(1)).setProgress((double) Game.getInstance().getPollutionBar() / 100);
                    break;
            }
        }
    }
}
