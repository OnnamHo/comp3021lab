package base;

import java.util.ArrayList;

public class NoteBook{

	private ArrayList<Folder> folders;
	
	public NoteBook()
	{
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title)
	{
		TextNote note = new TextNote(title);
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

}
	
	
	
