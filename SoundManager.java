package AMS;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SoundManager {
    
    private static Media clickMedia;
    private static boolean soundsEnabled = true;
    
    /**
     * Initialize and load the click sound
     * Call this ONCE when the application starts
     */
    public static void initialize() {
        try {
            // Load click sound from resources folder
            File soundFile = new File("src/AMS/Resources/click.mp3");
            
            System.out.println("Looking for sound at: " + soundFile.getAbsolutePath());
            System.out.println("File exists: " + soundFile.exists());
            
            if (soundFile.exists()) {
                clickMedia = new Media(soundFile.toURI().toString());
                System.out.println("Click sound loaded successfully!");
            } else {
                System.out.println("Warning: click.mp3 not found at " + soundFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Error loading click sound: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Play the click sound
     * Call this whenever a button is clicked
     */
    public static void playClick() {
        if (soundsEnabled && clickMedia != null) {
            // Create a new MediaPlayer each time to allow multiple simultaneous plays
            MediaPlayer player = new MediaPlayer(clickMedia);
            player.setVolume(1); // 30% volume
            player.setOnEndOfMedia(() -> player.dispose()); // Clean up when done
            player.play();
        }
    }
    
    /**
     * Enable or disable sounds
     */
    public static void setSoundsEnabled(boolean enabled) {
        soundsEnabled = enabled;
    }
    
    public static boolean isSoundsEnabled() {
        return soundsEnabled;
    }
}