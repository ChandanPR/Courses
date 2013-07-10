package gc;

/**
 * 
 * References:
 * http://www.ibm.com/developerworks/library/j-jtp12214/
 * https://wikis.oracle.com/display/HotSpotInternals/MicroBenchmarks
 * 
 * Just-in-time compilation
 * HotSpot dynamic compilation - Interpret, Profile, Dynamic Compile
 * Continuous Recompile : 
 * On Stack Replacement (OSR) : allow a switch from interpretation to compiled code 
 * (or swapping one version of compiled code for another) in the middle of a loop
 * 
 * 
 * Converting a virtual method call to a direct method call is called monomorphic call transformation
 * 
 * 
 * -XX:+PrintCompilation, -verbose:gc
 * -XX:+TieredCompilation
 * -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1
 * @author chandanr
 * 
 * 
 * 
 * SERVER MODE:
 * For 1000
 *     147    1             office.CompilerOptimizationDemo::testLoop (22 bytes)
 *     
 * For 10000
 *     190    1             office.CompilerOptimizationDemo::testLoop (22 bytes)
 *     194    1 %           office.CompilerOptimizationDemo::testLoop @ 7 (22 bytes)
 *     
 * For 100000 onwards
 *  109    1 %           office.CompilerOptimizationDemo::testLoop @ 7 (21 bytes)
 *  114    1             office.CompilerOptimizationDemo::testLoop (21 bytes)
 *  115    2 %           office.CompilerOptimizationDemo::main @ 13 (47 bytes)
 *  116    2 %           office.CompilerOptimizationDemo::main @ -2 (47 bytes)   made not entrant
 *  117    3 %           office.CompilerOptimizationDemo::main @ 32 (47 bytes)
 *
 *
 * With Both: -XX:+PrintCompilation -XX:+TieredCompilation
 *  108    1       3       java.lang.String::hashCode (67 bytes)
    108    2       3       java.lang.String::charAt (33 bytes)
    113    4       3       java.lang.CharacterData::of (120 bytes)
    113    3       3       java.lang.AbstractStringBuilder::ensureCapacityInternal (16 bytes)
    113    5       3       java.lang.CharacterDataLatin1::getProperties (11 bytes)
    113    6       3       java.lang.Character::toLowerCase (9 bytes)
    114    7       3       java.lang.CharacterDataLatin1::toLowerCase (39 bytes)
    127    8       3       java.lang.AbstractStringBuilder::append (29 bytes)
    128    9       3       java.lang.Object::<init> (1 bytes)
    129   10     n 0       java.lang.System::arraycopy (0 bytes)   (static)
    141   11       1       java.lang.String::length (5 bytes)
    144   13       3       java.io.Win32FileSystem::isSlash (18 bytes)
    144   12       3       java.lang.StringBuilder::append (8 bytes)
    145   14  s    3       java.lang.StringBuffer::append (8 bytes)
    158   15       3       office.CompilerOptimizationDemo::testLoop (21 bytes)
    193    1 %     3       office.CompilerOptimizationDemo::testLoop @ 7 (21 bytes)
    193    2 %     4       office.CompilerOptimizationDemo::testLoop @ 7 (21 bytes)
    197    1 %     3       office.CompilerOptimizationDemo::testLoop @ -2 (21 bytes)   made not entrant
   2034   16       4       office.CompilerOptimizationDemo::testLoop (21 bytes)
   2035   15       3       office.CompilerOptimizationDemo::testLoop (21 bytes)   made not entrant
   3854   17       3       office.CompilerOptimizationDemo::main (47 bytes)
   3854    3 %     3       office.CompilerOptimizationDemo::main @ 13 (47 bytes)
   3855    4 %     4       office.CompilerOptimizationDemo::main @ 13 (47 bytes)
   3856    3 %     3       office.CompilerOptimizationDemo::main @ -2 (47 bytes)   made not entrant
   3857    4 %     4       office.CompilerOptimizationDemo::main @ -2 (47 bytes)   made not entrant
   3857    5 %     4       office.CompilerOptimizationDemo::main @ 32 (47 bytes)

 */
public class CompilerOptimizationDemo {
	private static final int COUNT = Integer.MAX_VALUE;
	public static void main(String[] args) {
		CompilerOptimizationDemo demo = new CompilerOptimizationDemo();
		for(int i=0; i<COUNT; i++){
			demo.testLoop();
		}
		for(int i=0; i<COUNT; i++){
			demo.testLoop();
		}
//		System.out.println(demo.testLoop());
	}
	
	private int testLoop(){
		int counter = 0;
		for(int i=0; i<COUNT; i++){
			counter++;
		}
		return counter;
	}

}
