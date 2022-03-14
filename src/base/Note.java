package base;

import java.util.Date;

public class Note implements Comparable<Note>, java.io.Serializable{
	private Date date;
	private String title = "";
	private static final long serialVersionUID = 1L;
	
	public Note(String title)
	{
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}

	public String getTitle() 
	{
		return title;
	}
	
	
	/*
	public boolean equals(Note notetest)
	{
		
		if(this.title.equals(notetest.title) == true)
		{
			return true;
		}
		
		return false;
	}*/
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
	@Override
	public int compareTo(Note o) 
	{
		if(o.date.after(date))
		{
			return 1;
		}
		else if(o.date.before(date))
		{
			return -1;
		}
		return 0;
		/*if(o.title.compareTo(this.title) < 0)
		{
			return 1;
		}
		else if(o.title.compareTo(this.title) > 0)
		{
			return -1;
		}
		
		return 0;*/
	}

	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}
	

	
	
}
