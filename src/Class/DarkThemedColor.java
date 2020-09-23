/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.awt.Color;
/**
 *
 * @author Admin
 */
public class DarkThemedColor {
    private String[] BG_COLORS = {"#212121", "#424242"};
    private  String[] TEXT_COLORS = {"#FAFAFA", "#BDBDBD"};
    private  boolean bgColor, primary;
    
    public DarkThemedColor(){
        
    }
    
    public DarkThemedColor(boolean bgColor, boolean primary ){
        this.bgColor = bgColor;
        this.primary = primary;
        
    }
    
//    public Color colorChoice(){
//        Color color;
////        = new Color(Color.decode(BG_COLORS[0]));
//        
//        
//        if(bgColor && primary){
//            color = Color.decode(BG_COLORS[0]);
//            return color;
//        }
//        
////        return color;
//            
//    }
}
