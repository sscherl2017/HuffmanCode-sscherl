public class HuffmanNode implements Comparable<HuffmanNode>
{
	private String key;
	
	private int value;
	
	protected HuffmanNode left;
	
	/** A pointer to the HuffmanNode to the right of this HuffmanNode. */
	protected HuffmanNode right;
	
	public HuffmanNode(String k, int v)
	{
		key = k;
		value = v;
		left = null;
		right = null;
	}
	
	/**
	* A Constructor which initializes the HuffmanNode with the given value and the given HuffmanNodes to the left
	* and right of it. 
	* @param v	The value of the HuffmanNode
	* @param l	The pointer to the HuffmanNode to the left
	* @param r	The pointer to the HuffmanNode to the right
	*/
	public HuffmanNode(String k, int v, HuffmanNode l, HuffmanNode r)
	{
		key = k;
		value = v;
		setLeft(l);
		setRight(r);
	}
	
	/**
	* Returns the HuffmanNode to the left.
	* @return	The HuffmanNode to the left
	*/
	public HuffmanNode left()
	{
		return left;
	}
	
	/**
	* Returns the HuffmanNode to the right.
	* @return	The HuffmanNode to the right 
	*/
	public HuffmanNode right()
	{
		return right;
	}
	
	public String getKey()
	{
		return key;
	}
	
	/**
	* Returns the value of the HuffmanNode.
	* @return	The value of the HuffmanNode
	*/
	public int getValue()
	{
		return value;
	}
	
	/**
	* Sets HuffmanNode to the left to the given HuffmanNode.
	* @param node	The HuffmanNode which will be set as the left HuffmanNode
	*/
	public void setLeft(HuffmanNode node)
	{
		left = node;
	}
	
	/**
	* Sets HuffmanNode to the right to the given HuffmanNode.
	* @param node	The HuffmanNode which will be set as the right HuffmanNode
	*/	
	public void setRight(HuffmanNode node)
	{
		right = node;
	}
	
	/**
	* Returns true if the HuffmanNode is a leaf, false if it is not.
	* @return	True if the HuffmanNode is a leaf, false if it is not
	*/
	public boolean isLeaf()
	{
		return (left == null && right == null);
	}
	
	public String toString()
	{
		if (isLeaf())
			return key + value + "";
		else
		{
			if (right == null)
				return key + value + "(" + left.toString() + ",)";
			else if (left == null)
				return key + value + "(," + right.toString() + ")";
			return key + value + "(" + left.toString() + "," + right.toString() + ")";
		}
	}
	
	public int compareTo(HuffmanNode other)
	{
		return value - other.value;
	}
}