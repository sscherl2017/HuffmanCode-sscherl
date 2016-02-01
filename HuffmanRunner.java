import java.util.HashMap; 

import java.util.PriorityQueue;

/**
* This class contains functions that can turn a String into a HashMap, turn a HashMap into a PriorityQueue of HuffmanNodes,
* turn a PriorityQueue into a tree of HuffmanNodes. Using the input and the tree, this class can create a String of bits
* of the minimum length to encode the input. Using the the tree and String of bits it can recreate the original input. I
* use a HashMap because you can access and add to it in constant time.
* @author Sam Scherl
* @version 12/14/15
*/
public class HuffmanRunner
{
	/* The String that will be encoded. */
	private static String input;
	
	/* The HuffmanNode which contains all the other HuffmanNodes. */
	private static HuffmanNode tree;
	
	/**
	* The main class which takes a String and calls all the functions necessary to convert it into Huffman code.
	* @param args	This is not used
	*/
	public static void main(String[] args)
	{
		input = "mississippi river";
		tree = createTree(createPriorityQueue(createMap()));
		System.out.println(bitCreator());
		System.out.println(bitCreator().length());
		System.out.println(bitReader(bitCreator()));
	}
	
	/**
	* This traverses through the input and creates a HashMap that contains all the characters 
	* with the amount of times they occur.
	* @return	The HashMap that contains all the characters with the amount of times they occur.
	*/
	public static HashMap<String, Integer> createMap()
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>(input.length() * 2);
		for (int i = 0; i < input.length(); i++)
		{
			if (map.containsKey(String.valueOf(input.charAt(i))))
				map.put(String.valueOf(input.charAt(i)), map.get(String.valueOf(input.charAt(i))) + 1);
			else
				map.put(String.valueOf(input.charAt(i)), 1);
		}
		return map;
	}
	
	/**
	* This traverses a HashMap and populates a PriorityQueue filled with HuffmanNodes which represent individual
	* characters and their respective occurrences.
	* @param map	The HashMap that will be traversed
	* @return	A PriorityQueue of HuffmanNodes
	*/
	public static PriorityQueue<HuffmanNode> createPriorityQueue(HashMap<String, Integer> map)
	{
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
		for(HashMap.Entry<String, Integer> entry : map.entrySet())
			queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));		
		return queue;
	}
	
	/**
	* Traverses through a PriorityQueue and creates a HuffmanNode that contains all the other HuffmanNodes.
	* @param queue	The PriorityQueue that will be traversed
	* @return	A HuffmanNode that contains all the other HuffmanNodes from the PriorityQueue
	*/
	public static HuffmanNode createTree(PriorityQueue<HuffmanNode> queue)
	{
		HuffmanNode combined = new HuffmanNode("", 0);
		if (queue.size() == 1)
			combined = new HuffmanNode(queue.peek().getKey(), queue.peek().getValue());
		while (queue.size() != 1)
		{
			HuffmanNode left = queue.peek();
			queue.remove();
			HuffmanNode right = queue.peek();
			queue.remove();
			combined = new HuffmanNode(left.getKey() + right.getKey(), left.getValue() + right.getValue(), left, right);
			queue.offer(combined);
		}
		return combined;
	}
	
	/**
	* Turns the given input into a String of bits, of the least possible length, based on the Huffman tree that was created.
	* @return	A String of bits that represents the input
	*/
	public static String bitCreator()
	{
		String output = "";
		if (tree.isLeaf())
		{
			for (int j = 0; j < input.length(); j++)
				output += "0";
		}
		for (int i = 0; i < input.length(); i++)
		{
			HuffmanNode current = tree;
			while (!current.isLeaf())
			{
				if (current.left().getKey().indexOf(String.valueOf(input.charAt(i))) != -1)
				{
					output += "0";
					current = current.left();
				}
				else
				{
					output += "1";
					current = current.right();
				}
			}
		}
		return output;
	}
	
	/**
	* Turns a String of bits into a String of text based on the Huffman tree that was created.
	* @param bits	A String of bits that represents text
	* @return	The original input that was converted into bits.
	*/
	public static String bitReader(String bits)
	{
		String output = "";
		int i = 0;
		if (tree.isLeaf())
		{
			for (int j = 0; j < bits.length(); j++)
				output += tree.getKey();
			return output;
		}
		while (i < bits.length())
		{
			HuffmanNode current = tree;
			while (!current.isLeaf())
			{
				if (bits.charAt(i) == '0')
					current = current.left();
				else 
					current = current.right();
				i++;
			}
			output += current.getKey();
		}
		return output;
	}
}