package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NoteBook implements java.io.Serializable{

	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;
	
	public NoteBook()
	{
		folders = new ArrayList<Folder>();
	}
	
	
	public boolean createTextNote(String folderName, String title)
	{
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
		
	}
	
	 
	
	public boolean createTextNote(String folderName, String title, String content)
	{
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title)
	{
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder>getFolders()
	{
		return folders;
	}

	private boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for(Folder f1: folders)
		{
			if(f1.getName().equals(folderName))
			{
				f = f1;
				break;
			}
			else
			{
				f=null;
			}
		}

		if(f == null)
		{
			f = new Folder(folderName);
			folders.add(f);
			f.addNote(note);
			return true;
		}

		for(Note n: f.getNotes())
		{
			if(note.getTitle().equals(n.getTitle()))
			{
				System.out.println("Creating note " + n.getTitle() +" under folder "+folderName+" failed");
				return false; 
			}
			
		}

		f.addNote(note);	
		return true;
	}
	
	public void sortFolders()
	{
		for(Folder f: folders)
		{
			f.sortNotes();

		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords)
	{
		List<Note> result = new ArrayList<Note>();
		for(Folder f: folders)
		{
			//result = f.searchNotes(keywords);
			if(f.searchNotes(keywords).isEmpty() == false)
			{
				for(Note n: f.searchNotes(keywords))
				{
					result.add(n);
				}
				
			}
			
		}
		return result;
	}

	public boolean save(String file)
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

		return true;
		
	}
	
	public NoteBook(String file)
	{
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try
		{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();

			folders = n.folders;
			//serialVersionUID = n.serialVersionUID;
			
			in.close();

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
	
	
	
