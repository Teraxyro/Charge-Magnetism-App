package com.company;
import java.awt.event.*;
import java.math.MathContext;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.math.BigDecimal;

import static java.lang.Math.abs;
import static javax.swing.BoxLayout.LINE_AXIS;
import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends JPanel implements Runnable{

    private static final int magneticField = 1;

    private static final int dimension_X = 863;
    private static final int dimension_Y = 680;
    private static final JFrame frame = new JFrame();

    private static final imageClass imageList = new imageClass();

    private static final ImageIcon LogoImage = imageList.getLogoImage();

    private static final ImageIcon backgroundImage = imageList.getBackgroundImage();
    private static ImageIcon labButtonImage = imageList.getLabButtonImage();
    private static ImageIcon ObstclButtonImage = imageList.getObstclButtonImage();

    private static ImageIcon labButtonImageClkd = imageList.getLabButtonImageClkd();
    private static ImageIcon ObstclButtonImageClkd = imageList.getObstclButtonImageClkd();

    private static final JLabel background = new JLabel("", backgroundImage, JLabel.CENTER);
    private static JButton startButtonLab =  new JButton(labButtonImage);
    private static JButton startButtonObstcl = new JButton(ObstclButtonImage);

    private static JButton startButtonLabClkd =  new JButton(labButtonImageClkd);
    private static JButton startButtonObstclClkd = new JButton(ObstclButtonImageClkd);

    private static boolean stat = false;
    private static boolean mouseStat = false;

    private static JPanel mainPanel = new JPanel();
    private static JPanel mainPanel2 = new JPanel();

    private static JPanel actionPanel = new JPanel();
    private static JPanel infoPanel = new JPanel();
    //private static JPanel graphPanel;

    private static JToolBar mainTB = new JToolBar();

    private static JButton chargeSetButton;
    private static JButton startButton;
    private static JButton stopButton;
    private static JButton exitButton;
    private static JButton measureButton;
    private static JButton graphButton;
    private static JCheckBox showV = new JCheckBox("Show Velocity Direction");

    private static JPanel boundsPanel;
    private static JPanel boundsPanelX;
    private static JPanel boundsPanelY;
    private static JPanel stepsPanel;

    private static JPanel chargeSettingPanel;
    private static JPanel setChargePanel;
    private static JPanel setMassPanel;
    private static JPanel setVelocityPanel;
    private static JPanel setButtonPanel;

    private static JTextField minX = new JTextField("-10");
    private static JTextField minY = new JTextField("-10");
    private static JTextField maxX = new JTextField("10");
    private static JTextField maxY = new JTextField("10");
    private static JTextField stepsX = new JTextField("1");
    private static JTextField stepsY = new JTextField("1");
    private static JLabel stepsTitle = new JLabel("Step Size");
    private static JLabel textX = new JLabel("< X <");
    private static JLabel textY = new JLabel("< Y <");

    private static double remainderXmin = 0;
    private static double remainderXmax = 0;
    private static double remainderYmin = 0;
    private static double remainderYmax = 0;

    private static JPanel showVPanel = new JPanel();

    private static JLabel chargeTitle;
    private static JTextField chargeValue;
    private static String charge = "";
    private static JLabel chargeUnit;
    private static JLabel massTitle;
    private static JTextField massValue;
    private static String mass = "";
    private static JLabel massUnit;
    private static JLabel velocityTitleX;
    private static JLabel velocityTitleY;
    private static JTextField velocityValueX;
    private static JTextField velocityValueY;
    private static String velocity = "";
    private static JLabel velocityUnitX;
    private static JLabel velocityUnitY;
    private static JButton setButton = new JButton("Set");

    private static JPanel velocityPanelX;
    private static JPanel velocityPanelY;

    private static JPanel measurePanel;
    private static JButton getRuler;
    private static JButton deleteRuler;
    private static JLabel rulerValue;
    private static JPanel valuePanel;

    private static final JPanel fillerPanel = new JPanel();
    //private static ImageIcon chargeSetImage = new ImageIcon("ChargeButton.jpg");
    private static ImageIcon startButtonImage = imageList.getStartButtonImage();
    private static ImageIcon stopButtonImage = imageList.getStopButtonImage();
    private static ImageIcon exitButtonImage = imageList.getExitButtonImage();
    //private static ImageIcon measureButtonImage = new ImageIcon("measureButton.jpg");
    //private static ImageIcon graphButtonImage = new ImageIcon("graphButton.jpg");

    private static double domain;
    private static double range;
    private static double minXVal;
    private static double maxXVal;
    private static double minYVal;
    private static double maxYVal;
    private static int stepCountMinX;
    private static int stepCountMaxX;
    private static int stepCountMinY;
    private static int stepCountMaxY;
    private static double remainderXMinPercent;
    private static double remainderXMaxPercent;
    private static double remainderYMinPercent;
    private static double remainderYMaxPercent;
    private static int pixelsPerStepX = 32;
    private static int pixelsPerStepY = 32;
    private static Point p1 = new Point();
    private static Point p2 = new Point();
    private static int xx = 0;
    private static int xy1 = 0;
    private static int xy2 = dimension_Y;
    private static int yy = 0;
    private static int yx1 = 0;
    private static int yx2 = dimension_X;
    private static int totStepX = 20;
    private static int totStepY = 20;
    private static ArrayList<Double> x = new ArrayList<>();
    private static ArrayList<Double> y = new ArrayList<>();
    private static int count = 0;
    private static boolean refresh = true;
    private static int firstSkipX = 0;
    private static int firstSkipY = 0;
    private static int secondSkipX = 0;
    private static int secondSkipY = 0;
    private static Main graphPanel;
    private static double theta = 0;

    private static boolean jobStart = false;
    private static boolean jobDone = false;

    private static int a = 0;
    private static int r = 0;


    private static JLabel XGraphOptLabel = new JLabel("X: ");
    private static JLabel YGraphOptLabel = new JLabel("Y: ");
    private static JPanel XGraphPanel = new JPanel();
    private static JPanel YGraphPanel = new JPanel();

    private static JButton graphSetButton = new JButton("Set");
    private static JPanel graphButtonPanel;

    private static double angleStep;

    //private static JLabel xVals = new JLabel();
    //private static JLabel yVals = new JLabel();

    private static int chargeXCoordinate;
    private static int chargeYCoordinate;

    private static int startCoordX;
    private static int startCoordY;

    private static double radius;
    private static double startAngle;
    private static double startAngleVal = 0;

    private static double xRoot;
    private static double yRoot;
    private static double f = 0;

    private static double constxRoot;
    private static double constyRoot;

    private static double angVelocity;

    private static double angle = calcSqAngle(0);

    private static double straightLengthX = 0;
    private static double straightLengthY = 0;

    private static double velocX;
    private static double velocY;

    private static boolean graphSetClicked = false;
    private static boolean chargeSetClicked = false;
    private static boolean startClicked = false;

    private static boolean getRulerClicked = false;

    private static int mPatchAX = 50;
    private static int mPatchAY = 50;
    private static int mPatchBX = 150;
    private static int mPatchBY = 50;

    private static int targetXCoord;
    private static int targetYCoord;

    private static void setLists()
    {
        double val = -10;
        System.out.println("enterred");
        for(double i = 0.0; i <= 20; i++)
        {
            if(i <= 10)
            {
                x.add(val + i);
                y.add(val + i);
            }
            else if(i > 10)
            {
                x.add(i - 10);
                y.add(i - 10);
            }
        }
    }

    private static void createFrame()
    {
        frame.setTitle("Magnet Lab");
        frame.setSize(dimension_X, dimension_Y);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.setIconImage(LogoImage.getImage());
        frame.setResizable(false);
        boundSetter();
        clkdBoundSetter();
        background.setBounds(0,0,1000,700);
        frame.add(background);

        background.add(startButtonLab);
        background.add(startButtonObstcl);
        background.add(startButtonObstclClkd);
        background.add(startButtonLabClkd);

        startButtonObstclClkd.setVisible(false);
        startButtonLabClkd.setVisible(false);

        startButtonLab.addActionListener(new startLabButtonListener());
        startButtonLabClkd.addActionListener(new startLabButtonListener());
        startButtonLab.addMouseListener(new startLabButtonMsListener());
        startButtonLabClkd.addMouseListener(new startLabButtonMsListener());

        startButtonObstcl.addActionListener(new startObstclButtonListener());
        startButtonObstclClkd.addActionListener(new startObstclButtonListener());
        startButtonObstcl.addMouseListener(new startObstclButtonMsListener());
        startButtonObstclClkd.addMouseListener(new startObstclButtonMsListener());

        frame.setVisible(true);
    }



    private static int c = 0;

    private static boolean jobStopped = false;


    //private static Timer timer;

    @Override
    public void run()
    {
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
        createFrame();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        //Scanner keyboard = new Scanner(System.in);
        super.paintComponent(g);
        System.out.println("count:" + count);
        //for(int i = 0; i < x.size(); i++)
        //System.out.println(x.get(i));
        System.out.println("-----------enterred---------");
        if(c == 0 || reset)
        {
            setLists();
            reset = false;
        }
        //while(true) {
        //Graphics2D g2db = (Graphics2D) g;
        Graphics2D g2d = (Graphics2D) g;
        //g2db.setColor(Color.BLUE);
        //g2d.setColor(Color.BLACK);
        int value = dimension_X - 200;
        //System.out.println(value / 2);
        for (int n = 0; n <= totStepX; n++) {
            if (x.get(n) != 0) {
                //System.out.println(x.get(n));

                //g.setColor(Color.LIGHT_GRAY.darker());
                //g.setColor(Color.BLUE.brighter().brighter());

                if(n == 0 && firstSkipX != 0) {
                    xx += firstSkipX;
                    //n++;
                }
                g.setColor(Color.LIGHT_GRAY);
                p1.setLocation(xx, xy1);
                p2.setLocation(xx, xy2);
                //System.out.println("xx" + xx);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                xx += pixelsPerStepX;
            } else {
                //System.out.println("here");
                g.setColor(Color.BLACK);
                p1.setLocation(xx, xy1);
                p2.setLocation(xx, xy2);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);

                for(int i = 0; i <= totStepY; i++)
                {
                    g.drawString("" + y.get(y.size() - i - 1), xx + 5, yy);
                    yy+=pixelsPerStepY;
                }

                if(count == 0) {
                    System.out.println("sC set");
                    chargeXCoordinate = xx - 6;
                    startCoordX = xx - 6;
                }
                xx += pixelsPerStepX;
                yy = 0;
            }
        }
        xx = 0;

        for (int n = 0; n <= totStepY; n++) {
            if (y.get(n) != 0) {
                //System.out.println(y.get(n));

                //g.setColor(Color.LIGHT_GRAY.darker());
                //g.setColor(Color.BLUE.brighter().brighter());
                if(n == 0 && secondSkipY != 0) {
                    yy += secondSkipY;
                    //n++;
                }
                //System.out.println("firstSkip:" + firstSkipY);
                g.setColor(Color.LIGHT_GRAY);
                p1.setLocation(yx1, yy);
                p2.setLocation(yx2, yy);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                yy += pixelsPerStepY;
            } else {
                //System.out.println("here");
                g.setColor(Color.BLACK);
                p1.setLocation(yx1, yy);
                p2.setLocation(yx2, yy);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                for(int i = 0; i <= totStepX; i++)
                {
                    if(x.get(i) != 0) {
                        g.drawString("" + x.get(i), xx, yy + 15);
                    }
                    xx += pixelsPerStepX;
                }
                if(count == 0) {
                    chargeYCoordinate = yy - 6;
                    startCoordY = yy - 6;
                }
                yy += pixelsPerStepY;
                xx = 0;
            }
        }

        yy = 0;
        xx = 0;
        System.out.println("size:" + graphPanel.getSize().width + "," + graphPanel.getSize().height);

        //System.out.println("repeated");

        /*else
        {
            refresh = true;
            paint(g);
        }*/

        //g.setColor(Color.BLUE);
        //g.fillOval(startCoordX - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY + 6, startCoordY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY + 7, pixelsPerStepY *6, pixelsPerStepY *6);
        g.setColor(Color.LIGHT_GRAY.darker());
        System.out.println("xc"+chargeXCoordinate+"yc"+chargeYCoordinate);
        g.fillOval(chargeXCoordinate, chargeYCoordinate, 12, 12);

        if(chargeXCoordinate != startCoordX || chargeYCoordinate != startCoordY)
        {
            //g.drawRec
        }
        /*
        System.out.println("------------------------");
        System.out.println("X:" + chargeXCoordinate);
        System.out.println("Y:" + chargeYCoordinate);
        System.out.println("-------------------------");*/
        /*for(double i = 0; i <= startAngleVal; i+=0.1)
        {
            double xLoc = radius * pixelsPerStepX;
            double trigX;
            if(startAngleVal <= 90) {
                trigX=xLoc * Math.cos(Math.toRadians(startAngleVal + 270));
            }
            else
                trigX = xLoc * Math.cos(Math.toRadians(startAngleVal - 90));
            double newPositionX = startCoordX + trigX;

            double yLocM = radius * pixelsPerStepY;
            double trigY;
            if(startAngleVal <= 90) {
                trigY=yLocM * Math.sin(Math.toRadians(startAngleVal + 270));
            }
            else
                trigY = yLocM * Math.sin(Math.toRadians(startAngleVal - 90));
            //System.out.println("angleval:" + Math.sin(Math.toRadians(startAngle)));
            double yLoc = startCoordY - yLocM;
            double newPositionY = yLoc - trigY;

            g.setColor(Color.BLUE);
            g.drawRect((int) newPositionX, (int) newPositionY, 1,1);
        }*/
/*
        double xHS = radius * 2 * Math.sin(theta);
        double yHS = radius * 2 * Math.cos(theta);
        double xHP = xHS * pixelsPerStepX / Integer.parseInt(stepsX.getText());
        double yHP = yHS * pixelsPerStepY / Integer.parseInt(stepsY.getText());
        double xHSq = xHP * xHP;
        double yHSq = yHP * yHP;
        double hSq = xHSq + yHSq;
        double hP = Math.sqrt(hSq);

        double xWS = radius * 2 * Math.cos(theta);
        double yWS = radius * 2 * Math.sin(theta);
        double xWP = xWS * pixelsPerStepX / Integer.parseInt(stepsX.getText());
        double yWP = yWS * pixelsPerStepY / Integer.parseInt(stepsY.getText());
        double xWSq = xWP * xWP;
        double yWSq = yWP * yWP;
        double wSq = xWSq + yWSq;
        double wP = Math.sqrt(wSq);
*/
        double widt = radius * pixelsPerStepX * 2 / Double.parseDouble(stepsX.getText());
        double widtH = widt / 2;
        double widthH = (int) widtH;
        double heigh = radius * pixelsPerStepY * 2 / Double.parseDouble(stepsY.getText());
        double height = (int) heigh;
        System.out.println("pPerStepX"+pixelsPerStepX);
        System.out.println("pPerStepY" + pixelsPerStepY);
        double heightH = heigh / 2;
        //double wH = wP / 2;
        //double xps = startCoordX - wH;
        double widthS = widthH * widthH;
        double heightS = height * height;
        double lsq = widthS + heightS;
        double l = Math.sqrt(lsq);
        double xpsM = l * Math.cos(Math.toRadians(angle));
        double ypsM = l * Math.sin(Math.toRadians(angle));
        //double yps = startCoordY - hP;

        //System.out.println("hP:" + hP + "wP:" + wP);

        //double xps = startCoordX + xpsM;
        //double yps = startCoordY - ypsM;

        double xps = startCoordX - widthH;
        double yps = startCoordY - height;

        double dX = radius * pixelsPerStepX * Math.cos(theta + Math.toRadians(270)) / Double.parseDouble(stepsX.getText());
        double dY = radius * pixelsPerStepY * Math.sin(theta + Math.toRadians(270)) / Double.parseDouble(stepsY.getText());

        double rMY = radius * pixelsPerStepY / Double.parseDouble(stepsY.getText());

        double xCo = xps - dX;
        double yCo = rMY + yps + dY;
        //double dx = radius *

        if(c==0)
        {
            straightLengthX = startCoordX + 6;
            straightLengthY = startCoordY + 6;
            c++;
        }

        g.setColor(Color.BLUE);
        //g2d.rotate(-theta, startCoordX+6, startCoordY+6);
        //g2d.drawArc((int) xps+7, (int) yps + 6, (int) wP, (int) hP, 270, (int) startAngleVal);
        //g.fillOval((int) xCo + 6, (int) yCo + 6, 5, 5);
        System.out.println("------------------------");
        System.out.println("widthH" + widthH);
        System.out.println("------------------------");
        //g.drawArc((int) xps + 7, (int) yps + 6, width, height, 270, (int) startAngleVal);
        if(radius != 0) {
            g.drawArc((int) xCo + 7, (int) yCo + 6, (int) widt, (int) height, 270 + (int) Math.toDegrees(theta), (int) startAngleVal);
        }
        else
        {
            g.drawLine(startCoordX+6, startCoordY+6, (int) straightLengthX, (int) straightLengthY);
        }
        //g2d.drawArc((int) xps + 7, (int) yps + 6, width, height, 270, (int) keyboard.nextInt());

        if(getRulerClicked)
        {
            g.setColor(Color.YELLOW);
            g.fillRect(mPatchAX, mPatchAY, 50,50);
            g.fillRect(mPatchBX, mPatchBY, 50, 50);
            g.setColor(Color.BLACK);
            g.fillRect(mPatchAX+45,mPatchAY,5,5);
            g.fillRect(mPatchBX,mPatchBY,5,5);
            g.drawString("Left", mPatchAX + 5, mPatchAY + 30);
            g.drawString("Right", mPatchBX + 15, mPatchBY + 30);
        }

        System.out.println("theta:" + theta);
        count++;
        if(easy)
        {
            System.out.println("ez:" + easy);
            int qt = 6 * pixelsPerStepY;
            targetXCoord = 314 - 4;
            targetYCoord = 314 - 4 - qt;
            g2d.setColor(Color.BLACK);
            g2d.fillOval(targetXCoord, targetYCoord, 20, 20);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(targetXCoord + 5, targetYCoord + 5, 10, 10);
            g2d.setColor(Color.GRAY);
            double wdt = pixelsPerStepY * 1.25;
            int leng = pixelsPerStepX * 12 - 64;
            g2d.fillRect(300 - 55, 215, leng, (int) wdt);
            if(chargeXCoordinate + 3 >= targetXCoord && chargeXCoordinate + 3 <= targetXCoord + 20 && chargeYCoordinate + 3 >= targetYCoord && chargeYCoordinate + 3 <= targetYCoord + 20) {
                jobStopped = true;
                double endTime = System.nanoTime();
                double timeLengthNS = endTime - startTime;
                double timeLength = timeLengthNS / 1000000000;
                BigDecimal bd = new BigDecimal(timeLength);
                bd = bd.round(new MathContext(3));

                //timeLength = Math.round(timeLength * 100.0) / 100.0;
                JOptionPane.showMessageDialog(mainPanel2, "Congratulations!! You hit the target in " + bd.doubleValue() + "s");
            }
            else if(chargeXCoordinate + 3 >= 300 - 55 && chargeXCoordinate + 3 <= 300 - 15 + leng && chargeYCoordinate + 3 >= 215 && chargeYCoordinate + 3 <= 215 + (int) wdt) {
                jobStopped = true;
                JOptionPane.showMessageDialog(mainPanel2, "Crash");
            }
            //easy = false;
        }
        else if(hard)
        {
            System.out.println("hard:" + hard);
            targetXCoord = 550;
            targetYCoord = 457;
            g2d.setColor(Color.BLACK);
            g2d.fillOval(targetXCoord, targetYCoord, 20, 20);
            g2d.setColor(Color.WHITE);
            g2d.fillOval(targetXCoord + 5, targetYCoord + 5, 10, 10);
            g2d.setColor(Color.GRAY);
            double wdt1 = pixelsPerStepY * 0.5;
            int leng1 = pixelsPerStepX * 12 - 64;
            g2d.fillRect(500, 290, (int) wdt1, (int) leng1);
            double wdt2 = pixelsPerStepY * 1.15;
            int leng2 = pixelsPerStepX * 12 - 64;
            g2d.fillRect(190, 490, leng2, (int) wdt2);
            if(chargeXCoordinate + 3 >= targetXCoord && chargeXCoordinate + 3 <= targetXCoord + 20 && chargeYCoordinate + 3 >= targetYCoord && chargeYCoordinate + 3 <= targetYCoord + 20)
            {
                jobStopped = true;
                double endTime = System.nanoTime();
                double timeLengthNS = endTime - startTime;
                double timeLength = timeLengthNS / 1000000000;
                BigDecimal bd = new BigDecimal(timeLength);
                bd = bd.round(new MathContext(3));

                //timeLength = Math.round(timeLength * 100.0) / 100.0;
                JOptionPane.showMessageDialog(mainPanel2, "Congratulations!! You hit the target in " + bd.doubleValue() + "s");
            }
            else if(chargeXCoordinate + 3 >= 500 && chargeXCoordinate + 3 <= 500 + (int) wdt1 && chargeYCoordinate + 3 >= 290 && chargeYCoordinate + 3 <= 290 + leng1)
            {
                jobStopped = true;
                JOptionPane.showMessageDialog(mainPanel2, "Crash");
            }
            else if(chargeXCoordinate + 3 >= 190 && chargeXCoordinate + 3 <= 190 + leng2 && chargeYCoordinate + 3 >= 490 && chargeYCoordinate + 3 <= 490 + wdt2)
            {
                jobStopped = true;
                JOptionPane.showMessageDialog(mainPanel2, "Crash");
            }
        }
    }


    private static void calcRadius(double mass, double charge, double velocityX, double velocityY)
    {
        if(Math.abs(charge) > 0) {
            double vXSQ = velocityX * velocityX;
            double vYSQ = velocityY * velocityY;
            double velocitySQ = vXSQ + vYSQ;
            double velocity = Math.sqrt(velocitySQ);

            double numerator = mass * velocity;
            double denominator = Math.abs(charge) * magneticField;

            System.out.println("numerator " + numerator + "/" + "denominator " + denominator);

            radius = numerator / denominator;
            System.out.println("radius:" + radius);
        }
        else
        {
            radius = 0;
        }
    }

/*
    public class dynamicDisplay extends Main imp Runnable
    {
        public void run()
        {
            while(true)
            {
                if(jobStart)
                {
                    for (double i = 0; i <= 360.1; i += angleStep) {
                        createChargeCircleStep();
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        //invokeRepaint();
                        //graphPanel.repaint();

                        graphPanel.paintImmediately(0, 0, 637, 637);
                        //graphPanel.validate();
                        startAngle += angleStep;
                        startAngleVal += angleStep;
                        System.out.println("startAngle" + startAngle);
                        if (startAngle <= 360.009 && startAngle >= 360)
                            startAngle = 0;
                    }
                }
                jobStart = false;
                jobDone = true;
            }
        }
    }
/*
    public class dynamicDisplay extends Main implements Runnable
    {
        public void run()
        {

        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            System.out.println("enterred");
            //for(int i = 0; i < x.size(); i++)
            //System.out.println(x.get(i));
            if(count == 0)
            {
                setLists();
            }
            //while(true) {
            //Graphics2D g2db = (Graphics2D) g;
            //Graphics2D g2d = (Graphics2D) g;
            //g2db.setColor(Color.BLUE);
            //g2d.setColor(Color.BLACK);
            int value = dimension_X - 200;
            //System.out.println(value / 2);
            for (int n = 0; n <= totStepX; n++) {
                if (x.get(n) != 0) {
                    //System.out.println(x.get(n));

                    //g.setColor(Color.LIGHT_GRAY.darker());
                    //g.setColor(Color.BLUE.brighter().brighter());

                    if(n == 0 && firstSkipX != 0) {
                        xx += firstSkipX;
                        //n++;
                    }
                    g.setColor(Color.LIGHT_GRAY);
                    p1.setLocation(xx, xy1);
                    p2.setLocation(xx, xy2);
                    //System.out.println("xx" + xx);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    xx += pixelsPerStepX;
                } else {
                    //System.out.println("here");
                    g.setColor(Color.BLACK);
                    p1.setLocation(xx, xy1);
                    p2.setLocation(xx, xy2);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);

                    for(int i = 0; i <= totStepY; i++)
                    {
                        g.drawString("" + y.get(y.size() - i - 1), xx + 5, yy);
                        yy+=pixelsPerStepY;
                    }
                    if(count == 0) {
                        chargeXCoordinate = xx - 6;
                        startCoordX = chargeXCoordinate;
                    }
                    xx += pixelsPerStepX;
                    yy = 0;
                }
            }
            xx = 0;
            for (int n = 0; n <= totStepY; n++) {
                if (y.get(n) != 0) {
                    //System.out.println(y.get(n));

                    //g.setColor(Color.LIGHT_GRAY.darker());
                    //g.setColor(Color.BLUE.brighter().brighter());
                    if(n == 0 && secondSkipY != 0) {
                        yy += secondSkipY;
                        //n++;
                    }
                    //System.out.println("firstSkip:" + firstSkipY);
                    g.setColor(Color.LIGHT_GRAY);
                    p1.setLocation(yx1, yy);
                    p2.setLocation(yx2, yy);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    yy += pixelsPerStepY;
                } else {
                    //System.out.println("here");
                    g.setColor(Color.BLACK);
                    p1.setLocation(yx1, yy);
                    p2.setLocation(yx2, yy);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    for(int i = 0; i <= totStepX; i++)
                    {
                        if(x.get(i) != 0) {
                            g.drawString("" + x.get(i), xx, yy + 15);
                        }
                        xx += pixelsPerStepX;
                    }
                    if(count == 0) {
                        chargeYCoordinate = yy - 6;
                        startCoordY = chargeYCoordinate;
                    }
                    yy += pixelsPerStepY;
                    xx = 0;
                }
            }

            yy = 0;
            xx = 0;
            System.out.println("size:" + graphPanel.getSize().width + "," + graphPanel.getSize().height);

            //System.out.println("repeated");

        /*else
        {
            refresh = true;
            paint(g);
        }

            //g.setColor(Color.BLUE);
            //g.fillOval(startCoordX - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY + 6, startCoordY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY - pixelsPerStepY + 7, pixelsPerStepY *6, pixelsPerStepY *6);
            g.setColor(Color.LIGHT_GRAY.darker());
            g.fillOval(chargeXCoordinate, chargeYCoordinate, 12, 12);

            if(chargeXCoordinate != startCoordX || chargeYCoordinate != startCoordY)
            {
                //g.drawRec
            }
        /*
        System.out.println("------------------------");
        System.out.println("X:" + chargeXCoordinate);
        System.out.println("Y:" + chargeYCoordinate);
        System.out.println("-------------------------");
            count++;
        }
    }
*/

    private static void calcTheta()
    {
        double vX = vXV;
        double vY = vYV;
        theta = Math.atan2(vY, vX);
    }

    private static void createChargeCircleStep() {
        ///////////////////
        //radius = 3;
        ///////////////////
        System.out.println("----======yRoot========------:" + yRoot);
        double xLoc = radius * pixelsPerStepX / Integer.parseInt(stepsX.getText());

        double trigX = xLoc * Math.cos(Math.toRadians(startAngle));
        double newPositionX = xRoot + trigX;
        chargeXCoordinate = (int) newPositionX;

        double yLocM = radius * pixelsPerStepY / Integer.parseInt(stepsY.getText());
        double trigY = yLocM * Math.sin(Math.toRadians(startAngle));
        //System.out.println("angleval:" + Math.sin(Math.toRadians(startAngle)));
        f++;
        double newPositionY = yRoot - trigY;
        chargeYCoordinate = (int) newPositionY;
    }

    private static void createMainPanel() throws InterruptedException {
        mainPanel.setPreferredSize(new Dimension(dimension_X, dimension_Y));
        mainPanel.setLayout(new BoxLayout(mainPanel, LINE_AXIS));
        if(q == 0) {
            graphPanel = new Main();
        }
        //mouseMeasure g = new mouseMeasure();
        graphPanel.addMouseListener(new pressTapes());
        graphPanel.addMouseMotionListener(new dragTapes());
        //graphPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, PAGE_AXIS));
        //graphPanel.setBackground(Color.RED);
        actionPanel.setPreferredSize(new Dimension(dimension_X - 200, dimension_Y));
        infoPanel.setPreferredSize(new Dimension(200, dimension_Y));
        infoPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        actionPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        //infoPanel.setBackground(Color.RED);
        //JButton test = new JButton("test");
        //infoPanel.add(test);


        //chargeSetButton = new JButton(chargeSetImage);
        //chargeSetButton.addActionListener(new openChargeOptions());
        startButton = new JButton(startButtonImage);


        //chargeSetButton.setBackground(Color.WHITE);
        startButton.setBackground(Color.WHITE);

        startButton.addActionListener(new start());

        //measureButton = new JButton(measureButtonImage);
        exitButton = new JButton(exitButtonImage);
        exitButton.addActionListener(new exit());
        //graphButton = new JButton(graphButtonImage);
        //graphButton.addActionListener(new openGraphOptions());

        stopButton = new JButton(stopButtonImage);
        stopButton.setBackground(Color.WHITE);

        stopButton.addActionListener(new stopJob());
        //mainTB.add(measureButton);
        //mainTB.add(graphButton);
        //mainTB.add(chargeSetButton);
        if (q == 0) {
            mainTB.add(startButton);
            //createStopButton();
            mainTB.add(stopButton);
            //mainTB.add(stopButton);
            //mainTB.add(showV);
            //Thread.sleep(1000);
            mainTB.add(exitButton);


            mainTB.setBounds(0, 0, dimension_X, 40);
        }

        infoPanel.add(mainTB);

        infoPanel.add(Box.createRigidArea(new Dimension(200, 30)));
        if (q == 0) {
            createChargeOptions();
        }
        if(a == 0) {
            createRulerPanel();
            createGraphOptions();
        }

        infoPanel.setLayout(new BoxLayout(infoPanel, PAGE_AXIS));
        openChargeOptions();
        //infoPanel.add(Box.createRigidArea(new Dimension(200,0)));
        openGraphOptions();
        openRulerPanel();
        infoPanel.add(Box.createRigidArea(new Dimension(200, 70)));
        showVPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        showVPanel.add(showV);
        //infoPanel.add(showVPanel);

        //actionPanel.add(mainTB);
        actionPanel.add(graphPanel);
        mainPanel.add(infoPanel);
        mainPanel.add(actionPanel);
/*
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("a");
                if(startAngleVal <= 360) {
                    System.out.println("t");
                    createChargeCircleStep();
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    //invokeRepaint();
                    graphPanel.repaint();
                    //graphPanel.paintImmediately(0, 0, 637, 637);
                    //graphPanel.validate();
                    startAngle += angleStep;
                    startAngleVal += angleStep;
                    System.out.println("startAngle" + startAngle);
                    if (startAngle <= 360.009 && startAngle >= 360)
                        startAngle = 0;
                }
                else
                    jobDone = true;

            }
        });*/

    }

    private static boolean mousePressed1 = false;
    private static boolean mousePressed2 = false;
    private static int valX;
    private static int valY;
    /*
    private static void moveTapes()
    {
        int posX;
        int posY;
        int diffX;
        int diffY;
        System.out.println("VX" + valX);
        if(valX >= mPatchAX && valX <= mPatchAX + 50 && valY >= mPatchAY && valY <= mPatchAY + 50) {
            posX = valX;
            posY = valY;
            while (mousePressed1) {
                System.out.println("region pressed");
                mousePressed2 = true;
                if(valX != posX || valY != posY)
                {
                    diffX = valX - posX;
                    diffY = valY - posY;
                    mPatchAX += diffX;
                    mPatchAY += diffY;
                    graphPanel.paintImmediately(0,0, 637, 637);
                }

            /*
            if(valX >= mPatchAX && valX <= mPatchAX + 50 && valY >= mPatchAY && valY <= mPatchAY + 50)
            {
                posX = valX;
                posY = valY;
                System.out.println("mouse pressed in region");
                while(e.)
                {
                    valX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                    valY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                    if(valX != posX || valY != posY)
                    {
                        diffX = valX - posX;
                        diffY = valY - posY;
                        mPatchAX += diffX;
                        mPatchAY += diffY;
                    }
                    graphPanel.paintImmediately(0,0, 637, 637);
                }
            }
            else if(valX >= mPatchBX && valX <= mPatchBX + 50 && valY >= mPatchBY && valY <= mPatchBY + 50)
            {
                while(true)
                {

                }
            }
            }
            mousePressed2 = false;
        }
        else if(valX >= mPatchBX && valX <= mPatchBX + 50 && valY >= mPatchBY && valY <= mPatchBY + 50)
        {
            posX = valX;
            posY = valY;
            while(mousePressed1)
            {
                mousePressed2 = true;
                if(valX != posX || valY != posY)
                {
                    diffX = valX - posX;
                    diffY = valY - posY;
                    mPatchBX += diffX;
                    mPatchBY += diffY;
                }
                graphPanel.paintImmediately(0,0, 637, 637);
            }
            mousePressed2 = false;
        }
    }

    private static class mouseMeasure implements MouseListener
    {
        //int valX;
        //int valY;
        int posX;
        int posY;
        int diffX;
        int diffY;
        public void mousePressed(MouseEvent e) {
            mousePressed1 = true;
            valX = (int) e.getPoint().getX();
            valY = (int) e.getPoint().getY();
            System.out.println("pressed");
            while(mousePressed2) {
                valX = (int) e.getPoint().getX();

                System.out.println("valX" + valX);
                valY = (int) e.getPoint().getY();
            }

            //valX = (int) MouseInfo.getPointerInfo().getLocation().getX();
            //valY = (int) MouseInfo.getPointerInfo().getLocation().getY();
            valX = (int) e.getPoint().getX();
            valY = (int) e.getPoint().getY();
            System.out.println("mousePressed");
            System.out.println("mouseLoc" + valX);
            System.out.println("mPatchAX" + mPatchAX);
            if(valX >= mPatchAX && valX <= mPatchAX + 50 && valY >= mPatchAY && valY <= mPatchAY + 50)
            {
                posX = valX;
                posY = valY;
                System.out.println("mouse pressed in region");
                while(e.)
                {
                    valX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                    valY = (int) MouseInfo.getPointerInfo().getLocation().getY();
                    if(valX != posX || valY != posY)
                    {
                        diffX = valX - posX;
                        diffY = valY - posY;
                        mPatchAX += diffX;
                        mPatchAY += diffY;
                    }
                    graphPanel.paintImmediately(0,0, 637, 637);
                }
            }
            else if(valX >= mPatchBX && valX <= mPatchBX + 50 && valY >= mPatchBY && valY <= mPatchBY + 50)
            {
                while(true)
                {

                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            mousePressed1 = false;
            valX = 0;
            valY = 0;
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    }
*/
    private static class stopJob implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jobStopped = true;
        }
    }

    private static double cV = 0;
    private static double mV = 0;
    private static double vXV = 0;
    private static double vYV = 0;

    private static void setChargeOpt()
    {
        if(chargeSetClicked)
        {

            try {
                cV = Double.parseDouble(chargeValue.getText());
            }
            catch(NumberFormatException e)
            {
                cV = 0;
            }
            try {
                mV = Double.parseDouble(massValue.getText());
            }
            catch(NumberFormatException e)
            {
                mV = 0;
            }
            try {
                vXV = Double.parseDouble(velocityValueX.getText());
            }
            catch(NumberFormatException e)
            {
                vXV = 0;
            }
            try {
                vYV = Double.parseDouble(velocityValueY.getText());
            }
            catch(NumberFormatException e)
            {
                vYV = 0;
            }


            straightLengthX = startCoordX+6;
            straightLengthY = startCoordY+6;
            calcRadius(mV, cV, vXV, vYV);
            calcAngVelocity();
            calcTheta();
            //System.out.printf("theta---------"+theta);
            calcChargeAngle();
            startAngleVal = 0;

            angle = calcSqAngle(Math.toDegrees(theta));
            graphPanel.paintImmediately(0,0,637,637);
            System.out.println("startCoordX----------" + startCoordX);
            f = 0;
            chargeSetClicked = false;
        }
    }

    private static class setChargeOptions implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            chargeSetClicked = true;
        }
    }
/*
    private static void createStopButton()
    {
        stopButton = new JButton(stopButtonImage);
        stopButton.setBackground(Color.WHITE);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    stopButton.addActionListener(new stopJob());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            class stopJob implements ActionListener
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("jobStop clicked");
                    jobDone = true;
                }
            }
        });*/

/*
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        jobDone = true;
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }*/

    private static void setGraphOpt() throws InterruptedException {
        if(graphSetClicked)
        {
            //createChargeCircleStep();
            //xRoot = constxRoot;
            //yRoot = constyRoot;

            createGraphPanel();
            count = 0;
            c = 0;
            graphPanel.paintImmediately(0,0,637,637);
            //straightLengthX = startCoordX + 6;
            //straightLengthY = startCoordY + 6;
            //graphPanel.paintImmediately(0,0,637,637);


            calcTheta();
            calcChargeAngle();

            angle = calcSqAngle(Math.toDegrees(theta));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //System.out.println("clicked");
            System.out.println("startCoordX==================" + startCoordX);
            count = 0;

            //graphPanel.validate();
            f = 0;
            graphSetClicked = false;
        }
    }

    private static class setGraphOptions implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            graphSetClicked = true;
        }
    }
    private static boolean mousePressed = false;
    private static class pressTapes implements MouseListener
    {
        double xVal;
        double yVal;
        @Override
        public void mouseClicked(MouseEvent e) {
            e.consume();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            valX = (int) e.getPoint().getX();
            valY = (int) e.getPoint().getY();
            if(valX >= mPatchAX && valX <= mPatchAX + 50 && valY >= mPatchAY && valY <= mPatchAY + 50)
            {
                System.out.println("passed");
                mousePressed1 = true;
            }
            else if(valX >= mPatchBX && valX <= mPatchBX + 50 && valY >= mPatchBY && valY <= mPatchBY + 50)
            {
                mousePressed2 = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(mousePressed1)
                mousePressed1 = false;
            else if(mousePressed2)
                mousePressed2 = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            e.consume();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.consume();
        }
    }
    private static int X1 = 0;
    private static int Y1 = 0;
    private static int X2 = 0;
    private static int Y2 = 0;
    private static double x1 = 0;
    private static double XD = 0;
    private static double YD = 0;
    private static double XSq = 0;
    private static double YSq = 0;
    private static double LSq = 0;
    private static double L = 0;

    private static class dragTapes implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if(mousePressed1 && getRulerClicked)
            {
                System.out.println("Here");
                X1 = e.getX();
                Y1 = e.getY();
                //int diffX = X - posX;
                //int diffY = Y - posY;
                mPatchAX = X1;
                mPatchAY = Y1;
                graphPanel.paintImmediately(0,0, 637, 637);
            }
            else if(mousePressed2 && getRulerClicked)
            {
                System.out.println("Here");
                X2 = e.getX();
                Y2 = e.getY();
                //int diffX = X - posX;
                //int diffY = Y - posY;
                mPatchBX = X2;
                mPatchBY = Y2;
                graphPanel.paintImmediately(0,0, 637, 637);
            }
            if(getRulerClicked) {
                x1 = X1 + 50;
                System.out.println("X1 " + x1);
                System.out.println("X2 " + X2);
                XD = Math.abs(x1 - X2) * Double.parseDouble(stepsX.getText()) / pixelsPerStepX;
                YD = Math.abs(Y1 - Y2) * Double.parseDouble(stepsY.getText()) / pixelsPerStepY;
                XSq = XD * XD;
                YSq = YD * YD;
                LSq = XSq + YSq;
                L = Math.sqrt(LSq);
                L = Math.round(L * 100.0) / 100.0;
                rulerValue.setText("" + L);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            e.consume();
        }
    }

    private static void accessController() throws InterruptedException {
        while(true)
        {
            Thread.sleep(25);
            setGraphOpt();
            Thread.sleep(25);
            setChargeOpt();
            Thread.sleep(25);
            startOsc();
            Thread.sleep(25);
            //moveTapes();
        }
    }

    public static void calcChargeAngle()
    {
        startAngle = Math.toDegrees(theta) + 180 + 90;
        if(startAngle >= 360)
        {
            startAngle-=360;
        }
        double xLoc = radius * pixelsPerStepX / Double.parseDouble(stepsX.getText());
        double trigX = xLoc * Math.cos(Math.toRadians(startAngle));
        if (f == 0) {
            xRoot = startCoordX - trigX;
        }
        double yLocM = radius * pixelsPerStepY / Double.parseDouble(stepsY.getText());
        double trigY = yLocM * Math.sin(Math.toRadians(startAngle));
        //System.out.println("angleval:" + Math.sin(Math.toRadians(startAngle)));
        if (f == 0) {
            yRoot = startCoordY + trigY;
        }
            /*
            System.out.println("xRoot:" + xRoot);
            System.out.println("xPos" + startCoordX);
            System.out.println("yRoot:" + yRoot);
            System.out.println("yPos:" + startCoordY);*/
        f++;
    }

    private static void startOsc()
    {
        if(startClicked)
        {
            System.out.println("start clicked");
            //if(velocityValueX.getText() != null || velocityPanelY != null) {
            if (Double.parseDouble(velocityValueX.getText()) != 0 || Double.parseDouble(velocityValueY.getText()) != 0) {
                startAngleVal = 0;
                straightLengthX = startCoordX + 6;
                straightLengthY = startCoordY + 6;
                System.out.println("------------------------");
                System.out.println("X:" + chargeXCoordinate);
                System.out.println("Y:" + chargeYCoordinate);
                System.out.println("-------------------------");
                //timer.start();
                    /*
                    System.out.println("passed");
                    while(!jobDone)
                    {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    timer.stop();*/
                ///////////////////////////////////////////////////

                if (radius != 0) {
                    for (double i = 0; i <= 360.1; i += angleStep) {
                        if(!jobStopped) {
                            createChargeCircleStep();
                            try {
                                Thread.sleep(0);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            //invokeRepaint();
                            //graphPanel.repaint();

                            graphPanel.paintImmediately(0, 0, 637, 637);
                            //graphPanel.validate();
                            if (Double.parseDouble(chargeValue.getText()) > 0) {
                                startAngle += angleStep;
                                startAngleVal += angleStep;
                            } else if (Double.parseDouble(chargeValue.getText()) < 0) {
                                startAngle -= angleStep;
                                startAngleVal -= angleStep;
                            }

                            System.out.println("startAngle" + startAngle);
                            if (startAngle <= 360.009 && startAngle >= 360)
                                startAngle = 0;
                        }
                        else {
                            startAngle = Math.toDegrees(theta) + 180 + 90;
                            startAngleVal = 0;
                            chargeXCoordinate = startCoordX;
                            chargeYCoordinate = startCoordY;
                            jobStopped = false;
                            break;
                        }
                    }
                }

                else
                {
                    velocX = Double.parseDouble(velocityValueX.getText()) * pixelsPerStepX / 10;
                    velocY = Double.parseDouble(velocityValueY.getText()) * pixelsPerStepY / 10;
                    velocX /= Double.parseDouble(stepsX.getText());
                    velocY /= Double.parseDouble(stepsY.getText());
                    double valX = chargeXCoordinate;
                    double valY = chargeYCoordinate;
                    while(true) {
                        if(!jobStopped) {
                            valX += velocX;
                            valY -= velocY;
                            chargeXCoordinate = (int) valX;
                            chargeYCoordinate = (int) valY;
                            straightLengthX += velocX;
                            straightLengthY -= velocY;


                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            graphPanel.paintImmediately(0, 0, 637, 637);
                        }
                        else {
                            chargeXCoordinate = startCoordX;
                            chargeYCoordinate = startCoordY;
                            straightLengthX = startCoordX + 6;
                            straightLengthY = startCoordY + 6;
                            jobStopped = false;
                            break;
                        }
                    }
                }

                f = 0;
                chargeXCoordinate = startCoordX;
                chargeYCoordinate = startCoordY;
                graphPanel.repaint();
                //count = 0;
                System.out.println("------------------------");
                //System.out.println("X:" + chargeXCoordinate);
                //System.out.println("Y:" + chargeYCoordinate);
                //System.out.println("-------------------------");

            }
            //}
            startClicked = false;
        }
    }

    private static double startTime = 0;
    private static double endTime = 0;

    private static class start implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            startTime = System.nanoTime();
            startClicked = true;
        }
    }

    private static void calcAngVelocity()
    {
        double vXSQ = vXV * vXV;
        double vYSQ = vYV * vYV;
        double velocitySQ = vXSQ + vYSQ;
        double velocity = Math.sqrt(velocitySQ);

        angVelocity = velocity / radius;
        angleStep = angVelocity / 10;
    }
    private static boolean reset = false;
    private static class exit implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("lab" + lab);
            c = 0;
            reset = true;
            startAngleVal = 0;
            minX.setText("-10");
            minY.setText("-10");
            maxX.setText("10");
            maxY.setText("10");
            minYVal = 0;
            minXVal = 0;
            maxXVal = 0;
            maxYVal = 0;
            getRulerClicked = false;
            count = 0;
            chargeValue.setText("0");
            velocityValueX.setText("0");
            velocityValueY.setText("0");
            massValue.setText("0");
            try {
                rulerValue.setText("");
            }
            catch(NullPointerException f)
            {

            }
            createGraphPanel();
            graphPanel.paintImmediately(0,0,637,637);
            if(lab) {
                frame.remove(mainPanel);
                frame.repaint();
                frame.validate();
                lab = false;
            }
            else if(obstacle) {
                frame.remove(mainPanel2);
                frame.repaint();
                frame.validate();
                obstacle = false;
                if(easy)
                    easy = false;
                if(hard)
                    hard = false;
            }

            frame.add(background);
        }
    }

    private static double calcSqAngle(double angle)
    {
        double angSq = Math.toDegrees(Math.atan(0.5));
        double angCorner = angSq + 90;
        return angCorner + angle;
    }

    private static class openChargeOptions implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            openChargeOptions();
        }
    }

    private static void createGraphOptions()
    {
        boundsPanel = new JPanel();
        boundsPanelX = new JPanel();
        boundsPanelY = new JPanel();
        stepsPanel = new JPanel();

        boundsPanel.setLayout(new BoxLayout(boundsPanel, PAGE_AXIS));
        boundsPanel.setPreferredSize(new Dimension(150, 200));

        minX.setPreferredSize(new Dimension(30,40));
        textX.setPreferredSize(new Dimension(30,40));
        maxX.setPreferredSize(new Dimension(30,40));

        boundsPanelX.setLayout(new BoxLayout(boundsPanelX, LINE_AXIS));
        boundsPanelY.setLayout(new BoxLayout(boundsPanelY, LINE_AXIS));

        boundsPanelX.add(minX);
        boundsPanelX.add(textX);
        boundsPanelX.add(maxX);

        minY.setPreferredSize(new Dimension(30,40));
        textY.setPreferredSize(new Dimension(30,40));
        maxY.setPreferredSize(new Dimension(30,40));

        boundsPanelY.add(minY);
        boundsPanelY.add(textY);
        boundsPanelY.add(maxY);

        stepsX.setPreferredSize(new Dimension(30,40));
        stepsY.setPreferredSize(new Dimension(30,40));

        stepsPanel.setLayout(new BoxLayout(stepsPanel, PAGE_AXIS));
        XGraphPanel.setLayout(new BoxLayout(XGraphPanel, LINE_AXIS));
        XGraphPanel.add(XGraphOptLabel);
        XGraphPanel.add(stepsX);
        stepsPanel.add(XGraphPanel);
        YGraphPanel.setLayout(new BoxLayout(YGraphPanel, LINE_AXIS));
        YGraphPanel.add(YGraphOptLabel);
        YGraphPanel.add(stepsY);
        stepsPanel.add(YGraphPanel);
        stepsPanel.setBorder(BorderFactory.createTitledBorder("Step Size"));

        graphButtonPanel = new JPanel();
        graphButtonPanel.add(graphSetButton);
        graphSetButton.addActionListener(new setGraphOptions());

        boundsPanel.add(boundsPanelX);
        boundsPanel.add(boundsPanelY);
        boundsPanel.add(stepsPanel);
        boundsPanel.add(graphButtonPanel);
        //boundsPanel.add(fillerPanel);
        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Graph Options");
        title.setTitleJustification(TitledBorder.CENTER);
        boundsPanel.setBorder(title);
    }

    private static void openGraphOptions()
    {
        //infoPanel.removeAll();
        //infoPanel.revalidate();
        //infoPanel.repaint();
        if(lab)
        {
            infoPanel.add(boundsPanel);
            infoPanel.validate();
        }
        else if(obstacle)
        {
            infoPanel2.add(boundsPanel);
            infoPanel2.validate();
        }

    }

    private static void createChargeOptions()
    {
        System.out.println("chargePanel enterred");
        chargeSettingPanel = new JPanel();
        setChargePanel = new JPanel();
        setMassPanel = new JPanel();
        setVelocityPanel = new JPanel();
        setButtonPanel = new JPanel();

        chargeSettingPanel.setLayout(new BoxLayout(chargeSettingPanel, PAGE_AXIS));
        setChargePanel.setLayout(new BoxLayout(setChargePanel, LINE_AXIS));
        setMassPanel.setLayout(new BoxLayout(setMassPanel, LINE_AXIS));
        setVelocityPanel.setLayout(new BoxLayout(setVelocityPanel, PAGE_AXIS));

        chargeTitle = new JLabel("Set Charge: ");
        chargeValue = new JTextField("0");
        chargeValue.setPreferredSize(new Dimension(20,20));
        chargeUnit = new JLabel(" C");
        setChargePanel.add(chargeTitle);
        setChargePanel.add(chargeValue);
        setChargePanel.add(chargeUnit);

        massTitle = new JLabel("Set Mass: ");
        massValue = new JTextField("0");
        massValue.setPreferredSize(new Dimension(20,20));
        massUnit = new JLabel(" kg");
        setMassPanel.add(massTitle);
        setMassPanel.add(massValue);
        setMassPanel.add(massUnit);

        velocityTitleX = new JLabel("Set Velocity X: ");
        velocityTitleY = new JLabel("Set Velocity Y: ");
        velocityValueX = new JTextField("0");
        velocityValueY = new JTextField("0");
        velocityValueX.setPreferredSize(new Dimension(40,20));
        velocityValueY.setPreferredSize(new Dimension(40,20));
        velocityUnitX = new JLabel(" m/s");

        velocityPanelX = new JPanel();
        velocityPanelX.setLayout(new BoxLayout(velocityPanelX, LINE_AXIS));
        velocityPanelX.add(velocityTitleX);
        velocityPanelX.add(velocityValueX);
        velocityPanelX.add(velocityUnitX);

        velocityPanelY = new JPanel();
        velocityPanelY.setLayout(new BoxLayout(velocityPanelY, LINE_AXIS));
        velocityPanelY.add(velocityTitleY);
        velocityPanelY.add(velocityValueY);
        velocityUnitY = new JLabel(" m/s");
        velocityPanelY.add(velocityUnitY);

        setVelocityPanel.add(velocityPanelX);
        setVelocityPanel.add(velocityPanelY);

        setButton.addActionListener(new setChargeOptions());

        setButtonPanel.add(setButton);

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Charge Settings");
        title.setTitleJustification(TitledBorder.CENTER);
        chargeSettingPanel.setBorder(title);

        chargeSettingPanel.add(setChargePanel);
        chargeSettingPanel.add(setMassPanel);
        chargeSettingPanel.add(setVelocityPanel);
        chargeSettingPanel.add(setVelocityPanel);
        chargeSettingPanel.add(setButtonPanel);
    }

    private static void openChargeOptions()
    {
        //infoPanel.removeAll();
        //infoPanel.revalidate();
        //infoPanel.repaint();
        System.out.println("obstacle" + obstacle);
        if(lab) {
            infoPanel.add(chargeSettingPanel);
            infoPanel.validate();
        }
        else if(obstacle)
        {
            infoPanel2.add(chargeSettingPanel);
            infoPanel2.validate();
        }
    }

    private static void createRulerPanel()
    {
        measurePanel = new JPanel();
        valuePanel = new JPanel();
        measurePanel.setPreferredSize(new Dimension(200, 700));
        //measurePanel.setLayout(new BoxLayout(measurePanel, PAGE_AXIS));
        valuePanel.setLayout(new BoxLayout(valuePanel, LINE_AXIS));
        //valuePanel.setPreferredSize(new Dimension(150, 50));
        valuePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Measurement"));
        rulerValue = new JLabel();
        rulerValue.setPreferredSize(new Dimension(150, 40));
        valuePanel.add(rulerValue);

        getRuler = new JButton("Get Measuring Tapes");
        getRuler.addActionListener(new getRulerClick());
        deleteRuler = new JButton("Delete Measuring Tapes");
        deleteRuler.addActionListener(new deleteRulerClick());

        measurePanel.add(getRuler);
        measurePanel.add(deleteRuler);
        measurePanel.add(valuePanel);

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Measuring Tape");
        title.setTitleJustification(TitledBorder.CENTER);
        measurePanel.setBorder(title);
    }

    private static class getRulerClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            getRulerClicked = true;
            graphPanel.paintImmediately(0,0,637,637);
        }
    }

    private static class deleteRulerClick implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            getRulerClicked = false;
            graphPanel.paintImmediately(0,0,637,637);
        }
    }

    private static boolean easy = false;
    private static boolean hard = false;

    private static void openRulerPanel()
    {
        infoPanel.add(measurePanel);
        infoPanel.validate();
    }
    private static Component v = Box.createRigidArea(new Dimension(200, 150));
    private static void createObstclPanel()
    {

    }

    private static void createLabPanel()
    {

    }

    private static void createGraphPanel()
    {
        System.out.println("countg:" + count);
        //System.out.println("enterred");
        minXVal = Double.parseDouble(minX.getText());
        maxXVal = Double.parseDouble(maxX.getText());
        minYVal = Double.parseDouble(minY.getText());
        maxYVal = Double.parseDouble(maxY.getText());
        double storedX = 0;
        double storedY = 0;
        domain = maxXVal - minXVal;
        range = maxYVal - minYVal;
        //System.out.println(minXVal);
        double startPoint;
        if(minXVal > 0)
            startPoint = minXVal;
        else if(maxXVal < 0)
            startPoint = maxXVal;
        else
            startPoint = 0.0;
        double numb = startPoint;
        while(numb >= minXVal)
        {
            if(numb - Double.parseDouble(stepsX.getText()) >= minXVal)
            {
                numb -= Double.parseDouble(stepsX.getText());
                //System.out.println(numb);
                storedX = numb;
                stepCountMinX++;
            }
            else
            {
                remainderXmin = numb - minXVal;
                storedX = numb;
                break;
            }
            //System.out.println(1);
        }
        //System.out.println("1w");
        numb = startPoint;
        while(numb < maxXVal)
        {
            if(numb + Double.parseDouble(stepsX.getText()) <= maxXVal)
            {
                numb += Double.parseDouble(stepsX.getText());
                stepCountMaxX++;
                //System.out.println("step:" + stepCountMaxX);
                System.out.println("numb:"+numb);
            }
            else
            {
                System.out.println("maxXVal:"+maxXVal);
                remainderXmax = maxXVal - numb;
                break;
            }
        }
        if(minYVal > 0)
            startPoint = minYVal;
        else if(maxYVal < 0)
            startPoint = maxYVal;
        else
            startPoint = 0.0;
        numb = startPoint;
        System.out.println("start:"+startPoint);
        while(numb > minYVal)
        {
            if(numb - Double.parseDouble(stepsY.getText()) >= minYVal)
            {
                numb -= Double.parseDouble(stepsY.getText());
                stepCountMinY++;
                //System.out.println("numb:"+numb);
            }
            else
            {
                remainderYmin = numb - minYVal;
                break;
            }
        }

        numb = startPoint;
        while(numb < maxYVal)
        {
            if(numb + Double.parseDouble(stepsY.getText()) <= maxYVal)
            {
                numb += Double.parseDouble(stepsY.getText());
                stepCountMaxY++;
                storedY = numb;
                System.out.println("numbmax:" + numb);
            }
            else
            {
                remainderYmax = maxYVal - numb;
                storedY = numb;
                System.out.println("numbrem:" + numb);
                break;
            }
        }

        remainderXMinPercent = remainderXmin / Double.parseDouble(stepsX.getText());
        remainderXMaxPercent = remainderXmax / Double.parseDouble(stepsX.getText());
        remainderYMinPercent = remainderYmin / Double.parseDouble(stepsY.getText());
        remainderYMaxPercent = remainderYmax / Double.parseDouble(stepsY.getText());

        remainderXmin = 0;
        remainderXmax = 0;
        remainderYmin = 0;
        remainderYmax = 0;

        System.out.println("%:"+remainderXMaxPercent);
        int Tx = 655;
        int Sx = stepCountMaxX + stepCountMinX;
        totStepX = stepCountMaxX + stepCountMinX;
        System.out.println(stepCountMaxX);
        stepCountMinX = 0;
        stepCountMaxX = 0;
        System.out.println("totStepX:"+totStepX);
        double sumX;
        //if(abs(minXVal) != maxXVal)
        sumX = Sx + remainderXMinPercent + remainderXMaxPercent;
        //else
        //    sumX = Sx + remainderXMinPercent + remainderXMaxPercent;
        //double sumX = Sx + remainderXMinPercent + remainderXMaxPercent;
        System.out.println("sumx:"+sumX);
        double pPerStepX = Tx / sumX;
        System.out.println("pPerStep:"+pPerStepX);
        pixelsPerStepX = (int) pPerStepX;

        int Ty = 655;
        int Sy = stepCountMaxY + stepCountMinY;
        totStepY = stepCountMaxY + stepCountMinY;
        stepCountMinY = 0;
        stepCountMaxY = 0;
        double sumY;
        if(minYVal >= 0 || maxYVal <= 0)
            sumY = Sy + remainderYMinPercent + remainderYMaxPercent - 1;
        else
            sumY = Sy + remainderYMinPercent + remainderYMaxPercent;
        //double sumY = Sy + remainderYMinPercent + remainderYMaxPercent;
        double pPerStepY = Ty / sumY;
        System.out.println("decimal" + pPerStepY);
        pixelsPerStepY = (int)pPerStepY;



        double firstSkipXd = pixelsPerStepX * remainderXMinPercent;
        firstSkipX = (int) firstSkipXd;
        double secondSkipXd = pixelsPerStepX * remainderXMaxPercent;
        secondSkipX = (int) secondSkipXd;
        double firstSkipYd = pixelsPerStepY * remainderYMinPercent;
        firstSkipY = (int) firstSkipYd;
        double secondSkipYd = pixelsPerStepY * remainderYMaxPercent;
        secondSkipY = (int) secondSkipYd;

        x.clear();
        y.clear();
        double value;
        double c = 0;
        //System.out.println("fss:"+firstSkipX);
        //System.out.println("sss:"+secondSkipX);
        if(firstSkipX != 0)
            c++;
        if(secondSkipX != 0)
            c++;
        //System.out.println("c:"+c);
        value = storedX;
        for(int i = 0; i <= totStepX + c; i++)
        {
            System.out.println("value:"+value);
            x.add(value);
            value+=Double.parseDouble(stepsX.getText());
        }
        c = 0;
        if(firstSkipY != 0)
            c++;
        if(secondSkipY != 0)
            c++;

        value = storedY;
        for(int i = 0; i <= totStepY + c; i++)
        {
            System.out.println("y"+value);
            y.add(value);
            value-=Double.parseDouble(stepsY.getText());
        }
    }

    private static void openGraphPanel()
    {

    }

    private static void boundSetter()
    {
        startButtonLab.setBounds(255,36,350,262);
        startButtonObstcl.setBounds(255, 346, 350, 262);
    }

    private static void clkdBoundSetter()
    {
        startButtonLabClkd.setBounds(255,36,350,262);
        startButtonObstclClkd.setBounds(255, 346, 350, 262);
    }

    private static class startLabButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private static void removeMainComponents()
    {
        frame.remove(background);
        frame.repaint();
        frame.validate();
        //frame.validate();//frame.pain;
    }

    private static int q = 0;

    private static class startObstclButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {



        }
    }
    //private static boolean lab = false;
    //private static boolean obstacle = false;
    private static class startLabButtonMsListener implements MouseListener
    {
        boolean status;
        public void mousePressed(MouseEvent e) {
            startButtonLab.setVisible(false);
            startButtonLabClkd.setVisible(true);
        }

        public void mouseReleased(MouseEvent e) {
            startButtonLabClkd.setVisible(false);
            startButtonLab.setVisible(true);
            if(stat) {
                removeMainComponents();
                try {
                    lab = true;
                    if(true)
                    {
                        infoPanel.removeAll();


                        createMainPanel();
                        infoPanel.revalidate();
                    }

                    q++;
                    //a++;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                q++;
                z = 0;

                frame.add(mainPanel);
                frame.revalidate();
            }
        }

        public void mouseEntered(MouseEvent e) {
            stat = true;
        }

        public void mouseExited(MouseEvent e) {
            stat = false;
        }

        public void mouseClicked(MouseEvent e) {
        }
    }
    private static int z = 0;
    private static JPanel infoPanel2 = new JPanel();
    private static final String passPhraseEZ = "ezMonkey";
    private static final String passPhraseHard = "diffGorilla";
    private static Scanner keyboard = new Scanner(System.in);

    private static JFrame passFrame = new JFrame();
    private static JPanel passPanel = new JPanel();
    private static JLabel passMessage = new JLabel("Please Enter Passphrase:");
    private static JTextField passInput = new JTextField();
    private static JPanel passButtonsPanel = new JPanel();
    private static JButton okButton = new JButton("OK");
    private static JButton cancelButton = new JButton("Cancel");
    //320 X 112
    private static void createPassFrame()
    {
        //passFrame.setPreferred
    }

    private static class startObstclButtonMsListener implements MouseListener
    {
        public void mousePressed(MouseEvent e) {
            startButtonObstcl.setVisible(false);
            startButtonObstclClkd.setVisible(true);
        }

        public void mouseReleased(MouseEvent e) {
            startButtonObstclClkd.setVisible(false);
            startButtonObstcl.setVisible(true);
            String passPhraseInput = JOptionPane.showInputDialog(background, "Please Enter Passphrase:", null);
            //String passPhraseInput = "ezMonkey";
            System.out.println("password: " + passPhraseInput);
            try {
                if (passPhraseInput.equals(passPhraseEZ) || passPhraseInput.equals(passPhraseHard)) {
                    if (stat) {
                        removeMainComponents();
                        System.out.println("0");
                        //keyboard.nextLine();
                        obstacle = true;
                        if (true) {
                            //System.out.println("IN");
                            infoPanel2.removeAll();
                            infoPanel2.repaint();
                            infoPanel2.validate();
                            createMainPanel2();
                        }
                        System.out.println("0 0");

                        q++;
                        //z++;
                        a = 0;
                        System.out.println("mainPanel2 should be made");
                        //frame.add(okButton);
                        if (passPhraseInput.equals(passPhraseEZ))
                            easy = true;
                        else if (passPhraseInput.equals(passPhraseHard))
                            hard = true;
                        //keyboard.nextLine();
                        frame.add(mainPanel2);
                        frame.revalidate();
                        //graphPanel.repaint();
                        graphPanel.paintImmediately(0, 0, 637, 637);

                        System.out.println("hello" + actionPanel.isDisplayable());
                    }
                } else
                    JOptionPane.showMessageDialog(background, "Invalid Passphrase");
            }
            catch(NullPointerException g)
            {

            }
        }

        public void mouseEntered(MouseEvent e) {
            stat = true;
        }

        public void mouseExited(MouseEvent e) {
            stat = false;
        }

        public void mouseClicked(MouseEvent e) {
        }
    }

    //private static JPanel infoPanel2 = new JPanel();
    private static boolean obstacle = false;
    private static boolean lab = false;

    private static void createMainPanel2()
    {
        System.out.println("creating panel");
        mainPanel2.setPreferredSize(new Dimension(dimension_X,dimension_Y));
        mainPanel2.setLayout(new BoxLayout(mainPanel2, LINE_AXIS));
        if(q == 0)
            graphPanel = new Main();
        //mouseMeasure g = new mouseMeasure();
        graphPanel.addMouseListener(new pressTapes());
        graphPanel.addMouseMotionListener(new dragTapes());
        //graphPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, PAGE_AXIS));
        //graphPanel.setBackground(Color.RED);
        actionPanel.setPreferredSize(new Dimension(dimension_X-200,dimension_Y));
        infoPanel2.setPreferredSize(new Dimension(200, dimension_Y));
        infoPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
        actionPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        //infoPanel.setBackground(Color.RED);
        //JButton test = new JButton("test");
        //infoPanel.add(test);


        //chargeSetButton = new JButton(chargeSetImage);
        //chargeSetButton.addActionListener(new openChargeOptions());
        startButton = new JButton(startButtonImage);


        //chargeSetButton.setBackground(Color.WHITE);
        startButton.setBackground(Color.WHITE);

        startButton.addActionListener(new start());

        //measureButton = new JButton(measureButtonImage);
        exitButton = new JButton(exitButtonImage);
        exitButton.addActionListener(new exit());
        //graphButton = new JButton(graphButtonImage);
        //graphButton.addActionListener(new openGraphOptions());

        stopButton = new JButton(stopButtonImage);
        stopButton.setBackground(Color.WHITE);

        stopButton.addActionListener(new stopJob());
        //mainTB.add(measureButton);
        //mainTB.add(graphButton);
        //mainTB.add(chargeSetButton);
        if(q == 0) {
            mainTB.add(startButton);
            //createStopButton();
            mainTB.add(stopButton);
            //mainTB.add(stopButton);
            //mainTB.add(showV);
            //Thread.sleep(1000);
            mainTB.add(exitButton);


            mainTB.setBounds(0, 0, dimension_X, 40);
        }
        infoPanel2.add(mainTB);

        infoPanel2.add(Box.createRigidArea(new Dimension(200, 30)));
        if(q == 0) {
            //createGraphOptions();
            createChargeOptions();
        }
        //createRulerPanel();
        infoPanel2.setLayout(new BoxLayout(infoPanel2, PAGE_AXIS));
        openChargeOptions();
        //infoPanel.add(Box.createRigidArea(new Dimension(200,0)));
        //openGraphOptions();
        //openRulerPanel();
        infoPanel2.add(Box.createRigidArea(new Dimension(200,500)));
        //showVPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //showVPanel.add(showV);
        //infoPanel.add(showVPanel);

        //actionPanel.add(mainTB);
        actionPanel.add(graphPanel);
        mainPanel2.add(infoPanel2);
        mainPanel2.add(actionPanel);


    }

    //private static boolean correctPass = false;

    public static void main(String[] args) throws InterruptedException {
        Thread startApp = new Thread(new Main());
        startApp.start();
        accessController();
    }

}
