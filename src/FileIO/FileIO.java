package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileIO {
	public final static String PATH_TO_CSV = "Csv/ExampleCommands.csv";

	/* Csv reading */
	private static BufferedReader readCsv(String pathToFile) throws FileNotFoundException {
		return new BufferedReader(new FileReader(pathToFile));
	}

	// To send tokens
	public static ArrayList<StringTokenizer> readCommands() throws IOException {
		ArrayList<StringTokenizer> commands = new ArrayList<>();
		BufferedReader reader = readCsv(PATH_TO_CSV);
		String line = null;
		while ((line = reader.readLine()) != null) {
			commands.add(new StringTokenizer(line,","));
		}
		return commands;
	}
}