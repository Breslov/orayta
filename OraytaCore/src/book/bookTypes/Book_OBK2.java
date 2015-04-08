package book.bookTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import book.contents.BookID;
import book.contents.ChapterID;
import book.contents.IChapter;
import fileManager.ZipReader;
import search.ISearchQuery;
import search.ISearchResult;
import tree.TreeNode;

public class Book_OBK2 extends ABook
{
	private final static String AllLevelSigns = "$#^@~!";
	private String NoIndexLevelSigns = "!";
	private String UpperLevelSigns = "";
	private String LowerLevelSigns = "";

	List<String> rawTextLines;
	
	public Book_OBK2(String path)
	{
		mFilePath = path ;

		String zipComment = ZipReader.readComment(mFilePath);
		
		parseSttings(zipComment);

		mDisplayName = mBookSettings.get("DisplayName");
		
		int id = -1;
		try { id = Integer.parseInt(mBookSettings.get("UniqueId")); }
		catch (NumberFormatException e) {}
		
		mID = new BookID(id, mDisplayName);
	}
	
	

	@Override
	public void buildContents() 
	{
		readRawText();
		
		if (!rawTextLines.isEmpty())
		{
			detectLevelSigns();
			buildChapters();
		}
	}	
	
	private void buildChapters() 
	{
		TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
		TreeNode<ChapterID> chapterIDTree = new TreeNode<ChapterID>(null);
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				int levelCode = AllLevelSigns.indexOf(firstChar);
				
				if (levelCode != -1)
				{
					//TODO:
				}
				else
				{
					//TODO:
				}
			}
		}
		
		
		
		mContents.setChapterContentsTree(chapterContentsTree);
		mContents.setChapterIDTree(chapterIDTree);
	}



	private void detectLevelSigns() 
	{		
		String AllSigns = reverseString(AllLevelSigns);
		
		Boolean signFound[];
		signFound = new Boolean[AllSigns.length()];
		for (int i=0; i<AllSigns.length(); i++) signFound[i] = false;
		
		for (String line:rawTextLines)
		{
			if (!line.isEmpty())
			{
				char firstChar = line.charAt(0);
				int levelCode = AllSigns.indexOf(firstChar);
				
				//Level sign found
				if (levelCode > 0 && levelCode < AllSigns.length()) signFound[levelCode] = true;
			}
		}
		
		//Split into 2 chunks, (if these exist):
		
		//TODO: Switch to numbers
		
		int firstStop = 1; for (; firstStop<AllSigns.length() && signFound[firstStop]; firstStop++) LowerLevelSigns += AllSigns.substring(firstStop, firstStop+1);
		int secondStop = firstStop + 1; for (; secondStop<AllSigns.length() && signFound[secondStop]; secondStop++) UpperLevelSigns += AllSigns.substring(secondStop, secondStop+1);
	}

	private void readRawText() 
	{
		ZipReader zr = new ZipReader(mFilePath);
		try 
		{
			String rawtext = zr.readContents("BookText");
			rawTextLines = Arrays.asList(rawtext.split("\\r?\\n"));
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String reverseString(String str) {
		return new StringBuilder(str).reverse().toString();
	}



	public <Collection> ISearchResult search(ISearchQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
