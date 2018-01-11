package be.pxl.sacramentoHomesTest;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import be.pxl.sacramentoHomes.Property;
import be.pxl.sacramentoHomes.PropertySystem;

public class PropertySystemTest {
	private PropertySystem propertySystem;

	@Before
	public void init() {
		propertySystem = new PropertySystem();
	}

	@Test
	public void propertySystemDataPopulationTest() {
		assertEquals(985, propertySystem.numberOfSales());
	}

	@Test
	public void propertiesAbovePriceTest() {
		HashSet<Property> result = propertySystem.propertiesAbovePrice(800000);
		assertEquals(4, result.size());
	}

	@Test
	public void propertiesForZipCodeTest() {
		HashSet<Property> result = propertySystem.propertiesForZipCode(95820);
		assertEquals(23, result.size());
	}

	@Test
	public void lastPropertiesSold() {
		HashSet<Property> result = propertySystem.lastPropertiesSold(5);
		result.forEach(p -> System.out.println(p.getSaleDate()));
		assertEquals(5, result.size());
	}
	
	@Test
	public void findCheapestTest(){
		Property property = propertySystem.findCheapest();
		System.out.println(property.getPrice());
		assertEquals(56950, property.getPrice());
	}

}
