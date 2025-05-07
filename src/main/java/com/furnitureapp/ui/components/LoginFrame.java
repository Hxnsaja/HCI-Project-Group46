package com.furnitureapp.ui.components;

import com.furnitureapp.ui.panels.DesignerDashboard;
import com.furnitureapp.util.AppConstants;
import com.furnitureapp.util.ThemeManager;
import com.furnitureapp.util.UIUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * A modern and visually appealing login frame for the furniture design application
 */
public class LoginFrame extends JFrame {
    // UI Components
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JLabel statusLabel;
    private JPanel mainPanel;
    
    public LoginFrame() {
        // Set up the frame
        setTitle("Furniture Designer Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800); // Match main UI size
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true); // Remove window decorations for modern look
        
        // Create main panel with gradient background
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        
        // Create header panel with logo/title
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create form panel
        JPanel formPanel = createFormPanel();
        
        // Center the form panel with empty space on sides for balance
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.setOpaque(false);
        centeringPanel.add(formPanel);
        
        mainPanel.add(centeringPanel, BorderLayout.CENTER);
        
        // Add decorative footer panel
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        setContentPane(mainPanel);
        
        // Set up action listeners
        setupActionListeners();
        
        // Set window shape to rounded rectangle
        setShape(new RoundRectangle2D.Double(0, 0, 1280, 800, 20, 20));
        
        // Make the frame visible
        setVisible(true);
    }
    
    /**
     * Creates the header panel with logo and title
     */
    private JPanel createHeaderPanel() {
        // Create gradient panel for header
        JPanel headerPanel = UIUtils.createGradientPanel(
            AppConstants.PRIMARY_COLOR,
            AppConstants.PRIMARY_DARK_COLOR,
            true
        );
        headerPanel.setLayout(new MigLayout("fill, insets 40 20 40 20"));
        
        // Create logo panel
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw furniture icon
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                
                // Draw a house/home logo instead of sofa
                g2d.setColor(Color.WHITE);
                
                // House outline
                int houseWidth = 90;
                int houseHeight = 70;
                int roofHeight = 30;
                
                // Draw roof
                int[] xPoints = {centerX - houseWidth/2, centerX, centerX + houseWidth/2};
                int[] yPoints = {centerY - houseHeight/2, centerY - houseHeight/2 - roofHeight, centerY - houseHeight/2};
                g2d.fillPolygon(xPoints, yPoints, 3);
                
                // Draw house body
                g2d.fillRect(centerX - houseWidth/2, centerY - houseHeight/2, houseWidth, houseHeight);
                
                // Draw door
                g2d.setColor(AppConstants.PRIMARY_DARK_COLOR);
                int doorWidth = 20;
                int doorHeight = 40;
                g2d.fillRect(centerX - doorWidth/2, centerY + houseHeight/2 - doorHeight, doorWidth, doorHeight);
                
                // Draw window
                g2d.setColor(AppConstants.ACCENT_COLOR);
                int windowSize = 15;
                g2d.fillRect(centerX - houseWidth/4, centerY - houseHeight/4, windowSize, windowSize);
                g2d.fillRect(centerX + houseWidth/4 - windowSize, centerY - houseHeight/4, windowSize, windowSize);
                
                // Draw chimney
                g2d.setColor(AppConstants.PRIMARY_DARK_COLOR);
                g2d.fillRect(centerX + houseWidth/4, centerY - houseHeight/2 - roofHeight/2, 10, roofHeight/2 + 10);
            }
        };
        logoPanel.setOpaque(false);
        logoPanel.setPreferredSize(new Dimension(150, 150));
        
        // App title
        JLabel titleLabel = UIUtils.createStyledLabel(AppConstants.APP_NAME, 42, true, Color.WHITE);
        
        // Subtitle
        JLabel subtitleLabel = UIUtils.createStyledLabel("Design your space in 2D and 3D", 18, false, new Color(230, 230, 250));
        
        // Add components to header panel
        headerPanel.add(logoPanel, "align center, wrap");
        headerPanel.add(titleLabel, "align center, wrap");
        headerPanel.add(subtitleLabel, "align center");
        
        // Add window control buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        controlPanel.setOpaque(false);
        
        // Minimize button
        JButton minimizeBtn = new JButton("─");
        minimizeBtn.setForeground(Color.WHITE);
        minimizeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        minimizeBtn.setBorderPainted(false);
        minimizeBtn.setContentAreaFilled(false);
        minimizeBtn.setFocusPainted(false);
        minimizeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimizeBtn.addActionListener(e -> setState(JFrame.ICONIFIED));
        
        // Close button
        JButton closeBtn = new JButton("×");
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 22));
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> System.exit(0));
        
        controlPanel.add(minimizeBtn);
        controlPanel.add(closeBtn);
        
        headerPanel.add(controlPanel, "pos 0 0 100% 40");
        
        return headerPanel;
    }
    
    /**
     * Creates the form panel with login fields
     */
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new MigLayout("fillx, insets 40", "[grow]"));
        formPanel.setBackground(UIManager.getColor("Panel.background"));
        formPanel.setPreferredSize(new Dimension(480, 400));
        
        // Create card-like panel for form fields
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new MigLayout("fillx, insets 40", "[grow]"));
        cardPanel.setBackground(UIManager.getColor("Panel.background"));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        ThemeManager.applyElevation(cardPanel, 3);
        
        // Login title
        JLabel loginTitle = UIUtils.createStyledLabel("Sign In", 24, true, AppConstants.PRIMARY_COLOR);
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Username field with label above it
        JLabel userLabel = UIUtils.createStyledLabel("Username", 14, true, UIManager.getColor("Label.foreground"));
        userField = UIUtils.createStyledTextField(20);
        userField.setToolTipText("Enter your username (admin)");
        userField.setPreferredSize(new Dimension(userField.getPreferredSize().width, 40));
        
        // Password field with label above it
        JLabel passLabel = UIUtils.createStyledLabel("Password", 14, true, UIManager.getColor("Label.foreground"));
        passField = UIUtils.createStyledPasswordField(20);
        passField.setToolTipText("Enter your password (Test@123)");
        passField.setPreferredSize(new Dimension(passField.getPreferredSize().width, 40));
        
        // Login button
        loginBtn = UIUtils.createStyledButton("SIGN IN", null, AppConstants.PRIMARY_COLOR, Color.WHITE);
        loginBtn.setPreferredSize(new Dimension(loginBtn.getPreferredSize().width, 50));
        
        // Status label for error messages
        statusLabel = UIUtils.createStyledLabel("", 12, false, new Color(255, 80, 80));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Add form elements to card panel
        cardPanel.add(loginTitle, "align center, wrap 30");
        cardPanel.add(userLabel, "wrap 5");
        cardPanel.add(userField, "growx, wrap 25");
        cardPanel.add(passLabel, "wrap 5");
        cardPanel.add(passField, "growx, wrap 35");
        cardPanel.add(loginBtn, "growx, h 50, wrap 10");
        cardPanel.add(statusLabel, "growx, wrap 10");
        
        // Add hint text
        JLabel hintLabel = UIUtils.createStyledLabel("Hint: Use admin/Test@123", 12, false, UIManager.getColor("Label.disabledForeground"));
        hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Theme toggle with modern design
        JPanel themePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        themePanel.setOpaque(false);
        
        JLabel themeLabel = UIUtils.createStyledLabel("Theme:", 12, false, UIManager.getColor("Label.foreground"));
        
        JToggleButton themeToggle = new JToggleButton();
        themeToggle.setText(ThemeManager.getCurrentTheme() == ThemeManager.Theme.LIGHT ? "Dark Mode" : "Light Mode");
        themeToggle.setFocusPainted(false);
        themeToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themeToggle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        themeToggle.setPreferredSize(new Dimension(110, 30));
        themeToggle.addActionListener(e -> {
            if (themeToggle.isSelected()) {
                ThemeManager.changeTheme(ThemeManager.Theme.GREEN_GOLD);
                themeToggle.setText("Light Mode");
            } else {
                ThemeManager.changeTheme(ThemeManager.Theme.LIGHT);
                themeToggle.setText("Dark Mode");
            }
        });
        
        themePanel.add(themeLabel);
        themePanel.add(themeToggle);
        
        // Add components to form panel
        formPanel.add(cardPanel, "growx, wrap 20");
        formPanel.add(hintLabel, "align center, wrap 15");
        formPanel.add(themePanel, "align center");
        
        return formPanel;
    }
    
    /**
     * Creates a decorative footer panel
     */
    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(new Color(30, 30, 30));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        
        // Version info
        JLabel versionLabel = UIUtils.createStyledLabel("Version " + AppConstants.APP_VERSION, 12, false, new Color(180, 180, 180));
        footerPanel.add(versionLabel, BorderLayout.WEST);
        
        // Copyright
        JLabel copyrightLabel = UIUtils.createStyledLabel("© " + AppConstants.APP_AUTHOR, 12, false, new Color(180, 180, 180));
        footerPanel.add(copyrightLabel, BorderLayout.EAST);
        
        return footerPanel;
    }
    
    /**
     * Sets up action listeners for interactive components
     */
    private void setupActionListeners() {
        // Make the frame draggable
        setupDraggableFrame();
        
        // Button hover effect
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginBtn.setBackground(AppConstants.PRIMARY_LIGHT_COLOR);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                loginBtn.setBackground(AppConstants.PRIMARY_COLOR);
            }
        });
        
        // Login button action
        loginBtn.addActionListener(e -> attemptLogin());
        
        // Enter key in password field triggers login
        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    attemptLogin();
                }
            }
        });
    }
    
    /**
     * Makes the frame draggable (for the undecorated window)
     */
    private void setupDraggableFrame() {
        final Point[] dragPoint = {new Point(0, 0)};
        
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragPoint[0] = e.getPoint();
            }
        });
        
        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentLocation = getLocation();
                setLocation(
                    currentLocation.x + e.getX() - dragPoint[0].x,
                    currentLocation.y + e.getY() - dragPoint[0].y
                );
            }
        });
    }
    
    /**
     * Attempts to log in with the provided credentials
     */
    private void attemptLogin() {
        // Get the entered username and password
        String username = userField.getText();
        String password = new String(passField.getPassword());
        
        // Simple login validation
        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter both username and password");
            shakeWindow();
            return;
        }
        
        if (username.equals(AppConstants.VALID_USERNAME) && 
            password.equals(AppConstants.VALID_PASSWORD)) {
            // Successful login
            dispose(); // Close the login window
            new DesignerDashboard(); // Open the main dashboard
        } else {
            // Failed login
            statusLabel.setText("Invalid username or password");
            passField.setText("");
            shakeWindow();
        }
    }
    
    /**
     * Shakes the window as a visual cue for authentication failure
     */
    private void shakeWindow() {
        final int[] directions = {-1, 1, -1, 1, -1, 1, -1, 1, 0};
        final Point originalLocation = getLocation();
        
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            int count = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < directions.length) {
                    setLocation(originalLocation.x + directions[count] * 5, originalLocation.y);
                    count++;
                } else {
                    timer.stop();
                    setLocation(originalLocation);
                }
            }
        });
        
        timer.start();
    }
}
