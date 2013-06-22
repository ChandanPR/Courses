package tests.com.design.numbersystem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.design.numbersystem.NumberSystem;

/**
 * Test Class for testing the Number System.
 * @author chandanr
 */
public class NumberSystemTest {

	NumberSystem defaultNumberSystem = NumberSystem.getDefaultNumberSystem();
	
	@Test
	public void checkDigitsConversion() {
		assertEquals("The Conversion is not right", "ZERO", defaultNumberSystem.toString(0));
		assertEquals("The Conversion is not right", "ONE", defaultNumberSystem.toString(1));
		assertEquals("The Conversion is not right", "TWO",  defaultNumberSystem.toString(2));
		assertEquals("The Conversion is not right", "THREE",  defaultNumberSystem.toString(3));
		assertEquals("The Conversion is not right", "FOUR",  defaultNumberSystem.toString(4));
		assertEquals("The Conversion is not right", "FIVE",  defaultNumberSystem.toString(5));
		assertEquals("The Conversion is not right", "SIX",  defaultNumberSystem.toString(6));
		assertEquals("The Conversion is not right", "SEVEN",  defaultNumberSystem.toString(7));
		assertEquals("The Conversion is not right", "EIGHT",  defaultNumberSystem.toString(8));
		assertEquals("The Conversion is not right", "NINE",  defaultNumberSystem.toString(9));
		assertEquals("The Conversion is not right", "TEN",  defaultNumberSystem.toString(10));
		assertEquals("The Conversion is not right", "ELEVEN",  defaultNumberSystem.toString(11));
		assertEquals("The Conversion is not right", "TWELVE",  defaultNumberSystem.toString(12));
		assertEquals("The Conversion is not right", "THIRTEEN",  defaultNumberSystem.toString(13));
		assertEquals("The Conversion is not right", "FOURTEEN",  defaultNumberSystem.toString(14));
		assertEquals("The Conversion is not right", "FIFTEEN",  defaultNumberSystem.toString(15));
		assertEquals("The Conversion is not right", "SIXTEEN",  defaultNumberSystem.toString(16));
		assertEquals("The Conversion is not right", "SEVENTEEN",  defaultNumberSystem.toString(17));
		assertEquals("The Conversion is not right", "EIGHTEEN",  defaultNumberSystem.toString(18));
		assertEquals("The Conversion is not right", "NINETEEN",  defaultNumberSystem.toString(19));
	}
	
	
	@Test
	public void checkTensConversion() {
		assertEquals("The Conversion is not right", "TEN", defaultNumberSystem.toString(10));
		assertEquals("The Conversion is not right", "TWENTY",  defaultNumberSystem.toString(20));
		assertEquals("The Conversion is not right", "THIRTY",  defaultNumberSystem.toString(30));
		assertEquals("The Conversion is not right", "FOURTY",  defaultNumberSystem.toString(40));
		assertEquals("The Conversion is not right", "FIFTY",  defaultNumberSystem.toString(50));
		assertEquals("The Conversion is not right", "SIXTY",  defaultNumberSystem.toString(60));
		assertEquals("The Conversion is not right", "SEVENTY",  defaultNumberSystem.toString(70));
		assertEquals("The Conversion is not right", "EIGHTY",  defaultNumberSystem.toString(80));
		assertEquals("The Conversion is not right", "NINETY",  defaultNumberSystem.toString(90));
	}
	
	@Test
	public void checkLessThanHundredConversion(){
		assertEquals("The Conversion is not right", "NINETEEN", defaultNumberSystem.toString(19));
		assertEquals("The Conversion is not right", "FIFTY FIVE", defaultNumberSystem.toString(55));
		assertEquals("The Conversion is not right", "FOURTY THREE", defaultNumberSystem.toString(43));
		assertEquals("The Conversion is not right", "THIRTY TWO", defaultNumberSystem.toString(32));
		assertEquals("The Conversion is not right", "NINETY SEVEN", defaultNumberSystem.toString(97));
		assertEquals("The Conversion is not right", "SEVENTY EIGHT", defaultNumberSystem.toString(78));
		assertEquals("The Conversion is not right", "TWENTY ONE", defaultNumberSystem.toString(21));
	}
	
	
	@Test
	public void checkLessThanThousandConversion(){
		assertEquals("The Conversion is not right", "THREE HUNDRED AND FOURTY SIX", defaultNumberSystem.toString(346));
		assertEquals("The Conversion is not right", "SEVEN HUNDRED AND FOURTY THREE", defaultNumberSystem.toString(743));
		assertEquals("The Conversion is not right", "EIGHT HUNDRED", defaultNumberSystem.toString(800));
		assertEquals("The Conversion is not right", "FIVE HUNDRED AND NINE", defaultNumberSystem.toString(509));
		assertEquals("The Conversion is not right", "FIVE HUNDRED AND TWENTY", defaultNumberSystem.toString(520));
		assertEquals("The Conversion is not right", "ONE HUNDRED AND ONE", defaultNumberSystem.toString(101));
		assertEquals("The Conversion is not right", "ONE HUNDRED AND FOURTY", defaultNumberSystem.toString(140));
		assertEquals("The Conversion is not right", "ONE HUNDRED AND TWENTY THREE", defaultNumberSystem.toString(123));
		assertEquals("The Conversion is not right", "ONE HUNDRED AND ELEVEN", defaultNumberSystem.toString(111));
		assertEquals("The Conversion is not right", "TWO HUNDRED AND ELEVEN", defaultNumberSystem.toString(211));
		assertEquals("The Conversion is not right", "THREE HUNDRED AND FIFTEEN", defaultNumberSystem.toString(315));
		assertEquals("The Conversion is not right", "FOUR HUNDRED AND NINETEEN", defaultNumberSystem.toString(419));
	}
	
	@Test
	public void checkLessThanMillionConversion(){
		assertEquals("The Conversion is not right", "THREE HUNDRED THOUSAND AND FOURTY SIX", defaultNumberSystem.toString(300046));
		assertEquals("The Conversion is not right", "SEVEN HUNDRED AND EIGHTY FIVE THOUSAND AND THREE", defaultNumberSystem.toString(785003));
		assertEquals("The Conversion is not right", "EIGHT THOUSAND ONE HUNDRED AND ELEVEN", defaultNumberSystem.toString(8111));
		assertEquals("The Conversion is not right", "FOUR THOUSAND THREE HUNDRED AND THIRTY FOUR", defaultNumberSystem.toString(4334));
		assertEquals("The Conversion is not right", "FIFTY THOUSAND SEVEN HUNDRED AND EIGHTY NINE", defaultNumberSystem.toString(50789));
	}
	
	@Test
	public void checkMillionConversion(){
		assertEquals("The Conversion is not right", "FIVE HUNDRED MILLION AND ONE", defaultNumberSystem.toString(500000001));
		assertEquals("The Conversion is not right", "FOUR MILLION NINE HUNDRED THOUSAND ONE HUNDRED AND ONE", defaultNumberSystem.toString(4900101));
		assertEquals("The Conversion is not right", "ONE MILLION", defaultNumberSystem.toString(1000000));
		assertEquals("The Conversion is not right", "EIGHT MILLION AND ONE", defaultNumberSystem.toString(8000001));
		assertEquals("The Conversion is not right", "EIGHT MILLION ONE HUNDRED AND ONE", defaultNumberSystem.toString(8000101));
		assertEquals("The Conversion is not right", "EIGHT MILLION ONE THOUSAND AND ONE", defaultNumberSystem.toString(8001001));
		assertEquals("The Conversion is not right", "EIGHT MILLION AND SIXTY SEVEN", defaultNumberSystem.toString(8000067));
		assertEquals("The Conversion is not right", "SEVEN MILLION SIX HUNDRED AND THIRTY FOUR THOUSAND FIVE HUNDRED AND SEVEN", defaultNumberSystem.toString(7634507));
	}
	
	
	@Test(expected =IllegalArgumentException.class)
	public void testNegativeNumber(){
		defaultNumberSystem.toString(-24);
	}
	
	
}
