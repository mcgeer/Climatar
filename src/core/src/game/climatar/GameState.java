package game.climatar;

import game.climatar.map.Nation;


import game.climatar.map.Nation.*;

public class GameState {

    //The world states
    private int TotalGHG;
    private int PoliticalSafety;
    private int AveragePercp;
    private double AverageTemp;

    //The Nation states
    private int GHG;
    private int political;
    private int percp;
    private double temp;


    public boolean IsWorldAlive(){
        if(TotalGHG>= 90 || PoliticalSafety<=1000||AveragePercp >= 500||AverageTemp >=60)
        return false;
        else return true;
    }

    public boolean IsNationAlive(Nation n){
        if(GHG >=90||political<=1000||percp>=500||temp>=60)
            return false;
        else return true;
    }

}
