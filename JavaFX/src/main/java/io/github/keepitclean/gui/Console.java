package io.github.keepitclean.gui;

import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;

public class Console {
    @Getter
    private static ScrollPane scrollPane;

    /**
     * Initialize the Console
     */
    public static void initialize() {
        Font font = new Font(18);
        // Create the scrollable field
        scrollPane = new ScrollPane();
        // Add the textfield and set its size
        Text text = new Text();
        text.setWrappingWidth(950);
        text.setLineSpacing(5);
        text.setFont(font);
        // Add the text field to the scroll pane
        scrollPane.setContent(text);
    }

    /**
     * Prints a string in the console.
     * Supports \n as linebreak
     *
     * @param s - The string to print in the console
     */
    public static void println(String s) {
        // Check if the console currently is scrolled all the way down
        boolean down = scrollPane.getVvalue() == 0.0 || scrollPane.getVvalue() == scrollPane.getVmax();
        Text text = (Text) scrollPane.getContent();
        text.setText(text.getText() + s + "\n");
        // If the user was scrolled down prier to the new message, then automatically scroll down
        if (down) {
            scrollPane.setVvalue(scrollPane.getVmax());
        }
    }
}
