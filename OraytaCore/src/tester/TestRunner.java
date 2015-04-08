package tester;

import tester.classTester.BinarySearchTest;
import tester.classTester.BookTreeTester;
import tester.classTester.OBKReadTest;


/*
 * This class initiates code tests, as needed while developing.
 *  DON'T USE THIS FOR PRODUCTION  
 */

public class TestRunner 
{
	public static void main(String[] args)
	{
		runTest();
	}
	
	private static void runTest()
	{
		//ITest t = new BinarySearchTest();
		//ITest t = new BookTreeTester();
		ITest t = new OBKReadTest();
		
		t.Run();
	}

}
