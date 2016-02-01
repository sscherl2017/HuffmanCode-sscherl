public class HuffmanNode implements Comparable<HuffmanNode>
{
	/* The key of the HuffmanNode. */
	private String key;
	
	/* The value of the HuffmanNode. */
	private int value;
	
	/** A pointer to the HuffmanNode to the left of the HuffmanNode. */
	protected HuffmanNode left;
	
	/** A pointer to the HuffmanNode to the right of the HuffmanNode. */
	protected HuffmanNode right;
	
	/**
	* A constructor for HuffmanNodes that takes in a String and an int and leaves the left and
	* right HuffmanNodes as null.
	* @param k	The key for the HuffmanNode
	* @param v	The value for the HuffmanNode
	*/
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
	* @param k	The key of the HuffmanNode
	* @param v	The value of the HuffmanNode
	* @param l	The pointer to the HuffmanNode to the left
	* @param r	The pointer to the HuffmanNode to the right
	*/
	public HuffmanNode(String k, int v, HuffmanNode l, HuffmanNode r)
	{
		key = k;
		value = v;
		left = l;
		right = r;
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
	
	/*
	* Returns the key for the HuffmanNode.
	* @return	The key for the HuffmanNode
	*/
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
	* Returns true if the HuffmanNode is a leaf, false if it is not.
	* @return	True if the HuffmanNode is a leaf, false if it is not
	*/
	public boolean isLeaf()
	{
		return (left == null && right == null);
	}
	
	/**
	* Returns a String representation of the HuffmanNode.
	* @return	A String representation of the HuffmanNode
	*/
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
	
	/**
	* Compares the value of the HuffmanNode to the value of another HuffmanNode.
	* @param other	The HuffmanNode that will be compared against
	* @return	The difference between the values of the two HuffmanNodes
	*/
	public int compareTo(HuffmanNode other)
	{
		return value - other.value;
	}
}