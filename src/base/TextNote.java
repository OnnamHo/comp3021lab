package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note{

	String content;
	
	private String getTextFromFile(String absolutePath)
	{
		String result="";
		try
		{
			
			File f = new File(absolutePath);
			Scanner read = new Scanner(f);
			while(read.hasNextLine())
			{
				result = result.concat(read.nextLine() + "\n"); 
			}
			read.close();
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public TextNote(String title) 
	{
		super(title);
	}
	
	public TextNote(String title, String content)
	{
		super(title);
		this.content = content;
	}
	
	public TextNote(File f)
	{
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
		
	}
	
	public void exportTextToFile(String pathFolder)
	{

		try {
			File file = new File(pathFolder +".txt");
			
			FileWriter fw = new FileWriter(file);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
				
	}
	

}
