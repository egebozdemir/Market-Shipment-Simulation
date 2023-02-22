package App;

import FileIO.FileIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Simulation {
	public static void run() throws IOException {
		Company market = new Company();
		Commands command = new Commands(market);
		ArrayList<StringTokenizer> commands = FileIO.readCommands();

		for (StringTokenizer c : commands) {
			command.runCommand(c);
		}
	}
}