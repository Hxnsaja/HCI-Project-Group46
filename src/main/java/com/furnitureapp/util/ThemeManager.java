package com.furnitureapp.util;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages application themes and UI customization
 */
public class ThemeManager {
    private static final Logger LOGGER = Logger.getLogger(ThemeManager.class.getName());
    
    // Theme options
    public enum Theme {
        LIGHT, DARK, ARC_LIGHT, ARC_DARK, GREEN_GOLD
    }
    
    private static Theme currentTheme = Theme.GREEN_GOLD;
    
    /**
     * Sets up UI defaults for custom components
     */
    public static void setupUIDefaults() {
        // Set custom UI properties
        UIManager.put("Button.arc", 12);
        UIManager.put("Component.arc", 12);
        UIManager.put("ProgressBar.arc", 12);
        UIManager.put("TextComponent.arc", 12);
        
        // Custom fonts
        Font defaultFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font boldFont = new Font("Segoe UI", Font.BOLD, 13);
        Font smallFont = new Font("Segoe UI", Font.PLAIN, 11);
        
        UIManager.put("defaultFont", defaultFont);
        UIManager.put("Button.font", boldFont);
        UIManager.put("ToolTip.font", smallFont);
        
        // Custom borders
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIManager.getColor("TextField.borderColor"), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        );
        UIManager.put("TextField.border", textFieldBorder);
        UIManager.put("PasswordField.border", textFieldBorder);
        
        // If using green-gold theme, apply additional customizations
        if (currentTheme == Theme.GREEN_GOLD) {
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.innerFocusWidth", 1);
            UIManager.put("TabbedPane.selectedBackground", new Color(34, 139, 34, 40));
            UIManager.put("TabbedPane.hoverColor", new Color(255, 215, 0, 40));
            UIManager.put("TabbedPane.underlineColor", AppConstants.ACCENT_COLOR);
            UIManager.put("TabbedPane.tabSeparatorColor", new Color(200, 200, 200, 80));
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.width", 10);
            UIManager.put("Menu.selectionBackground", AppConstants.PRIMARY_LIGHT_COLOR);
            UIManager.put("MenuItem.selectionBackground", AppConstants.PRIMARY_LIGHT_COLOR);
        }
    }
    
    /**
     * Changes the application theme
     * @param theme the theme to apply
     * @return true if theme was changed successfully
     */
    public static boolean changeTheme(Theme theme) {
        try {
            switch (theme) {
                case LIGHT:
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                case DARK:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                case ARC_LIGHT:
                    UIManager.setLookAndFeel(new FlatArcIJTheme());
                    break;
                case ARC_DARK:
                    UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
                    break;
                case GREEN_GOLD:
                    // Apply FlatDarkLaf as base and then customize
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    // Customize colors
                    UIManager.put("Component.focusColor", AppConstants.ACCENT_COLOR);
                    UIManager.put("Button.default.focusColor", AppConstants.ACCENT_COLOR);
                    UIManager.put("Button.default.background", AppConstants.PRIMARY_COLOR);
                    UIManager.put("Button.default.foreground", Color.WHITE);
                    UIManager.put("Component.borderColor", new Color(100, 100, 100));
                    UIManager.put("Component.disabledBorderColor", new Color(80, 80, 80));
                    break;
                default:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
            }
            
            // Update UI defaults after theme change
            setupUIDefaults();
            
            // Update all open windows
            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }
            
            currentTheme = theme;
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to change theme: " + e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Gets the current theme
     * @return the current theme
     */
    public static Theme getCurrentTheme() {
        return currentTheme;
    }
    
    /**
     * Creates a rounded border with the specified color
     * @param color the border color
     * @param radius the corner radius
     * @return a rounded border
     */
    public static Border createRoundedBorder(Color color, int radius) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1, true),
            BorderFactory.createEmptyBorder(radius, radius, radius, radius)
        );
    }
    
    /**
     * Applies elevated panel effect
     * @param panel the panel to elevate
     * @param elevation elevation level (1-5)
     */
    public static void applyElevation(JPanel panel, int elevation) {
        int shadow = Math.min(elevation * 2, 10);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(shadow, shadow, shadow, shadow),
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1)
        ));
    }
}
