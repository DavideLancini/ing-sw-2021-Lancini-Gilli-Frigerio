package it.polimi.ingsw.view.gui.menus;

public class OfflineMenu extends MainMenu{
    public OfflineMenu(){
        super();
        panel.remove(id);
        panel.remove(username);
        options = new String[]{"Offline Game", "Credits", "Quit"};
    }
}
