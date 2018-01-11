package be.pxl.sacramentoHomesTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import be.pxl.sacramentoHomes.Property;

public class PropertyTest {

	@Test
	public void propertyCreateTest(){
		Property property = new Property("3526 HIGH ST,SACRAMENTO,95838,CA,2,1,836,Residential,Wed May 21 00:00:00 EDT 2008,59222,38.631913,-121.434879");
		assertEquals(LocalDate.of(2008, 5, 21), property.getSaleDate());
	}

}
