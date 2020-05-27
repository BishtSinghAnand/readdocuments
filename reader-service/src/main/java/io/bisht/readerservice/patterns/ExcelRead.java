package io.bisht.readerservice.patterns;

import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExcelRead<T> implements ReaderFactory<T>{

	@Override
	public List<T> reader(Reader reader) {
		// TODO Auto-generated method stub
		return null;
	}

}
