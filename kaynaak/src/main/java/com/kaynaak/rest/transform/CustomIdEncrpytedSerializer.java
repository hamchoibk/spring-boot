package com.kaynaak.rest.transform;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomIdEncrpytedSerializer extends StdSerializer<Object> {

	private static final long serialVersionUID = -7390433569044160636L;

	public CustomIdEncrpytedSerializer() {
		this(null);
	}

	public CustomIdEncrpytedSerializer(Class<Object> t) {
		super(t);
	}

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		// TODO Auto-generated method stub

		if (value instanceof Integer) {
			Integer val = (Integer) value;
			val += 10;
		} else if (value instanceof String) {

		} else {

		}
		gen.writeString(value.toString());
	}

}