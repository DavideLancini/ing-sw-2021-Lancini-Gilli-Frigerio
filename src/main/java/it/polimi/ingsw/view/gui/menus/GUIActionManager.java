package it.polimi.ingsw.view.gui.menus;

import it.polimi.ingsw.model.Resource;
import it.polimi.ingsw.network.ClientNetInterface;
import it.polimi.ingsw.network.DisconnectedException;
import it.polimi.ingsw.network.messages.*;
import it.polimi.ingsw.view.Manager;
import it.polimi.ingsw.view.gui.ChoiceBox;
import it.polimi.ingsw.view.gui.ResIcons;

import javax.swing.*;
import java.util.Collection;

public class GUIActionManager extends Manager {

    private final GameMenu gm = new GameMenu();


    @Override
    public ClientMessage turn(boolean action) {
        return gm.turn(action);
    }

    public void view(ServerMessageView messView) {
        if(messView.getElem() != null)SwingUtilities.invokeLater(() -> gm.display(messView));
    }

    @Override
    public void displayError(String error) {
        ChoiceBox.prompt(error, "Invalid Action", new String[]{"Continue"});
    }

    @Override
    public ClientMessage arrangeDepot(Collection<Resource> resources) {
        Resource[] newResources = new Resource[]{Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY,Resource.EMPTY};
        Resource[] choice = new Resource[3];

        for (int i = 1; i < 4; i++) {
            choice[i-1] = new ArrangeMenu(resources,i).prompt();
        }

        Manager.assignDepot(resources, newResources, choice);

        return new ClientMessageTryDepotConfiguration(newResources, resources.size());
    }

    @Override
    public ClientMessage chooseLeaders(ServerMessageChooseLeaders leaders) {
        String[] icons = leaders.getLeaders(false);
        LeadersChoiceMenu lcm = new LeadersChoiceMenu(icons);
        int[] choice = null;
        while(choice == null) {
            choice = lcm.prompt();
        }
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
        int choice = ChoiceBox.prompt("How many players?", "Join Game", new String[]{"Singleplayer", "2 players", "3 players",  "4 players"})+1;
        if(choice<1)gm.close();
        return choice;
    }

    @Override
    public String[] showOnlineMenu() {
        MainMenu mm = new MainMenu();
        String playerid = "player"+(int)Math.floor(Math.random()*1000000);
        int choice = mm.prompt();
        return new String[]{String.valueOf(choice), mm.id.getText().equals("") ? playerid : mm.id.getText()};
    }

    @Override
    public boolean online() {
        int choice = ChoiceBox.prompt("Play Online?", "Masters of Renaissance",new String[]{"Online", "Local"});
        if(choice < 0) {
            throw new RuntimeException("Application Closed by User");
        }
        return choice == 0;
    }

    @Override
    public void waitForTurn() {
        gm.setWaiting();
    }

    @Override
    public String showOfflineMenu() {
        System.out.println("show offline");
        OfflineMenu mm = new OfflineMenu();
        int choice = mm.prompt();
        return String.valueOf(choice);
    }

    @Override
    public void startOfflineGame(Manager manager) {
        gm.setVisible();
        super.startOfflineGame(manager);
    }

    @Override
    public void showCredits() {
        //TODO: show credits
    }

    @Override
    public ClientNetInterface connect() throws DisconnectedException {
        ConnectMenu cm = new ConnectMenu();
        int[] info = cm.prompt();
        return new ClientNetInterface(cm.address.getText(), info[1], info[2]);
    }

    @Override
    public void close() {
        try {
            gm.close();
        }
        catch (Exception ignored){}
    }

}
