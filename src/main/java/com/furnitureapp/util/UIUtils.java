package com.furnitureapp.util;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Utility class for UI-related operations
 */
public class UIUtils {
    
    /**
     * Creates a rounded button with icon and text
     * @param text button text
     * @param icon button icon
     * @param bgColor background color
     * @param fgColor foreground color
     * @return a styled JButton
     */
    public static JButton createStyledButton(String text, Icon icon, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(8, 16, 8, 16));
        
        return button;
    }
    
    /**
     * Creates a styled label with custom font and color
     * @param text label text
     * @param fontSize font size
     * @param bold whether to use bold font
     * @param color text color
     * @return a styled JLabel
     */
    public static JLabel createStyledLabel(String text, int fontSize, boolean bold, Color color) {
        JLabel label = new JLabel(text);
        int fontStyle = bold ? Font.BOLD : Font.PLAIN;
        label.setFont(new Font("Segoe UI", fontStyle, fontSize));
        label.setForeground(color);
        return label;
    }
    
    /**
     * Creates a styled text field with rounded borders
     * @param columns number of columns
     * @return a styled JTextField
     */
    public static JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setMargin(new Insets(5, 8, 5, 8));
        return textField;
    }
    
    /**
     * Creates a styled password field with rounded borders
     * @param columns number of columns
     * @return a styled JPasswordField
     */
    public static JPasswordField createStyledPasswordField(int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordField.setMargin(new Insets(5, 8, 5, 8));
        return passwordField;
    }
    
    /**
     * Creates a rounded image from a rectangular image
     * @param image the original image
     * @param cornerRadius the corner radius
     * @return a rounded image
     */
    public static BufferedImage createRoundedImage(BufferedImage image, int cornerRadius) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        
        // Set rendering hints for better quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        // Create rounded rectangle
        g2.setClip(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        
        return output;
    }
    
    /**
     * Creates a panel with a gradient background
     * @param startColor gradient start color
     * @param endColor gradient end color
     * @param vertical whether the gradient is vertical
     * @return a panel with gradient background
     */
    public static JPanel createGradientPanel(Color startColor, Color endColor, boolean vertical) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                int width = getWidth();
                int height = getHeight();
                
                GradientPaint gp;
                if (vertical) {
                    gp = new GradientPaint(0, 0, startColor, 0, height, endColor);
                } else {
                    gp = new GradientPaint(0, 0, startColor, width, 0, endColor);
                }
                
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
    }
    
    /**
     * Creates an icon suitable for tabbed panes using a unicode symbol
     * @param symbol Unicode symbol to display
     * @param color Color of the symbol
     * @return An ImageIcon with the rendered symbol
     */
    public static ImageIcon createTabIcon(String symbol, Color color) {
        // Create a 16x16 image for the icon
        BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set rendering hints for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Set font and color
        g2d.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        g2d.setColor(color);
        
        // Get the size of the symbol
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(symbol);
        int textHeight = fm.getHeight();
        
        // Draw the symbol centered in the image
        g2d.drawString(symbol, (16 - textWidth) / 2, (16 + textHeight / 2) / 2);
        g2d.dispose();
        
        return new ImageIcon(image);
    }
    
    /**
     * Shows a styled error dialog
     * @param parent the parent component
     * @param message the error message
     * @param title the dialog title
     */
    public static void showErrorDialog(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Shows a styled information dialog
     * @param parent the parent component
     * @param message the information message
     * @param title the dialog title
     */
    public static void showInfoDialog(Component parent, String message, String title) {
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
