package zmandaTesting;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class putFilesInTree
{
	public static void main(String[] args) throws IOException
	{
		String inputFile = args[0];
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		String line = null;
		String curName;
		String result = "";

		ArrayList<String> listOfFilepaths = new ArrayList<String>();

		//store the file paths in an arrayList
		while ((line = br.readLine()) != null)
		{
			//System.out.println(line);
    		listOfFilepaths.add(line.trim());
		}

		int baseNumSlashes = 0;
		
		for (int i = 0; i < listOfFilepaths.size(); i++)
		{
			String filepath = listOfFilepaths.get(i);
			
			String[] slashSplitArray = filepath.split("/");

			curName = slashSplitArray[slashSplitArray.length-1];

			//number of forward slashes in the filepath
			int numSlashes = slashSplitArray.length-1;

			if(i == 0)
			{
				//create the base number based on the top level directory
				//top level dir assumed to come first in list
				baseNumSlashes = numSlashes;
			}

			//count should always be >= baseNumSlashes anyway
			//take the diff to figure out how many times to indent
			int diff = Math.abs(numSlashes - baseNumSlashes);

			//add the appropriate number of tabs before printing fileName
			for(int j = 0; j < diff; j++)
			{
				result = result + "   ";
			}

			result = result + curName;

			System.out.println(result);
			result = "";
		}
		
		
		// Above code is for drawing tree
		// -------------------------------------
		// Below code is for managing relationships

		//ArrayList<LinkedList<String>> relationships = new ArrayList<LinkedList<String>>();
		LinkedList<String>[] relationships = new LinkedList[15];
		
		//put the head nodes in each place
		//each file or directory becomes a head of its own linked list
		//these linked lists are stored in the array, relationships
		for(int x = 0; x < listOfFilepaths.size(); x++)
		{
			LinkedList<String> tmp = new LinkedList<String>();
			
			File f = new File(listOfFilepaths.get(x));
			tmp.add(f.getName());
			
			relationships[x] = tmp;
		}
		
		/* Checking that each file/dir name has been placed at the head of individual linked lists
		 * works fine
		for(int c = 0; c < relationships.length; c++)
		{
			LinkedList<String> t = relationships[c];
			System.out.println(t.getFirst());
		}
		*/
		
		for(String path : listOfFilepaths)
		{
			File ftemp = new File(path);
			String fName = ftemp.getName();
			
			/*
			if(ftemp.getParentFile() != null)
			{
				String parentFileName = ftemp.getParentFile().getName();
			}
			*/
			
			//String parentFileName = ftemp.getParentFile().getName();
			
			//Only the absolute top level directory would have a null parent
			if(ftemp.getParentFile() != null)
			{
				String parentFileName = ftemp.getParentFile().getName();
				
				//Get the appropriate list, with the parentFileName as the head of the list
				//int index = Arrays.asList(relationships).indexOf(parentFileName);
				
				int index = -1;
				
				for(int z = 0; z < relationships.length; z++)
				{
					LinkedList<String> t = relationships[z];
					String parentNode = t.getFirst();
					if(parentNode.equals(parentFileName))
						index = z;
				}
				
				
				///System.out.println("Index is: " + index);
				LinkedList<String> parentList = relationships[index];
				
				//Don't want to add an element to its own list
				if(!fName.equals(parentFileName))
					parentList.add(fName);
				
				//Place the list back into the overall array
				//child has been added to the appropriate parent's list
				relationships[index] = parentList;
			}
			
		}
		
		//printing out the array of linkedlists
		for(int y = 0; y < relationships.length; y++)
		{
			LinkedList<String> curList = relationships[y];
			
			//Every list has at least one element
			//If there is only one element (the identifying file for that list), then it doesn't have children
			//To find grandchildren, you have to explore children of each child node
			
			//In general, each list contains the name of a file or directory at its first element
			//If it's a file, then there won't be any other elements in that list
			//If it's a directory, then the list will also contain the immediate children of that dir
			if(curList.size() > 1)
				System.out.print(curList.getFirst() + " has these children: ");
			else
				System.out.print(curList.getFirst() + " has no children.");
			
			for(int a = 1; a < curList.size(); a++)
			{
				System.out.print(curList.get(a) + " ");
			}
			System.out.println();
		}
	}
}
