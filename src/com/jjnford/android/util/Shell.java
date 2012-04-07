package com.jjnford.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell {

	private static final String EOL = System.getProperty("line.separator");
	private static final String EXIT = "exit" + Shell.EOL;

	/**
	 * Used to buffer shell output off of the main thread.
	 * 
	 * @author JJ Ford
	 *
	 */
	private static class Buffer extends Thread {
		private InputStream mInputStream;
		private StringBuffer mBuffer;
		
		/**
		 * @param inputStream Data stream to get shell output from.
		 */
		public Buffer(InputStream inputStream) {
			mInputStream = inputStream;
			mBuffer = new StringBuffer();
			this.start();
		}
		
		public String getOutput() {
			return mBuffer.toString();
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				String line;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(mInputStream));
				if((line = reader.readLine()) != null) {
					mBuffer.append(Shell.EOL).append(line);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Shell() {}
	
	/*
	 * API
	 * 
	 */
	
	/**
	 * Gains privileges to root shell.  Device must be rooted to use.
	 * 
	 * @return True if root shell is obtained, false if not.
	 */
	public static boolean su() {
		return false;
	}
	
	/**
	 * Executes a command in the root shell.  Devices must be rooted to use.
	 * 
	 * @param cmd The command to execute in root shell.
	 * @return True if command is executed successfully, false if not.
	 */
	public static boolean sudo(String cmd) {
		return false;
	}
	
	/**
	 * Executes a native shell command.
	 * 
	 * @param cmd The command to execute in the native shell.
	 * @return True if the command is executes successfully, false if not.
	 */
	public static boolean exec(String cmd) {
		return false;
	}
	
	/**
	 * Gets the STDOUT output from the given command in the current shell.
	 * 
	 * @param cmd The command to execute and generate output.
	 * @return The output generated by the given command, null if none.
	 */
	public static String getOutput(String cmd) {
		return null;
	}
	
	/**
	 * Gets the STDERR output from the given command in the current shell.
	 * 
	 * @param cmd The command to execute and generate output.
	 * @return The output generated by the given command, null if none.
	 */
	public static String getError(String cmd) {
		return null;
	}
}
