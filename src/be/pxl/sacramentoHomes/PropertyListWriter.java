package be.pxl.sacramentoHomes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class PropertyListWriter extends Thread {
		private HashSet<Property> properties;
		private String listname;
		
		public PropertyListWriter(HashSet<Property> properties, String listname) {
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
