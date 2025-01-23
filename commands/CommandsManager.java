package commands;
import java.util.HashMap;

import engine.GameEngine;
/**
 * Décrivez votre classe CommandsManager ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CommandsManager
{
    private HashMap<String, Command> commands = new HashMap<>();
    private GameEngine aEngine;
    
    public CommandsManager(GameEngine pEngine){
        this.aEngine = pEngine;
        
        this.loadCommands();
    }
    
    private void loadCommands(){
        this.addCommand("summon", new SummonCommand(this.aEngine));
        this.addCommand("time", new TimeCommand(this.aEngine));
        this.addCommand("god", new GodCommand(this.aEngine));
        this.addCommand("give", new GiveCommand(this.aEngine));
        this.addCommand("seed", new SeedCommand(this.aEngine));
    }
    
    private void addCommand(String label, Command command){
        this.commands.put(label, command);
    }
    
    public void executeCommand(EnteredCommand pCmd){
        this.commands.get(pCmd.getCommandWord()).execute(pCmd.getSender(), pCmd.getCommandWord(), pCmd.getArgs());
    }
    
    public GameEngine getEngine(){ return this.aEngine; }
    
    public boolean isCommand(EnteredCommand command){ return this.commands.containsKey(command.getCommandWord()); }
}
