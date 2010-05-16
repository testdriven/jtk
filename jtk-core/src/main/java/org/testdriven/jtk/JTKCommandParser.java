package org.testdriven.jtk;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class JTKCommandParser {

    private static final String CLASSES_OPTION = "classes";
    private static final String SOURCES_OPTION = "sources";
    private final String[] args;

    public JTKCommandParser(String[] args) {
        this.args = args;
    }

    public JTKCommandOptions parse() throws ParseException {
        BasicParser parser = new BasicParser();
        Options options = getOptions();
        CommandLine commandLine = parser.parse(options, args);
        final String[] sources = commandLine.getOptionValues(SOURCES_OPTION);
        final String[] classes = commandLine.getOptionValues(CLASSES_OPTION);

        JTKCommandOptions jtkOpts = new JTKCommandOptions(sources, classes);

        return jtkOpts;
    }

    private static Options getOptions() {
        Options options = new Options();
        OptionBuilder.hasArgs().withValueSeparator(';').isRequired();
        options.addOption(OptionBuilder.create(SOURCES_OPTION));

        OptionBuilder.hasArgs().withValueSeparator(';').isRequired();
        options.addOption(OptionBuilder.create(CLASSES_OPTION));

        return options;
    }
}
