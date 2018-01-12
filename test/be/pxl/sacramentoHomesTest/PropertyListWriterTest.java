package be.pxl.sacramentoHomesTest;

import java.util.LinkedHashSet;

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
		LinkedHashSet<Property> properties = propertySystem.lastPropertiesSold(10);
		new PropertyListWriter(properties, "Last 10 sold").start();
	}
	
	@Test
	public void multiListWriter(){
		LinkedHashSet<Property> properties = propertySystem.propertiesAbovePrice(500000);
		LinkedHashSet<Property> properties2 = propertySystem.propertiesAbovePrice(750000);
		PropertyListWriter t1 = new PropertyListWriter(properties, "Above half a million");
		PropertyListWriter t2 = new PropertyListWriter(properties2, "Above three quarter of a million");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
