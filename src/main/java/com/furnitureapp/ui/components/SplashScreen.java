package com.furnitureapp.ui.components;

import com.furnitureapp.util.AppConstants;
import com.furnitureapp.util.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * Modern splash screen with progress animation
 */
public class SplashScreen extends JWindow {
    
    private JProgressBar progressBar;
    private Timer progressTimer;
    private Runnable onComplete;
    
    /**
     * Creates a new splash screen
     * @param onComplete callback to run when splash screen completes
     */
    public SplashScreen(Runnable onComplete) {
        this.onComplete = onComplete;
        
        // Set up the window
        setSize(520, 360);
        setLocationRelativeTo(null);
        
        // Create main panel with gradient background
        JPanel mainPanel = UIUtils.createGradientPanel(
            AppConstants.PRIMARY_DARK_COLOR,
            AppConstants.PRIMARY_COLOR,
            true
        );
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add logo panel at the top
        JPanel logoPanel = createLogoPanel();
        mainPanel.add(logoPanel, BorderLayout.CENTER);
        
        // Add title panel
        JPanel titlePanel = createTitlePanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Add progress panel at the bottom
        JPanel progressPanel = createProgressPanel();
        mainPanel.add(progressPanel, BorderLayout.SOUTH);
        
        // Set content pane
        setContentPane(mainPanel);
        
        // Make the window shape rounded
        setShape(new RoundRectangle2D.Double(0, 0, 520, 360, 25, 25));
        
        // Start progress animation
        startProgressAnimation();
        
        // Show the window
        setVisible(true);
    }
    
    /**
     * Creates the logo panel with furniture icon
     */
    private JPanel createLogoPanel() {
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                
                // Draw modern living room scene
                // Sofa
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(centerX - 60, centerY, 120, 40, 10, 10);  // Seat
                g2d.fillRoundRect(centerX - 60, centerY - 20, 120, 30, 8, 8);  // Back
                g2d.fillRoundRect(centerX - 70, centerY - 10, 20, 40, 8, 8);  // Left arm
                g2d.fillRoundRect(centerX + 50, centerY - 10, 20, 40, 8, 8);  // Right arm
                
                // Coffee table
                g2d.setColor(AppConstants.ACCENT_COLOR);
                g2d.fillRoundRect(centerX - 40, centerY + 50, 80, 5, 2, 2);  // Tabletop
                g2d.fillRect(centerX - 35, centerY + 55, 5, 15);  // Left leg
                g2d.fillRect(centerX + 30, centerY + 55, 5, 15);  // Right leg
                
                // Floor
                g2d.setColor(new Color(240, 240, 240, 100));
                g2d.fillRoundRect(centerX - 90, centerY + 70, 180, 10, 5, 5);
                
                // Decorative plants
                g2d.setColor(new Color(34, 139, 34));
                int leafSize = 15;
                // Left plant
                g2d.fillOval(centerX - 90, centerY - 10, leafSize, leafSize);
                g2d.fillOval(centerX - 85, centerY - 20, leafSize, leafSize);
                g2d.fillOval(centerX - 95, centerY - 20, leafSize, leafSize);
                // Right plant
                g2d.fillOval(centerX + 75, centerY - 10, leafSize, leafSize);
                g2d.fillOval(centerX + 70, centerY - 20, leafSize, leafSize);
                g2d.fillOval(centerX + 80, centerY - 20, leafSize, leafSize);
                
                // Plant pots
                g2d.setColor(AppConstants.ACCENT_COLOR);
                g2d.fillRoundRect(centerX - 95, centerY + 5, 25, 20, 5, 5);
                g2d.fillRoundRect(centerX + 70, centerY + 5, 25, 20, 5, 5);
                
                // Picture frame on wall
                g2d.setColor(AppConstants.ACCENT_COLOR);
                g2d.fillRoundRect(centerX - 30, centerY - 60, 60, 30, 5, 5);
                g2d.setColor(new Color(240, 240, 240));
                g2d.fillRoundRect(centerX - 25, centerY - 55, 50, 20, 3, 3);
                
                // Light effect from ceiling
                g2d.setColor(new Color(255, 255, 230, 30));
                g2d.fillOval(centerX - 50, centerY - 80, 100, 40);
            }
        };
        logoPanel.setOpaque(false);
        return logoPanel;
    }
    
    /**
     * Creates the title panel with app name and subtitle
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout(0, 10));
        titlePanel.setOpaque(false);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // App title
        JLabel titleLabel = UIUtils.createStyledLabel(AppConstants.APP_NAME, 30, true, Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        
        // Subtitle
        JLabel subtitleLabel = UIUtils.createStyledLabel("Professional 3D Furniture Design Studio", 14, false, new Color(230, 230, 250));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(subtitleLabel, BorderLayout.CENTER);
        
        // Version
        JLabel versionLabel = UIUtils.createStyledLabel("Version " + AppConstants.APP_VERSION, 12, false, new Color(200, 200, 255));
        versionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(versionLabel, BorderLayout.SOUTH);
        
        return titlePanel;
    }
    
    /**
     * Creates the progress panel with progress bar and status
     */
    private JPanel createProgressPanel() {
        JPanel progressPanel = new JPanel(new BorderLayout(0, 5));
        progressPanel.setOpaque(false);
        
        // Status label
        JLabel statusLabel = UIUtils.createStyledLabel("Initializing application...", 12, false, Color.WHITE);
        progressPanel.add(statusLabel, BorderLayout.NORTH);
        
        // Progress bar with custom styling
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setForeground(AppConstants.ACCENT_COLOR);
        progressBar.setBackground(new Color(255, 255, 255, 50));
        progressBar.setBorderPainted(false);
        progressBar.setPreferredSize(new Dimension(100, 8));
        progressPanel.add(progressBar, BorderLayout.CENTER);
        
        // Author credit
        JLabel authorLabel = UIUtils.createStyledLabel("by " + AppConstants.APP_AUTHOR, 10, false, new Color(200, 200, 255));
        authorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        progressPanel.add(authorLabel, BorderLayout.SOUTH);
        
        return progressPanel;
    }
    
    /**
     * Starts the progress animation
     */
    private void startProgressAnimation() {
        progressTimer = new Timer(30, new ActionListener() {
            private int progress = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 1;
                progressBar.setValue(progress);
                
                if (progress >= 100) {
                    progressTimer.stop();
                    dispose();
                    if (onComplete != null) {
                        SwingUtilities.invokeLater(onComplete);
                    }
                }
            }
        });
        
        progressTimer.start();
    }
}
