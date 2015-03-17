/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;

import javax.faces.bean.*;
import java.util.Random;

@ApplicationScoped
@ManagedBean
public class Dice implements Comparable<Dice> {
        private int roll;
        private boolean pressed;
        public static Random random = new Random();
    public Dice(){
        this.roll = 1;
        this.pressed = false;
    }    
        
    public int getRoll() { 
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }
    
    public boolean isPressed() {
        return pressed;
    }

    public void setPressed() {
        this.pressed = !pressed;
    }  
    public void doRoll() { 
        if(!this.pressed)
        {
        this.roll = random.nextInt(1000000000)%6+1;
        }
        //return roll;
    }
    @Override
    public int compareTo(Dice compareDice) {
 
		int compareRoll = ((Dice) compareDice).getRoll(); 
 
		//ascending order
		return this.roll - compareRoll;
 
	}
    
}
