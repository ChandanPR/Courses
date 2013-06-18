package experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Just used the String's hash code implementation to find the unique
 * hash code for each sorted char array.
 * @author chandanpr
 */
public class AnagramFinder {
	
	public Map<Integer, List<String>> findAnagrams(List<String> words){
		Map<Integer, List<String>> anagramsMap = new HashMap<>();
		for(String word : words){
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			int hashCode = hashCode(chars);
			List<String> anagramsList = anagramsMap.get(hashCode);
			if(anagramsList == null){
				anagramsList = new ArrayList<>();
				anagramsMap.put(hashCode, anagramsList);
			}
			anagramsList.add(word);
		}
		return anagramsMap;
	}
	
	/**
     *
     * Horner's Method to hash strings
     * HashCode function taken from String
     * @return  a hash code value for this object.
     */
    private int hashCode(char[] value) {
        int h = 0;
        int length = value == null ? 0: value.length;
		if (h == 0 && length > 0) {
            for (int i = 0; i < length; i++) {
                h = 31 * h + value[i];
            }
        }
        return h;
    }
    
    
    public static void main(String[] args) {
		AnagramFinder anagramFinder = new AnagramFinder();
		System.out.println(anagramFinder.findAnagrams(getList("abb","bbb","cbb")));
		System.out.println(anagramFinder.findAnagrams(getList("art","rat","tar")));
	}
    
    public static List<String> getList(String... words){
    	List<String> list =  new ArrayList<>();
    	for(String word: words){
    		list.add(word);
    	}
    	return list;
    }

}
