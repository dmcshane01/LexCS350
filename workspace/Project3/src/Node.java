
public class Node {
	
	int data;
	Node link;
	

	public Node(int data, Node link)
	{
		this.data = data;
		this.link = link;
	}
	

	
	public void setData(int data) {
		this.data = data;
	}


	public void setLink(Node link) {
		this.link = link;
	}

	public int getData()
	{
		return data;
	}
	
	public Node getLink()
	{
		return link;
	}
}
