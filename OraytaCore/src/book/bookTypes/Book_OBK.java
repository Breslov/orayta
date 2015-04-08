package book.bookTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import book.contents.BookContents;
import book.contents.BookID;
import book.contents.ChapterID;
import book.contents.DChapter;
import book.contents.IChapter;
import fileManager.ZipReader;
import search.ISearchQuery;
import search.ISearchResult;
import tree.TreeNode;

public class Book_OBK extends ABook
{
	//Not only are these in order, they also should almost correspond with the level in the tree
	private final static String LevelSigns = "$#^@~!";
	
	public Book_OBK(String path)
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
	
	public void buildContents() 
	{
		ZipReader zr = new ZipReader(mFilePath);
		try 
		{
			String rawtext = zr.readContents("BookText");
			
			TreeNode<IChapter> chapterContentsTree = new TreeNode<IChapter>(null);
			TreeNode<ChapterID> chapterIDTree = new TreeNode<ChapterID>(null);
			
			TreeNode<IChapter> currentContentsNode = chapterContentsTree;
			TreeNode<ChapterID> currentIDNode = chapterIDTree;
			int currentsLevel = -1;
			
			List<String> linesList = Arrays.asList(rawtext.split("\\r?\\n"));
			ChapterID chapid;
			DChapter chap = new DChapter();
			
			
			for (String line:linesList)
			{
				if (!line.isEmpty())
				{
					char firstChar = line.charAt(0);
					int levelCode = LevelSigns.indexOf(firstChar);
					
					if (levelCode != -1)
					{
						chapid = new ChapterID(this.getID().getID());
						chap = new DChapter();
						
						//New chapter!
						String title = line.substring(2);
						chapid.setID(title);
						chapid.setLevel(levelCode);
						chap.setAddress(chapid);
						
						
						/*TODO: Ok, so here is the problem. 
						* Until now I was assuming all books are really in a tree format.
						* This is not true, for instance, take any gmara.
						* 
						* But leaving that aside, even if we assume all have a nice hierarchy, 
						*  how do we add a new chapter to the tree in the right position?
						*  Just assume all intermediate levels always exist? 
						*  
						*  For example, look at בראשית, when פרשת נח starts. We have psukim with no perek.
						*  
						*/
						///TODO:
						///TODO:
						
						//Add to the tree:
						
						//Root element
						if (levelCode == 0)
						{
							chapterContentsTree.data = chap;
							chapterIDTree.data = chapid;
						}
						else
						{
							if (levelCode > currentsLevel) 
							{
								currentContentsNode = currentContentsNode.addChild(chap);
								currentIDNode = currentIDNode.addChild(chapid);
							}
							else
							{
								while (levelCode <= currentsLevel)
								{
									currentContentsNode = currentContentsNode.parent;
									currentIDNode = currentIDNode.parent;
									
									if (currentContentsNode == null || currentIDNode == null) currentsLevel = -1;
									else
									{
										IChapter d = currentContentsNode.data;
										if (d != null)
										{
											currentsLevel = d.getChapterAddress().getLevel();
										}
									}
								}
								
								if (currentsLevel != -1)
								{
									currentContentsNode = currentContentsNode.addChild(chap);
									currentIDNode = currentIDNode.addChild(chapid);
								}
							}
							currentsLevel = levelCode;
						}
						
						///TODO:
						///TODO:
					}
					else
					{
						chap.setChapterText(chap.text() + line + "\n");
					}
				}
			}
			
			mContents = new BookContents();
			mContents.setChapterContentsTree(chapterContentsTree);
			mContents.setChapterIDTree(chapterIDTree);
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

	public <Collection> ISearchResult search(ISearchQuery query) {
		// TODO Auto-generated method stub
		return null;
	}


}
