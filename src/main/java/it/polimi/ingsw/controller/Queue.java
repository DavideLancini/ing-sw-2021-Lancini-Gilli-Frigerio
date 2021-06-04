package it.polimi.ingsw.controller;

public class Queue{
    private static Player[] player2 = new Player[2];
    private static Player[] player3 = new Player[3];
    private static Player[] player4 = new Player[4];
    private static int count2 = 0;
    private static int count3 = 0;
    private static int count4 = 0;

    public static void enterQueue(int gameMode, Player player){
        switch(gameMode){
            case 2:{

                //Insert player in queue
                player2[count2]=player;
                //Increase count

                count2++;

                if(count2 == 2) {
                    try {
                        new Game(player2);
                    } catch (Exception e) {
                        //TODO
                    }
                    count2=0;
                }
                break;
            }
            case 3:{

                //Insert player in queue
                player3[count3]=player;
                //Increase count

                count3++;

                if(count3 == 3) {
                    try {
                        new Game(player3);
                    } catch (Exception e) {
                        //TODO
                    }
                    count3=0;
                }
                break;
            }
            case 4:{

                //Insert player in queue
                player4[count4]=player;
                //Increase count

                count4++;

                if(count4 == 4) {
                    try {
                        new Game(player4);
                    } catch (Exception e) {
                        //TODO
                    }
                    count4=0;
                }
                break;
            }
        }
    }
}
