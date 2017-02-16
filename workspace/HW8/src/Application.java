import java.util.Random;


public class Application {
	
	public static void main(String[] args) {
		Student temp;
		ID id;
		StudentTable table = new StudentTable(30000);
		for(int i = 0; i < 15000; i++)
		{
			temp = new Student(randomName(7));
			id = temp.getID();
			table.put(id, temp);
		}
		
		System.out.println(table.firstFifty());
		System.out.println("Above is the first fifty names located in the table with 15,000 students and a size of 30,000");
		
		table = new StudentTable(1000000);
		for(int i = 0; i < 1000000; i++)
		{
			temp = new Student(randomName(7));
			id = temp.getID();
			table.put(id, temp);
		}
		System.out.println(table.firstFifty());
		System.out.println("Above is the first fifty names located in the table with 500,000 students and a size of 1,000,000");

	}
	
	public static String randomName(int length)
	{
		Random r = new Random();
		Character[] vowels = new Character[]{'a', 'e', 'i', 'o', 'u'};
		Character[] cons = new Character[]{'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
		Character[] all = new Character[]{'a', 'e', 'i', 'o', 'u', 'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
		String out = "";
		
		for(int i = 0; i < length; i++)
		{
			if(i == 0)
			{
				out += Character.toUpperCase(all[r.nextInt(26)]);
			}
			else if(i % 2 == 1) //add consonant if odd number position
			{
				out += vowels[r.nextInt(5)];
				
			}
			else
			{
				out += cons[r.nextInt(21)];			
			}
		}
		
		return out;
		
	}

}
