package io.bisht.readerservice.patterns;

import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class ReaderCsv<T> implements ReaderFactory<T>{

	
	final Class<T> typeParameterClass;

    public ReaderCsv(Class<T> typeParameterClass) {
        
		this.typeParameterClass = typeParameterClass;
    }
    
    public ReaderCsv() {
		this.typeParameterClass = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> reader(Reader reader) {
		
		  @SuppressWarnings("rawtypes")
		  CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                  .withType(typeParameterClass)
                  .withIgnoreLeadingWhiteSpace(true)
                  .build();

          return csvToBean.parse();
		
	}

}
