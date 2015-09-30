package br.com.takenet.takeio.entities.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	//2015-09-28T11:42:26
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public String marshal(Date v) {
		return dateFormat.format(v);
	}

	@Override
	public Date unmarshal(String v) throws ParseException {
		return dateFormat.parse(v);
	}

}
