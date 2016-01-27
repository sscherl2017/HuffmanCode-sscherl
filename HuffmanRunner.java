import java.util.HashMap; 

import java.util.PriorityQueue;

public class HuffmanRunner
{
	private static String input;
	
	private static HuffmanNode tree;
	
	public static void main(String[] args)
	{
		input = "mississippi river";
		tree = createTree(createPriorityQueue(createMap()));
		System.out.println(bitReader(bitCreator()));
	}
	
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
	
	public static PriorityQueue<HuffmanNode> createPriorityQueue(HashMap<String, Integer> map)
	{
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>();
		for(HashMap.Entry<String, Integer> entry : map.entrySet())
			queue.offer(new HuffmanNode(entry.getKey(), entry.getValue()));		
		return queue;
	}
	
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
	
	public static String bitCreator()
	{
		String output = "";
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
	
	public static String bitReader(String bits)
	{
		String output = "";
		int i = 0;
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