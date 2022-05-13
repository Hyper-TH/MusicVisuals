package c20361521;

import ie.tudublin.*;
import processing.core.PApplet;

public class ExhibitF extends Visual
{
    MyVisuals mv;

    public ExhibitF(MyVisuals mv)
    {
        this.mv = mv;
        cy = this.mv.height / 2;
    }
    float d = 1;
    float n = 2;
    int flowers = 6;
    float spin = 0;

    float cy = 0;
    
    //for similar noises
    float yoff = 0;

    public void render()
    {
        float average = 0;
        float sum = 0;
        float lerpedAverage = 0;

        //Average of the buffer
        for(int i =0; i < ab.size(); i++)
        {
            sum += abs(ab.get(i));
        }

        average = sum / ab.size();

        //Calculate the average amplitutde
        lerpedAverage = lerp(lerpedAverage,average, 0.1f);

        //background();
        mv.translate(mv.width / 2, mv.height / 2);

        mv.beginShape();

        float k = n / d;
        mv.stroke(255);
        mv.noFill();
        mv.strokeWeight(1);

        float raid = radians(spin);
        mv.rotate(raid);
        
        //for (float a = 0; a < TWO_PI * d; a += 0.02) 
        for (int a = 0; a < ab.size(); a++) 
        {
            //colour
            float colour = PApplet.map(ab.get(a), 0, PApplet.TWO_PI * d, 0, 255);
            mv.stroke(colour, 255, 255);

            //first
            float r = 200 * cos(k * mv.getAudioBuffer().get(a));
            float x = r * cos(a);
            float y = r * sin(a);
            mv.vertex(x, y);
            //how do you lerp?
            mv.point(20 *(x * lerpedAverage), 20 * (y * lerpedAverage));


            //second
            r = 200 * cos((k-1) * mv.getAudioBuffer().get(a));
            x = r * cos(a);
            y = r * sin(a);
            mv.vertex(x, y);
            //basiclaly, not a big deal, this doesn't work
            mv.point(20 *(x * lerpedAverage), 20 * (y * lerpedAverage));
        }
        d += 1;
        n += 1;
        spin +=0.1;

        mv.endShape(CLOSE);

      
    }
}
