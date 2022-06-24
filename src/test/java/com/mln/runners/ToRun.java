package com.mln.runners;

public class ToRun {
		public static void main(String[] args) throws Throwable {
		    String[] arguments = {"src/test/resources/Features/AddtoCartviaQuickView.feature", "com.mln.sharedStepDef"};
		    cucumber.api.cli.Main.main(arguments);
		}



}
