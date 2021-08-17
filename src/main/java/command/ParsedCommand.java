package command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParsedCommand {
    Command command = Command.NONE;
    String text = "";
    String text2 = "";

    public ParsedCommand(Command command, String text) {
        this.command = command;
        this.text = text;
    }

    public ParsedCommand(Command command, Double lat, Double lon) {
        this.command = command;
        this.text = lat.toString();
        this.text2 = lon.toString();
    }
}
