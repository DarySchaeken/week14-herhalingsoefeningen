package be.pxl.sacramentoHomes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.stream.Collectors;

public class PropertySystem {
	private HashSet<Property> properties;

	public PropertySystem() {
		properties = new HashSet<Property>();
		populateData();
	}

	public int numberOfSales() {
		return properties.size();
	}

	public HashSet<Property> propertiesAbovePrice(int price) {
		return properties.stream().filter(p -> p.getPrice() > price).collect(Collectors.toCollection(HashSet::new));
	}

	public HashSet<Property> propertiesForZipCode(int zipcode) {
		return properties.stream().filter(p -> p.getZip() == zipcode).collect(Collectors.toCollection(HashSet::new));
	}

	public HashSet<Property> propertiesSoldAfter(LocalDate date) {
		return properties.stream().filter(p -> p.getSaleDate().isAfter(date))
				.collect(Collectors.toCollection(HashSet::new));
	}

	public HashSet<Property> lastPropertiesSold(int amount) {
		return properties.stream().sorted((p1, p2) -> p1.compareToDate(p2)).limit(amount)
				.collect(Collectors.toCollection(HashSet::new));
	}

	public Property findCheapest() {
		return properties.stream()
				.filter(p -> p.getCity().toUpperCase().equals("SACRAMENTO") && p.getBeds() >= 3
						&& p.getSquareFootSurface() >= 1000)
				.sorted((p1, p2) -> p1.compareToPrice(p2)).findFirst().get();
	}

	private void populateData() {
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader("resources\\estateData\\sacramentorealestatetransactions.csv"))) {
			String line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				Property property = new Property(line);
				properties.add(property);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
