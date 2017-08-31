package br.cefetmg.games.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Utilitário para verificação de colisão.
 *
 * @author fegemo <coutinho@decom.cefetmg.br>
 */
public class Collision {

    public static final boolean lineOverlap(float amin, float amax, float bmin, float bmax){   
        return (amax>=bmin && amin<=bmax);        
    }
            
    /**
     * Verifica se dois círculos em 2D estão colidindo.
     * @param c1 círculo 1
     * @param c2 círculo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean circlesOverlap(Circle c1, Circle c2) {
        float distance;
        Vector2 circle = new Vector2(c1.x, c1.y);        
        distance = circle.dst2( new Vector2(c2.x, c2.y));       
        return (distance <= (c1.radius+c2.radius)*(c1.radius+c2.radius) );
    }

    /**
     * Verifica se dois retângulos em 2D estão colidindo.
     * Esta função pode verificar se o eixo X dos dois objetos está colidindo
     * e então se o mesmo ocorre com o eixo Y.
     * @param r1 retângulo 1
     * @param r2 retângulo 2
     * @return true se há colisão ou false, do contrário.
     */
    public static final boolean rectsOverlap(Rectangle r1, Rectangle r2) {        
        return ( lineOverlap(r1.x,r1.x+r1.width,r2.x,r2.x+r2.width) && lineOverlap(r1.y,r1.y+r1.height,r2.y,r2.y+r2.height) ); 
    }
    
    public static final boolean circleRectsOverlap(Circle c1, Rectangle r1) {
        Vector2 circle = new Vector2(c1.x,c1.y);
        Vector2 rectangle = new Vector2(r1.x+(r1.width/2),r1.y+(r1.height/2)); //pegar o centro//
        
        //Vector2 diagonal = circle.sub(rectangle); // sub modifica o próprio vetor
        Vector2 diagonal = new Vector2(circle.x - rectangle.x, circle.y - rectangle.y); //deferença entre os centros//
        Vector2 campled = new Vector2( ((Math.abs(diagonal.x) > (r1.width/2)) ? (diagonal.x/Math.abs(diagonal.x))* (r1.width/2) : diagonal.x),
                                ((Math.abs(diagonal.y) > (r1.height/2)) ? (diagonal.y/Math.abs(diagonal.y))* (r1.height/2) : diagonal.y));
        Vector2 nearestPoint = rectangle.add(campled); 
        float distance = circle.dst2(nearestPoint);
        return (distance <= ((c1.radius)*(c1.radius)));
    }
}
