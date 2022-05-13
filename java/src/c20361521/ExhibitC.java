package c20361521;

import ie.tudublin.*;
import processing.core.*;

public class ExhibitC extends Visual
{
    MyVisuals mv;

    public ExhibitC(MyVisuals mv)
    {
        this.mv = mv;
    }

    //for similar noises
    float yoff = 0;

    public void render()
    {
        

        /* START CIRCLE AND WAVEFORMS */
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

        // Waves
        for (int i = 0; i < ab.size(); i++) 
        {
            float c = PApplet.map(i, 0, ab.size(), 0, 255);
            mv.stroke(c, 255, 255);
            mv.lerpedBuffer[i] = PApplet.lerp(mv.lerpedBuffer[i], ab.get(i), 0.1f);

            // Left
            mv.line(0, i * 2f, mv.lerpedBuffer[i] * mv.height, i * 2f);
            
            // Right
            mv.line(mv.width, i * 2f, mv.width - (mv.lerpedBuffer[i] * mv.height), i  * 2);
            
            // Top
            mv.line(i * 3, 0, i * 3, mv.lerpedBuffer[i] * mv.height);
            
            // Bottom
            mv.line(i * 3, mv.height, i * 3, mv.height - (mv.lerpedBuffer[i] * (mv.height / 2) * 4));
        }    

        /* START CIRCLE */
        float d = PApplet.map(average, 0, 1, 0, 255);
        mv.stroke(d, 255, 255);        
        mv.strokeWeight(5);
        mv.noFill();
        
        // See the difference lerping makes? It smooths out the jitteryness of average, so the visual looks smoother
        //ellipse(width / 4, 100, 50 + average * 500, 50 + average * 500);
        mv.ellipse(mv.width / 2, mv.height / 2, 50 + (lerpedAverage * 5000), 50 + (lerpedAverage * 5000));  
        /* END CIRCLE */
        /* END CIRCLE AND WAVEFORMS */
    }
}

