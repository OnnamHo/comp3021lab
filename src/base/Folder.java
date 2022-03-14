package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable{

		private ArrayList<Note> notes;
		private String name;
		private static final long serialVersionUID = 1L;
		
		public Folder(String name)
		{
			this.name = name;
			notes = new ArrayList<Note>();
		}
		
		public void addNote(Note note)
		{
			notes.add(note);
		}
		
		public String getName()
		{
			return name;
		}
		
		public ArrayList<Note> getNotes()
		{		
			return notes;
		}
		

		@Override
		public String toString() {
			int nText=0;
			int nImage=0;

			for(Note note: notes)
			{
				//System.out.println(note);
				if(note instanceof TextNote)
				{
					nText++;
				}
				else
				{
					nImage++;
				}
			}

			return name + ":" + nText + ":" + nImage;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Folder other = (Folder) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public int compareTo(Folder o) {
			// TODO Auto-generated method stub
			if(o.name.compareTo(this.name) < 0)
			{
				return 1;
			}
			else if(o.name.compareTo(this.name) > 0)
			{
				return -1;
			}
			
			return 0;
		}
		
		public void sortNotes()
		{
			Collections.sort(notes);
		}
		
		public List<Note> searchNotes(String keywords)
		{
			String lowerKey = keywords.toLowerCase();
			String[] parts = lowerKey.split(" ");
			String[] orPart = new String[4];
			List<Note> result = new ArrayList<Note>();

			int[] index = new int[5];
			int count=-1;
			int j=-1;

			for(int i=0; i<parts.length; i++)
			{
				
				if(parts[i].contains("or"))
				{
					count++;
					index[count] = i;
					
				}
				
			}

			
			for(int i: index)
			{
				if(i!=0)
				{
					j++;
					orPart[j] = parts[i-1];
					j++;
					orPart[j] = parts[i+1];
					
				}
				
			}

			for(Note n: notes)
			{
				if((n.getTitle().toLowerCase().contains(orPart[0])) || n.getTitle().toLowerCase().contains(orPart[1]) && (n.getTitle().toLowerCase().contains(orPart[2]) || n.getTitle().toLowerCase().contains(orPart[3])))
				{
					result.add(n);
					
				}
				else if(n instanceof TextNote)
				{
					if( (((TextNote) n).content.toLowerCase().contains(orPart[0])||  ((TextNote) n).content.toLowerCase().contains(orPart[1])) && (((TextNote) n).content.toLowerCase().contains(orPart[2]) || ((TextNote) n).content.toLowerCase().contains(orPart[3])))
					{
						result.add(n);	
					}
				}

			}
			return result;
		}
}
