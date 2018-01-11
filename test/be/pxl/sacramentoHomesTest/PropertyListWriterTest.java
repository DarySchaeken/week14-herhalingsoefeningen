package be.pxl.sacramentoHomesTest;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import be.pxl.sacramentoHomes.Property;
import be.pxl.sacramentoHomes.PropertySystem;
import be.pxl.sacramentoHomes.PropertyListWriter;

public class PropertyListWriterTest {
	private PropertySystem propertySystem;
	
	@Before
	public void init(){
		propertySystem = new PropertySystem();
	}
	
	@Test
	public void singleListWriter(){
		HashSet<Property> properties = propertySystem.lastPropertiesSold(10);
		new PropertyListWriter(properties, "Last 10 sold").start();
	}
	
	@Test
	public void multiListWriter(){
		HashSet<Property> properties = propertySystem.propertiesAbovePrice(500000);
		HashSet<Property> properties2 = propertySystem.propertiesAbovePrice(750000);
		new PropertyListWriter(properties, "Above half a million").start();
		new PropertyListWriter(properties2, "Above three quarter of a million").start();
	}
}
