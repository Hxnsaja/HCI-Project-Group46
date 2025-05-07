package com.furnitureapp.ui.panels;

import com.furnitureapp.model.DesignModel;
import com.furnitureapp.model.FurnitureItem;
import com.furnitureapp.service.DesignService;
import com.furnitureapp.util.AppConstants;
import com.furnitureapp.util.ThemeManager;
import com.furnitureapp.util.UIUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Main dashboard for the furniture designer application
 */
public class DesignerDashboard extends JFrame {
    private DesignModel designModel;
    private Design2DPanel design2DPanel;
    private Design3DPanel design3DPanel;
    private RoomConfigPanel roomConfigPanel;
    private DesignService designService;
    private JTabbedPane tabbedPane;
    
    /**
     * Creates a new designer dashboard
     */
    public DesignerDashboard() {
        setTitle("Furniture Designer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Create the design service
        designService = new DesignService();
        
        // Create the shared design model
        designModel = new DesignModel();
        
        // Initialize UI
        initializeUI();
        
        // Make the frame visible
        setVisible(true);
    }
    
    /**
     * Initializes the UI components
     */
    private void initializeUI() {
        // Create the main toolbar
        JToolBar toolBar = createToolbar();
        add(toolBar, BorderLayout.NORTH);
        
        // Create the side panel for room configuration
        roomConfigPanel = new RoomConfigPanel(designModel);
        
        // Create a styled scroll pane for the config panel
        JScrollPane configScrollPane = new JScrollPane(roomConfigPanel);
        configScrollPane.setBorder(null);
        configScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        configScrollPane.setPreferredSize(new Dimension(300, 600));
        
        // Create a titled panel for room configuration
        JPanel configContainer = createStyledPanel("Room Configuration", configScrollPane);
        
        // Create the main content panels
        design2DPanel = new Design2DPanel(designModel);
        design3DPanel = new Design3DPanel(designModel);
        
        // Create tabbed pane for 2D/3D views with custom styling
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabbedPane.addTab("2D Design", null, design2DPanel, "Edit in 2D view");
        tabbedPane.addTab("3D Preview", null, design3DPanel, "View in 3D");
        
        // Add tab icons
        ImageIcon icon2D = UIUtils.createTabIcon("\u25A1", AppConstants.PRIMARY_COLOR); // Square symbol
        ImageIcon icon3D = UIUtils.createTabIcon("\u2B21", AppConstants.PRIMARY_COLOR); // Cube symbol
        tabbedPane.setIconAt(0, icon2D);
        tabbedPane.setIconAt(1, icon3D);
        
        // Create a titled panel for the design view
        JPanel designContainer = createStyledPanel("Design Workspace", tabbedPane);
        
        // Create a split pane with modern styling
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, designContainer, configContainer);
        splitPane.setDividerLocation(950);
        splitPane.setContinuousLayout(true);
        splitPane.setDividerSize(5);
        splitPane.setBorder(null);
        
        // Create a main content panel with padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.add(splitPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
        
        // Create status bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);
    }
    
    /**
     * Creates a styled panel with title and content
     * @param title The panel title
     * @param content The content component
     * @return A styled panel
     */
    private JPanel createStyledPanel(String title, Component content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        // Create header with gradient background
        JPanel headerPanel = UIUtils.createGradientPanel(
            AppConstants.PRIMARY_COLOR,
            AppConstants.PRIMARY_DARK_COLOR,
            false
        );
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        
        // Add title label
        JLabel titleLabel = UIUtils.createStyledLabel(title, 14, true, Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Create content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(content, BorderLayout.CENTER);
        
        // Add drop shadow effect
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(60, 60, 60)),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        
        // Add components to main panel
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the main toolbar
     * @return the toolbar
     */
    private JToolBar createToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        toolBar.setLayout(new MigLayout("insets 0, gap 10", "[left][center, grow][right]"));
        
        // App logo and title in toolbar
        JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        brandPanel.setOpaque(false);
        
        // Simple furniture icon as logo
        JLabel logoLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int w = getWidth();
                int h = getHeight();
                
                // Draw a stylized chair
                g2d.setColor(AppConstants.PRIMARY_COLOR);
                g2d.fillRoundRect(w/2 - 8, 5, 16, 4, 2, 2);  // top
                g2d.fillRoundRect(w/2 - 8, 10, 3, 10, 1, 1); // left leg
                g2d.fillRoundRect(w/2 + 5, 10, 3, 10, 1, 1); // right leg
                g2d.fillRoundRect(w/2 - 10, 16, 20, 4, 2, 2); // seat
                
                // Gold accent
                g2d.setColor(AppConstants.ACCENT_COLOR);
                g2d.fillRoundRect(w/2 - 6, 21, 12, 2, 1, 1);
            }
        };
        logoLabel.setPreferredSize(new Dimension(30, 28));
        
        JLabel titleLabel = new JLabel(AppConstants.APP_NAME);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(AppConstants.PRIMARY_COLOR);
        
        brandPanel.add(logoLabel);
        brandPanel.add(titleLabel);
        
        // Create toolbar section panels
        JPanel filePanel = createToolbarSection("File Actions");
        JPanel editPanel = createToolbarSection("Edit Actions");
        
        // File operations
        JButton newButton = createToolbarButton("New Design", "\u2795"); // Plus sign
        JButton saveButton = createToolbarButton("Save", "\u2B07"); // Down arrow
        
        newButton.addActionListener(this::newDesign);
        saveButton.addActionListener(this::saveDesign);
        
        filePanel.add(newButton);
        filePanel.add(saveButton);
        
        // Edit operations
        JButton deleteButton = createToolbarButton("Delete", "\u2716"); // X symbol
        JButton colorButton = createToolbarButton("Color", "\u25F0"); // Color box
        JButton scaleButton = createToolbarButton("Scale", "\u21F2"); // Scale arrow
        
        deleteButton.addActionListener(e -> deleteSelectedItem());
        colorButton.addActionListener(e -> changeItemColor());
        scaleButton.addActionListener(e -> scaleSelectedItem());
        
        editPanel.add(deleteButton);
        editPanel.add(colorButton);
        editPanel.add(scaleButton);
        
        // View operations panel
        JPanel viewPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        viewPanel.setOpaque(false);
        
        // Create a styled theme toggle with icon
        JToggleButton themeToggle = new JToggleButton();
        themeToggle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        themeToggle.setFocusPainted(false);
        themeToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        themeToggle.setSelected(ThemeManager.getCurrentTheme() != ThemeManager.Theme.LIGHT);
        
        // Create a panel to hold the toggle button with icon and text
        JPanel themeTogglePanel = new JPanel(new BorderLayout(5, 0));
        themeTogglePanel.setOpaque(false);
        
        // Add icon
        JLabel themeIcon = new JLabel("\u263D"); // Moon symbol
        themeIcon.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        themeIcon.setForeground(AppConstants.ACCENT_COLOR);
        themeIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Add text
        JLabel themeText = new JLabel(ThemeManager.getCurrentTheme() == ThemeManager.Theme.LIGHT ? "Dark Mode" : "Light Mode");
        themeText.setFont(themeToggle.getFont());
        
        themeTogglePanel.add(themeIcon, BorderLayout.WEST);
        themeTogglePanel.add(themeText, BorderLayout.CENTER);
        
        themeToggle.setLayout(new BorderLayout());
        themeToggle.add(themeTogglePanel);
        themeToggle.setText(""); // Clear original text
        
        themeToggle.addActionListener(e -> {
            if (themeToggle.isSelected()) {
                ThemeManager.changeTheme(ThemeManager.Theme.GREEN_GOLD);
                themeText.setText("Light Mode");
                themeIcon.setText("\u263D"); // Moon symbol
            } else {
                ThemeManager.changeTheme(ThemeManager.Theme.LIGHT);
                themeText.setText("Dark Mode");
                themeIcon.setText("\u2600"); // Sun symbol
            }
        });
        
        viewPanel.add(themeToggle);
        
        // Add panels to toolbar
        toolBar.add(brandPanel, "left");
        toolBar.add(filePanel, "center");
        toolBar.add(editPanel, "center");
        toolBar.add(viewPanel, "right");
        
        return toolBar;
    }
    
    /**
     * Creates a toolbar section with a title
     */
    private JPanel createToolbarSection(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setOpaque(false);
        
        // Add a subtle separator before the title (except for first section)
        if (!title.equals("File Actions")) {
            JSeparator separator = new JSeparator(JSeparator.VERTICAL);
            separator.setPreferredSize(new Dimension(1, 28));
            separator.setForeground(new Color(120, 120, 120, 100));
            panel.add(separator);
        }
        
        return panel;
    }
    
    /**
     * Creates a consistent toolbar button with icon and text
     */
    private JButton createToolbarButton(String text, String symbol) {
        JButton button = new JButton();
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(5, 10, 5, 10));
        
        // Create panel for content
        JPanel content = new JPanel(new BorderLayout(5, 0));
        content.setOpaque(false);
        
        // Add symbol
        JLabel iconLabel = new JLabel(symbol);
        iconLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        iconLabel.setForeground(AppConstants.ACCENT_COLOR);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(iconLabel, BorderLayout.WEST);
        
        // Add text
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(button.getFont());
        content.add(textLabel, BorderLayout.CENTER);
        
        button.setLayout(new BorderLayout());
        button.add(content);
        button.setText(""); // Clear original text
        
        return button;
    }
    
    /**
     * Creates the status bar
     * @return the status bar
     */
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(80, 80, 80)),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        
        // Left section with status
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        leftPanel.setOpaque(false);
        
        JLabel statusIcon = new JLabel("\u2022"); // Bullet point
        statusIcon.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
        statusIcon.setForeground(new Color(40, 180, 40)); // Green dot
        
        JLabel statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        leftPanel.add(statusIcon);
        leftPanel.add(statusLabel);
        
        // Right section with version
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightPanel.setOpaque(false);
        
        JLabel versionLabel = new JLabel("Version " + AppConstants.APP_VERSION);
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        versionLabel.setForeground(new Color(120, 120, 120));
        
        rightPanel.add(versionLabel);
        
        statusBar.add(leftPanel, BorderLayout.WEST);
        statusBar.add(rightPanel, BorderLayout.EAST);
        
        return statusBar;
    }
    
    /**
     * Creates a new design
     * @param e the action event
     */
    private void newDesign(ActionEvent e) {
        // Confirm if there are unsaved changes
        int result = JOptionPane.showConfirmDialog(this,
                "Create a new design? Any unsaved changes will be lost.",
                "New Design", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            designModel.reset();
        }
    }
    
    /**
     * Saves the current design
     * @param e the action event
     */
    private void saveDesign(ActionEvent e) {
        String name = JOptionPane.showInputDialog(this, "Enter a name for this design:");
        
        if (name != null && !name.isEmpty()) {
            try {
                // Convert DesignModel to Design for saving
                designService.saveDesign(designModel.toDesign(name, "admin"));
                JOptionPane.showMessageDialog(this, "Design saved successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving design: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Exports the current design as an image
     */
    private void exportDesign() {
        // Method left for potential future implementation but button removed
        JOptionPane.showMessageDialog(this, "Export functionality will be implemented soon.", 
                "Coming Soon", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Deletes the selected furniture item
     */
    private void deleteSelectedItem() {
        FurnitureItem selectedItem = designModel.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete", 
                    "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this item?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            designModel.removeFurnitureItem(selectedItem);
        }
    }
    
    /**
     * Changes the color of the selected furniture item
     */
    private void changeItemColor() {
        FurnitureItem selectedItem = designModel.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item to change color", 
                    "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Color selectedColor = JColorChooser.showDialog(this, 
                "Choose Color", selectedItem.getColor());
        
        if (selectedColor != null) {
            selectedItem.setColor(selectedColor);
            // Notify model of the change
            designModel.notifyListeners("ITEM_COLOR_CHANGED");
        }
    }
    
    /**
     * Scales the selected furniture item
     */
    private void scaleSelectedItem() {
        FurnitureItem selectedItem = designModel.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item to scale", 
                    "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String input = JOptionPane.showInputDialog(this, 
                "Enter scale factor (0.5 - 2.0):", 
                "1.0");
        
        try {
            float scale = Float.parseFloat(input);
            if (scale >= 0.5 && scale <= 2.0) {
                selectedItem.scale(scale);
                // Notify model of the change
                designModel.notifyListeners("ITEM_SCALED");
            } else {
                JOptionPane.showMessageDialog(this, "Scale must be between 0.5 and 2.0", 
                        "Invalid Scale", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException | NullPointerException ex) {
            // User canceled or entered invalid input
        }
    }
    
    /**
     * Rotates the selected furniture item
     */
    private void rotateSelectedItem() {
        // Method left for potential future implementation but button removed
        FurnitureItem selectedItem = designModel.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item to rotate", 
                    "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Rotate by 90 degrees each time
        float currentRotation = selectedItem.getRotation();
        selectedItem.setRotation((currentRotation + 90) % 360);
        
        // Notify model of the change
        designModel.notifyListeners("ITEM_ROTATED");
    }
}
