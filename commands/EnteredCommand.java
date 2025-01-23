package commands;

import entities.Player;
/**
 * Décrivez votre classe EnteredCommand ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class EnteredCommand
{
    private String command;
    private String[] args;
    private Player sender;
    
    public EnteredCommand(String typedCommand, Player sender){
        String[] words = typedCommand.split(" ");
        this.command = words[0];
        this.args = new String[words.length-1];
        for(int i=1; i<words.length; i++){
            this.args[i-1] = words[i];
        }
        this.sender = sender;
    }
    
    public String getCommandWord(){ return this.command; }
    public String[] getArgs(){ return this.args; }
    public Player getSender(){ return this.sender; }
}
