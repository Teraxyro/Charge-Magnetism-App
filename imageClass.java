package com.company;

import javax.swing.*;
import java.awt.*;

public class imageClass {
    private final ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("namedBackground.jpg"));
    private ImageIcon labButtonImage = new ImageIcon(getClass().getClassLoader().getResource("LabButton.jpg"));
    private ImageIcon ObstclButtonImage = new ImageIcon(getClass().getClassLoader().getResource("ObstclButton.jpg"));
    private ImageIcon IconImage = new ImageIcon(getClass().getClassLoader().getResource("Magnetism Logo.jpg"));
    private ImageIcon labButtonImageClkd = new ImageIcon(getClass().getClassLoader().getResource("LabButtonClkd.jpg"));
    private ImageIcon ObstclButtonImageClkd = new ImageIcon(getClass().getClassLoader().getResource("ObstclButtonClkd.jpg"));

    private ImageIcon startButtonImage = new ImageIcon(getClass().getClassLoader().getResource("PlayButton.jpg"));
    private ImageIcon stopButtonImage = new ImageIcon(getClass().getClassLoader().getResource("StopButton.jpg"));
    private ImageIcon exitButtonImage = new ImageIcon(getClass().getClassLoader().getResource("exitButton.jpg"));

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public ImageIcon getLabButtonImage() {
        return labButtonImage;
    }

    public ImageIcon getObstclButtonImage() {
        return ObstclButtonImage;
    }

    public ImageIcon getLabButtonImageClkd() {
        return labButtonImageClkd;
    }

    public ImageIcon getObstclButtonImageClkd() {
        return ObstclButtonImageClkd;
    }

    public ImageIcon getStartButtonImage() {
        return startButtonImage;
    }

    public ImageIcon getStopButtonImage() {
        return stopButtonImage;
    }

    public ImageIcon getExitButtonImage() {
        return exitButtonImage;
    }

    public ImageIcon getLogoImage() {
        return IconImage;
    }


}
