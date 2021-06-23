package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.Manager;

import javax.swing.*;
import java.util.Collection;
import java.util.logging.Logger;

public class GUIActionManager extends Manager {

    private static Logger logger = Client.logger;
    private static ClientNetInterface net;
    private static GameMenu gm = new GameMenu();


    public GUIActionManager() {

    }

    @Override
    public ClientMessage turn(boolean action) {
        return gm.turn(action);
    }

    public void view(ServerMessageView opanel) {
        if(opanel.getView(false) != null)gm.display(opanel);
    }

    @Override
    public void displayError(String error) {

    }

    @Override
    public ClientMessage arrangeDepot(Collection<Resource> resources) {
        return null;
    }

    @Override
    public ClientMessage chooseLeaders(ServerMessageChooseLeaders leaders) {
        String[] icons = leaders.getLeaders(false);
        gm.setVisible();
        LeadersChoiceMenu lcm = new LeadersChoiceMenu(icons);
        lcm.prompt();

        return new ClientMessageChosenLeaders(lcm.choice[0], lcm.choice[1]);
    }

    @Override
    public ClientMessage addResource() {
        return new ClientMessagePlaceResource(Resource.values()[ChoiceBox.prompt("Choose a starting resource: ", "Initial Game Setup",
                new ImageIcon[]{
                        ResIcons.STONE.toIcon(),
                        ResIcons.SHIELD.toIcon(),
                        ResIcons.SERVANT.toIcon(),
                        ResIcons.COIN.toIcon()
                })]);
    }

    @Override
    public ClientMessage chooseResource(Resource[] resources) {
        return null;
    }

    @Override
    public int joinMatch() {
        return ChoiceBox.prompt("How many players?", "Join Game", new String[]{"Singleplayer", "2 players", "3 players",  "4 players"})+1;
    }


    @Override
    public void createCustomMatch(String s) throws DisconnectedException {

    }

    @Override
    public String[] showMainMenu() {
        MainMenu mm = new MainMenu();
        mm.prompt();


        return new String[]{String.valueOf(mm.choice), mm.id.getText().equals("") ? mm.playerid : mm.id.getText()};
    }

    @Override
    public boolean online() {
        return ChoiceBox.prompt("Play Online?", "Masters of Renaissance",new String[]{"Online", "Offline"}) == 0;
    }

    @Override
    public void waitForTurn() {

    }

}
