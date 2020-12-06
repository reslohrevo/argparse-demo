package com.zog3.demo.argparsedemo;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ArgparseDemo {

	public static void main(String[] args) {
		System.out.println("Starting up");

		ArgumentParser parse = ArgumentParsers.newFor("ArgparseDemo").build() //
				.defaultHelp(true);

		//just flips true if the --verbose arg is present
		//default value = false
		parse.addArgument("--verbose") //
				.dest("verbose") //
				.action(Arguments.storeTrue());

		//flips true if --verb arg is present, overrides --verbose and precludes substrings
		parse.addArgument("--verb") //
				.dest("verb") //
				.action(Arguments.storeTrue());

		//takes and stores a string arg
		//spaces may be included "with quotes" or\ with\ backslashes
		//default value = ""
		parse.addArgument("string") //
				.action(Arguments.store()) //
				.type(String.class) //
				.setDefault("") //
				.nargs("?");

		//takes and stores an integer arg, rejects non-numerical input
		//default value = 1
		parse.addArgument("integers") //
				.type(Integer.class) //
				.setDefault(1) //
				.action(Arguments.store()) //
				.nargs("?");

		Namespace result = null;

		try {
			result = parse.parseArgs(args);
			System.out.println("No exception");
		} catch (ArgumentParserException e) {
			parse.handleError(e);
			System.out.println("Exception: " + e.getMessage());
		}

		System.out.println(result);
		System.out.println("--verbose " + result.getString("verbose"));
		System.out.println("string: " + result.getString("string"));
		System.out.println("integer: " + result.get("integers"));

	}

}
