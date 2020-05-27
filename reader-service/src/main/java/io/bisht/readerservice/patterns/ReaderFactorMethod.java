package io.bisht.readerservice.patterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaderFactorMethod<T> {
	
	@Autowired
	private ExcelRead<T> excel;
	
	@Autowired
	private ReaderCsv<T> read;
	
	public ReaderFactory<T> getIntace(String type){
		
		if("Excel".equals(type)) {
			
			return excel;
			
		}else if("Reader".equals(type)){
			
			return read;
			
		}
		
		return null;
		
	}
}
