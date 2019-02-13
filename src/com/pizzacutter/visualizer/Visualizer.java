package com.pizzacutter.visualizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.pizzacutter.Element;

public class Visualizer extends JFrame{
    private VisualizationWindow w;
    
    /**
     * container panel
     */
    JPanel containerPane = new JPanel();
    
    /**
     * buttons panel
     */
    JPanel buttonsPanel;
    
    /**
     * pizza visualization
     */
    private PizzaGrid grid;
    
    private String fileName = "";
    
    public Visualizer() {
        
        this.containerPane = new JPanel();
        this.containerPane.setLayout(new GridLayout(2, 1));
        
        testFilesButtons();
        this.grid = new PizzaGrid();
        
        containerPane.add(this.buttonsPanel);
        containerPane.add(this.grid);
        
        this.w = new VisualizationWindow();
        this.w.setSize(700, 600);
        this.w.setLocation(150,150);
        this.w.add(this.containerPane);
        this.w.setVisible(true);
        this.w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void testFilesButtons() {
        this.buttonsPanel = new JPanel(new GridLayout(1,4,4,4));
        this.buttonsPanel.setMaximumSize(new Dimension(50, 200));
        JButton fileExampleButton = new JButton("example");
        fileExampleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = "src//com//pizzacutter//data//a_example.in";
                grid.redraw();
            }          
         });
        fileExampleButton.setPreferredSize(new Dimension(90, 50));
        this.buttonsPanel.add(fileExampleButton);
        JButton fileSmallButton = new JButton("small");
        fileSmallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = "src//com//pizzacutter//data//b_small.in";
                grid.redraw();
            }          
         });
        fileSmallButton.setPreferredSize(new Dimension(80, 50));
        this.buttonsPanel.add(fileSmallButton);
        JButton fileMediumButton = new JButton("medium");
        fileMediumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = "src//com//pizzacutter//data//c_medium.in";
                grid.redraw();
            }          
         });
        fileMediumButton.setPreferredSize(new Dimension(80, 50));
        this.buttonsPanel.add(fileMediumButton);
        JButton fileBigButton = new JButton("big");
        fileBigButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileName = "src//com//pizzacutter//data//d_big.in";
                grid.redraw();
            }          
         });
        fileBigButton.setPreferredSize(new Dimension(80, 50));
        this.buttonsPanel.add(fileBigButton);
    }
    
    public void addPizzaGrid(Element[][] pizza) {
        this.grid = new PizzaGrid();
        this.grid.setPizza(pizza);
    }
    
    public void redraw() {
        this.grid.redraw();
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public static class PizzaGrid extends JPanel {
        
        static int basicWidth = 800;
        static int basicLength = 800;

        static int startX = 100;
        static int startY = 100;
        
        private int width = 0;
        private int length = 0;
        
        private int squareSize = 10;

        private Element[][] pizza;
        private Point[][] pizzaGrid;
        private List<Color> colors = null;

        public PizzaGrid() {
        }

        public void setPizza(Element[][] pizza) {
            if (pizza.length > 0) {
                this.pizza = pizza;
                this.pizzaGrid = new Point[pizza.length][pizza[0].length];
                setSize(pizza.length, pizza[0].length);
                Point point = null;
                for (int i = 0; i < pizza.length; i++) {
                    for (int j = 0; j < pizza[0].length; j++) {
                        point = new Point(i*squareSize+startX, j*squareSize+startY);
                        this.pizzaGrid[i][j] = point;
                    }
                }
            }
        }
        
        public void setSize(int width, int length) {
            this.width = width*squareSize;
            this.length = length*squareSize;
            if (this.width > basicWidth ||
                    this.length > basicLength) {
                double percent = Math.max((double)basicWidth/(double)this.width,
                        (double)basicLength/(double)this.length);
                this.squareSize = (int) (this.squareSize*percent);
                this.width = width*squareSize;
                this.length = length*squareSize;
            }
            this.colors = new ArrayList<Color>();
            setColors(width, length);
        }
        
        public void setColors(int width, int length) {
            Random r = new Random(this.colors.size()*3);
            for (int i = 0; i < width*length; i++) {
                float h = r.nextFloat();
                float s = r.nextFloat();
                float b = r.nextFloat();
                this.colors.add(Color.getHSBColor(h, s, b));
            }
        }
        
        public Color getSliceColor(int i, int j) {
            int colorNumber = this.pizza[i][j].getSliceMark();
            if (colorNumber == -1)
                return Color.WHITE;
            return this.colors.get(colorNumber);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (this.pizzaGrid != null) {
                for (int i = 0; i < this.pizzaGrid.length; i++) {
                    for (int j = 0; j < this.pizzaGrid[0].length; j++) {
                        g.setColor(getSliceColor(i, j));
                        g.fillRect(pizzaGrid[i][j].y, pizzaGrid[i][j].x, squareSize, squareSize);
                    }
                }
                
                g.setColor(Color.BLACK);
                g.drawRect(startX, startY, length, width);
    
                // draw line dividors
                for (int i = startY; i <= width; i += squareSize) {
                    g.drawLine(startX, i+squareSize, length+squareSize, i+squareSize);
                }
                
                for (int i = startX; i <= length; i += squareSize) {
                    g.drawLine(i+squareSize, startY, i+squareSize, width+squareSize);
                }
            }

        }

        public void redraw() {
            repaint();
        }

    }
    
}
