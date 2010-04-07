package org.testdriven.jtk;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class JTKCommandParser {

	private final String[] args;

	public JTKCommandParser(String[] args) {
		this.args = args;
	}

	public JTKCommandOptions parse() throws ParseException {
		BasicParser parser = new BasicParser();
		Options options = getOptions();
		CommandLine commandLine = parser.parse(options, args);
		
		JTKCommandOptions jtkOpts = new JTKCommandOptions(commandLine.getOptionValues("src"));
		
		return jtkOpts;
	}

	private static Options getOptions() {
		Options options = new Options();
		options.addOption(OptionBuilder.hasArgs().withValueSeparator(';').isRequired().create("src"));

		return options;
	}

}
