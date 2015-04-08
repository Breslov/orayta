package search;

import book.BookID;
import book.ChapterID;

/*
 * This class holds the search results from each specific book.
 */

public interface ISearchResult 
{
	public BookID getBookID();
	public ChapterID getChapterID();
	public String getPreview();
}
