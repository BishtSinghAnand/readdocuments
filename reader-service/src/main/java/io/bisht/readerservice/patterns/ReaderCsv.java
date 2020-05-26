package io.bisht.readerservice.patterns;

import java.io.Reader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReaderCsv<T> implements ReaderFactory<T>{

	
	final Class<T> typeParameterClass;

    public ReaderCsv(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

	@Override
	public List<T> reader(Reader reader) {
		
		  CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                  .withType(typeParameterClass)
                  .withIgnoreLeadingWhiteSpace(true)
                  .build();

          // convert `CsvToBean` object to list of users
          return csvToBean.parse();
		
	}

}
