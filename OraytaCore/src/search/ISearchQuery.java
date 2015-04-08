package search;

/*
 * Represents a search query. Should hold data like SearchString, SearchType, special modifiers, etc'
 */

public interface ISearchQuery
{
	public String getSearchString();
	public Boolean exactMatch();
	
	
}