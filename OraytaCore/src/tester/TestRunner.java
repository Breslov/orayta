package tester;

import tester.classTester.BinarySearchTest;


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
		ITest t = new BinarySearchTest();
		t.Run();
	}

}
