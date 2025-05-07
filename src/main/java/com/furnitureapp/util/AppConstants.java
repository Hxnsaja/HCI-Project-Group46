package com.furnitureapp.util;

import java.awt.Color;

/**
 * Constants used throughout the application
 */
public class AppConstants {
    // Application information
    public static final String APP_NAME = "Furniture Designer Pro";
    public static final String APP_VERSION = "2.0.0";
    public static final String APP_AUTHOR = "Design by Group 64";
    
    // Default room dimensions
    public static final int DEFAULT_ROOM_WIDTH = 500;
    public static final int DEFAULT_ROOM_LENGTH = 400;
    public static final int DEFAULT_ROOM_HEIGHT = 250;
    
    // UI Constants
    public static final int PADDING_SMALL = 5;
    public static final int PADDING_MEDIUM = 10;
    public static final int PADDING_LARGE = 20;
    
    // Color palette
    public static final Color PRIMARY_COLOR = new Color(34, 139, 34);       // Forest Green
    public static final Color PRIMARY_DARK_COLOR = new Color(0, 100, 0);    // Dark Green
    public static final Color PRIMARY_LIGHT_COLOR = new Color(144, 238, 144); // Light Green
    public static final Color ACCENT_COLOR = new Color(255, 215, 0);        // Gold (keeping as accent)
    public static final Color TEXT_PRIMARY_COLOR = new Color(33, 33, 33);
    public static final Color TEXT_SECONDARY_COLOR = new Color(117, 117, 117);
    
    // Default furniture colors
    public static final Color[] FURNITURE_COLORS = {
        new Color(139, 69, 19),    // Brown
        new Color(160, 82, 45),    // Sienna
        new Color(210, 180, 140),  // Tan
        new Color(120, 80, 40),    // Dark Brown
        new Color(218, 165, 32),   // Gold
        new Color(102, 51, 153),   // Purple
        new Color(34, 139, 34),    // Forest Green
        new Color(178, 34, 34)     // Firebrick Red
    };
    
    // Authentication
    public static final String VALID_USERNAME = "admin";
    public static final String VALID_PASSWORD = "Test@123";
    
    // File extensions
    public static final String DESIGN_FILE_EXTENSION = ".design";
    public static final String EXPORT_IMAGE_EXTENSION = ".png";
    
    // Directories
    public static final String DESIGNS_DIRECTORY = "designs";
    public static final String EXPORTS_DIRECTORY = "exports";
}
