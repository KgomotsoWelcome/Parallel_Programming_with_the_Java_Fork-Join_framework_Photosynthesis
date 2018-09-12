public class Tree

{
	int xposition, yposition, treeSize, xEnd, yEnd;
	
	public Tree(int x, int y, int size )
	{
		xposition = x;
		yposition = y;
		treeSize = size;
	}
	
	public void setX(int x)
	{
		this.xposition = x;
	}
	
	public void setY(int y)
	{
		this.yposition = y;
	}
	
	public void setSize(int size)
	{
		this.treeSize = size;
	}
	
	public int getX()
	{
		return xposition;
	}
	
	public int getY()
	{
		return yposition;
	}
	
	public int getSize()
	{
		return treeSize;
	}
	
	public int getEndX()
	{
		xEnd = getX() + getSize();
		return xEnd;
	}
	
	public int getEndY()
	{
		yEnd = getY() + getSize();
		return yEnd;
	}
	
	public String toString()
	{
		return "yCoordinate = " + getY() + " xCoordinate = " + getX()+ " TreeSize = "+ getSize()+" EndX = "+getEndX()+" EndY = "+getEndY();
	}
}
