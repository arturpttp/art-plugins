package net.dev.art.tst;

import java.beans.Expression;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("all")
public class Main {

	public static void sendError(Exception e) {

		System.err.println(" ");
		System.err.println("Um Erro Ocorreu ");
		System.err.println("CAUSA > " + e.getClass().getSimpleName());
		System.err.println("CLASSE > " + e.getStackTrace()[0].getClassName());
		System.err.println("LINHA > " + e.getStackTrace()[0].getLineNumber());

	}

	public static void main(String[] args) {
		try {
			int s = (Integer) null;
		} catch (NullPointerException e) {
			sendError(e);
		}

	}

}