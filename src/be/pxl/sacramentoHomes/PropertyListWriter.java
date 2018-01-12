package be.pxl.sacramentoHomes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;

public class PropertyListWriter extends Thread {
		private LinkedHashSet<Property> properties;
		private String listname;
		
		public PropertyListWriter(LinkedHashSet<Property> properties, String listname) {
			this.properties = properties;
			this.listname = listname;
		}

		@Override
		public void run() {
			try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("resources\\estateData\\" + listname + ".csv"))){
				bufferedWriter.write(listname + System.lineSeparator());
				for(Property property: properties){
					bufferedWriter.write(property.toString() + System.lineSeparator());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
}
