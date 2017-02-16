
public class Student {
	
	private String name;
	private ID id;
	

	public Student(String name, ID id)
	{
		this.name = name;
		this.id = id;
	}
	
	public Student(String name)
	{
		this.name = name;
		id = new ID();
	}
	
	public ID getID()
	{
		return id;
	}
	public boolean equals(Student comp)
	{
		return (comp.name.equals(this.name) && comp.id.equals(this.id));
	}
	
	public String toString()
	{
		return name;
	}
	

}
