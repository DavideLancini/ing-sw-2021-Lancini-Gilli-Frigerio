package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.model.ResourceCounter;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.ChoiceBox;
import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import java.util.Collection;
import java.util.logging.Logger;

public class GUIActionManager extends Manager {

    private static Logger logger = Client.logger;
    private static ClientNetInterface net;
    private static final GameMenu gm = new GameMenu();


    @Override
    public ClientMessage turn(boolean action) {
        return gm.turn(action);
    }

    public void view(ServerMessageView messView) {
        if(messView.getView(false) != null)gm.display(messView);
    }

    @Override
    public void displayError(String error) {
        //TODO:
    }

    @Override
    public ClientMessage arrangeDepot(Collection<Resource> resources) {
        Resource[] newResources = new Resource[]{Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY};
        Resource[] choice = new Resource[3];

        for (int i = 1; i < 4; i++) {
            int result;
            do {result = ChoiceBox.prompt(
                    "Resources to arrange: " + ResourceCounter.count(resources) +
                            "\n" +
                            "Choose  which resources to put in row n° " + i + "  (Max "+i+" resources): ",
                    "Arranging resources in your depot",
                    new ImageIcon[]{ResIcons.STONE.toIcon(), ResIcons.SERVANT.toIcon(), ResIcons.COIN.toIcon(), ResIcons.SHIELD.toIcon(), ResIcons.EMPTY.toIcon()}
            );}
            while (result < 0);
            choice[i-1] = Resource.values()[result];

        }

        if(resources.remove(choice[0])) newResources[0] = choice[0];
        if(resources.remove(choice[1])) newResources[1] = choice[1];
        if(resources.remove(choice[1])) newResources[2] = choice[1];
        if(resources.remove(choice[2])) newResources[3] = choice[2];
        if(resources.remove(choice[2])) newResources[4] = choice[2];
        if(resources.remove(choice[2])) newResources[5] = choice[2];

        return new ClientMessageTryDepotConfiguration(newResources, resources.size());
    }

    @Override
    public ClientMessage chooseLeaders(ServerMessageChooseLeaders leaders) {
        String[] icons = leaders.getLeaders(false);
        LeadersChoiceMenu lcm = new LeadersChoiceMenu(icons);
        int[] choice = lcm.prompt();

        return new ClientMessageChosenLeaders(choice[0], choice[1]);
    }

    @Override
    public ClientMessage addResource() {
        int choice = -1;

        while(choice < 0)
            choice = ChoiceBox.prompt("Choose a starting resource: ", "Initial Game Setup",
                new ImageIcon[]{ResIcons.STONE.toIcon(), ResIcons.SERVANT.toIcon(), ResIcons.COIN.toIcon(), ResIcons.SHIELD.toIcon()});

        return new ClientMessagePlaceResource(Resource.values()[choice]);
    }

    @Override
    public ClientMessage chooseResource(Resource[] choice) {
        System.out.println("Select which resource to convert a white marble to:\t[0]. "+choice[0]+"\t[1]. "+choice[1]);
        boolean position = ChoiceBox.prompt("Select which resource to convert a white marble to:", "Taking Resources", new ImageIcon[]
                {choice[0].toIcon(), choice[1].toIcon()}) == 1;

        return new ClientMessageChosenWhite(position);
    }

    @Override
    public int joinMatch() {
        gm.setVisible();
        return ChoiceBox.prompt("How many players?", "Join Game", new String[]{"Singleplayer", "2 players", "3 players",  "4 players"})+1;
    }


    @Override
    public void createCustomMatch(String s) throws DisconnectedException {
        //TODO: possibly to remove
    }

    @Override
    public String[] showMainMenu() {
        MainMenu mm = new MainMenu();
        mm.prompt();
        return new String[]{String.valueOf(mm.choice), mm.id.getText().equals("") ? mm.playerid : mm.id.getText()};
    }

    @Override
    public boolean online() {
        int choice = ChoiceBox.prompt("Play Online?", "Masters of Renaissance",new String[]{"Online", "Offline"});
        if(choice < 0) throw new RuntimeException("Application Closed by User");
        return choice == 0;
    }

    @Override
    public void waitForTurn() {
        gm.setWaiting();
    }

}
