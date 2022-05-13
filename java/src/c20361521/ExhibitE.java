package c20361521;

import ie.tudublin.*;
import processing.core.*;

public class ExhibitE extends Visual 
{
    MyVisuals mv;

    public ExhibitE(MyVisuals mv)
    {
        this.mv = mv;
    }

     // Gayzels
    public void render()
    {
        float r = 1f;
        int numPoints = 3;
        float thetaInc = PApplet.TWO_PI / (float) numPoints;

        mv.strokeWeight(2);                
        float lastX = mv.width / 2, lastY = mv.height / 2;

        for(int i = 0 ; i < 1000 ; i++)
        {
            float c = PApplet.map(i, 0, 300, 0, 255) % 255.0f;
            
            mv.stroke(c, 255, 255, 100);
            
            float theta = i * (thetaInc + mv.lerpedAverage * 5);
            float x = mv.width / 2 + PApplet.sin(theta) * r;
            float y = mv.height / 2 - PApplet.cos(theta) * r;
            
            r += 0.5f + mv.lerpedAverage;
            
            mv.line(lastX, lastY, x, y);
            
            lastX = x;
            lastY = y;
        }
    }
}

