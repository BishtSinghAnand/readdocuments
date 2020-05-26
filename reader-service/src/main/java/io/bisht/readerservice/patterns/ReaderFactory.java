package io.bisht.readerservice.patterns;

import java.io.Reader;
import java.util.List;

public interface ReaderFactory <T> {
	List<T> reader(Reader reader);
}
