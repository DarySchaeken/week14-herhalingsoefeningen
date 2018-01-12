package be.pxl.sacramentoHomes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Property {

	// Property Order in File:
	// street,city,zip,state,beds,baths,sq__ft,type,sale_date,price,latitude,longitude
	// Example input:
	// 3526 HIGH ST,SACRAMENTO,95838,CA,2,1,836,Residential,Wed May 21 00:00:00
	// EDT 2008,59222,38.631913,-121.434879

	private static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("E MMM d HH:mm:ss z uuuu")
			.withLocale(Locale.US);

	private String street;
	private String city;
	private int zip;
	private String state;
	private int beds;
	private int baths;
	private int squareFootSurface;
	private String type;
	private LocalDate saleDate;
	private int price;
	private double latitude;
	private double longitude;

	public Property(String input) {
		String[] inputs = input.split(",");
		street = inputs[0];
		city = inputs[1];
		zip = Integer.parseInt(inputs[2]);
		state = inputs[3];
		beds = Integer.parseInt(inputs[4]);
		baths = Integer.parseInt(inputs[5]);
		squareFootSurface = Integer.parseInt(inputs[6]);
		type = inputs[7];
		saleDate = LocalDate.from(DATE_INPUT_FORMAT.parse(inputs[8]));
		price = Integer.parseInt(inputs[9]);
		latitude = Double.parseDouble(inputs[10]);
		longitude = Double.parseDouble(inputs[11]);
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public int getZip() {
		return zip;
	}

	public String getState() {
		return state;
	}

	public int getBeds() {
		return beds;
	}

	public int getBaths() {
		return baths;
	}

	public int getSquareFootSurface() {
		return squareFootSurface;
	}

	public String getType() {
		return type;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public int getPrice() {
		return price;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int compareToPrice(Property p) {
		if (this.getPrice() >= p.getPrice()) {
			return 1;
		} else {
			return -1;
		}
	}

	// Property Order in File:
	// street,city,zip,state,beds,baths,sq__ft,type,sale_date,price,latitude,longitude
	// Example input:
	// 3526 HIGH ST,SACRAMENTO,95838,CA,2,1,836,Residential,Wed May 21 00:00:00
	// EDT 2008,59222,38.631913,-121.434879

	@Override
	public String toString() {
		LocalTime localTime = LocalTime.MIDNIGHT;
		ZonedDateTime zonedDateTime = ZonedDateTime.of(saleDate, localTime, ZoneId.of("EST5EDT"));
		return String.format("%s,%s,%d,%s,%d,%d,%d,%s,%s,%d,%f,%f", street, city, zip, state, beds, baths,
				squareFootSurface, type, DATE_INPUT_FORMAT.format(zonedDateTime), price, latitude, longitude);
	}

}
